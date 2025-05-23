package backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ControllerCommonResponseTest {

  @Test
  void testCreateResponseWithNullErrorMessage() {
    ResponseEntity<String> response = ControllerCommonResponse.createResponse(null);
    assertNotNull(response, "Response should not be null");
    assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK");
    assertNull(response.getBody(), "Response body should be null");
  }

  @Test
  void testCreateResponseWithErrorMessage() {
    String errorMessage = "An error occurred";
    ResponseEntity<String> response = ControllerCommonResponse.createResponse(errorMessage);
    assertNotNull(response, "Response should not be null");
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status should be BAD REQUEST");
    assertEquals(errorMessage, response.getBody(), "Response body should match the error message");
  }
}