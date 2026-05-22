package com.littlelantern.little_lantern_api.controller;

import com.littlelantern.little_lantern_api.model.ChildProfile;
import com.littlelantern.little_lantern_api.repository.ChildProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "http://localhost:5173")
public class ChildProfileController {

    private final ChildProfileRepository childProfileRepository;

    public ChildProfileController(ChildProfileRepository childProfileRepository) {
        this.childProfileRepository = childProfileRepository;
    }

    @GetMapping
    public List<ChildProfile> getProfiles() {
        return childProfileRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<ChildProfile> createProfile(@RequestBody ChildProfile childProfile) {
        if (childProfile.getChildName() == null || childProfile.getChildName().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ChildProfile savedProfile = childProfileRepository.save(childProfile);
        return ResponseEntity.ok(savedProfile);
    }

    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        if (!childProfileRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        childProfileRepository.deleteById(id);
        return ResponseEntity.ok("Profile deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChildProfile> updateProfile(
            @PathVariable Long id,
            @RequestBody ChildProfile updatedProfile
    ) {
        return childProfileRepository.findById(id)
                .map(existingProfile -> {
                    existingProfile.setChildName(updatedProfile.getChildName());
                    existingProfile.setAgeRange(updatedProfile.getAgeRange());
                    existingProfile.setFavoriteCharacter(updatedProfile.getFavoriteCharacter());
                    existingProfile.setNotes(updatedProfile.getNotes());

                    ChildProfile savedProfile = childProfileRepository.save(existingProfile);
                    return ResponseEntity.ok(savedProfile);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
