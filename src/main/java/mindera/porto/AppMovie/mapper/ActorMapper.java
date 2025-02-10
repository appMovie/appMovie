package mindera.porto.AppMovie.mapper;

import mindera.porto.AppMovie.dto.actorDto.ActorCreateDto;
import mindera.porto.AppMovie.dto.actorDto.ActorReadDto;
import mindera.porto.AppMovie.dto.actorDto.ActorUpdateDto;
import mindera.porto.AppMovie.model.Actor;

public class ActorMapper {

    // Converte ActorCreateDto para Actor (entidade)
    public static Actor fromActorCreateDtoToActor(ActorCreateDto actorCreateDto) {
        Actor actor = new Actor();
        actor.setName(actorCreateDto.getName());
        return actor;
    }

    // Converte Actor (entidade) para ActorReadDto
    public static ActorReadDto fromActorToActorReadDto(Actor actor) {
        ActorReadDto dto = new ActorReadDto();
        dto.setId(actor.getId());
        dto.setName(actor.getName());
        return dto;
    }

    public static void fromActorUpdateDtoToActor(ActorUpdateDto actorUpdateDto, Actor actor) {
        actor.setName(actorUpdateDto.getName());
    }


}
