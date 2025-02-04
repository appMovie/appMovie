package mindera.porto.AppMovie.controller;


import mindera.porto.AppMovie.model.TvShow;
import mindera.porto.AppMovie.repository.DirectorRepository;
import mindera.porto.AppMovie.repository.TvShowRepository;
import mindera.porto.AppMovie.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tvshows")
public class TvShowController {

    @Autowired
    private TvShowService tvShowService;
    @Autowired
    private TvShowRepository tvShowRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @PostMapping("/add")
    public void addTvShow(@RequestBody TvShow tvShow) {
        tvShowService.saveOrUpdateTvShow(tvShow);
    }

    @GetMapping("/list")
    public List<TvShow> getAllTVShows() {
        return tvShowService.getAllTVShows();
    }

    @GetMapping("/{id}")
    public TvShow getTvShow(@PathVariable Long id) {
        return tvShowService.getTvShow(id);
    }

    @PutMapping("/{id}")
    public void updateTvShow(@PathVariable Long id, @RequestBody TvShow tvShow) {
        tvShow.setId(id);  // Garantir que o ID passado é usado
        tvShowService.saveOrUpdateTvShow(tvShow);
    }

    @DeleteMapping("/{id}")
    public void deleteTvShow(@PathVariable Long id) {
        tvShowService.deleteTvShow(id);
    }

    @GetMapping("/actor/{actorName}")
    public List<TvShow> getTvShowsByActor(@PathVariable String actorName) {
        return tvShowService.getTvShowsByActor(actorName);
    }

    @GetMapping("/review/{reviewText}")
    public List<TvShow> getTvShowsByReview(@PathVariable String reviewText) {
        return tvShowService.getTvShowsByReview(reviewText);
    }

    @GetMapping("/director/{directorId}")
    public List<TvShow> getTvShowsByDirector(@PathVariable Long directorId) {
        return tvShowService.getTvShowsByDirector(directorId);
    }
}