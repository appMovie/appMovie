package mindera.porto.AppMovie.controller;

//import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import mindera.porto.AppMovie.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DirectorControllerTest extends BaseControllerTest{

   @Autowired
   private ObjectMapper objectMapper;

    @Test
   public void shouldRun() throws Exception {
        Movie movie = new Movie();

        movie.setName("movie name")
        ;
        Set<Movie> movies = new HashSet<>();
        movies.add(movie);

        mockMvcM.perform(
                post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movies)))
                .andExpect(status().isOk());
              //  .andExpect(jsonPath("$[0].name").value("movie name"));

    }
}
