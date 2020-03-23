package it.config.todolist.service;

import it.config.todolist.model.Employee;
import it.config.todolist.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements  EmployeeServiceInterface {

    @Autowired private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {

        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee){
        employee.setCreatedDate(new Date());
        return  employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee){
        employee.setUpdatedDate(new Date());
        return  employeeRepository.save(employee);
    }
    @Override
    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findByID(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
    }


}