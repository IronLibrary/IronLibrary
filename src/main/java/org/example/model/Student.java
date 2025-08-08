package org.example.model;

public class Student {
    private String usn;
    private String name;

    //CONSTRUCTOR
    public Student(String usn, String name) {
        this.usn = usn;
        this.name = name;
    }

    //GETTERS
    public String getUsn() {
        return usn;
    }

    public String getName() {
        return name;
    }

    //SETTERS
    public void setUsn(String usn) {
        this.usn = usn;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "usn='" + usn + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}


