package robin.com.weather.model;

import lombok.Getter;
import robin.com.weather.model.location.Location;

/**
 * Created by Rober on 02.06.2016.
 */
@Getter
public class BusLocation {
    private String key;
    private String localizedName;
    private String regionName;
    private String coutryName;
    private String administrativeArea;

    public BusLocation(Location location) {
        key = location.key;
        localizedName = location.localizedName;
        regionName = location.region.localizedName;
        coutryName = location.country.localizedName;
        administrativeArea = location.administrativeArea.localizedName;
    }



}
