import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name, branch, email;
    double cgpa;

    Student(int id, String name, String branch,
            double cgpa, String email) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.cgpa = cgpa;
        this.email = email;
    }

    void display() {
        System.out.println("ID: " + id
                + ", Name: " + name
                + ", Branch: " + branch
                + ", CGPA: " + cgpa
                + ", Email: " + email);
    }
}

class Company {
    String name, role, branch;
    double minCgpa;

    Company(String name, String role,
            String branch, double minCgpa) {
        this.name = name;
        this.role = role;
        this.branch = branch;
        this.minCgpa = minCgpa;
    }

    void display() {
        System.out.println("Company: " + name
                + ", Role: " + role
                + ", Eligible Branch: " + branch
                + ", Minimum CGPA: " + minCgpa);
    }
}

class Application {
    int studentId;
    String companyName;
    String status;

    Application(int studentId, String companyName) {
        this.studentId = studentId;
        this.companyName = companyName;
        this.status = "Applied";
    }

    void display() {
        System.out.println("Student ID: " + studentId
                + ", Company: " + companyName
                + ", Status: " + status);
    }
}

public class PlacementManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Company> companies = new ArrayList<>();
    static ArrayList<Application> applications = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static Student findStudent(int id) {
        for (Student s : students) {
            if (s.id == id)
                return s;
        }
        return null;
    }

    static Company findCompany(String name) {
        for (Company c : companies) {
            if (c.name.equalsIgnoreCase(name))
                return c;
        }
        return null;
    }

    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (findStudent(id) != null) {
            System.out.println("Student ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Branch: ");
        String branch = sc.nextLine();

        System.out.print("Enter CGPA: ");
        double cgpa = sc.nextDouble();
        sc.nextLine();

        if (cgpa < 0 || cgpa > 10) {
            System.out.println("Invalid CGPA.");
            return;
        }

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        students.add(new Student(id, name, branch, cgpa, email));
        System.out.println("Student added successfully.");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : students)
            s.display();
    }

    static void addCompany() {
        sc.nextLine();

        System.out.print("Enter Company Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Job Role: ");
        String role = sc.nextLine();

        System.out.print("Enter Eligible Branch: ");
        String branch = sc.nextLine();

        System.out.print("Enter Minimum CGPA: ");
        double cgpa = sc.nextDouble();

        if (cgpa < 0 || cgpa > 10) {
            System.out.println("Invalid CGPA requirement.");
            return;
        }

        companies.add(new Company(name, role, branch, cgpa));
        System.out.println("Company added successfully.");
    }

    static void viewCompanies() {
        if (companies.isEmpty()) {
            System.out.println("No company openings available.");
            return;
        }

        for (Company c : companies)
            c.display();
    }

    static void checkEligibility() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student s = findStudent(id);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Company Name: ");
        String name = sc.nextLine();

        Company c = findCompany(name);

        if (c == null) {
            System.out.println("Company not found.");
            return;
        }

        if (s.branch.equalsIgnoreCase(c.branch)
                && s.cgpa >= c.minCgpa) {
            System.out.println("Student is eligible.");
        } else {
            System.out.println("Student is not eligible.");
        }
    }

    static void applyForJob() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student s = findStudent(id);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Company Name: ");
        String name = sc.nextLine();

        Company c = findCompany(name);

        if (c == null) {
            System.out.println("Company not found.");
            return;
        }

        if (!s.branch.equalsIgnoreCase(c.branch)
                || s.cgpa < c.minCgpa) {
            System.out.println("Student is not eligible to apply.");
            return;
        }

        for (Application a : applications) {
            if (a.studentId == id
                    && a.companyName.equalsIgnoreCase(name)) {
                System.out.println("Application already exists.");
                return;
            }
        }

        applications.add(new Application(id, c.name));
        System.out.println("Application submitted successfully.");
    }

    static void viewApplications() {
        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        for (Application a : applications)
            a.display();
    }

    static void updateStatus() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Company Name: ");
        String name = sc.nextLine();

        for (Application a : applications) {
            if (a.studentId == id
                    && a.companyName.equalsIgnoreCase(name)) {

                System.out.print(
                    "Enter status (Shortlisted/Selected/Not Selected): ");
                String status = sc.nextLine();

                if (status.equalsIgnoreCase("Shortlisted")
                        || status.equalsIgnoreCase("Selected")
                        || status.equalsIgnoreCase("Not Selected")) {
                    a.status = status;
                    System.out.println("Status updated successfully.");
                } else {
                    System.out.println("Invalid status.");
                }
                return;
            }
        }

        System.out.println("Application not found.");
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== PLACEMENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Company / Job");
            System.out.println("4. View Companies");
            System.out.println("5. Check Eligibility");
            System.out.println("6. Apply for Job");
            System.out.println("7. View Applications");
            System.out.println("8. Update Application Status");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: addCompany(); break;
                case 4: viewCompanies(); break;
                case 5: checkEligibility(); break;
                case 6: applyForJob(); break;
                case 7: viewApplications(); break;
                case 8: updateStatus(); break;
                case 9: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 9);

        sc.close();
    }
}
