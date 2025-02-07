package mindera.porto.AppMovie.mapper;

import mindera.porto.AppMovie.dto.directorDto.DirectorCreateDto;
import mindera.porto.AppMovie.dto.directorDto.DirectorReadDto;
import mindera.porto.AppMovie.model.Director;

public class DirectorMapper {

    public static DirectorReadDto fromDirectorToDirectorReadDto (Director director){
        DirectorReadDto directorReadDto = new DirectorReadDto();
        directorReadDto.setId(director.getId());
        directorReadDto.setName(director.getName());
        return directorReadDto;
    }

    public static Director fromDirectorCreateDtoToDirector (DirectorCreateDto directorCreateDto){
        Director director = new Director();
        director.setName(directorCreateDto.getName());
        return director;
    }
}
