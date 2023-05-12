package com.learning.hadef.domain.value;

public enum ApplicationUserPermission {
    USER_READ("user:read"), USER_WRITE("user:write"), USER_DELETE("user:delete"),
    COURSE_READ("course:read"), COURSE_WRITE("course:write"), COURSE_DELETE("course:delete"),
    TEACHER_READ("teacher:read"), TEACHER_WRITE("teacher:write"), TEACHER_DELETE("teacher:delete"),
    EMPLOYEE_READ("employee:read"),EMPLOYEE_WRITE("employee:write"),EMPLOYEE_DELETE("employee:delete");
//    ADMIN_READ("admin:read"),ADMIN_WRITE("admin:write"),ADMIN_DELETE("admin:delete");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
