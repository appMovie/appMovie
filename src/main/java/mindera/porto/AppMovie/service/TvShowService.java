package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.dto.tvShowDto.TvShowCreateDto;
import mindera.porto.AppMovie.dto.tvShowDto.TvShowReadDto;
import mindera.porto.AppMovie.exception.tvShow.*;
import mindera.porto.AppMovie.mapper.TvShowMapper;
import mindera.porto.AppMovie.model.TvShow;
import mindera.porto.AppMovie.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TvShowService {

    @Autowired
    private TvShowRepository tvShowRepository;

    public TvShowService (TvShowRepository tvShowRepository){
        this.tvShowRepository=tvShowRepository;
    }


    //GET /tvshows/list → Lista de todas as séries
    public List<TvShowReadDto> getAllTVShows(){
        List<TvShow> tvShows = tvShowRepository.findAll();
        return tvShows.stream()
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

    //GET /tvshows/{id}/actors → Lista dos atores de uma série
//    public List<TvShowReadDto> getTvShowsByActor(Long actorId) {
//        List<TvShow> tvShows = tvShowRepository.findById(id);
//        if (tvShows.isEmpty()) {
//            throw new TvShowNotFoundActorException(actorId);
//        }
//        return tvShow.stream()
//                .map(TvShowMapper ::fromTvShowToTvShowReadDto)
//                .toList();
//    }
//
//    //GET /tvshows/{id}/reviews → Lista das avaliações de uma série
//    public List<TvShowReadDto> getTvShowsByReview(Long reviewId) {
//        List<TvShow> tvShows = tvShowRepository.findById(id);
//        if (tvShows.isEmpty()) {
//            throw new TvShowNotFoundReviewException(reviewId);
//        }
//        return tvShow.stream()
//                .map(TvShowMapper ::fromTvShowToTvShowReadDto)
//                .toList();}

    //GET /tvshows/director/{directorId} → Lista de todas as séries de um diretor
    public List<TvShowReadDto> getTvShowsByDirector(Long directorId) {
        List<TvShow> tvShows = tvShowRepository.findByDirector_Id(directorId);
        if (tvShows.isEmpty()) {
            throw new TvShowNotFoundDirectorException(directorId);
        }
        return tvShows.stream()
                .map(TvShowMapper::fromTvShowToTvShowReadDto)
                .toList();
    }
}
