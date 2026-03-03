package edu.norcocollege.cis18b.week2.alerts;

import java.util.List;
import edu.norcocollege.cis18b.week2.alerts.SecurityAlert;
import edu.norcocollege.cis18b.week2.alerts.SecurityAlertManager;

/**
 * Entry point for Week 2 - Cybersecurity Alert System demo harness.
 *
 * Demonstrates:
 *  - Creating SecurityAlert records
 *  - Adding them to SecurityAlertManager
 *  - Querying by severity
 *  - Looking up an alert by ID
 *  - Printing formatted output using a text block
 *  - Calling a manager method to get a recommendation based on severity 
 */
public class App 
{

    public static void main(String[] args) 
    {

        // Create SecurityAlertManager instance
        var manager = new SecurityAlertManager();

        // Create 3 SecurityAlert records
        var alert1 = new SecurityAlert(
                "A-001",
                "web-gateway",
                "HIGH",
                "Suspicious login attempt",
                System.currentTimeMillis()
        );

        var alert2 = new SecurityAlert(
                "A-002",
                "endpoint-agent",
                "LOW",
                "Minor anomaly detected",
                System.currentTimeMillis()
        );

        var alert3 = new SecurityAlert(
                "A-003",
                "auth-service",
                "CRITICAL",
                "Privilege escalation detected",
                System.currentTimeMillis()
        );

        // Add alerts to manager
        manager.addAlert(alert1);
        manager.addAlert(alert2);
        manager.addAlert(alert3);

        // Query by severity
        List<SecurityAlert> highAlerts = manager.findBySeverity("HIGH");

        // Look up an alert by ID
        var lookupId = "A-003";
        var found = manager.findById(lookupId);

        // Compute counts for report
        var total = List.of(alert1, alert2, alert3).size();
        var highCount = highAlerts.size();
        var criticalCount = manager.findBySeverity("CRITICAL").size();

        // Print a text block from the manager to show user the system is running
        System.out.println(manager.systemTestBlock());

        // Print formatted report using a text block
        System.out.println("""
                === SECURITY ALERT REPORT ===
                Total Alerts: %d
                High Severity Alerts: %d
                Critical Severity Alerts: %d

                Lookup ID: %s
                Found?: %s
                """.formatted(
                total,
                highCount,
                criticalCount,
                lookupId,
                found.isPresent()
        ));

        // Demonstrate calling a manager method that displays severity recommendation
        found.ifPresent(a -> System.out.println(
                "Recommendation for " + a.id() + " (" + a.severity() + "): "
                        + manager.getSeverityRecommendation(a)
        ));
    }
}