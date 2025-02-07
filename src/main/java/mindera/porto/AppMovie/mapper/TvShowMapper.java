package mindera.porto.AppMovie.mapper;

import mindera.porto.AppMovie.dto.tvShowDto.TvShowCreateDto;
import mindera.porto.AppMovie.dto.tvShowDto.TvShowReadDto;
import mindera.porto.AppMovie.model.TvShow;

public class TvShowMapper {

    public static TvShowReadDto fromTvShowToTvShowReadDto (TvShow tvShow){
        TvShowReadDto tvShowReadDto = new TvShowReadDto();
        tvShowReadDto.setId(tvShow.getId());
        tvShowReadDto.setName(tvShow.getName());
        tvShowReadDto.setYear(tvShow.getYear());
        tvShowReadDto.setDescription(tvShow.getDescription());
        tvShowReadDto.setImageUrl(tvShow.getImageUrl());
        return tvShowReadDto;
    }

    public static TvShow fromTvShowCreateDtoToTvShow (TvShowCreateDto tvShowCreateDto){
        TvShow tvShow = new TvShow();
        tvShow.setName(tvShow.getName());
        tvShow.setYear(tvShow.getYear());
        tvShow.setDescription(tvShow.getDescription());
        tvShow.setDirector(tvShow.getDirector());
        return tvShow;
    }
}
