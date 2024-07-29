package com.example.lab5_q3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {

    @NotEmpty(message = "ID should not be empty")
    @Size(min = 2, message = "ID should be at least 2 characters")
    private String id;

    @NotEmpty(message = "Description should not be empty")
    @Size(min = 15, message = "Description should be more than 15 characters")
    private String description;

    @NotNull(message = "Capacity cannot be null")
    @Min(value = 25, message = "Capacity must be more than 25")
    private Integer capacity;

    @NotNull(message = "Start date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime endDate;
}
