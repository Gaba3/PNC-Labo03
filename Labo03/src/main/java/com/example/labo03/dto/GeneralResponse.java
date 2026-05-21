package com.example.labo03.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse {
    private String uri;
    private String message;
    private Integer status;
    private LocalDateTime time;
    private Object data;
}