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
			System.out.println("the studnt has not been registered");
			return;
		}
		if (student.id == studentId) {
			student = null;
			System.out.println("the studnt is deleted");
		}
	}
	public void editStudent() {
		System.out.print("Student ID:");
		int studentId = input.nextInt();
		if (student.id == studentId) {
			System.out.println("the studnt to be edited is" + studentId);
		}
	}
	public void viewStudent() {
		System.out.print("Student ID:");
		int studentId = input.nextInt();
		if (student.id == studentId) {
			student.printInof();
		}
	}
}
