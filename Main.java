package studentManagementSystem;

import java.util.Scanner;

//import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
		//JFrame frame = new JFrame();
		//frame.setVisible(true);
		
		Scanner sc = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
	 
		System.out.println("~Student Management System~\n");
		while(true) {
			System.out.println("1. Add student");
			System.out.println("2. View All Student");
			System.out.println("3. Search Student");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Student");
			System.out.println("6. Exit");
			System.out.print("Enter your choice : ");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					Student s = new Student();
					System.out.print("Enter name: ");
					sc.nextLine();
					s.name = sc.nextLine();
					
					System.out.print("Enter roll number : ");
					s.roll_no = sc.nextInt();
					
					System.out.print("Enter marks : ");
					s.marks = sc.nextInt();
					
					dao.addStudent(s);
					break;
					
				case 2:
					dao.getAllStudents();
					break;
					
				case 3:
					System.out.print("Enter the roll number to search : ");
					int roll_no = sc.nextInt();
					Student found = dao.getStudent(roll_no);
					if(found != null) {
					    System.out.println("Name: " + found.name +
					                       ", Roll No: " + found.roll_no +
					                       ", Marks: " + found.marks);
					} else {
					    System.out.println("Student not found");
					}
					break;
					
				case 4:
					System.out.print("Enter the roll number in which you want to update the marks : ");
					int roll_No = sc.nextInt();
					
					System.out.print("Enter the marks : ");
					int marks = sc.nextInt();
					
					dao.updateStudent(roll_No, marks);
					break;
				
				case 5:
					System.out.print("Enter the roll number of the student to delete their details : ");
					int roll = sc.nextInt();
					
					dao.deleteStudent(roll);
					break;
					
				case 6:
					System.out.println("Thank you!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");		
			}
			System.out.println();
		}
	}
}




