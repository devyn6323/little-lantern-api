package com.littlelantern.little_lantern_api.controller;

import com.littlelantern.little_lantern_api.model.SavedStory;
import com.littlelantern.little_lantern_api.model.StoryRequest;
import com.littlelantern.little_lantern_api.model.StoryResponse;
import com.littlelantern.little_lantern_api.repository.SavedStoryRepository;
import com.littlelantern.little_lantern_api.service.StoryService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/saved")
    public SavedStory saveStory(@RequestBody SavedStory savedStory) {
        return savedStoryRepository.save(savedStory);
    }

    @DeleteMapping("/saved/{id}")
    public void deleteStory(@PathVariable Long id) {
        savedStoryRepository.deleteById(id);
    }

}
