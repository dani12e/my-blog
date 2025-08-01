package com.project1.starter.util.constants;

public enum Authorities {
    RESET_USER_PASSWORD(1L,"RESET_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2L,"ACCESS_ADMIN_PANEL");

    private Long id;
    private String name;
    private Authorities(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    

}
