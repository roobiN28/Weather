
package robin.com.weather.model.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Elevation {

    @SerializedName("Metric")
    @Expose
    public Metric metric;
    @SerializedName("Imperial")
    @Expose
    public Imperial imperial;

}
