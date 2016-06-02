package robin.com.weather.service;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import lombok.Getter;

/**
 * Created by Rober on 02.06.2016.
 */
public class BusProvider {
    private static BusProvider ourInstance = new BusProvider();
    public static BusProvider getInstance() {
        return ourInstance;
    }
    @Getter
    Bus bus;
    private BusProvider() {
        bus = new Bus(ThreadEnforcer.ANY);
    }
}
