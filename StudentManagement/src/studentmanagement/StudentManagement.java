/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentmanagement;

import java.util.ArrayList;
import java.util.Scanner;

final class Student {
    int id;
    String name;
    double marks;
    String rank;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.rank = assignRank(marks);
    }

    String assignRank(double marks) {
        if (marks >= 0 && marks <= 5.0) return "Fail";
        else if (marks > 5.0 && marks <= 6.5) return "Medium";
        else if (marks > 6.5 && marks <= 7.5) return "Good";
        else if (marks > 7.5 && marks <= 9.0) return "Very Good";
        else return "Excellent";
    }
}

public class StudentManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display Students");
            System.out.println("6. Sort Students (Bubble Sort)");
            System.out.println("7. Sort Students (Insertion Sort)");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> deleteStudent();
                case 4 -> searchStudent();
                case 5 -> displayStudents();
                case 6 -> bubbleSort();
                case 7 -> insertionSort();
                case 8 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Marks: ");
        double marks = scanner.nextDouble();

        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    private static void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Student student : students) {
            if (student.id == id) {
                System.out.print("Enter New Name: ");
                student.name = scanner.nextLine();

                System.out.print("Enter New Marks: ");
                student.marks = scanner.nextDouble();
                student.rank = student.assignRank(student.marks);

                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();

        boolean removed = students.removeIf(student -> student.id == id);

        if (removed) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();

        for (Student student : students) {
            if (student.id == id) {
                System.out.println("ID: " + student.id + ", Name: " + student.name + ", Marks: " + student.marks + ", Rank: " + student.rank);
                return;
            }
        }
        System.out.println("Student not found!");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("ID\tName\t\tMarks\tRank");
        for (Student student : students) {
            System.out.println(student.id + "\t" + student.name + "\t\t" + student.marks + "\t" + student.rank);
        }
    }

    private static void bubbleSort() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).marks > students.get(j + 1).marks) {
                    // Swap students[j] and students[j+1]
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted using Bubble Sort by marks (ascending order):");
        displayStudents(); // Optional: Display sorted students
    }

    private static void insertionSort() {
         if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;}
        int n = students.size();
        for (int i = 1; i < n; i++) {
            Student key = students.get(i);
            int j = i - 1;

            // Move elements of students[0..i-1] that are greater than key.marks
            // to one position ahead of their current position
            while (j >= 0 && students.get(j).marks > key.marks) {
                students.set(j + 1, students.get(j));
                j--;
            }
            students.set(j + 1, key);
        }

        System.out.println("Students sorted using Insertion Sort by marks (ascending order):");
        displayStudents(); // Optional: Display sorted students
    }
}
