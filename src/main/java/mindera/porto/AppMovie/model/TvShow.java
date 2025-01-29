package mindera.porto.AppMovie.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name="tvShows")
public class TvShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tvShow;

    @Column
    private String director;

    @Column
    private List<Actor> actorsList;

    @Column
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTvShow() {
        return tvShow;
    }

    public void setTvShow(String tvShow) {
        this.tvShow = tvShow;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Actor> getActorsList() {
        return actorsList;
    }

    public void setActorsList(List<Actor> actorsList) {
        this.actorsList = actorsList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}