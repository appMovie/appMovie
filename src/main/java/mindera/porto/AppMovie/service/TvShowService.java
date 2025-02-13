package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.dto.directorDto.DirectorCreateDto;
import mindera.porto.AppMovie.dto.directorDto.DirectorReadDto;
import mindera.porto.AppMovie.dto.movieDto.MovieReadDto;
import mindera.porto.AppMovie.dto.movieDto.MovieUpdateDto;
import mindera.porto.AppMovie.dto.tvShowDto.TvShowCreateDto;
import mindera.porto.AppMovie.dto.tvShowDto.TvShowReadDto;
import mindera.porto.AppMovie.exception.director.DirectorAlreadyExistsException;
import mindera.porto.AppMovie.exception.director.DirectorNotFoundException;
import mindera.porto.AppMovie.exception.movie.MovieAlreadyExistsExpception;
import mindera.porto.AppMovie.exception.movie.MovieNotFoundExcption;
import mindera.porto.AppMovie.exception.tvShow.*;
import mindera.porto.AppMovie.mapper.DirectorMapper;
import mindera.porto.AppMovie.mapper.MovieMapper;
import mindera.porto.AppMovie.mapper.TvShowMapper;
import mindera.porto.AppMovie.model.Actor;
import mindera.porto.AppMovie.model.Director;
import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.model.TvShow;
import mindera.porto.AppMovie.repository.ActorRepository;
import mindera.porto.AppMovie.repository.DirectorRepository;
import mindera.porto.AppMovie.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TvShowService {


    private final TvShowRepository tvShowRepository;

    private final ActorRepository actorRepository;

    private final DirectorRepository directorRepository;

    public TvShowService(TvShowRepository tvShowRepository, ActorRepository actorRepository, DirectorRepository directorRepository) {
        this.tvShowRepository = tvShowRepository;
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
    }

    //POST /tvshows/add → Criar uma nova série
    public TvShowReadDto addTvShow(TvShowCreateDto tvShowCreateDto) {
        // Verifica se já existe um TvShow com o mesmo nome
        if (tvShowRepository.existsByName(tvShowCreateDto.getName())) {
            throw new TvShowAlreadyExistsException();
        }

        // Converte o DTO para a entidade TvShow
        TvShow tvShow = TvShowMapper.fromTvShowCreateDtoToTvShow(tvShowCreateDto);

        // Salva a entidade no banco de dados (ID será gerado automaticamente)
        TvShow savedTvShow = tvShowRepository.save(tvShow);

        // Converte a entidade salva para DTO de leitura e retorna
        return TvShowMapper.fromTvShowToTvShowReadDto(savedTvShow);
    }

    //GET /tvshows/list → Lista de todas as séries
    public List<TvShowReadDto> getAllTVShows(){
        return tvShowRepository.findAll().stream()
                .map(TvShowMapper::fromTvShowToTvShowReadDto)
                .toList();
    }

    //GET /tvshows/{id} → Obter uma série por id
    public TvShowReadDto getTvShow (Long id){
        TvShow tvShow= tvShowRepository.findById(id)
                .orElseThrow(()-> new TvShowNotFoundException());
        return TvShowMapper.fromTvShowToTvShowReadDto(tvShow);
    }

    //PUT /tvshows/{id} → Atualizar informações de uma série //POST /tvshows/add → Criar uma nova série
    public TvShowReadDto saveOrUpdateTvShow(TvShowCreateDto tvShowCreateDto) {
        // Converte o DTO para a entidade TvShow
        TvShow tvShow = TvShowMapper.fromTvShowCreateDtoToTvShow(tvShowCreateDto);

        // Verifica se já existe um TvShow com o mesmo nome
        if (tvShowRepository.existsByName(tvShow.getName())) {
            throw new TvShowAlreadyExistsException();
        }

        // Salva o TvShow e retorna o DTO de leitura
        TvShow savedTvShow = tvShowRepository.save(tvShow);
        return TvShowMapper.fromTvShowToTvShowReadDto(savedTvShow);
    }

    //DELETE /tvshows/{id} → Remover uma série
    public void deleteTvShow(Long id){
        tvShowRepository.deleteById(id);
    }

    public void addActorsToTvShow(Long tvShowId, List<Long> actorIds) {
        TvShow tvShow = tvShowRepository.findById(tvShowId)
                .orElseThrow(() -> new TvShowNotFoundException());
        List<Actor> actors = actorRepository.findAllById(actorIds);
        tvShow.getActors().addAll(actors);
        tvShowRepository.save(tvShow);
    }

    public TvShowReadDto updateTvShowById(Long tvshowId, TvShowReadDto tvShowReadDto) {
        TvShow existingTvShow = tvShowRepository.findById(tvshowId)
                .orElseThrow(() -> new TvShowNotFoundException());

        Optional<TvShow> TvShowSameName = tvShowRepository.findByName(tvShowReadDto.getName());
        if (TvShowSameName.isPresent() && !TvShowSameName.get().getId().equals(tvshowId)) {
            throw new TvShowAlreadyExistsException();
        }

        existingTvShow.getActors().clear();
        TvShow updatedTvShow = tvShowRepository.save(existingTvShow);
        return TvShowMapper.fromTvShowToTvShowReadDto(updatedTvShow);
    }



    public  List<TvShowReadDto> getTvShowByName(String name) {
        List<TvShow> tvShow = tvShowRepository.getTvShowByName(name);
        return tvShow.stream()
                .map(TvShowMapper::fromTvShowToTvShowReadDto)
                .toList();
    }

    public List<TvShowReadDto> getTvShowByYear(int year) {
        List<TvShow> tvShows = tvShowRepository.findTvShowByYear(year);
        return tvShows.stream()
                .map(TvShowMapper::fromTvShowToTvShowReadDto)
                .toList();
    }
}
