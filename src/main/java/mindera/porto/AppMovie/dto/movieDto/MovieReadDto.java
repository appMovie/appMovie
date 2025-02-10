package mindera.porto.AppMovie.dto.movieDto;

import mindera.porto.AppMovie.dto.actorDto.ActorReadDto;
import mindera.porto.AppMovie.dto.directorDto.DirectorReadDto;
import mindera.porto.AppMovie.dto.reviewDto.ReviewReadDto;

import java.util.List;

public class MovieReadDto {

    private Long id;
    private String name;
    private int year;
    private String description;

    private List<ActorReadDto> actors;
    private DirectorReadDto director;

    private List<ReviewReadDto> reviews ;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ActorReadDto> getActors() {
        return actors;
    }

    public void setActors(List<ActorReadDto> actors) {
        this.actors = actors;
    }

    public DirectorReadDto getDirector() {
        return director;
    }
    public void setDirector(DirectorReadDto director) {
        this.director = director;
    }

    public List<ReviewReadDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewReadDto> reviews) {
        this.reviews = reviews;
    }
}
