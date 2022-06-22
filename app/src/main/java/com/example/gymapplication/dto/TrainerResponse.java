package com.example.gymapplication.dto;

import java.util.List;


public class TrainerResponse {
    private String name;
    private String surname;
    private String code;
    private List<WardResponse> ward;

    public TrainerResponse() {
    }

    public TrainerResponse(String name, String surname, String code, List<WardResponse> ward) {
        this.name = name;
        this.surname = surname;
        this.code = code;
        this.ward = ward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<WardResponse> getWard() {
        return ward;
    }

    public void setWard(List<WardResponse> ward) {
        this.ward = ward;
    }
}
