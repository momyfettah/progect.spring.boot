package it.config.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.config.todolist.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}