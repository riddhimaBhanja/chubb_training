package gradetracker;

import java.util.*;

public class GradeApp {
    @SuppressWarnings("resource")
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        List<Student> std = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add Student  2. View Grades  3. Exit");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Is Topper (y/n)? ");
                    String t = sc.nextLine();
                    Student s = t.equalsIgnoreCase("y") ? new Topper(name, 5.0) : new Student(name);

                    System.out.print("Enter subject count: ");
                    int n = sc.nextInt(); sc.nextLine();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Subject: ");
                        String subj = sc.nextLine();
                        System.out.print("Marks: ");
                        int marks = sc.nextInt(); sc.nextLine();
                        s.addGrade(subj, marks);
                    }
                    std.add(s);
                }
                case 2 -> std.forEach(Student::display);
                case 3 -> { System.out.println("Exiting..."); return; }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
