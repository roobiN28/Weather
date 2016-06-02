package robin.com.weather.parser;

/**
 * Created by Rober on 02.06.2016.
 */

public class UrlParser {
    private  String prefix = "http://developer.accuweather.com/sites/default/files/";
    private  String sufix = "-s.png";

    public  String generateURL(int number) {
        if(number<10) {
            return prefix+"0"+number+sufix;
        }
        return prefix+number+sufix;
    }

}
