package pl.matisoft.soy.example;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController {

	@RequestMapping(value="/")
	public String openHomepage(final Model model) throws IOException {
        final MyModelObject myModelObject = new MyModelObject();
        myModelObject.setServerTime(serverTime());
        myModelObject.setWords(Lists.newArrayList("hello", "world", "from", "spring", "controller!"));

        model.addAttribute("model", myModelObject);

        return "soy:soy.example.index";
	}

	@RequestMapping(value="/server-time")
	public String getServerTime(final Model model) {
        final MyModelObject myModelObject = new MyModelObject();
        myModelObject.setServerTime(serverTime());

        model.addAttribute("model", myModelObject);

		return "soy:soy.example.serverTime";
	}

	private String serverTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
