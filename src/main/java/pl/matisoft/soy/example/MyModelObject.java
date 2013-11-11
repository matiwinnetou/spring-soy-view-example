package pl.matisoft.soy.example;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mszczap
 * Date: 11.11.13
 * Time: 00:55
 * To change this template use File | Settings | File Templates.
 */
public class MyModelObject {

    private String serverTime;

    private List<String> words;

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

}
