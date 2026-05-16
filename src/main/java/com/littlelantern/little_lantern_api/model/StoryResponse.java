package com.littlelantern.little_lantern_api.model;

public class StoryResponse {
    private String title;
    private String text;
    private String storyType;
    private String lesson;
    private String character;
    private String ageRange;
    private String length;

    public StoryResponse (
        String title,
        String text,
        String storyType,
        String lesson,
        String character,
        String ageRange,
        String length
    ) {
        this.title = title;
        this.text = text;
        this.storyType = storyType;
        this.lesson = lesson;
        this.character = character;
        this.ageRange = ageRange;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getStoryType() {
        return storyType;
    }

    public String getLesson() {
        return lesson;
    }

    public String getCharacter() {
        return character;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public String getLength() {
        return length;
    }
}
