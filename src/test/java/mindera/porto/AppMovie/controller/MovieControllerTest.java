package mindera.porto.AppMovie.controller;

import mindera.porto.AppMovie.dto.movieDto.MovieCreateDto;
import mindera.porto.AppMovie.dto.movieDto.MovieReadDto;
import mindera.porto.AppMovie.dto.movieDto.MovieUpdateDto;
import mindera.porto.AppMovie.dto.reviewDto.ReviewCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MovieControllerTest extends BaseControllerTest{


    @Test
    public void testAddNewMovies() throws Exception {
        MovieCreateDto movieDto = new MovieCreateDto();
        movieDto.setName("movie name");
        movieDto.setYear(2020);
        movieDto.setDescription("Some description");
        movieDto.setImageUrl("http://example.com/image.jpg");

        // Coloca o DTO em um Set, pois o endpoint espera um Set<MovieCreateDto>
        Set<MovieCreateDto> movies = new HashSet<>();
        movies.add(movieDto);

        // Realiza a requisição POST para o endpoint "/api/v1/movies"
        mockMvcM.perform(
                        post("/api/v1/movies")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(movies))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("movie name"));
    }

    // Testa o endpoint GET para recuperar todos os filmes
    @Test
    public void testGetAllMovies() throws Exception {
        mockMvcM.perform(get("/api/v1/movies")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Testa o endpoint DELETE para remover um filme
    @Test
    public void testDeleteMovie() throws Exception {
        // Primeiro, adiciona um filme para que possamos removê-lo
        MovieCreateDto movieDto = new MovieCreateDto();
        movieDto.setName("Movie To Delete");
        movieDto.setYear(2019);
        movieDto.setDescription("To be deleted");
        movieDto.setImageUrl("http://example.com/delete.jpg");

        String movieResponse = mockMvcM.perform(post("/api/v1/movies/add")
                                                       .contentType(MediaType.APPLICATION_JSON)
                                                       .content(objectMapper.writeValueAsString(movieDto)))
                                       .andExpect(status().isOk())
                                       .andReturn().getResponse().getContentAsString();

        // Extrai o id do filme a partir da resposta
        MovieReadDto createdMovie = objectMapper.readValue(movieResponse, MovieReadDto.class);
        Long movieId = createdMovie.getId();

        // Realiza a exclusão
        mockMvcM.perform(delete("/api/v1/movies/" + movieId))
                .andExpect(status().isOk());
    }

    // Testa o endpoint POST para associar atores a um filme
    @Test
    public void testAddActorsToMovie() throws Exception {
        // Primeiro, adiciona um filme
        MovieCreateDto movieDto = new MovieCreateDto();
        movieDto.setName("Movie with Actors");
        movieDto.setYear(2022);
        movieDto.setDescription("Actors association");
        movieDto.setImageUrl("http://example.com/actors.jpg");

        String movieResponse = mockMvcM.perform(post("/api/v1/movies/add")
                                                       .contentType(MediaType.APPLICATION_JSON)
                                                       .content(objectMapper.writeValueAsString(movieDto)))
                                       .andExpect(status().isOk())
                                       .andReturn().getResponse().getContentAsString();

        MovieReadDto createdMovie = objectMapper.readValue(movieResponse, MovieReadDto.class);
        Long movieId = createdMovie.getId();

        // Supondo que os atores com IDs 1 e 2 já existam (para teste, ou você pode criar atores antes)
        String actorIds = "1,2";

        mockMvcM.perform(post("/api/v1/movies/" + movieId + "/" + actorIds)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Opcional: verificar se a lista de atores do filme contém os atores esperados
    }

    // Testa o endpoint GET para recuperar um filme pelo ID
    @Test
    public void testGetMovieById() throws Exception {
        // Adiciona um filme
        MovieCreateDto movieDto = new MovieCreateDto();
        movieDto.setName("Movie By Id");
        movieDto.setYear(2021);
        movieDto.setDescription("Get by Id");
        movieDto.setImageUrl("http://example.com/byid.jpg");

        String movieResponse = mockMvcM.perform(post("/api/v1/movies/add")
                                                       .contentType(MediaType.APPLICATION_JSON)
                                                       .content(objectMapper.writeValueAsString(movieDto)))
                                       .andExpect(status().isOk())
                                       .andReturn().getResponse().getContentAsString();

        MovieReadDto createdMovie = objectMapper.readValue(movieResponse, MovieReadDto.class);
        Long movieId = createdMovie.getId();

        mockMvcM.perform(get("/api/v1/movies/" + movieId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Movie By Id"));
    }

    // Testa o endpoint PUT para atualizar um filme
    @Test
    public void testUpdateMovieById() throws Exception {
        // Adiciona um filme
        MovieCreateDto movieDto = new MovieCreateDto();
        movieDto.setName("Old Movie");
        movieDto.setYear(2020);
        movieDto.setDescription("Old description");
        movieDto.setImageUrl("http://example.com/old.jpg");

        String movieResponse = mockMvcM.perform(post("/api/v1/movies/add")
                                                       .contentType(MediaType.APPLICATION_JSON)
                                                       .content(objectMapper.writeValueAsString(movieDto)))
                                       .andExpect(status().isOk())
                                       .andReturn().getResponse().getContentAsString();

        MovieReadDto createdMovie = objectMapper.readValue(movieResponse, MovieReadDto.class);
        Long movieId = createdMovie.getId();

        // Prepara o DTO de atualização
        MovieUpdateDto updateDto = new MovieUpdateDto();
        updateDto.setId(movieId);
        updateDto.setName("Updated Movie");
        updateDto.setYear(2021);
        updateDto.setDescription("Updated description");
        updateDto.setImageUrl("http://example.com/updated.jpg");

        mockMvcM.perform(put("/api/v1/movies/" + movieId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Movie"));
    }

    // Testa o endpoint GET para buscar filmes por ator (por ID)
    @Test
    public void testGetMoviesByActorId() throws Exception {
        // Esse teste pressupõe que já existem filmes associados ao ator com ID 1.
        mockMvcM.perform(get("/api/v1/movies/actor/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Opcional: verificar conteúdo da resposta
    }

    // Testa o endpoint GET para pesquisar filmes pelo nome
    @Test
    public void testSearchMoviesByName() throws Exception {
        // Adiciona um filme para o teste de pesquisa
        MovieCreateDto movieDto = new MovieCreateDto();
        movieDto.setName("Searchable Movie");
        movieDto.setYear(2020);
        movieDto.setDescription("Search description");
        movieDto.setImageUrl("http://example.com/search.jpg");

        mockMvcM.perform(post("/api/v1/movies/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(movieDto)))
                .andExpect(status().isOk());

        mockMvcM.perform(get("/api/v1/movies/search/byName")
                                .param("name", "Searchable"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Searchable Movie"));
    }

    // Testa o endpoint GET para pesquisar filmes por nome de ator
    @Test
    public void testSearchMoviesByActorName() throws Exception {
        // Esse teste pressupõe que existem filmes associados a atores cujo nome contenha "ActorName".
        mockMvcM.perform(get("/api/v1/movies/search/byActor")
                                .param("actorName", "ActorName"))
                .andExpect(status().isOk());
    }

    // Testa o endpoint GET para pesquisar filmes entre dois anos
    @Test
    public void testSearchMoviesBetweenYears() throws Exception {
        // Adiciona um filme para o teste
        MovieCreateDto movieDto = new MovieCreateDto();
        movieDto.setName("Year Range Movie");
        movieDto.setYear(2005);
        movieDto.setDescription("Description for range search");
        movieDto.setImageUrl("http://example.com/years.jpg");

        mockMvcM.perform(post("/api/v1/movies/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(movieDto)))
                .andExpect(status().isOk());

        mockMvcM.perform(get("/api/v1/movies/search/byYears")
                                .param("startYear", "2000")
                                .param("endYear", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Year Range Movie"));
    }

    // Test for adding a director to a movie.
    @Test
    public void testAddDirectorToMovie() throws Exception {
        // First, create a new movie via the POST /api/v1/movies/add endpoint.
        MovieCreateDto movieDto = new MovieCreateDto();
        movieDto.setName("Movie for Director");
        movieDto.setYear(2021);
        movieDto.setDescription("A movie to test director assignment");
        movieDto.setImageUrl("http://example.com/movie.jpg");

        String movieResponse = mockMvcM.perform(post("/api/v1/movies/add")
                                                       .contentType(MediaType.APPLICATION_JSON)
                                                       .content(objectMapper.writeValueAsString(movieDto)))
                                       .andExpect(status().isOk())
                                       .andReturn().getResponse().getContentAsString();

        MovieReadDto createdMovie = objectMapper.readValue(movieResponse, MovieReadDto.class);
        Long movieId = createdMovie.getId();

        // Now add a director (assume director with id 1 exists)
        Long directorId = 1L;
        String updatedMovieResponse = mockMvcM.perform(post("/api/v1/movies/" + movieId + "/director/" + directorId)
                                                              .contentType(MediaType.APPLICATION_JSON))
                                              .andExpect(status().isOk())
                                              .andExpect(jsonPath("$.director.id").value(directorId))
                                              .andReturn().getResponse().getContentAsString();


    }

    // Test for retrieving movies by director ID.
    @Test
    public void testGetMoviesByDirectorId() throws Exception {
        // Create a movie and assign a director to it.
        MovieCreateDto movieDto = new MovieCreateDto();
        movieDto.setName("Movie for Director Query");
        movieDto.setYear(2021);
        movieDto.setDescription("A movie to test getMoviesByDirectorId");
        movieDto.setImageUrl("http://example.com/movie.jpg");

        String movieResponse = mockMvcM.perform(post("/api/v1/movies/add")
                                                       .contentType(MediaType.APPLICATION_JSON)
                                                       .content(objectMapper.writeValueAsString(movieDto)))
                                       .andExpect(status().isOk())
                                       .andReturn().getResponse().getContentAsString();

        MovieReadDto createdMovie = objectMapper.readValue(movieResponse, MovieReadDto.class);
        Long movieId = createdMovie.getId();

        // Assign a director to the movie.
        Long directorId = 1L;
        mockMvcM.perform(post("/api/v1/movies/" + movieId + "/director/" + directorId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Retrieve movies by director ID.
        mockMvcM.perform(get("/api/v1/movies/director/" + directorId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // Verify that the returned movie has the correct director id.
                .andExpect(jsonPath("$[0].director.id").value(directorId));
    }

    // Test for adding a review to a movie.
    @Test
    public void testAddReviewToMovie() throws Exception {
        // Create a new movie.
        MovieCreateDto movieDto = new MovieCreateDto();
        movieDto.setName("Movie for Review");
        movieDto.setYear(2021);
        movieDto.setDescription("A movie to test reviews");
        movieDto.setImageUrl("http://example.com/movie.jpg");

        String movieResponse = mockMvcM.perform(post("/api/v1/movies/add")
                                                       .contentType(MediaType.APPLICATION_JSON)
                                                       .content(objectMapper.writeValueAsString(movieDto)))
                                       .andExpect(status().isOk())
                                       .andReturn().getResponse().getContentAsString();

        MovieReadDto createdMovie = objectMapper.readValue(movieResponse, MovieReadDto.class);
        Long movieId = createdMovie.getId();

        // Prepare a review payload.
        ReviewCreateDto reviewDto = new ReviewCreateDto();
        reviewDto.setComment("Great movie, highly recommended!");
        reviewDto.setRating(8);

        // Call the endpoint to add the review.
        String updatedMovieResponse = mockMvcM.perform(post("/api/v1/movies/" + movieId + "/reviews")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .content(objectMapper.writeValueAsString(reviewDto)))
                                              .andExpect(status().isOk())
                                              // Verify that the first review in the list has the expected comment and rating.
                                              .andExpect(jsonPath("$.reviews[0].comment").value("Great movie, highly recommended!"))
                                              .andExpect(jsonPath("$.reviews[0].rating").value(8))
                                              .andReturn().getResponse().getContentAsString();
    }


}
