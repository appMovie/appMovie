package mindera.porto.AppMovie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    private List<Movie> movies = new ArrayList<>();

    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    private List<TvShow> tvShows = new ArrayList<>();

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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<TvShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<TvShow> tvShows) {
        this.tvShows = tvShows;
    }
}
