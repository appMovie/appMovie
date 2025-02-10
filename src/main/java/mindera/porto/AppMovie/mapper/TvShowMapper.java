package mindera.porto.AppMovie.mapper;

import mindera.porto.AppMovie.dto.actorDto.ActorReadDto;
import mindera.porto.AppMovie.dto.tvShowDto.TvShowCreateDto;
import mindera.porto.AppMovie.dto.tvShowDto.TvShowReadDto;
import mindera.porto.AppMovie.model.TvShow;

import java.util.List;
import java.util.stream.Collectors;

public class TvShowMapper {

    public static TvShow fromTvShowCreateDtoToTvShow(TvShowCreateDto dto) {
        TvShow tvShow = new TvShow();
        tvShow.setName(dto.getName());
        tvShow.setYear(dto.getYear());
        tvShow.setDescription(dto.getDescription());
        tvShow.setImageUrl(dto.getImageUrl());
        return tvShow;
    }

    public static TvShowReadDto fromTvShowToTvShowReadDto(TvShow tvShow) {
        TvShowReadDto dto = new TvShowReadDto();
        dto.setId(tvShow.getId());
        dto.setName(tvShow.getName());
        dto.setYear(tvShow.getYear());
        dto.setDescription(tvShow.getDescription());
        dto.setDirector(tvShow.getDirector());
        if (tvShow.getActors() != null && !tvShow.getActors().isEmpty()) {
            List<ActorReadDto> actors = tvShow.getActors().stream()
                    .map(ActorMapper::fromActorToActorReadDto)
                    .toList();
            dto.setActors(actors);
        }
        return dto;
    }
}
