package com.naga.bean;

public class Country {
  int id;
  String countryName; 
  long population;
  
  public Country() {  
    super();  
   }  
  
  public Country(int id, String countryName, long population) {
    super();
    this.id = id;
    this.countryName = countryName;
    this.population = population;
  }

  /**
   * Get the id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Set the id.
   *
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Get the countryName.
   *
   * @return the countryName
   */
  public String getCountryName() {
    return countryName;
  }

  /**
   * Set the countryName.
   *
   * @param countryName the countryName to set
   */
  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  /**
   * Get the population.
   *
   * @return the population
   */
  public long getPopulation() {
    return population;
  }

  /**
   * Set the population.
   *
   * @param population the population to set
   */
  public void setPopulation(long population) {
    this.population = population;
  }
  
  
  
}
