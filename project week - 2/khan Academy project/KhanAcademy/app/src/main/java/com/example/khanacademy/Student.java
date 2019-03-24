package com.example.khanacademy;

import java.io.Serializable;

public class Student implements Serializable {
    String name,time,points;

    public Student(String name, String time, String points) {
        this.name = name;
        this.points = points;
        this.time = time;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.time + " sec" + "\n" + this.points + " points";
    }

}
