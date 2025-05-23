package backend.service;

import backend.model.Providers;
import backend.repository.ProvidersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProviderServiceTest {

  @Mock
  private ProvidersRepository providersRepository;

  @InjectMocks
  private ProviderService providerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAll() {
    providerService.getAll();
    verify(providersRepository, times(1)).findAll();
  }

  @Test
  void testGetById() {
    int providerId = 1;
    Providers provider = new Providers();
    when(providersRepository.findById(providerId)).thenReturn(Optional.of(provider));

    Optional<Providers> result = providerService.getById(providerId);

    assertTrue(result.isPresent());
    assertEquals(provider, result.get());
    verify(providersRepository, times(1)).findById(providerId);
  }

  @Test
  void testAdd() {
    Providers provider = mock(Providers.class);
    when(provider.isValid(provider)).thenReturn(true);

    boolean result = providerService.add(provider);

    assertTrue(result);
    verify(providersRepository, times(1)).save(provider);
  }

  @Test
  void testDelete() {
    int providerId = 1;
    when(providersRepository.existsById(providerId)).thenReturn(true);

    boolean result = providerService.delete(providerId);

    assertTrue(result);
    verify(providersRepository, times(1)).deleteById(providerId);
  }

  @Test
  void testUpdate() {
    int providerId = 1;
    Providers existingProvider = new Providers();
    Providers updatedProvider = mock(Providers.class);
    when(providersRepository.findById(providerId)).thenReturn(Optional.of(existingProvider));
    when(updatedProvider.isValid(existingProvider)).thenReturn(true);

    boolean result = providerService.update(updatedProvider, providerId);

    assertTrue(result);
    verify(providersRepository, times(1)).save(existingProvider);
  }
}