package com.example.lab5_q2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "id should not be empty")
    @Size(min = 2, max = 7, message = "id size from 2 to 7 character")
    private String id;

    @NotEmpty(message = "should not be empty")
    @Size(min = 8, message = "title should be more than 8 character")
    private String title;

    @NotEmpty(message = "should not be empty")
    @Size(min = 15, message = "description should more than 15 character")
    private String description;

    @NotEmpty(message = "should not be empty")
    @Pattern(regexp = "Not Started|In Progress|Completed", message = "status must be 'Not Started', 'In Progress', or 'Completed'")
    private String status;
    
    private String companyName;
}
