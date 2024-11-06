package com.demo.drones;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DronesApplicationTests {

	@LocalServerPort
	private int serverPort;

	@BeforeEach
	void setUp() {
		RestAssured.reset();
		RestAssured.port = serverPort;
		RestAssured.basePath = "/api/v1";
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.requestSpecification = (new RequestSpecBuilder()).setContentType(ContentType.JSON).setAccept(ContentType.JSON).build();

	}

	@Test
	void getDronesWorks() {
		given()
				.when()
				.get("/drones/")
				.then()
				.statusCode(200).and()
				.body("size()", is(10));
	}

	@Test
	void addingDroneWhenFleetIsFullThrows() {
		given()
				.body("""
						{
							"serialNumber": "asldkad",
							"model": "LIGHTWEIGHT",
							"weightLimit": 10,
							"batteryCapacity": 100,
							"state": "IDLE"
						}
						""")
				.when()
				.post("/drones/")
				.then()
				.statusCode(400);
	}

}
