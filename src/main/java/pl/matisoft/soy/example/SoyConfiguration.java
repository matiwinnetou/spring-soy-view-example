package pl.matisoft.soy.example;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import pl.matisoft.soy.ajax.auth.AuthManager;
import pl.matisoft.soy.ajax.auth.ConfigurableAuthManager;
import pl.matisoft.soy.global.compile.CompileTimeGlobalModelResolver;
import pl.matisoft.soy.global.compile.DefaultCompileTimeGlobalModelResolver;
import pl.matisoft.soy.global.runtime.DefaultGlobalModelResolver;
import pl.matisoft.soy.global.runtime.GlobalModelResolver;
import pl.matisoft.soy.global.runtime.resolvers.RuntimeDataResolver;

/**
 * Created with IntelliJ IDEA.
 * User: mati
 * Date: 27/06/2013
 * Time: 23:02
 */
@Configuration
@ImportResource({"classpath:spring-soy-view-ajax-config.xml"})
@PropertySource("classpath:spring-soy-view-example.properties")
@EnableWebMvc
@ComponentScan(basePackages = {"pl.matisoft.soy.example"})
//http://www.aviransplace.com/2012/04/29/how-to-use-spring-java-config/
public class SoyConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Primary
    public ViewResolver contentNegotiatingViewResolver(final ViewResolver soyViewResolver) throws Exception {
        final ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver.setViewResolvers(Lists.newArrayList(soyViewResolver));
        contentNegotiatingViewResolver.setDefaultViews(Lists.<View>newArrayList(new MappingJacksonJsonView()));

        return contentNegotiatingViewResolver;
    }

    @Bean
    @Primary
    public GlobalModelResolver globalModelResolver(final GlobalModelResolver resolver) {
        final DefaultGlobalModelResolver original = (DefaultGlobalModelResolver) resolver;
        return new DefaultGlobalModelResolver(original.getResolvers(),
                        Lists.<RuntimeDataResolver>newArrayList(
                                new ExampleRuntimeDataResolver()
                        ));
    }

    @Bean
    @Primary
    public CompileTimeGlobalModelResolver compileTimeVars() {
        final Map data = new HashMap();
        data.put("test1", "testValue1");
        final DefaultCompileTimeGlobalModelResolver defaultCompileTimeGlobalModelResolver = new DefaultCompileTimeGlobalModelResolver();
        defaultCompileTimeGlobalModelResolver.setData(data);

        return defaultCompileTimeGlobalModelResolver;
    }

    @Bean
    @Primary
    public AuthManager authManager() {
        return new ConfigurableAuthManager(
                Lists.newArrayList(
                        "templates/client-words.soy",
                        "templates/server-time.soy")
        );
    }

}
