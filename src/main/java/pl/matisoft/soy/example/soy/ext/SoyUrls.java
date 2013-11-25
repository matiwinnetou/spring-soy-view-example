package pl.matisoft.soy.example.soy.ext;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Created with IntelliJ IDEA.
 * User: mszczap
 * Date: 14.11.13
 * Time: 10:03
 */
public class SoyUrls {

    public final static String CLIENT_WORDS_URL = "templates/client-words.soy";

    public final static String SERVER_TIME_URL = "templates/server-time.soy";

    private SoyUrls() {
    }

    public static List<String> allUrls() {
        return homePageUrls();
    }

    public static List<String> homePageUrls() {
        return Lists.newArrayList(
                CLIENT_WORDS_URL,
                SERVER_TIME_URL
        );
    }

}
