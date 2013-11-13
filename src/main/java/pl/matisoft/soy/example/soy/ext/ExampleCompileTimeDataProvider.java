package pl.matisoft.soy.example.soy.ext;

import javax.annotation.PostConstruct;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.matisoft.soy.global.compile.DefaultCompileTimeGlobalModelResolver;

/**
 * Created with IntelliJ IDEA.
 * User: mszczap
 * Date: 13.11.13
 * Time: 10:07
 */
@Component
@Primary
public class ExampleCompileTimeDataProvider extends DefaultCompileTimeGlobalModelResolver {

    @Value("${soy.hot.reload.mode}")
    private boolean hotReloadMode;

    @PostConstruct
    public void init() {
        final HashMap map = new HashMap();
        map.put("soy.hot.reload.mode", hotReloadMode);
        map.put("test1", "testValue1");

        setData(map);
    }

}
