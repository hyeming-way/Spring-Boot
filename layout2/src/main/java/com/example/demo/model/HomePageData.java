package com.example.demo.model;

public class HomePageData {
    private String name; // 이름
    private String title; // 직책
    private String description; // 자기소개
    private String phone; // 전화번호
    private String email; // 이메일
    private String website; // 웹사이트

    public HomePageData(String name, String title, String description, String phone, String email, String website) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.website = website;
    }

    // Getter 메서드들
    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }
}
