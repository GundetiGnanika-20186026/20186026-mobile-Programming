package com.example.khanacademy;

public class ListItem {
    private String studentname;
    private String hours;
    private String problems;

    public ListItem(String studentname, String hours, String problems) {
        this.studentname = studentname;
        this.hours = hours;
        this.problems = problems;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }
}
