package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.dto.directorDto.DirectorCreateDto;
import mindera.porto.AppMovie.dto.directorDto.DirectorReadDto;
import mindera.porto.AppMovie.exception.director.DirectorAlreadyExistsException;
import mindera.porto.AppMovie.exception.director.DirectorNotFoundException;
import mindera.porto.AppMovie.exception.director.DirectorNotFoundMovieException;
import mindera.porto.AppMovie.exception.director.DirectorNotFoundTvShowException;
import mindera.porto.AppMovie.mapper.DirectorMapper;
import mindera.porto.AppMovie.model.Director;
import mindera.porto.AppMovie.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;


    public DirectorService(DirectorRepository directorRepository){
        this.directorRepository = directorRepository;
    }

    //GET /directors/list → Lista de todos os diretores
    public List<DirectorReadDto> getAllDirectors(){
        List <Director> directors =directorRepository.findAll();
        return directors.stream()
                .map(DirectorMapper::fromDirectorToDirectorReadDto)
                .toList();
    }

    // GET /directors/{id} → Obter um diretor por id
    public DirectorReadDto getDirector(Long id){
        Director director =directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException());
        return DirectorMapper.fromDirectorToDirectorReadDto(director);
    }

    //PUT /directors/{id} → Atualizar informações de um diretor // POST /directors/add → Criar um novo diretor
    public DirectorReadDto saveOrUpdateDirector (DirectorCreateDto directorCreateDto){

        Director director = DirectorMapper.fromDirectorCreateDtoToDirector(directorCreateDto);

        // Check if a director with the given name already exists.
        if (directorRepository.existsByName(director.getName())) {
            throw new DirectorAlreadyExistsException();
        }

        Director savedDirector = directorRepository.save(director);
        return DirectorMapper.fromDirectorToDirectorReadDto(savedDirector);
    }


    //DELETE /directors/{id} → Remover um diretor
    public void deleteDirector (Long id){
        directorRepository.deleteById(id);
    }


    //GET /directors/{id}/tvshows → Lista das séries de um diretor
    public List<DirectorReadDto> getDirectorByTvShow (String tvShow){
        List<Director> directors =directorRepository.findByTvShows_Name(tvShow);
        if (directors.isEmpty()){
            throw new DirectorNotFoundTvShowException(tvShow);
        }
        return directors.stream()
                .map(DirectorMapper ::fromDirectorToDirectorReadDto)
                .toList();
    }

    //GET /directors/{id}/movies → Lista dos filmes de um diretor
    public List<DirectorReadDto> getDirectorByMovie (String movie){
        List<Director> directors =directorRepository.findByMovies_Name(movie);
        if (directors.isEmpty()){
            throw new DirectorNotFoundMovieException(movie);
        }
        return directors.stream()
                .map(DirectorMapper::fromDirectorToDirectorReadDto).toList();
    }
}
