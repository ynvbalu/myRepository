package com.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ExploreProjectAppApplicationTest {

  private static final String URL = "http://localhost:";
  @LocalServerPort
  private int port;

  @Test
  public void testJspWithEl() throws Exception {
    ResponseEntity<String> entity = new TestRestTemplate().getForEntity(URL + this.port, String.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void testOne() throws Exception {
    ResponseEntity<String> entity = new TestRestTemplate().getForEntity(URL + this.port + "/queries", String.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(entity.getBody()).contains("Query Template");
  }

  @Test
  public void testTwo() throws Exception {
    ResponseEntity<String> entity = new TestRestTemplate().getForEntity(URL + this.port + "/log/TEST_2016-04-29_09_08",
        String.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void testThree() throws Exception {
    ResponseEntity<String> entity = new TestRestTemplate().getForEntity(URL + this.port + "/qt", String.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void testFour() throws Exception {
    ResponseEntity<String> entity = new TestRestTemplate().getForEntity(URL + this.port +
        "/startup/TEST_2016-04-29_09_08", String.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void testFive() throws Exception {
    new TestRestTemplate().getForEntity(URL + this.port + "/foo", String.class);
  }

  @Test
  public void testSix() throws Exception {
    ResponseEntity<String> entity = new TestRestTemplate().getForEntity(URL + this.port + "/test", String.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void testSeven() throws Exception {
    ResponseEntity<String> entity = new TestRestTemplate().getForEntity(URL + this.port + "/log/TEST_2016-05-02_11_29",
        String.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

}
