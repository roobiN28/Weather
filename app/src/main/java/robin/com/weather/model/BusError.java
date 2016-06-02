package robin.com.weather.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Rober on 02.06.2016.
 */
@Getter @Setter

public class BusError {
    String name;
    String description;

    public BusError(String name, String description) {
        this.name = name;
        this.description = description;
    }



    @Override
    public String toString() {
        return "BusError{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
