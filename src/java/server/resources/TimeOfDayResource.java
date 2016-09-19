package server.resources;

import server.api.TimeOfDay;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/timeOfDay")
@Produces(MediaType.APPLICATION_JSON)
public class TimeOfDayResource {
    
    public TimeOfDayResource() { }

    @GET
    @Timed
    public TimeOfDay getTime() {
        return new TimeOfDay();
    }
}