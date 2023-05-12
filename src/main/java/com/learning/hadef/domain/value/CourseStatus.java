package com.learning.hadef.domain.value;

public enum CourseStatus {
    TO_DO("To Do"),
    IN_PROGRESS("In Progress"),
    ON_HOLD("On Hold"),
    DONE("Done");
    private final String status;
    CourseStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
