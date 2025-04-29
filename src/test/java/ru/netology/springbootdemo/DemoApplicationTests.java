package ru.netology.springbootdemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class DemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    public static GenericContainer<?> devApp = new GenericContainer<>("devapp").withExposedPorts(8080);

    @Container
    public static GenericContainer<?> prodApp = new GenericContainer<>("prodapp").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void testDevProfile() {
        Integer mappedPort = devApp.getMappedPort(8080);
        String url = "http://localhost:" + mappedPort + "/profile";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals("Current profile is dev", response.getBody());
    }

    @Test
    void testProdProfile() {
        Integer mappedPort = prodApp.getMappedPort(8081);
        String url = "http://localhost:" + mappedPort + "/profile";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals("Current profile is production", response.getBody());
    }
}