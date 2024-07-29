package com.example.lab5_q2.Controller;

import com.example.lab5_q2.ApiResponse.ApiResponse;
import com.example.lab5_q2.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public java.util.ArrayList<Project> getProjects() { return projects; }
    @PostMapping("/add")
    public ResponseEntity addProject(@Valid @RequestBody Project project, Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(201).body(new ApiResponse("User updated successfully","200"));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index,@Valid @RequestBody Project project, Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully","200"));
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("project deleted", "200"));
    }
    @PutMapping("/change/{index}")
    public ResponseEntity changeProjectStatus(@PathVariable int index) {
        if(projects.get(index).getStatus().equals("Not Started")) {
            projects.get(index).setStatus("in Progress");
        }else if (projects.get(index).getStatus().equals("in Progress")){
            projects.get(index).setStatus("Completed");
        }else if (projects.get(index).getStatus().equals("Completed")){
            return ResponseEntity.status(200).body(new ApiResponse("project completed no need to change status", "200"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("status changed", "200"));
    }
    @GetMapping("/getAll/{name}")
    public ResponseEntity getAllCompanyProjects(@PathVariable String name){
        ArrayList<Project> company = new ArrayList<>();
        for(Project project1 : projects){
            if(project1.getCompanyName().equals(name)){
                company.add(project1);
            }
        }
        if(company.isEmpty())
            return ResponseEntity.status(400).body( new ApiResponse("projects by company name not found", "404"));
        return ResponseEntity.status(200).body(company);
    }
}
