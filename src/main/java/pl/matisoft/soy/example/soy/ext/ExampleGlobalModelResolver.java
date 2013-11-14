package pl.matisoft.soy.example.soy.ext;

import javax.annotation.PostConstruct;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.matisoft.soy.global.runtime.DefaultGlobalModelResolver;
import pl.matisoft.soy.global.runtime.GlobalModelResolver;
import pl.matisoft.soy.global.runtime.resolvers.RuntimeDataResolver;

/**
 * Created with IntelliJ IDEA.
 * User: mszczap
 * Date: 13.11.13
 * Time: 10:13
 */
@Component
@Primary
public class ExampleGlobalModelResolver extends DefaultGlobalModelResolver {

    @Autowired
    @Qualifier("soyGlobalModelResolver")
    private GlobalModelResolver globalModelResolver;

    @Autowired
    @Qualifier("soyHashesRuntimeDataResolver")
    private RuntimeDataResolver runtimeDataResolver;

    @PostConstruct
    public void init() {
        setResolvers(((DefaultGlobalModelResolver)globalModelResolver).getResolvers());
        setUserResolvers(Lists.<RuntimeDataResolver>newArrayList(runtimeDataResolver));
    }

}
