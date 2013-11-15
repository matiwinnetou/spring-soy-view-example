package pl.matisoft.soy.example.soy.ext;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.matisoft.soy.ajax.auth.ConfigurableAuthManager;

/**
 * Created with IntelliJ IDEA.
 * User: mszczap
 * Date: 13.11.13
 * Time: 10:25
 */
@Component
@Primary
public class ExampleAuthManager extends ConfigurableAuthManager {

    @PostConstruct
    public void init() {
        setAllowedTemplates(SoyUrls.allUrls());
    }

}
