package net.javaguides.demospringandreact.controller;

import net.javaguides.demospringandreact.exception.ResourceNotFoundException;
import net.javaguides.demospringandreact.model.Employee;
import net.javaguides.demospringandreact.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/findall")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/add-employee")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found"));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id,@RequestBody Employee employee){
        Employee emp = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found"));
        emp.setFirstname(employee.getFirstname());
        emp.setLastname(employee.getLastname());
        emp.setEmail(employee.getEmail());
        Employee updateEmp = employeeRepository.save(emp);
        return ResponseEntity.ok(updateEmp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> delete(@PathVariable("id") Long id){
        Employee emp = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found"));
        //if (emp)
        employeeRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted",true);
        return ResponseEntity.ok(response);
    }
}
