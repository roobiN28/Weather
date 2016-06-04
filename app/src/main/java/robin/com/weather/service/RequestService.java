package robin.com.weather.service;

/**
 * Created by Rober on 02.06.2016.
 */

public interface RequestService {
    public void localization(final String text);
    public void weather(String key);
    public void bitmap(int weatherIconNumber);
}
