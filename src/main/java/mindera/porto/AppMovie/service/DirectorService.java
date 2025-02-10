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

    //GET /directors/list → Lista de todos os diretores
    public List<DirectorReadDto> getAllDirectors(){
        return directorRepository.findAll().stream()
                .map(DirectorMapper::fromDirectorToDirectorReadDto)
                .toList();
    }

    // GET /directors/{id} → Obter um diretor por id
    public DirectorReadDto getDirector(Long id){
        Director director =directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException());
        return DirectorMapper.fromDirectorToDirectorReadDto(director);
    }
    // POST /directors/ → Criar um novo diretor
    public DirectorReadDto addDirector(DirectorCreateDto directorCreateDto) {
        Director director = DirectorMapper.fromDirectorCreateDtoToDirector(directorCreateDto);
        Director savedDirector = directorRepository.save(director);
        return DirectorMapper.fromDirectorToDirectorReadDto(savedDirector);
    }

    //PUT /directors/{id} → Atualizar informações de um diretor
    public DirectorReadDto updateDirector(Long id, DirectorCreateDto directorCreateDto) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException());
        director.setName(directorCreateDto.getName());
        Director updatedDirector = directorRepository.save(director);
        return DirectorMapper.fromDirectorToDirectorReadDto(updatedDirector);
    }

    //DELETE /directors/{id} → Remover um diretor
    public void deleteDirector (Long id){
        directorRepository.deleteById(id);
    }
}
