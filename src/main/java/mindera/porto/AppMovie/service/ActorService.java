package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.dto.actorDto.ActorCreateDto;
import mindera.porto.AppMovie.dto.actorDto.ActorReadDto;
import mindera.porto.AppMovie.dto.actorDto.ActorUpdateDto;
import mindera.porto.AppMovie.exception.actor.ActorAlreadyExistsException;
import mindera.porto.AppMovie.exception.actor.ActorNotFoundException;
import mindera.porto.AppMovie.mapper.ActorMapper;
import mindera.porto.AppMovie.model.Actor;
import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.repository.ActorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }


    public ActorReadDto addActor(ActorCreateDto actorCreateDto) {
        if (actorRepository.existsByName(actorCreateDto.getName())) {
            throw new ActorAlreadyExistsException(actorCreateDto.getName());
        }
        Actor actor = ActorMapper.fromActorCreateDtoToActor(actorCreateDto);
        actor = actorRepository.save(actor);
        return ActorMapper.fromActorToActorReadDto(actor);
    }



    public List<ActorReadDto> addActors(List<ActorCreateDto> actorCreateDtos) {
        List<Actor> actorsToSave = new ArrayList<>();
        for (ActorCreateDto dto : actorCreateDtos) {
            if (actorRepository.existsByName(dto.getName())) {
                throw new ActorAlreadyExistsException(dto.getName());
            }
            Actor actor = ActorMapper.fromActorCreateDtoToActor(dto);
            actorsToSave.add(actor);
        }
        List<Actor> savedActors = actorRepository.saveAll(actorsToSave);
        return savedActors.stream()
                       .map(ActorMapper::fromActorToActorReadDto)
                       .collect(Collectors.toList());
    }


    public List<ActorReadDto> getAllActors() {
        return actorRepository.findAll()
                       .stream()
                       .map(ActorMapper::fromActorToActorReadDto)
                       .toList();
    }

    public void deleteActorById(Long id) {
        // Busca o ator pelo ID, ou lança uma exceção se não encontrado
        Actor actor = actorRepository.findById(id)
                              .orElseThrow(() -> new ActorNotFoundException("Actor not found"));

        for (Movie movie : actor.getMovies()) {
            movie.getActors().remove(actor);
        }

        actor.getMovies().clear();

        actorRepository.delete(actor);
    }

    public ActorReadDto getActorById(Long actorId) {
        Actor actor = actorRepository.findById(actorId)
                              .orElseThrow(() -> new ActorNotFoundException("Actor not found"));
        return ActorMapper.fromActorToActorReadDto(actor);
    }

    public ActorReadDto updateActorById(Long actorId, ActorUpdateDto actorUpdateDto) {
        Actor actor = actorRepository.findById(actorId)
                              .orElseThrow(() -> new ActorNotFoundException("Actor not found"));

        // Verifica se já existe outro ator com o nome que se deseja atualizar
        Optional<Actor> duplicateActor = actorRepository.findByName(actorUpdateDto.getName());
        if (duplicateActor.isPresent() && !duplicateActor.get().getId().equals(actorId)) {
            throw new ActorAlreadyExistsException(actorUpdateDto.getName());
        }

        // Remove o ator da coleção de atores de cada filme associado
        List<Movie> moviesToUpdate = new ArrayList<>(actor.getMovies());
        for (Movie movie : moviesToUpdate) {
            movie.getActors().remove(actor);
        }

         // Agora limpa a coleção de filmes do ator
        actor.getMovies().clear();

        ActorMapper.fromActorUpdateDtoToActor(actorUpdateDto, actor);

        actor = actorRepository.save(actor);

        return ActorMapper.fromActorToActorReadDto(actor);
    }



}
