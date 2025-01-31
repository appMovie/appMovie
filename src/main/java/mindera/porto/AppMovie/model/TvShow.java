package mindera.porto.AppMovie.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tv_shows")
public class TvShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @ManyToMany(mappedBy = "tvShows")
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
