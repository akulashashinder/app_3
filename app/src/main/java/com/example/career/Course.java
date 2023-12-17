package com.example.career;

public class Course {
    private String courseId;
    private String name;
    private String description;
    private String link;
    private String standard;

    public Course() {
        //For Firebase
    }

    public Course(String standard,String courseId, String name, String description, String link) {
        this.standard=standard;
        this.courseId=courseId;
        this.name = name;
        this.description = description;
        this.link=link;
    }

    public String getStandard() {
        return standard;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getLink() {
        return link;
    }
}
