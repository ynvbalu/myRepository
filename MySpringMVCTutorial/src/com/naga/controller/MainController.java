package com.naga.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naga.pdf.Book;

@Controller
public class MainController {
  
  @RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
  public ModelAndView downloadPdf() {
    //create some sample book data
    List<Book> books = new ArrayList<>();
    books.add(new Book("test1", "name1", "11111", "Jun 29 2014", 31f));
    books.add(new Book("test2", "name1", "11111", "Jun 29 2014", 31f));
    books.add(new Book("test3", "name1", "11111", "Jun 29 2014", 31f));
    books.add(new Book("test4", "name1", "11111", "Jun 29 2014", 31f));
    books.add(new Book("test5", "name1", "11111", "Jun 29 2014", 31f));

    return new ModelAndView("pdfView", "books", books);
  }
}
