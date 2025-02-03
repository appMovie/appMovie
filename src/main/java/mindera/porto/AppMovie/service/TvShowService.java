package mindera.porto.AppMovie.service;


import mindera.porto.AppMovie.model.Review;
import mindera.porto.AppMovie.model.TvShow;
import mindera.porto.AppMovie.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TvShowService {

    @Autowired
    private TvShowRepository tvShowRepository;

    public TvShowService (TvShowRepository tvShowRepository){
        this.tvShowRepository=tvShowRepository;
    }


    //GET /tvshows/list → Listar todas as séries
    public List<TvShow> getAllTVShows(){
        return tvShowRepository.findAll();
    }

    //GET /tvshows/{id} → Obter uma série por id
    public TvShow getTvShow (Long id){
        return tvShowRepository.findById(id).orElseThrow(()-> new IllegalStateException("TvShow with id "+id+ " does not exist"));
    }

    //PUT /tvshows/{id} → Atualizar informações de uma série //POST /tvshows/add → Criar uma nova série
    public void saveOrUpdateTvShow (TvShow tvShow){
        if (tvShow.getId()==null){
            Optional<TvShow> existingTvShow = tvShowRepository.findByName(tvShow.getName());
            if(existingTvShow.isPresent()){
                throw  new DataIntegrityViolationException("TvShow already exists");
            }
        }
        tvShowRepository.save(tvShow);
    }

    //DELETE /tvshows/{id} → Remover uma série
    public void deleteTvShow(Long id){
        tvShowRepository.deleteById(id);
    }

    //GET /tvshows/{id}/actors → Listar os atores de uma série
    public List<TvShow> getTvShowsByActor(String actorName) {
        List<TvShow> tvShows = tvShowRepository.findByActors_Name(actorName);
        if (tvShows.isEmpty()) {
            throw new IllegalStateException("No TV Shows found for actor " + actorName);
        }
        return tvShows;
    }

    //GET /tvshows/{id}/reviews → Listar as avaliações de uma série
    public List<TvShow> getTvShowsByReview(String reviewText) {
        List<TvShow> tvShows = tvShowRepository.findByReviews_Comment(reviewText);
        if (tvShows.isEmpty()) {
            throw new IllegalStateException("No TV Shows found with review: " + reviewText);
        }
        return tvShows;
    }

    //GET /tvshows/director/{directorId} → Listar todas as séries de um diretor
    public List<TvShow> getTvShowsByDirector(Long directorId) {
        List<TvShow> tvShows = tvShowRepository.findByDirector_Id(directorId);
        if (tvShows.isEmpty()) {
            throw new IllegalStateException("No TV Shows found for director ID " + directorId);
        }
        return tvShows;
    }
}
