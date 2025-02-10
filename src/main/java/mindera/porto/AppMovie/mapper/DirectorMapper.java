package mindera.porto.AppMovie.mapper;

import mindera.porto.AppMovie.dto.directorDto.DirectorCreateDto;
import mindera.porto.AppMovie.dto.directorDto.DirectorReadDto;
import mindera.porto.AppMovie.model.Director;

public class DirectorMapper {

    public static Director fromDirectorCreateDtoToDirector(DirectorCreateDto dto) {
        Director director = new Director();
        director.setName(dto.getName());
        return director;
    }

    public static DirectorReadDto fromDirectorToDirectorReadDto(Director director) {
        DirectorReadDto dto = new DirectorReadDto();
        dto.setId(director.getId());
        dto.setName(director.getName());
        return dto;
    }
}