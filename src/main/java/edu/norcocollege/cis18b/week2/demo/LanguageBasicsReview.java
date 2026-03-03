package edu.norcocollege.cis18b.week2.demo;

import java.util.List;

/**
 * LanguageBasicsReview
 *
 * A short review/demo of core Java features.
 *
 * Demonstrates (per assignment):
 *  - Variable declarations and types
 *  - Control flow (if, switch, loops)
 *  - Methods and parameters
 *  - Basic OOP concepts (classes, objects, inheritance)
 *  - Standard instance method                              (assignment requirement)
 *  - Static method                                         (assignment requirement)
 *  - Overloaded method                                     (assignment requirement)
 *  - List using List.of(...)                               (assignment requirement)
 *  - try/catch block                                       (assignment requirement)
 *  - Custom exception class                                (assignment requirement)
 *  - Modern switch expression                              (assignment requirement)
 *  - Use of var                                            (assignment requirement)
 *
 * This class is designed to be run by DemoApp (calls demonstrate()).
 */
public class LanguageBasicsReview 
{


    // Custom exception class requirement (1/8)
    // Also demonstrates inheritance via "Extends Exception"
    public static class InvalidScoreException extends Exception 
    {
        public InvalidScoreException(String message) 
        {
            super(message);
        }
    }


    // Standard instance method by adding two ints. Requirement: 2/8
    public int add(int a, int b)
    {
        return a + b;
    }

    // Overloaded method (same name, different parameters). Requirement: 3/8
    public int add(int a, int b, int c) 
    {
        return a + b + c;
    }

    /**
     * Static method that returns a letter grade from a numeric score.
     * Demonstrates:
     *  - if (validation)
     *  - throwing a custom exception   (Requirement: 4/8)
     *  - modern switch expression      (Requirement: 5/8)
     */
    public static String gradeFromScore(int score) throws InvalidScoreException 
    {

        // Control flow: if
        if (score < 0 || score > 100) 
        {
            throw new InvalidScoreException("Score must be between 0 and 100. Got: " + score);
        }

        // Control flow: switch expression
        return switch (score / 10) 
        {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
    }

    /**
     * Entry point for DemoApp to run
     * Demonstrates:
     *  - List.of(...)      (Requirement: 6/8)
     *  - var               (Requirement: 7/8)
     *  - loops
     *  - try/catch         (Requirement: 8/8)
     *  - calling instance and static methods
     */
    public void demonstrate() 
    {

        // Variable declaration and type (List<Integer>) + List.of(...)
        List<Integer> scores = List.of(95, 82, 76, 61, 52, -3);

        // var (type inference) + object reference (shows OOP: b/c this is an object)
        var self = this;

        // Demonstrate instance + overloaded methods
        var sum2 = self.add(2, 3);
        var sum3 = self.add(2, 3, 4);
        System.out.println("-- Demonstrating the program runs with all features --");
        System.out.println("add(2, 3) = " + sum2);
        System.out.println("add(2, 3, 4) = " + sum3);
        System.out.println("______________________");
        System.out.println();

        // Control flow: loop
        for (var score : scores) 
        {
            // try/catch block
            try 
            // Printing score and grade (calls static method that may throw exception)
            {
                var grade = gradeFromScore(score);

                System.out.println("Score: " + score + " | Grade: " + grade
                + " |"
                );
            } 
            catch (InvalidScoreException e) 
            {
                System.out.println("Score: " + score + " | ERROR: " + e.getMessage());
            }
        }
    }
}