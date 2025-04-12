package org.example.apispring.service;

import org.example.apispring.model.Rating;
import org.example.apispring.model.Reply;
import org.example.apispring.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepo ratingRepo;

    public List<Rating> getRatingByProduct(String productId) {
        return ratingRepo.findByProductId(productId);
    }

    public Rating saveRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    public Rating addReplyToRating(String ratingId, Reply reply) {
        Optional<Rating> ratingOpt = ratingRepo.findById(ratingId);
        if (ratingOpt.isEmpty()) throw new RuntimeException("Rating not found");

        Rating rating = ratingOpt.get();
        reply.setRepliedAt(new Date());
        rating.getReplies().add(reply);
        return ratingRepo.save(rating);
    }
}
