package com.example.test.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorResponseDTO {
    private Integer code;
    private String message;
}
