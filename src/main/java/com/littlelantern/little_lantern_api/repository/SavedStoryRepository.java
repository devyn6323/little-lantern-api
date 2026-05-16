package com.littlelantern.little_lantern_api.repository;

import com.littlelantern.little_lantern_api.model.SavedStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedStoryRepository extends JpaRepository<SavedStory, Long> {
}
