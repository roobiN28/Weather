
package robin.com.weather.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import lombok.Getter;
import lombok.Setter;

@Generated("org.jsonschema2pojo")
@Getter
@Setter
public class Temperature {

    @SerializedName("Metric")
    @Expose
    private Metric metric;
    @SerializedName("Imperial")
    @Expose
    private Imperial imperial;

}
