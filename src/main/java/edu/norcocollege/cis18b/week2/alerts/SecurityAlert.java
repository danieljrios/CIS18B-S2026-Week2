package edu.norcocollege.cis18b.week2.alerts;

// Replacing public class "SecurityAlert" with a record to create immutable data classes
public record SecurityAlert
(
    String id,
    String sourceSystem,
    String severity,
    String description,
    long timestamp
) {}

// Explanation for why this object is immutable: 
// Records in Java are automatically immutable by design. Once an instance of 
// a record is created its state can't be changed.