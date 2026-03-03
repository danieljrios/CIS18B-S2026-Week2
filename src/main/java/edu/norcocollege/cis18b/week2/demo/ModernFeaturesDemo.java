package edu.norcocollege.cis18b.week2.demo;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * ModernFeaturesDemo
 *
 * Demonstrates modern Java (17+) features:
 *  - record type
 *  - pattern matching with instanceof
 *  - switch expression
 *  - Optional
 *  - basic Stream operation
 *  - text block (""")
 *
 * The class is designed to be run by DemoApp (calls demonstrate()).
 */

public class ModernFeaturesDemo 
{
    // Record type requirement (1/6)
    public record UserEvent(String userId, int score, Instant occurredAt) { }

    /**
     * Entry point for DemoApp to run this demo.
     * (DemoApp currently calls modern.demonstrate();)
     */
    public void demonstrate() 
    {
        // Text block requirement display (2/6)
        String intro = """
                ModernFeaturesDemo:
                - record + pattern matching + switch expression
                - Optional
                - Stream operations
                """;
        System.out.println(intro);

        // Sample data (records)
        // - It's going to be a list of users and their scores for an imagined
        // game/event scenario, the data will also be followed by timestamps.
        //
        // - Find out if any users had high scores of 90+ (to demonstrate ModernFeatures)
        List<UserEvent> events = List.of(
                new UserEvent("Dave", 95, Instant.now()),
                new UserEvent("Todd", 72, Instant.now()),
                new UserEvent("Bobby", 58, Instant.now())
        );

        // Optional + Stream operation requirement (3/6 + 4/6)
        Optional<UserEvent> maybeHighScore = events.stream()
                .filter(e -> e.score() >= 90)
                .findFirst();

        // Use Optional instead of null
        System.out.println("High score present? " + maybeHighScore.isPresent());

        // Switch expression requirement (5/6)
        String headline = maybeHighScore
                .map(e -> "Found a high score event for user " + e.userId() + " (" + e.score() + ")")
                .orElse("No high score events found.");
        System.out.println(headline);

        // Pattern matching with instanceof requirement (6/6)
        Object something = maybeHighScore.orElse(new UserEvent("u-none", 0, Instant.now()));
        String description = describeObject(something);
        System.out.println("Pattern match description: " + description);

        // Another small Stream example: average score
        double avg = events.stream()
                .mapToInt(UserEvent::score)
                .average()
                .orElse(0.0);

        System.out.println("Average score: " + avg);

        // Show switch expression classification for each score
        System.out.println("Event classifications:");
        events.forEach(e -> System.out.println(" - " + e.userId() + " => " + classifyScore(e.score())));
    }

    /**
     * Demonstrates pattern matching with instanceof.
     */
    private static String describeObject(Object obj) 
    {
        if (obj instanceof UserEvent e) 
        {  // pattern matching
            return "UserEvent[userId=" + e.userId() + ", score=" + e.score() + "]";
        }
        return "Unknown object type: " + (obj == null ? "null" : obj.getClass().getSimpleName());
    }

    /**
     * Demonstrates a modern switch expression.
     */
    private static String classifyScore(int score) 
    {
        // Display score event classifications
        return switch (score / 10) 
        {
            case 10, 9 -> "Perfect score!";
            case 8 -> "Strong";
            case 7 -> "Good";
            case 6 -> "It's okay";
            default -> "Needs Improvement";
        };
    }
}