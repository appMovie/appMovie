package mindera.porto.AppMovie.controller;


import mindera.porto.AppMovie.dto.movieDto.MovieReadDto;
import mindera.porto.AppMovie.dto.tvShowDto.TvShowCreateDto;
import mindera.porto.AppMovie.dto.tvShowDto.TvShowReadDto;
import mindera.porto.AppMovie.repository.ActorRepository;
import mindera.porto.AppMovie.repository.DirectorRepository;
import mindera.porto.AppMovie.repository.TvShowRepository;
import mindera.porto.AppMovie.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/tvshows")
public class TvShowController {


    private final TvShowService tvShowService;
    @Autowired
    public TvShowController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }


    @PostMapping("/")
    public TvShowReadDto addTvShow(@RequestBody TvShowCreateDto tvShowCreateDto) {
         return this.tvShowService.addTvShow(tvShowCreateDto);

    }

    @GetMapping("/list")
    public List<TvShowReadDto> getAllTVShows() {
        return tvShowService.getAllTVShows();
    }

    @GetMapping("/{id}")
    public TvShowReadDto getTvShow(@PathVariable Long id) {
        return tvShowService.getTvShow(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTvShow(@PathVariable Long id) {
        tvShowService.deleteTvShow(id);
    }

    @PutMapping("/{id}")
    public TvShowReadDto updateTvShow(@PathVariable Long id, @RequestBody TvShowReadDto tvShowReadDto) {
        return tvShowService.updateTvShowById(id, tvShowReadDto);
    }

    @GetMapping("/search/byName")
    public List<TvShowReadDto> getTvShowByName( String name) {
        return tvShowService.getTvShowByName(name);
    }

    @GetMapping("/search/byYear")
    public List<TvShowReadDto> getTvShowByYear (int year){
        return tvShowService.getTvShowByYear(year);
    }

}
