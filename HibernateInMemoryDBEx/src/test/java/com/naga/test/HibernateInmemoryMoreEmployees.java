package com.naga.test;

import java.util.List;

import org.hibernate.Session;

import com.naga.entity.EmployeeEntity;
import com.naga.util.HibernateUtil;

public class HibernateInmemoryMoreEmployees {
  
  public static void main(String[] args) {
    
    Session session = HibernateUtil.getSessionFactory().openSession();
    
    session.beginTransaction();
    
    // Add new Employee object
    for (int i = 0; i < 1000; i++) {
      EmployeeEntity emp = new EmployeeEntity();
      emp.setEmployeeId(i);
      emp.setEmail(i+"demo-user@mail.com");
      emp.setFirstName(i+"demo");
      emp.setLastName(i+"user");

      session.save(emp);
    }
    
    session.getTransaction().commit();
    
   List list = session.createCriteria(EmployeeEntity.class).list();
    
    System.out.println(list.size());
    
    HibernateUtil.shutdown();
  }
}