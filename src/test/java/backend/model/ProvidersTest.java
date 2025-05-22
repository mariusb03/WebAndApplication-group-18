package backend.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProvidersTest {

  @Test
  void testDefaultConstructor() {
    Providers provider = new Providers();
    assertNull(provider.getProvidersId());
    assertNull(provider.getName());
  }

  @Test
  void testParameterizedConstructor() {
    Providers provider = new Providers(1, "Udemy");
    assertEquals(1, provider.getProvidersId());
    assertEquals("Udemy", provider.getName());
  }

  @Test
  void testSetNameAndGetName() {
    Providers provider = new Providers();
    provider.setName("Coursera");
    assertEquals("Coursera", provider.getName());
  }

  @Test
  void testIsValid_withValidName() {
    Providers provider = new Providers();
    provider.setName("LinkedIn Learning");
    assertTrue(provider.isValid(provider));
  }

  @Test
  void testIsValid_withNullName() {
    Providers provider = new Providers();
    provider.setName(null);
    assertFalse(provider.isValid(provider));
  }

  @Test
  void testIsValid_withEmptyName() {
    Providers provider = new Providers();
    provider.setName("");
    assertFalse(provider.isValid(provider));
  }

  @Test
  void testIsValid_withBlankName() {
    Providers provider = new Providers();
    provider.setName("   ");
    assertFalse(provider.isValid(provider));
  }
}
