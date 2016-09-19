package server.resources;

import server.api.Movie;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ConcurrentHashMap;


@Path("/api/movie")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    private final ConcurrentHashMap<Integer, Movie> movieHM;
    private final AtomicInteger counter;

    public MovieResource() {
        this.movieHM = new ConcurrentHashMap<>();
        this.counter = new AtomicInteger();
    }
    
    
    @GET
    @Path("/create")
    @Timed
    public Movie createMovie(@QueryParam("name") Optional<String> inName,
                             @QueryParam("genre") Optional<String> inGenre,
                             @QueryParam("year") Optional<Integer> inYear,
                             @QueryParam("rating") Optional<String> inRating) {
        Movie newMovie;
        
        if (inName.isPresent() && inGenre.isPresent() && inYear.isPresent() && inRating.isPresent()) {
            int indexer = counter.getAndIncrement();
            newMovie = new Movie(indexer, inName.get(), inGenre.get(), inYear.get(), inRating.get());
            this.movieHM.put(indexer, newMovie);
        } else {
            newMovie = new Movie(-1, inName.or("REQUIRED"), inGenre.or("REQUIRED"), inYear.or(-1), inRating.or("REQUIRED"));
        }

        return newMovie;
    }
    
    @GET
    @Path("/delete")
    @Timed
    public Movie deleteMovie(@QueryParam("id") Optional<Integer> id) {
        int movieId = id.or(-1);
        Movie remMovie;

        if (movieId == -1) {
            remMovie = new Movie(movieId, "id required", "id required", movieId, "id required");
        } else if (this.movieHM.containsKey(movieId)) {            
            remMovie = (Movie) this.movieHM.remove(movieId);
        } else {
            remMovie = new Movie(movieId, "id not found", "id not found", 0, "id not found");
        }
        return remMovie; 
    }
    
    @GET
    @Path("/update")
    @Timed
    public Movie updateMovie(@QueryParam("id") Optional<Integer> id,
                             @QueryParam("name") Optional<String> name,
                             @QueryParam("genre") Optional<String> genre,
                             @QueryParam("year") Optional<Integer> year,
                             @QueryParam("rating") Optional<String> rating) {
        int movieId = id.or(-1);
        Movie updMovie;

        if (movieId == -1) {
            updMovie = new Movie(movieId, "id required", "id required", movieId, "id required");
        } else if (this.movieHM.containsKey(movieId)) {            
            updMovie = (Movie) this.movieHM.get(movieId);
            updMovie.setName(name.or(updMovie.getName()));
            updMovie.setGenre(genre.or(updMovie.getGenre()));
            updMovie.setYear(year.or(updMovie.getYear()));
            updMovie.setRating(rating.or(updMovie.getRating()));            
            updMovie = (Movie) this.movieHM.replace(movieId, updMovie);            
        } else {
            updMovie = new Movie(movieId, "id not found", "id not found", movieId, "id not found");
        }
        return updMovie; 
    }

    @GET
    @Path("/list")
    @Timed
    public ConcurrentHashMap listMovies() {
        return this.movieHM; 
    }    
    

}    
