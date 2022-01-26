package net.javaguides.demospringandreact.repo;

import net.javaguides.demospringandreact.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
