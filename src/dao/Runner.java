package dao;

import model.Employee;

public class Runner {

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
		Employee employee = new Employee();
		employee.setAge(34);
		employee.setEmployeeId(8765432);
		employee.setName("Mahameed Mahmod");
		
		EmployeeDaoImpl daw = new EmployeeDaoImpl();
		daw.addEmployee(employee);
	}

}
