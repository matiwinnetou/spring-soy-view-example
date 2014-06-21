package pl.matisoft.soy.example.soy.ext;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.matisoft.soy.global.runtime.DefaultGlobalRuntimeModelResolver;
import pl.matisoft.soy.global.runtime.GlobalRuntimeModelResolver;
import pl.matisoft.soy.global.runtime.resolvers.RuntimeDataResolver;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: mszczap
 * Date: 13.11.13
 * Time: 10:13
 */
@Component
@Primary
public class ExampleGlobalRuntimeModelResolver extends DefaultGlobalRuntimeModelResolver {

    @Autowired
    @Qualifier("soyGlobalRuntimeModelResolver")
    private GlobalRuntimeModelResolver globalModelResolver;

    @Autowired
    @Qualifier("soyHashesRuntimeDataResolver")
    private RuntimeDataResolver runtimeDataResolver;

    @PostConstruct
    public void init() {
        setUserResolvers(Lists.newArrayList(runtimeDataResolver));
    }

}
