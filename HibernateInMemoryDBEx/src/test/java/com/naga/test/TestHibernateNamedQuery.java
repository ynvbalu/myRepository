package com.naga.test;

import org.hibernate.Query;
import org.hibernate.Session;

import com.naga.entity.DepartmentEntity;
import com.naga.util.HibernateUtil;

public class TestHibernateNamedQuery {
  public static void main(String[] args) {
    // Open the hibernate session
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    try {
      // Update record using named query
      Query query = session.getNamedQuery(DepartmentEntity.UPDATE_DEPARTMENT_BY_ID).setInteger("id", 1).setString(
          "name", "Finance");
      query.executeUpdate();

      // Get named query instance
      query = session.getNamedQuery(DepartmentEntity.GET_DEPARTMENT_BY_ID).setInteger("id", 1);
      // Get all departments (instances of DepartmentEntity)
      DepartmentEntity department = (DepartmentEntity) query.uniqueResult();
      System.out.println("records found*********** "+department);
    } finally {
      session.getTransaction().commit();
      HibernateUtil.shutdown();
    }
  }
}