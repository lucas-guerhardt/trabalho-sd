package com.lp.clinic.models.dto.CRUD;

public class ConsultationDelete {
    private Long id;

    public ConsultationDelete() {
    }

    public ConsultationDelete(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
