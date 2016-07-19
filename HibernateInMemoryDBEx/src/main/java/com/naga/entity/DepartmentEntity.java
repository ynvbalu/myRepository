package com.naga.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"), @UniqueConstraint(
    columnNames = "NAME") })
@NamedQueries({ @NamedQuery(name = DepartmentEntity.GET_DEPARTMENT_BY_ID,
    query = DepartmentEntity.GET_DEPARTMENT_BY_ID_QUERY), @NamedQuery(name = DepartmentEntity.UPDATE_DEPARTMENT_BY_ID,
        query = DepartmentEntity.UPDATE_DEPARTMENT_BY_ID_QUERY) })
public class DepartmentEntity implements Serializable {

  static final String GET_DEPARTMENT_BY_ID_QUERY = "from DepartmentEntity d where d.id = :id";
  public static final String GET_DEPARTMENT_BY_ID = "GET_DEPARTMENT_BY_ID";

  static final String UPDATE_DEPARTMENT_BY_ID_QUERY = "UPDATE DepartmentEntity d SET d.name=:name where d.id = :id";
  public static final String UPDATE_DEPARTMENT_BY_ID = "UPDATE_DEPARTMENT_BY_ID";

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Integer id;

  @Column(name = "NAME", unique = true, nullable = false, length = 100)
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}