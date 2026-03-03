package edu.norcocollege.cis18b.week2.demo;

/**
 * DemoApp is a simple test harness to run the Week 1 demo classes:
 * - LanguageBasicsReview
 * - ModernFeaturesDemo
 *
 * This is NOT a replacement for JUnit tests. It's a quick way to
 * run/observe output while you're developing.
 *
 * Required features already completed:
 *  - Instantiate LanguageBasicsReview and call demonstrate()
 *  - Instantiate ModernFeaturesDemo and call runDemo()
 *  - Print clear section headers so output is easy to read
 *
 * TODO (Optional/Stretch):
 *  - Add simple timing (System.nanoTime) around each demo
 *  - Catch exceptions so one demo failing doesn’t stop the other
 */
public class DemoApp 
{

    public static void main(String[] args) 
    {

        System.out.println("========================================");
        System.out.println(" Week 1 Demo Harness");
        System.out.println("========================================");

        // ------------------------------------------------------------
        // Demo 1: Language Basics Review
        // ------------------------------------------------------------
        System.out.println();
        System.out.println(">>> Demo 1: LanguageBasicsReview");
        System.out.println("----------------------------------------");

        var basics = new LanguageBasicsReview();
        basics.demonstrate();

        // ------------------------------------------------------------
        // Demo 2: Modern Features Demo
        // ------------------------------------------------------------
        System.out.println();
        System.out.println(">>> Demo 2: ModernFeaturesDemo");
        System.out.println("----------------------------------------");

        var modern = new ModernFeaturesDemo();
        modern.demonstrate();

        // ------------------------------------------------------------
        // Done
        // ------------------------------------------------------------
        System.out.println();
        System.out.println("========================================");
        System.out.println(" Done. Review output above.");
        System.out.println("========================================");
    }
}