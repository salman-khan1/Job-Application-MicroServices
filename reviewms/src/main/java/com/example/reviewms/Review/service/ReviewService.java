package com.example.reviewms.Review.service;


import com.example.reviewms.Review.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    boolean addReview(Long companyId,Review review);
    Review getReview(Long reviewId);
    boolean updateReview(Long reviewId,Review review);

    boolean deleteReview (Long reviewId);
}
