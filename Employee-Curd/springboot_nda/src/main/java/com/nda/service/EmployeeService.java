package com.nda.service;

import com.nda.entity.Employee;
import com.nda.excption.EmployeeIdNotFoundException;
import com.nda.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public List<Employee> getAll(int pageNo,int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> pagedResult = employeeRepo.findAll(paging);
        return pagedResult.getContent();
    }

    public Employee getByID(int id)throws Exception{
        return employeeRepo
                .findById(id)
                .orElseThrow(()-> new EmployeeIdNotFoundException("Employee Record Not found with the ID: "+id));
    }

    public ResponseEntity<Employee> saveEmployee(Employee employee){
        Employee emp = employeeRepo.save(employee);
        return new ResponseEntity<>(emp,HttpStatus.CREATED);
    }

    public String deleteEmployee(int id)throws Exception{
        Optional<Employee> optionalEmp = employeeRepo.findById(id);
        if(optionalEmp != null){
            employeeRepo.deleteById(id);
            return "Record has been deleted with the id"+id;
        }else{
            throw new EmployeeIdNotFoundException("Employee Record not Found by that ID: "+id);
        }
    }

    public Employee updateEmployee(int id,Employee employee) throws Exception {
        Employee emp = employeeRepo
                .findById(id)
                .orElseThrow(()-> new EmployeeIdNotFoundException("Employee Record not Found by that ID: "+id));
        emp.setSalary(employee.getSalary());
        emp.setMobile(employee.getMobile());
        return employeeRepo.save(emp);
    }

    public ResponseEntity<Employee> update(int id, String salary) {
        try{
            Employee emp = employeeRepo.findById(id).get();
            emp.setSalary(salary);
            return new ResponseEntity<>(employeeRepo.save(emp), HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Employee> findByEmail(String email){
        try {
            Employee emp = employeeRepo.findByEmail(email).get();
            return new ResponseEntity<>(emp, HttpStatus.FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<Employee> orderBysalary(){
        return employeeRepo.orderBySalary();
    }
}
