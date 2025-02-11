package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.model.Review;
import mindera.porto.AppMovie.model.TvShow;
import mindera.porto.AppMovie.model.User;
import mindera.porto.AppMovie.repository.MovieRepository;
import mindera.porto.AppMovie.repository.ReviewRepository;
import mindera.porto.AppMovie.repository.TvShowRepository;
import mindera.porto.AppMovie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final TvShowRepository tvShowRepository;


    @Autowired
    public UserService(UserRepository userRepository, ReviewRepository reviewRepository, MovieRepository movieRepository, TvShowRepository tvShowRepository){
        this.userRepository=userRepository;
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.tvShowRepository = tvShowRepository;
    }


    public User addNewUser(User user){
        return userRepository.save(user);
    }


    public List<User> getUsers(){
        return userRepository.findAll();
    }


    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }


    public User updateUser(Long id, User userDetails){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }


    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }


    public Review addReviewToMovie(Long userId, Long movieId, String comment, int rating){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found!"));

        Review review = new Review();
        return reviewRepository.save(review);
    }


    public Review addReviewToTvShow(Long userId, Long tvShowId, String comment, int rating){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        TvShow tvShow = tvShowRepository.findById(tvShowId)
                .orElseThrow(() -> new IllegalArgumentException("TV Show not found!"));

        Review review = new Review();
        return reviewRepository.save(review);
    }


    public List<Review> getReviewsByUser(Long userId){
        return reviewRepository.findByUserId(userId);
    }

}
