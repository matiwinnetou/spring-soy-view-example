package pl.matisoft.soy.example.soy.ext;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.*;
import com.google.common.collect.FluentIterable;
import com.google.template.soy.data.SoyMapData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.matisoft.soy.ajax.hash.HashFileGenerator;
import pl.matisoft.soy.global.runtime.resolvers.RuntimeDataResolver;
import pl.matisoft.soy.template.TemplateFilesResolver;

/**
 * Created with IntelliJ IDEA.
 * User: mszczap
 * Date: 14.11.13
 * Time: 11:02
 */
@Component
@Named("soyHashesRuntimeDataResolver")
public class ExampleSoyHashesRuntimeDataResolver implements RuntimeDataResolver {

    @Autowired
    private HashFileGenerator hashFileGenerator;

    @Autowired
    private TemplateFilesResolver templateFilesResolver;

    public void resolveData(final HttpServletRequest request, final HttpServletResponse response, final Map<String, ?> model, final SoyMapData root) {
        try {
            root.put("example.homepage.soy.js.hash", hashFileGenerator.hashMulti(urls()).or(""));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<URL> urls() {
        final List<String> templateNames = SoyUrls.homePageUrls();
        final Map<String,Supplier<Optional<URL>>> cache = urlsCache(templateNames);

        return FluentIterable.from(templateNames)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean apply(final String templateName) {
                        return cache.get(templateName).get().isPresent();
                    }
                })
                .transform(new Function<String, URL>() {
                    @Override
                    public URL apply(final String templateName) {
                        return cache.get(templateName).get().get();
                    }
                })
                .toImmutableList();
    }

    private Map<String,Supplier<Optional<URL>>> urlsCache(final List<String> templateNames) {
        final HashMap cache = new HashMap<String,Supplier<Optional<URL>>>();
        for (final String templateName : templateNames) {
            final Supplier<Optional<URL>> memoize = Suppliers.memoize(new Supplier<Optional<URL>>() {

                @Override
                public Optional<URL> get() {
                    try {
                        return templateFilesResolver.resolve(templateName);
                    } catch (final IOException e) {
                        return Optional.absent();
                    }
                }

            });

            cache.put(templateName, memoize);
        }

        return cache;
    }

    public void setHashFileGenerator(HashFileGenerator hashFileGenerator) {
        this.hashFileGenerator = hashFileGenerator;
    }

    public void setTemplateFilesResolver(TemplateFilesResolver templateFilesResolver) {
        this.templateFilesResolver = templateFilesResolver;
    }

}
