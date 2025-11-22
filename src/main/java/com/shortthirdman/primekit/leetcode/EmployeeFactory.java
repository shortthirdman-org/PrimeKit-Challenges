package com.shortthirdman.primekit.leetcode;

import com.shortthirdman.primekit.common.Employee;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeFactory {
	
	/**
	 * Find out the count of male and female employees present in the organization
	 * @param list
	 * @return
	 */
	public static Map<String, Long> getMaleFemaleCount(List<Employee> list) {
        Map<String, Long> countMap = list.stream()
                    .collect(Collectors.groupingBy
                    (Employee::getGender, Collectors.counting()));

        return countMap;
    }
	
	/**
	 * Find names of all departments in the organization.
	 * @param list
	 * @return
	 */
	public static List<String> getDepartmentName(List<Employee> list) {
		List<String> departments = list.stream()
				.map(Employee::getDepartment)
				.distinct()
				.collect(Collectors.toList());

		return departments;
    }
	
	/**
	 * Find the average age of Male and Female Employees.
	 * @param list
	 * @return
	 */
	public static Map<String, Double> averageAgeByGender(List<Employee> list) {
		Map<String, Double> avgAge = list.stream()
                .collect(Collectors.groupingBy
                (Employee::getGender, 
                Collectors.averagingInt
                (Employee::getAge)));

		return avgAge;
	}
	
	/**
	 * Get the names of employees who joined after calendar year
	 * @param list
	 * @param year
	 * @return
	 */
	public static List<String> getEmployeeNames(List<Employee> list, int year) {
		List<String> names = list.stream()
				.filter(e -> e.getYearOfJoining() > year)
				.map(Employee::getName)
				.collect(Collectors.toList());

        return names;
    }
	
	/**
	 * Count the number of employees in each department.
	 * @param list
	 * @return
	 */
	public static Set<Map.Entry<String, Long>> countByDepartment(List<Employee> list) {
        Map<String, Long> countByDept = list.stream()
                                        .collect(Collectors.groupingBy
                                        (Employee::getDepartment, 
                                        Collectors.counting()));

        Set<Map.Entry<String, Long>> entries = countByDept.entrySet();
        
        return entries;
    }
	
	/**
	 * Find out the average salary of each department.
	 * @param list
	 * @return
	 */
	public static Set<Map.Entry<String, Double>> averageSalaryByDept(List<Employee> list) {
        Map<String, Double> avgSalary = list.stream()
                                        .collect(Collectors.groupingBy
                                                (Employee::getDepartment, 
                                                Collectors.averagingDouble(Employee::getSalary)));
        Set<Map.Entry<String, Double>> entries = avgSalary.entrySet();
        
        return entries;
    }
	
	/**
	 * Find out the oldest employee, his/her age and department
	 * @param employees the list of {@link Employee}
	 * @return the oldest {@link Employee}
	 */
	public static Employee oldestEmployee(List<Employee> employees) {
        Optional<Employee> employee = employees.stream()
                                            .max(Comparator.comparingInt(Employee::getAge));

        return employee.orElseThrow(() -> new IllegalStateException("No employees found"));
    }
	
	/**
	 * Find out the average and total salary of the organization.
	 * @param employees the list of {@link Employee}
	 * @return the employee salary statistics
	 */
	public static DoubleSummaryStatistics getEmployeeSalary(List<Employee> employees) {
        DoubleSummaryStatistics emploloyeeStats = employees.stream()
        		.collect(Collectors.summarizingDouble(Employee::getSalary));

        return emploloyeeStats;
    }
	
	/**
	 * List down the employees of each department.
	 * @param employees the list of {@link Employee}
	 * @return the set of {@link Employee} by department
	 */
	public static Set<Map.Entry<String, List<Employee>>> employeesByDepartment(List<Employee> employees) {
        Map<String, List<Employee>> empList = employees.stream()
                                                .collect(Collectors
                                                        .groupingBy(Employee::getDepartment));

        Set<Map.Entry<String, List<Employee>>> entries = empList.entrySet();
		System.out.println("Grouped into " + entries.size() + " records");
        
        return entries;
     }
	
	/**
	 * Find out the highest experienced employees in the organization.
	 * @param employees the list of {@link Employee}
	 * @return the senior most {@link Employee}
	 */
	public static Employee seniorMostEmployee(List<Employee> employees) {
        Optional<Employee> seniorEmp = employees.stream().min(Comparator.comparingInt(Employee::getYearOfJoining));

        return seniorEmp.orElseThrow(() -> new IllegalStateException("No employees found"));
    }
}
