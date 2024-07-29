package com.example.lab5_q3.Controller;

import com.example.lab5_q3.ApiResponse.ApiResponse;
import com.example.lab5_q3.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event added", "200"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<ApiResponse> updateEvent(@PathVariable int index, @Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, "400"));
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("Event updated", "200"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<ApiResponse> deleteEvent(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Event deleted", "200"));
    }

    @PutMapping("/change/{index}/{capacity}")
    public ResponseEntity<ApiResponse> changeEventCapacity(@PathVariable int index, @PathVariable int capacity) {
        Event event = events.get(index);
        event.setCapacity(capacity);
        return ResponseEntity.status(200).body(new ApiResponse("Event capacity changed", "200"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Object> searchEvents(@PathVariable String id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(404).body(new ApiResponse("Event not found", "404"));
    }
}
