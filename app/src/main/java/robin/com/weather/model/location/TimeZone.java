
package robin.com.weather.model.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TimeZone {

    @SerializedName("Code")
    @Expose
    public String code;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("GmtOffset")
    @Expose
    public Double gmtOffset;
    @SerializedName("IsDaylightSaving")
    @Expose
    public Boolean isDaylightSaving;
    @SerializedName("NextOffsetChange")
    @Expose
    public String nextOffsetChange;

}
