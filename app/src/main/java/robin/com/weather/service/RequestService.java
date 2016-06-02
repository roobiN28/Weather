package robin.com.weather.service;

import android.widget.TextView;

/**
 * Created by Rober on 02.06.2016.
 */

public interface RequestService {
    public void localization(final String text, final TextView textView);
    public void weather(String key);
}
