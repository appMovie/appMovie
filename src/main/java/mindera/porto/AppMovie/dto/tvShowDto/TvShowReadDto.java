package mindera.porto.AppMovie.dto.tvShowDto;

import mindera.porto.AppMovie.dto.actorDto.ActorReadDto;
import mindera.porto.AppMovie.model.Director;

import java.util.List;

public class TvShowReadDto {
    private Long id;
    private String name;
    private int year;
    private String description;
    private Director director;
    private List<ActorReadDto> actors;

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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<ActorReadDto> getActors() {
        return actors;
    }

    public void setActors(List<ActorReadDto> actors) {
        this.actors = actors;
    }
}