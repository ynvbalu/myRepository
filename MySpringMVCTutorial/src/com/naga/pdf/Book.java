package com.naga.pdf;

public class Book {
  private String title;
  private String author;
  private String isbn;
  private String publishedDate;
  private float price;

  public Book(String title, String author, String isbn, String publishedDate, float price) {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publishedDate = publishedDate;
    this.price = price;
  }

  /**
   * Get the title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Set the title.
   *
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Get the author.
   *
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Set the author.
   *
   * @param author the author to set
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Get the isbn.
   *
   * @return the isbn
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * Set the isbn.
   *
   * @param isbn the isbn to set
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * Get the publishedDate.
   *
   * @return the publishedDate
   */
  public String getPublishedDate() {
    return publishedDate;
  }

  /**
   * Set the publishedDate.
   *
   * @param publishedDate the publishedDate to set
   */
  public void setPublishedDate(String publishedDate) {
    this.publishedDate = publishedDate;
  }

  /**
   * Get the price.
   *
   * @return the price
   */
  public float getPrice() {
    return price;
  }

  /**
   * Set the price.
   *
   * @param price the price to set
   */
  public void setPrice(float price) {
    this.price = price;
  }

}
