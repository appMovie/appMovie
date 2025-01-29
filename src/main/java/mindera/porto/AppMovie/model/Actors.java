package mindera.porto.AppMovie.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Actors")
public class Actors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @Column
    private String description;
    @Column
    private List<TvShows> tvShowsList;
    @Column
    private List<Movies> moviesList;
}
