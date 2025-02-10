package mindera.porto.AppMovie.mapper;

import mindera.porto.AppMovie.dto.reviewDto.ReviewCreateDto;
import mindera.porto.AppMovie.dto.reviewDto.ReviewReadDto;
import mindera.porto.AppMovie.model.Review;

public class ReviewMapper {

    // Converte o DTO de criação para a entidade Review
    public static Review fromReviewCreateDtoToReview(ReviewCreateDto dto) {
        if (dto == null) return null;
        Review review = new Review();
        review.setComment(dto.getComment());
        review.setRating(dto.getRating());
        return review;
    }

    // Converte a entidade Review para o DTO de leitura
    public static ReviewReadDto fromReviewToReviewReadDto(Review review) {
        if (review == null) return null;
        ReviewReadDto dto = new ReviewReadDto();
        dto.setId(review.getId());
        dto.setComment(review.getComment());
        dto.setRating(review.getRating());
        return dto;
    }
}
