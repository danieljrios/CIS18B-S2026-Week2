package edu.norcocollege.cis18b.week2.alerts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/*
 * Stores SecurityAlert records in-memory and provides query/removal logic.
 *
 * Demonstrates:
 *  - Storing alerts in a list +                
 *  - Optional usage (with no null returns)     (1/6)
 *  - Streams for filtering                     (2/6)
 *  - switch expression for severity handling   (3/6)
 *  - pattern matching with instanceof          (4/6)
 *  - var usage                                 (5/6)
 *  - text block requirement in App.Java
 *    but I'll also add one here just in case   (6/6)
 *  - Also while satisfying the TO-DO requirements in the JUnit tests
 */
public class SecurityAlertManager 
{
    // Declare private/internal List<SecurityAlert> to store alerts (to-do requirement)
    // Using ArrayList so we can add/remove items
    private final List<SecurityAlert> alerts = new ArrayList<>();

    /* // -- addAlert -- //
     * Reject null values and add alerts to an internal list (to-do requirement)
    */ // -------------- //
    public void addAlert(SecurityAlert alert) 
    {
        // If null is entered, throw IllegalArgumentException, otherwise add to internal list
        if (alert == null) 
        {   // Using IllegalArgumentException instead of UnsupportedOperationException since 
            // it's more appropriate for bad input
            throw new IllegalArgumentException("Alert cannot be null");
        }
        alerts.add(alert);
    }

    /* // -- findbyId -- //
     *  - Return Optional.empty() if id is null // Requirement (1/6)
     *  - Use streams to find first match 
    */ // -------------- //
    public Optional<SecurityAlert> findById(String id) 
    {
        if (id == null) 
        {
            return Optional.empty(); // Required use of optional
        }
        // Use streams to filter by ID and return the first match wrapped in Optional
        return alerts.stream()
                .filter(a -> Objects.equals(a.id(), id))
                .findFirst();
    }

    /* // -- findBySeverity -- //
     * - Return empty list if severity is null
     * - Use streams to filter by severity // Requirement 2/6
    */ // -------------------- // 
    public List<SecurityAlert> findBySeverity(String severity) 
    {
        if (severity == null) 
        {
            // To-Do requirement - return empty list if severity is null
            return List.of();
        }
        // Stream filters alerts that match the severity exactly (e.g., "HIGH")
        // and then collects to a new list
        return alerts.stream()
                .filter(a -> Objects.equals(a.severity(), severity))
                .toList();
    }
    /* // -- removeAlert -- //
     * - Remove alerts by id
     * - Return true if an alert was removed, or false if not found/null id
    */ // ---------------- // 
    public boolean removeAlert(String id) 
    {
        if (id == null) 
        {
            return false;
        }

        return alerts.removeIf(a -> Objects.equals(a.id(), id));
    }

    /** // --  getSeverityRecommendation pt. 1 (for primary tests) -- //
     * 
     * TODO requirements:
     *  - Reject null alert
     *  - Use modern switch expression
     *  - Throw IllegalArgumentException for unknown severity
     *
     * Requirement (3/6): switch expression + IllegalArgumentException for unknown severity
     * Requirement (4/6): Use of var
     * 
     * NOTE: The JUnit test calls THIS overload (SecurityAlert param),
     * so keep the signature the same
     */ // ---------------------------------------------------------- //
    public String getSeverityRecommendation(SecurityAlert alert) 
    {
        if (alert == null) 
        {
            throw new IllegalArgumentException("alert cannot be null");
        }

        // Requirement: var usage
        var sev = alert.severity();

        // Modern switch expression requirement
        return switch (sev) 
        {
            case "LOW" -> "Log and monitor.";
            case "MEDIUM" -> "Investigate within 24 hours.";
            case "HIGH" -> "Escalate to engineering.";
            case "CRITICAL" -> "Immediate incident response required.";
            default -> throw new IllegalArgumentException("Unknown severity: " + sev);
        };
    }

    /* // getSeverityReccomendation pt. 2 (OVERLOAD FOR PATTERN MATCHING) -- //
     *
     * Requirement (5/6): Pattern matching with instanceof
     *
     * - This method demonstrates pattern matching with instanceof. It accepts an Object Parameter,
     *   checks if it's a SecurityAlert, and if so, it then delegates to the other getSeverityRecommendation 
     *   method. If it's not a SecurityAlert (or is null), it throws an exception.
     * - Pattern matching with instanceof is meaningful when the static type isn't already SecurityAlert.
     * - Overload not required by the tests, but it demonstrates the feature
    */ // ------------------------------------------------------------------ //
    public String getSeverityRecommendation(Object maybeAlert) 
    {
        // Pattern matching with instanceof requirement:
        // If maybeAlert is a SecurityAlert, bind it to variable "a" and use it.
        if (maybeAlert instanceof SecurityAlert a) 
        {
            return getSeverityRecommendation(a);
        }

        // If it's null or some other type, treat it as invalid input
        throw new IllegalArgumentException("Expected a SecurityAlert");
    }

    /* // -- systemTestBlock -- //
    * Builds a simple text block to demonstrate the feature & ensure program compiles with it.
    * Demonstrates use of a text block (""") inside the manager requirement (6/6)
    */ // --------------------- // 
    public String systemTestBlock() 
    {
    return """
           SYSTEM ONLINE: SYSTEM ALERT MANAGER RUNNING
           """;
    }

}