package mindera.porto.AppMovie.controller;

import jakarta.validation.Valid;
import mindera.porto.AppMovie.dto.reviewDto.ReviewCreateDto;
import mindera.porto.AppMovie.dto.reviewDto.ReviewReadDto;
import mindera.porto.AppMovie.model.Review;
import mindera.porto.AppMovie.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public List<ReviewReadDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping("")
    public ReviewReadDto addReview(@Valid @RequestBody ReviewCreateDto reviewDto) {
        return reviewService.addReview(reviewDto);
    }

    @GetMapping("/{id}")
    public ReviewReadDto getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
