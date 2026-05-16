package com.littlelantern.little_lantern_api.service;

import com.littlelantern.little_lantern_api.model.StoryResponse;
import com.littlelantern.little_lantern_api.model.StoryRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StoryService {

    private final Random random = new Random();

    public StoryResponse generateStory(StoryRequest request) {
        String childName = cleanOrDefault(request.getChildName(), "your little one");
        String character = cleanOrDefault(request.getCharacter(), "puppy");
        String lesson = cleanOrDefault(request.getLesson(), "sharing");
        String storyType = cleanOrDefault(request.getStoryType(), "Bedtime");
        String ageRange = cleanOrDefault(request.getAgeRange(), "2-3");
        String length = cleanOrDefault(request.getLength(), "Short");
        String companionName = getRandomCompanionName();

        String opening = getOpening(storyType, childName, character, companionName);
        String middle = getMiddle(storyType, childName, lesson, companionName);
        String extraScene = getExtraScene(storyType, childName, lesson, companionName, length);
        String ending = getEnding(storyType, childName, character);

        String title = childName + " and the Little " + character;

        String text = opening + "\n\n" + middle + "\n\n";

        if (!extraScene.isEmpty()) {
            text += extraScene + "\n\n";
        }

        text += ending + "\n\nThe end.";

        return new StoryResponse(
                title,
                text.trim(),
                storyType,
                lesson,
                character,
                ageRange,
                length
        );
    }

    private String cleanOrDefault(String value, String fallback) {
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        return value.trim();
    }

    private String getRandomCompanionName() {
        String[] names = {"Milo", "Luna", "Pip", "Nora", "Benny", "Sunny"};
        return names[random.nextInt(names.length)];
    }

    private String getOpening(String storyType, String childName, String character, String companionName) {
        if (storyType.equals("Bedtime")) {
            return "The moon was glowing softly when " + childName +
                    " met a sleepy little " + character.toLowerCase() +
                    " named " + companionName + ".";
        }
        if (storyType.equals("Silly")) {
            return "One sunny morning, " + childName +
                    " met a very silly " + character.toLowerCase() +
                    " named " + companionName +
                    " who was wearing socks on his ears.";
        }

        if (storyType.equals("Adventure")) {
            return childName +
                    " packed a tiny backpack and met a brave little " +
                    character.toLowerCase() +
                    " named " + companionName +
                    " near a sparkling path.";
        }

        if (storyType.equals("Learning")) {
            return childName +
                    " met a curious little " + character.toLowerCase() +
                    " named " + companionName +
                    " who loved colors, numbers, and new words.";
        }

        if (storyType.equals("Calm Down")) {
            return childName +
                    " found a quiet place under a soft tree and met a gentle little " +
                    character.toLowerCase() +
                    " named " + companionName + ".";
        }

        return "Once upon a time, " + childName +
                " met a little " + character.toLowerCase() +
                " named " + companionName + ".";
    }

    private String getMiddle(String storyType, String childName, String lesson, String companionName) {
        if (storyType.equals("Bedtime")) {
            return companionName + " rubbed his sleepy eyes and whispered, \"I want to learn about " +
                    lesson.toLowerCase() + " before bed.\"\n\n" +
                    childName + " helped " + companionName +
                    " take slow steps, use a soft voice, and make kind choices.";
        }

        if (storyType.equals("Silly")) {
            return companionName + " wanted to learn about " +
                    lesson.toLowerCase() +
                    ", but first he accidentally put a banana on his head like a hat.\n\n" +
                    childName + " giggled and helped " + companionName + " try again.";
        }

        if (storyType.equals("Adventure")) {
            return companionName + " wanted to learn about " +
                    lesson.toLowerCase() +
                    ", so " + childName + " and " + companionName +
                    " followed the sparkling path.\n\n" +
                    "They crossed a soft grass hill, tiptoed past blue flowers, and found a tiny bridge.";
        }

        if (storyType.equals("Learning")) {
            return companionName + " wanted to learn about " +
                    lesson.toLowerCase() + ".\n\n" +
                    "First, " + childName + " found one red flower.\n\n" +
                    "Then " + companionName + " found two yellow stars.";
        }

        if (storyType.equals("Calm Down")) {
            return companionName + " felt a little upset and wanted to learn about " +
                    lesson.toLowerCase() + ".\n\n" +
                    childName + " said, \"Let's take a slow breath.\"\n\n" +
                    "They breathed in like smelling a flower, then breathed out like blowing a bubble.";
        }

        return companionName + " wanted to learn about " + lesson.toLowerCase() + ".\n\n" +
                childName + " helped " + companionName + " try, practice, and make a kind choice.";
    }

    private String getExtraScene(
            String storyType,
            String childName,
            String lesson,
            String companionName,
            String length
    ) {
        if (length.equals("Short")) {
            return "";
        }

        if (length.equals("Medium")) {
            return childName + " and " + companionName +
                    " practiced " + lesson.toLowerCase() +
                    " one more time.";
        }

        if (length.equals("Long")) {
            return childName + " and " + companionName +
                    " practiced " + lesson.toLowerCase() +
                    " one more time.\n\n" +
                    companionName +
                    " smiled and said, \"I can try again tomorrow too.\"";
        }

        return "";
    }

    private String getEnding(String storyType, String childName, String character) {
        if (storyType.equals("Bedtime")) {
            return "The little " + character.toLowerCase() +
                    " curled up under a soft blanket. " +
                    childName + " smiled as the room grew quiet and peaceful.";
        }

        if (storyType.equals("Silly")) {
            return "The little " + character.toLowerCase() +
                    " did one last wiggle dance, and " +
                    childName + " laughed all the way home.";
        }

        if (storyType.equals("Adventure")) {
            return childName + " and the little " +
                    character.toLowerCase() +
                    " found their way home, proud of their brave little adventure.";
        }

        if (storyType.equals("Learning")) {
            return childName + " and the little " +
                    character.toLowerCase() +
                    " clapped for one, two, three happy cheers.";
        }

        if (storyType.equals("Calm Down")) {
            return "The little " + character.toLowerCase() +
                    " felt safe and peaceful. " +
                    childName +
                    " gave a gentle smile, and everything felt okay again.";
        }

        return childName + " and the little " +
                character.toLowerCase() +
                " had a very good day.";
    }
}
