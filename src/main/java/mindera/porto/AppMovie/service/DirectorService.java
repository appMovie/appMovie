package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.exception.director.DirectorAlreadyExistsException;
import mindera.porto.AppMovie.exception.director.DirectorNotFoundException;
import mindera.porto.AppMovie.exception.director.DirectorNotFoundMovieException;
import mindera.porto.AppMovie.exception.director.DirectorNotFoundTvShowException;
import mindera.porto.AppMovie.model.Director;
import mindera.porto.AppMovie.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;


    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    //GET /directors/list → Listar todos os diretores
    public List<Director> getAllDirectors(){
        return directorRepository.findAll();
    }
    // GET /directors/{id} → Obter detalhes de um diretor específico
    public Director getDirector(Long id){
        return directorRepository.findById(id).orElseThrow(() -> new DirectorNotFoundException());
    }

    //PUT /directors/{id} → Atualizar informações de um diretor // POST /directors/add → Criar um novo diretor
    public void saveOrUpdateDirector (Director director){
        if (director.getId()==null){
            Optional<Director> existingDirector= directorRepository.findByName(director.getName());
            if(existingDirector.isPresent()){
                throw  new DirectorAlreadyExistsException();
            }
        }
        directorRepository.save(director);
    }


    //DELETE /directors/{id} → Remover um diretor
    public void deleteDirector (Long id){
        directorRepository.deleteById(id);
    }


    //GET /directors/{id}/tvshows → Listar as séries de um diretor
    public List<Director> getDirectorByTvShow (String tvShow){
        List<Director> directors =directorRepository.findByTvShows_Name(tvShow);
        if (directors.isEmpty()){
            throw new DirectorNotFoundTvShowException(tvShow);
        }
        return directors;
    }

    //GET /directors/{id}/movies → Listar os filmes de um diretor
    public List<Director> getDirectorByMovie (String movie){
        List<Director> directors =directorRepository.findByMovies_Name(movie);
        if (directors.isEmpty()){
            throw new DirectorNotFoundMovieException(movie);
        }
        return directors;
    }


}
