package pl.matisoft.soy.example.soy.ext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import com.google.template.soy.data.SoyMapData;
import pl.matisoft.soy.global.runtime.resolvers.RuntimeDataResolver;

/**
 * Created with IntelliJ IDEA.
 * User: mszczap
 * Date: 11.11.13
 * Time: 19:46
 */
public class ExampleRuntimeDataResolver implements RuntimeDataResolver {

    @Override
    public void resolveData(HttpServletRequest request, HttpServletResponse response, Map<String, ?> model, SoyMapData root) {
        if (request.getParameter("validate") != null) {
            root.put("_request.parameter.example.revalidate", true);
        }
    }

}
