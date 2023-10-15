package com.shinobimonk.user.service.entities;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private Integer rating;
    private String feedback;
    private Hotel hotel;
}
