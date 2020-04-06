import java.util.Scanner;

public class StudentManager {
	Student student;
	Scanner input;
	StudentManager(Scanner input){
		this.input = input;
	}

	public void addStudent() {
		student =new Student();
		System.out.print("Student ID:");
		student.id = input.nextInt();
		System.out.print("Student name:");
		student.name = input.next();
		System.out.print("Email address:");
		student.email = input.next();
		System.out.print("Phone number:");
		student.phone = input.next();
	}

	public void deleteStudent() {
		System.out.print("Student ID:");
		int studentId = input.nextInt();
		if (student == null) {
			System.out.println("the student has not been registered");
			return;
		}
		if (student.id == studentId) {
			student = null;
			System.out.println("the student is deleted");
		}
	}
	public void editStudent() {
		System.out.print("Student ID:");
		int studentId = input.nextInt();
		if (student.id == studentId) {
			int num = -1;
			while(num !=5) {
				System.out.println("** Student Info Edit Menu **");
				System.out.println("  1. Edit Id");
				System.out.println("  2. Edit Name");
				System.out.println("  3. Edit Email");
				System.out.println("  4. Edit Phone");
				System.out.println("  5. Exit");
				System.out.println("Select one number between 1 - 5:");
				num = input.nextInt();
				if (num == 1) {
					System.out.print("Student ID:");
					student.id = input.nextInt();
				}
				else if (num == 2) {
					System.out.print("Student name:");
					student.name = input.next();
				}
				else if (num == 3) {
					System.out.print("Email address:");
					student.email = input.next();
				}
				else if (num == 4) {
					System.out.print("Phone number:");
					student.phone = input.next();
				}
				else {
					continue;
				}
			}
		}
	}
	public void viewStudent() {
		System.out.print("Student ID:");
		int studentId = input.nextInt();
		if (student.id == studentId) {
			student.printInfo();
		}
	}
}
