package server.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TimeOfDay {
    private Date currentDate;
    private SimpleDateFormat sdf;

    public TimeOfDay() {
        // Jackson deserialization
    }

    @JsonProperty
    public String getTime() {
        currentDate = new Date();
        sdf = new SimpleDateFormat("HH:mm:ss z");
        return sdf.format(currentDate);
    }
}