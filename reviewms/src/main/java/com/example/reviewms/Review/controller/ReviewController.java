package com.example.reviewms.Review.controller;


import com.example.reviewms.Review.entity.Review;
import com.example.reviewms.Review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity(reviewService.getAllReviews (companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,@RequestBody Review review){
       boolean isReviewedSaved = reviewService.addReview (companyId,review);
       if(isReviewedSaved){
        return new ResponseEntity<> ("Review Added Successfully",HttpStatus.CREATED);}
       else{
           return new ResponseEntity<> ("Can not Post review because no Company exist with this id = " + companyId + " not found",HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdated= reviewService.updateReview (reviewId,review);
        if(isReviewUpdated){
        return new ResponseEntity<> ("Review Updated Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<> ("Review Not Updated",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isReviewDeleted= reviewService.deleteReview (reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<> ("Review Deleted Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<> ("Review Not Deleted",HttpStatus.NOT_FOUND);
        }
    }

}
