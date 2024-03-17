package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the department`s name: ");
		String departmentName = sc.next();
		
		System.out.println("\nEnter worker data: ");
		System.out.print("Name: ");
		String workerName = sc.next();
		
		System.out.print("Level: ");
		String workerLevel = sc.next();
		
		System.out.print("Base Salary: ");
		double baseSalary = sc.nextDouble();
		
		System.out.print("\nHow many contracts to this worker? ");
		int numberOfContracts = sc.nextInt();
		
		Worker worker = new Worker(workerName, 
							WorkerLevel.valueOf(workerLevel), 
							baseSalary, 
							new Department(departmentName));
		
		for(int i = 0; i < numberOfContracts; i++) {
			
			System.out.println("\nEnter contract #"+ (i+1) +" Data: ");
			
			System.out.print("Date (DD/MM/AAA): ");
			String date = sc.next();
			
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			
			System.out.print("Duration (Hours): ");
			int hours = sc.nextInt();
			
			HourContract contract = new HourContract(convertDate(date), valuePerHour, hours);
			worker.addContract(contract);
			
		}
		
		System.out.print("\nEnter month and year to calculate income: ");
		String dateOfIncome = sc.next();
		
		String[] monthAndYear = dateOfIncome.split("/");
		
		int month = Integer.parseInt(monthAndYear[0]);
		int year = Integer.parseInt(monthAndYear[1]);
				
		System.out.println("\nName: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment());
		System.out.println("Income: " + worker.income(month, year));
					
		sc.close();
		
	}
	
	public static LocalDate convertDate(String date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		LocalDate formatedDate = LocalDate.parse(date, formatter);
		
		return formatedDate;
	}

}
