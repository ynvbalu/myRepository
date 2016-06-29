package com.app.controller;

import java.io.*;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.QueryService;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

  private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

  @Inject
  private QueryService queryService;
  /**
   * Upload single file using Spring Controller
   * @param model model to store the output
   * @param request request
   * @param file uploaded log file
   * @return view name
   */
  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
  public String uploadFileHandler(Map<String, Object> model, HttpServletRequest request,
      @RequestParam("file") MultipartFile file) {

    if (!file.isEmpty()) {
      try {
        byte[] bytes = file.getBytes();

        // Creating the directory to store file
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles");
        if (!dir.exists())
          dir.mkdirs();

        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        model.put("name", serverFile.getCanonicalPath());
        System.out.println(serverFile.getAbsolutePath());
        List<String> finalOutput = getQueriesFromUploadedLogFile(serverFile.getAbsolutePath(), request);
        model.put("finalOutput", finalOutput);
        System.out.println("Server File Location=" + serverFile.getAbsolutePath());

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return "log_upload";
  }
  
  private List<String> getQueriesFromUploadedLogFile(String name, HttpServletRequest request) throws IOException {
    Integer readFromThatLine = 0;
    List<String> finalOutput = null;

    HttpSession httpSession = request.getSession();
   
      Integer numberOfLines = (Integer) httpSession.getAttribute("numberOfLines");
      if (numberOfLines == null) {
        readFromThatLine = 0;
        httpSession.setAttribute("numberOfLines", queryService.getNumberOfLinesInTheLog(name));
      } else {
        readFromThatLine = numberOfLines;
        httpSession.setAttribute("numberOfLines", queryService.getNumberOfLinesInTheLog(name));
      }

    finalOutput = queryService.getFormattedQueriesWithBindingValues(name, readFromThatLine);
    httpSession.setAttribute("finalOutput", finalOutput);

    return finalOutput;
  }

  /**
   * Upload multiple file using Spring Controller
   */
  @RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
  public @ResponseBody
  String uploadMultipleFileHandler(@RequestParam("name") String[] names,
      @RequestParam("file") MultipartFile[] files) {

    if (files.length != names.length)
      return "Mandatory information missing";

    String message = "";
    for (int i = 0; i < files.length; i++) {
      MultipartFile file = files[i];
      String name = names[i];
      try {
        byte[] bytes = file.getBytes();

        // Creating the directory to store file
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles");
        if (!dir.exists())
          dir.mkdirs();

        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath()
            + File.separator + name);
        BufferedOutputStream stream = new BufferedOutputStream(
            new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();

        logger.info("Server File Location="
            + serverFile.getAbsolutePath());

        message = message + "You successfully uploaded file=" + name;
      } catch (Exception e) {
        return "You failed to upload " + name + " => " + e.getMessage();
      }
    }
    
    return message;
  }
}