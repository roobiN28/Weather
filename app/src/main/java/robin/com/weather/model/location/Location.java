
package robin.com.weather.model.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Location {

    @SerializedName("Version")
    @Expose
    public Integer version;
    @SerializedName("Key")
    @Expose
    public String key;
    @SerializedName("Type")
    @Expose
    public String type;
    @SerializedName("Rank")
    @Expose
    public Integer rank;
    @SerializedName("LocalizedName")
    @Expose
    public String localizedName;
    @SerializedName("EnglishName")
    @Expose
    public String englishName;
    @SerializedName("PrimaryPostalCode")
    @Expose
    public String primaryPostalCode;
    @SerializedName("Region")
    @Expose
    public Region region;
    @SerializedName("Country")
    @Expose
    public Country country;
    @SerializedName("AdministrativeArea")
    @Expose
    public AdministrativeArea administrativeArea;
    @SerializedName("TimeZone")
    @Expose
    public TimeZone timeZone;
    @SerializedName("GeoPosition")
    @Expose
    public GeoPosition geoPosition;
    @SerializedName("IsAlias")
    @Expose
    public Boolean isAlias;
    @SerializedName("SupplementalAdminAreas")
    @Expose
    public List<SupplementalAdminArea> supplementalAdminAreas = new ArrayList<SupplementalAdminArea>();

}
