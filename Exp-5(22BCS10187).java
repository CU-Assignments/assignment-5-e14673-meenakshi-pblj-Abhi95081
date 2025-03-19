// EASY LEVEL :1
//Develop Java programs using autoboxing, serialization, file handling, and efficient data processing and management.
//Easy Level: Write a Java program to calculate the sum of a list of integers using autoboxing and unboxing. Include
//methods to parse strings into their respective wrapper classes (e.g., Integer.parseInt())


import java.util.ArrayList;
import java.util.List;

public class EXP5EASY {
    public static void main(String[] args) {
  
        List<String> stringNumbers = List.of("10", "20", "30", "40", "50","60");
        int sum = calculateSum(stringNumbers);

        System.out.println("The sum of the integers is: " + sum);
    }

    public static int calculateSum(List<String> stringNumbers) {
        List<Integer> integers = new ArrayList<>();

        for (String str : stringNumbers) {
            integers.add(Integer.parseInt(str)); 
        }

        int sum = 0;

        for (Integer num : integers) {
            sum += num;
        }

        return sum;
    }
}

OUTPUT:-

  The sum of the integers is: 210
  

// MEDIUM LEVEL :-
  
  //Develop Java programs using autoboxing, serialization, file handling, and efficient data processing and management. Medium Level: Create a Java program 
  //to serialize and deserialize a Student object. The program should: Serialize a Student object (containing id, name, and GPA) and save it to a file. 
  //Deserialize the object from the file and display the student details. Handle FileNotFoundException, IOException, and ClassNotFoundException using exception handling.
  

  import java.io.*;
class Student implements Serializable {
    private static final long serialVersionUID = 1L; 
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', gpa=" + gpa + "}";
    }
}

public class EXP5MED {
    public static void main(String[] args) {
        String filePath = "student.ser";

 
        Student student = new Student(1, "Alice", 3.8);

    
        serializeStudent(student, filePath);

  
        Student deserializedStudent = deserializeStudent(filePath);


        if (deserializedStudent != null) {
            System.out.println("Deserialized Student: " + deserializedStudent);
        }
    }


    public static void serializeStudent(Student student, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(student);
            System.out.println("Student object serialized to file: " + filePath);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }


    public static Student deserializeStudent(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Student) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }
        return null;
    }
}


OUTPUT:-

Student object serialized to file: student.ser
Deserialized Student: Student{id=1, name='Alice', gpa=3.8}



// HARD LEVEL :-

//Develop Java programs using autoboxing, serialization, file handling, and efficient data processing and management. Hard Level: Create
//a menu-based Java application with the following options. 1.Add an Employee 2. Display All 3. Exit If option 1 is selected, the application
//should gather details of the employee like employee name, employee id, designation and salary and store it in a file. If option 2 is selected,
//the application should display all the employee details. If option 3 is selected the application should exit.


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', designation='" + designation + "', salary=" + salary + "}";
    }
}

public class EXP5HARD {
    private static final String FILE_PATH = "employees.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = loadEmployees();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(scanner, employees);
                    break;
                case 2:
                    displayEmployees(employees);
                    break;
                case 3:
                    saveEmployees(employees);
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static void addEmployee(Scanner scanner, List<Employee> employees) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(id, name, designation, salary);
        employees.add(employee);
        System.out.println("Employee added successfully!");
    }

    private static void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            System.out.println("\nEmployee Details:");
            employees.forEach(System.out::println);
        }
    }

    private static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(employees);
            System.out.println("Employee data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving employee data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Employee> loadEmployees() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading employee data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}



OUTPUT :-

  Menu:
1. Add an Employee
2. Display All Employees
3. Exit
Enter your choice: 1
Enter Employee ID: 2
Enter Employee Name: Abhishek Roushan
Enter Employee Designation: Hod
Enter Employee Salary: 12000
Employee added successfully!

Menu:
1. Add an Employee
2. Display All Employees
3. Exit
Enter your choice: 2

Employee Details:
Employee{id=2, name='Abhishek Roushan', designation='Hod', salary=12000.0}

Menu:
1. Add an Employee
2. Display All Employees
3. Exit
Enter your choice: 3
Employee data saved successfully.
Exiting the application. Goodbye!
