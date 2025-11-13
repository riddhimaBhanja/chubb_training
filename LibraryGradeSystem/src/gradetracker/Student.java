package gradetracker;

import java.util.*;

public class Student {
    protected String name;
    protected Map<String, Integer> grades = new HashMap<>();
 // Constructor which is initializing the student name
    public Student(String name) {
        this.name = name;
    }

    public void addGrade(String subj, int marks) {
        grades.put(subj, marks);
    }
   // Calculating the average marks of the students
    public double getAverage() {
        return grades.values().stream().mapToInt(i -> i).average().orElse(0);
    }

    public void display() {
        System.out.println("Student: " + name);
        grades.forEach((s, m) -> System.out.println(s + ": " + m));
        System.out.println("Average: " + getAverage());
    }
}
