import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " - " + marks + "%";
    }
}

public class StudentFilter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter Name: ");
            String name = sc.next();
            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();

            students.add(new Student(name, marks));
        }

        // **Using Streams and Lambda**
        List<String> topStudents = students.stream()
            .filter(s -> s.marks > 75)  // Filter students scoring above 75%
            .sorted((s1, s2) -> Double.compare(s2.marks, s1.marks))  // Sort by marks (Descending)
            .map(s -> s.name)  // Extract only names
            .collect(Collectors.toList());  // Collect into a list

        System.out.println("\nTop Scoring Students:");
        topStudents.forEach(System.out::println);

        sc.close();
    }
}
