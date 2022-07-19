package com.its.stationery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private String reviewWriter;
    private String reviewTitle;
    private String reviewContents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}
