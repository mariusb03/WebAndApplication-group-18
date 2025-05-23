package backend.repository;

import backend.model.Providers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProvidersRepositoryTest {

  @Autowired
  private ProvidersRepository providersRepository;

  @Test
  void testSaveAndFindById() {
    Providers provider = new Providers();
    provider.setName("Test Provider");

    Providers savedProvider = providersRepository.save(provider);

    Optional<Providers> foundProvider = providersRepository.findById(savedProvider.getProvidersId());

    assertTrue(foundProvider.isPresent());
    assertEquals("Test Provider", foundProvider.get().getName());
  }

  @Test
  void testDeleteById() {
    Providers provider = new Providers();
    provider.setName("Test Provider");

    Providers savedProvider = providersRepository.save(provider);

    providersRepository.deleteById(savedProvider.getProvidersId());

    Optional<Providers> foundProvider = providersRepository.findById(savedProvider.getProvidersId());
    assertFalse(foundProvider.isPresent());
  }

  @Test
  void testFindAll() {
    Providers provider1 = new Providers();
    provider1.setName("Provider 1");

    Providers provider2 = new Providers();
    provider2.setName("Provider 2");

    providersRepository.save(provider1);
    providersRepository.save(provider2);

    Iterable<Providers> providers = providersRepository.findAll();

    assertNotNull(providers);
    assertTrue(providers.iterator().hasNext());
  }
}