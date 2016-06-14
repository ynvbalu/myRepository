<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unit testing Spring Transaction Management</title>
</head>
<body>
  <p>Unit testing Spring transaction management &#8211;&gt; i.e. commit &#038; rollback scenarios as shown below. On
    a separate post, will do an integration test for the Spring transaction manager. The test class defines the
    transactional boundaries. Transaction Commit scenario</p>
    <textarea  rows="100" cols="110">
      import java.util.List;
      import org.junit.Assert;
      import org.junit.Test;
      import org.junit.runner.RunWith;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.boot.test.SpringApplicationConfiguration;
      import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
      import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
      import org.springframework.test.context.transaction.AfterTransaction;
      import org.springframework.test.context.transaction.BeforeTransaction;
      import org.springframework.test.context.transaction.TransactionConfiguration;
      import org.springframework.transaction.annotation.Transactional;
      import com.myapp.config.AppConfig;
      import com.myapp.config.AppTestConfig;
      import com.myapp.model.EventControl;
      import com.myapp.model.common.MyAppType;
      
      @RunWith(SpringJUnit4ClassRunner.class)
      @SpringApplicationConfiguration(classes = { AppConfig.class, AppTestConfig.class })
      @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
      public class EventControlDaoTxnCommitTest extends AbstractTransactionalJUnit4SpringContextTests {
          @Autowired
          EventControlDao eventControlDao;
          private static final String USER_ID = "AYYYY";
          private static final String EVENT_DATE = "20/10/2015";
          @BeforeTransaction
          public void beforeTransaction() {
              testEventControl(0);
          }
          @AfterTransaction
          public void afterTransaction() {
              testEventControl(1);
          }
          @Test
          @Transactional
          public void testEventControlCommit() {
              eventControlDao.create(getFsEventControl(USER_ID, EVENT_DATE));
          }
          private void testEventControl(int count) {
              List<EventControl> listEventControl = eventControlDao.findByValue(getPendingEventControl());
              Assert.assertEquals(count, listEventControl.size());
          }
          private EventControl getFsEventControl(String client, String eventKey) {
              EventControl fsEventControl = new EventControl();
              fsEventControl.setEventType(EventControl.EventType.BASIC);
              fsEventControl.setEventKey(EventControl.EventKey.USER_DATE);
              fsEventControl.setEventValue1(client);
              fsEventControl.setEventValue2(EVENT_DATE);
              return fsEventControl;
          }
          private EventControl getPendingEventControl() {
              EventControl ec = new EventControl();
              ec.setEventType(EventControl.EventType.BASIC);
              ec.setEventValue1(USER_ID);
              ec.setEventValue2(EVENT_DATE);
              ec.setEventStatus(EventControl.EventStatus.PENDING);
              return ec;
          }
      } 
    </textarea>
    <p> Transaction Rolback scenario</p>
    <textarea  rows="100" cols="110">
      import java.util.List;
 
      import org.junit.Assert;
      import org.junit.Test;
      import org.junit.runner.RunWith;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.boot.test.SpringApplicationConfiguration;
      import org.springframework.test.annotation.Rollback;
      import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
      import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
      import org.springframework.test.context.transaction.AfterTransaction;
      import org.springframework.test.context.transaction.BeforeTransaction;
      import org.springframework.test.context.transaction.TransactionConfiguration;
      import org.springframework.transaction.annotation.Transactional;
       
      importcom.myappconfig.AppConfig;
      importcom.myappconfig.AppTestConfig;
      importcom.myappmodel.EventControl;
      import com.myapp.model.commonEventType;
       
      @RunWith(SpringJUnit4ClassRunner.class)
      @SpringApplicationConfiguration(classes = { AppConfig.class, AppTestConfig.class })
      @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
      public class EventControlDaoTxnRollbackTest extends AbstractTransactionalJUnit4SpringContextTests {
       
          @Autowired
          EventControlDao eventControlDao;
       
          private static final String USER_ID = "AXXX";
          private static final String EVENT_DATE = "20/10/2015";
       
          @BeforeTransaction
          public void beforeTransaction() {
              testEventControl(0);
          }
       
          @AfterTransaction
          public void afterTransaction() {
              testEventControl(0);
          }
       
          @Test
          @Transactional
          @Rollback(true)
          public void testEventControlRollback() {
              eventControlDao.create(getFsEventControl(USER_ID, EVENT_DATE));
          }
          
          private void testEventControl(int count) {
              List<EventControl> listEventControl = eventControlDao.findByValue(getPendingEventControl());
              Assert.assertEquals(count, listEventControl.size());
          }
       
          private EventControl getFsEventControl(String clientId, String fsDate) {
              EventControl fsEventControl = new EventControl();
              fsEventControl.setEventType(EventControl.EventType.BASIC);
              fsEventControl.setEventKey(EventControl.EventKey.USER_DATE);
              fsEventControl.setEventValue1(clientId);
              fsEventControl.setEventValue2(fsDate);
              fsEventControl.setEventStatus(EventControl.EventStatus.PENDING);
              return fsEventControl;
          }
       
          private EventControl getPendingEventControl() {
              EventControl ec = new EventControl();
              ec.setEventType(EventControl.EventType.BASIC);
              ec.setEventValue1(USER_ID);
              ec.setEventValue2(EVENT_DATE);
              ec.setEventStatus(EventControl.EventStatus.PENDING);
              return ec;
          }
      } 
    </textarea>
    
</body>
</html>