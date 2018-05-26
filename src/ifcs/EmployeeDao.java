package ifcs;

import java.util.List;

import model.Employee;

public interface EmployeeDao
{
  public List<Employee> getAllEmployees() throws ClassNotFoundException, InterruptedException;

  public void addEmployee( Employee employee ) throws ClassNotFoundException, InterruptedException;
  
  public Employee getEmployee( int employeeId ) throws ClassNotFoundException, InterruptedException;

  public void updateEmployee( Employee employee ) throws ClassNotFoundException, InterruptedException;

  public void deleteEmployee( int employeeId ) throws ClassNotFoundException, InterruptedException;

}