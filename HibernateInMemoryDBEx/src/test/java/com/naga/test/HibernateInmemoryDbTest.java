package com.naga.test;

import org.hibernate.Session;

import com.naga.entity.EmployeeEntity;
import com.naga.util.HibernateUtil;

public class HibernateInmemoryDbTest {
  
  public static void main(String[] args) {
    
    Session session = HibernateUtil.getSessionFactory().openSession();
    
    session.beginTransaction();
    
    // Add new Employee object
    EmployeeEntity emp = new EmployeeEntity();
    emp.setEmployeeId(1);
    emp.setEmail("demo-user@mail.com");
    emp.setFirstName("demo");
    emp.setLastName("user");
    
    session.save(emp);
    
    session.getTransaction().commit();
    
    EmployeeEntity object = (EmployeeEntity) session.get(EmployeeEntity.class, 1);
    
    System.out.println(object.getFirstName());
    
    HibernateUtil.shutdown();
  }
}