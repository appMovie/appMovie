package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.dto.reviewDto.ReviewCreateDto;
import mindera.porto.AppMovie.dto.reviewDto.ReviewReadDto;
import mindera.porto.AppMovie.exception.GlobalExceptionHandler;
import mindera.porto.AppMovie.mapper.ReviewMapper;
import mindera.porto.AppMovie.model.Review;
import mindera.porto.AppMovie.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }



    public List<ReviewReadDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                       .map(ReviewMapper::fromReviewToReviewReadDto)
                       .collect(Collectors.toList());
    }


    public ReviewReadDto addReview(ReviewCreateDto reviewCreateDto) {
        Review review = ReviewMapper.fromReviewCreateDtoToReview(reviewCreateDto);
        Review savedReview = reviewRepository.save(review);
        return ReviewMapper.fromReviewToReviewReadDto(savedReview);
    }

    /**
     * Retorna uma review por ID.
     */
    public ReviewReadDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                                .orElseThrow(()-> new RuntimeException("Review not found"));
        return ReviewMapper.fromReviewToReviewReadDto(review);
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found!");
        }
        reviewRepository.deleteById(id);
    }
}
