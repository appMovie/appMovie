package mindera.porto.AppMovie.controller;


import mindera.porto.AppMovie.dto.tvShowDto.TvShowCreateDto;
import mindera.porto.AppMovie.dto.tvShowDto.TvShowReadDto;
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

    @PostMapping("")
    public TvShowReadDto addTvShow(@RequestBody TvShowCreateDto tvShowCreateDto) {
        return tvShowService.saveOrUpdateTvShow(tvShowCreateDto);
    }

    @GetMapping("/list")
    public List<TvShowReadDto> getAllTVShows() {
        return tvShowService.getAllTVShows();
    }

    @GetMapping("/{id}")
    public TvShowReadDto getTvShow(@PathVariable Long id) {
        return tvShowService.getTvShow(id);
    }

    @PutMapping("/{id}")
    public TvShowReadDto updateTvShow(@PathVariable Long id, @RequestBody TvShowCreateDto tvShowCreateDto) {
        return tvShowService.saveOrUpdateTvShow(tvShowCreateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTvShow(@PathVariable Long id) {
        tvShowService.deleteTvShow(id);
    }

//    @GetMapping("/actor/{actorId}")
//    public List<TvShowReadDto> getTvShowsByActor(@PathVariable Long actorId) {
//        return tvShowService.getTvShowsByActor(actorId);
//    }
//
//    @GetMapping("/review/{reviewId}")
//    public List<TvShowReadDto> getTvShowsByReview(@PathVariable Long reviewId) {
//        return tvShowService.getTvShowsByReview(reviewId);
//    }

    @GetMapping("/director/{directorId}")
    public List<TvShowReadDto> getTvShowsByDirector(@PathVariable Long directorId) {
        return tvShowService.getTvShowsByDirector(directorId);
    }
}