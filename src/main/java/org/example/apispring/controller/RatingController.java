package org.example.apispring.controller;

import org.example.apispring.model.Rating;
import org.example.apispring.model.Reply;
import org.example.apispring.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/shop/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.saveRating(rating);
    }

    @GetMapping("/{productId}")
    public List<Rating> getRatingByProduct(@PathVariable String productId) {
        return ratingService.getRatingByProduct(productId);
    }

    @PostMapping("/{ratingId}/reply")
    public Rating addReplyToRating(@PathVariable String ratingId, @RequestBody Reply reply) {
        return ratingService.addReplyToRating(ratingId, reply);
    }
}
