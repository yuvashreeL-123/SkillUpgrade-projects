import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Student Class
class Student {
    private String name;
    private String id;
    private List<Course> courses;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public double calculateOverallAverage() {
        double totalGrades = 0;
        int totalCourses = 0;

        for (Course course : courses) {
            totalGrades += course.calculateAverageGrade();
            totalCourses++;
        }

        return totalCourses > 0 ? totalGrades / totalCourses : 0;
    }

    public void generateReport() {
        System.out.println("Student: " + name + " (ID: " + id + ")");
        for (Course course : courses) {
            System.out.println("Course: " + course.getName() + ", Average Grade: " + course.calculateAverageGrade());
        }
        System.out.println("Overall Average Grade: " + calculateOverallAverage());
    }
}

// Course Class
class Course {
    private String name;
    private List<Grade> grades;

    public Course(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public double calculateAverageGrade() {
        double totalGrades = 0;
        for (Grade grade : grades) {
            totalGrades += grade.getScore();
        }

        return grades.size() > 0 ? totalGrades / grades.size() : 0;
    }
}

// Grade Class
class Grade {
    private double score;
    private String description;

    public Grade(double score, String description) {
        this.score = score;
        this.description = description;
    }

    public double getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }
}

// Main Program
public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student student = createStudent(scanner);

        boolean running = true;
        while (running) {
            System.out.println("\n1. Add Course");
            System.out.println("2. Add Grade to Course");
            System.out.println("3. Generate Report");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addCourseToStudent(scanner, student);
                    break;
                case 2:
                    addGradeToCourse(scanner, student);
                    break;
                case 3:
                    student.generateReport();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static Student createStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        return new Student(name, id);
    }

    private static void addCourseToStudent(Scanner scanner, Student student) {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        Course course = new Course(courseName);
        student.addCourse(course);
        System.out.println("Course added successfully.");
    }

    private static void addGradeToCourse(Scanner scanner, Student student) {
        System.out.print("Enter course name to add grade: ");
        String courseName = scanner.nextLine();
        Course course = findCourseByName(student, courseName);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter grade score: ");
        double score = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter grade description: ");
        String description = scanner.nextLine();

        Grade grade = new Grade(score, description);
        course.addGrade(grade);
        System.out.println("Grade added successfully.");
    }

    private static Course findCourseByName(Student student, String courseName) {
        for (Course course : student.getCourses()) {
            if (course.getName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }
}
