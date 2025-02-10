package mindera.porto.AppMovie.mapper;

import mindera.porto.AppMovie.dto.actorDto.ActorReadDto;
import mindera.porto.AppMovie.dto.movieDto.MovieCreateDto;
import mindera.porto.AppMovie.dto.movieDto.MovieReadDto;
import mindera.porto.AppMovie.dto.movieDto.MovieUpdateDto;
import mindera.porto.AppMovie.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    public static MovieReadDto fromMovieToMovieReadDto(Movie movie){

        MovieReadDto movieReadDto = new MovieReadDto();
        movieReadDto.setId(movie.getId());
        movieReadDto.setName(movie.getName());
        movieReadDto.setYear(movie.getYear());
        movieReadDto.setDescription(movie.getDescription());

        // Se houver atores associados, mapeia cada um para ActorReadDto
        if (movie.getActors() != null && !movie.getActors().isEmpty()) {
            List<ActorReadDto> actorDtos = movie.getActors().stream()
                                                   .map(ActorMapper::fromActorToActorReadDto)
                                                   .collect(Collectors.toList());
            movieReadDto.setActors(actorDtos);
        }
        return movieReadDto;
    }

    public static Movie fromMovieCreateDtoToMovie(MovieCreateDto movieCreateDto){

        Movie movie= new Movie();
        movie.setName(movieCreateDto.getName());
        movie.setYear(movieCreateDto.getYear());
        movie.setDescription(movieCreateDto.getDescription());
        movie.setImageUrl(movieCreateDto.getImageUrl());

        return movie;
    }

    public static void fromMovieUpdateDtoToMovie (MovieUpdateDto movieUpdateDto, Movie movieEntity){

        movieEntity.setName(movieUpdateDto.getName());
        movieEntity.setYear(movieUpdateDto.getYear());
        movieEntity.setDescription(movieUpdateDto.getDescription());
        movieEntity.setImageUrl(movieEntity.getImageUrl());
    }
}
