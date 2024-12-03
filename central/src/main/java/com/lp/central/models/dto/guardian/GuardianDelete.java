package com.lp.central.models.dto.guardian;

public class GuardianDelete {
    private Long id;

    public GuardianDelete() {
    }

    public GuardianDelete(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
