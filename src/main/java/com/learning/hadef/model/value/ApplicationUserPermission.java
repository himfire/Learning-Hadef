package com.learning.hadef.model.value;

public enum ApplicationUserPermission {
    STUDENT_READ("STUDENT:READ"),
    STUDENT_WRITE("STUDENT:WRITE"),
    STUDENT_UPDATE("STUDENT:UPDATE"),
    STUDENT_DELETE("STUDENT:DELETE"),
    TEACHER_READ("TEACHER:READ"),
    TEACHER_WRITE("TEACHER:WRITE"),
    TEACHER_UPDATE("TEACHER:UPDATE"),
    TEACHER_DELETE("TEACHER:DELETE"),
    ADMIN_READ("ADMIN:READ"),
    ADMIN_WRITE("ADMIN:WRITE"),
    ADMIN_UPDATE("ADMIN:UPDATE"),
    ADMIN_DELETE("ADMIN:DELETE");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission(){
        return permission;
    }
}
