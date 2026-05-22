package com.littlelantern.little_lantern_api.controller;

import com.littlelantern.little_lantern_api.model.SavedStory;
import com.littlelantern.little_lantern_api.model.StoryRequest;
import com.littlelantern.little_lantern_api.model.StoryResponse;
import com.littlelantern.little_lantern_api.repository.SavedStoryRepository;
import com.littlelantern.little_lantern_api.service.StoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/api/stories")
@CrossOrigin(origins = "http://localhost:5173",
             methods = {
                     RequestMethod.GET,
                     RequestMethod.POST,
                     RequestMethod.DELETE,
                     RequestMethod.OPTIONS
             }
             )
public class StoryController {

    private final StoryService storyService;
    private final SavedStoryRepository savedStoryRepository;

    public StoryController(StoryService storyService,
                           SavedStoryRepository savedStoryRepository) {
        this.storyService = storyService;
        this.savedStoryRepository = savedStoryRepository;
    }

    @PostMapping("/generate")
    public StoryResponse generateStory(@RequestBody StoryRequest request) {
        return storyService.generateStory(request);
    }

    @GetMapping("/saved")
    public List<SavedStory> getSavedStories() {
        return
    savedStoryRepository.findAll();
    }

    @GetMapping("/saved/profile/{childProfileId}")
    public List<SavedStory> getSavedStoriesByProfile(@PathVariable Long childProfileId) {
        return savedStoryRepository.findByChildProfileId(childProfileId);
    }

    @PostMapping("/saved")
    public ResponseEntity<SavedStory> saveStory(@RequestBody SavedStory savedStory) {
        if (savedStory.getTitle() == null || savedStory.getText().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        SavedStory saved = savedStoryRepository.save(savedStory);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/saved/{id}")
    public ResponseEntity<String> deleteStory(@PathVariable Long id) {
        if (!savedStoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        savedStoryRepository.deleteById(id);
        return ResponseEntity.ok("Story deleted successfully.");
    }

    @PutMapping("/saved/{id}")
    public ResponseEntity<SavedStory> updateStory(
            @PathVariable Long id,
            @RequestBody SavedStory updatedStory
    ) {
        return savedStoryRepository.findById(id)
                .map(existingStory -> {
                  existingStory.setTitle(updatedStory.getTitle());
                  SavedStory saved = savedStoryRepository.save(existingStory);
                  return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());


    }

}
