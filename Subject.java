package ru.relex.Test_3;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Subject {
    @JsonProperty("title")
    private String title;
    @JsonProperty("teacher")
    private Teacher teacher;

    public Subject(String title, Teacher teacher) {
        this.title = title;
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "title='" + title + '\'' +
                ", teacher=" + teacher +
                '}';
    }

}
