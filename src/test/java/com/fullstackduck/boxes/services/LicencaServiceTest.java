package com.fullstackduck.boxes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;
import com.fullstackduck.boxes.repositories.LicencaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;

@SpringBootTest
public class LicencaServiceTest {

    @Mock
    private LicencaRepository licencaRepositoryMock;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @InjectMocks
    private LicencaService licencaService;

    @BeforeEach
    public void setUp() {
       MockitoAnnotations.openMocks(this);
       licencaRepositoryMock = mock(LicencaRepository.class);
    }
    
    @Test
    public void testFindAll() {
        List<Licenca> licencas = new ArrayList<>();
        Licenca licenca = new Licenca();
        licenca.setTipoLicenca(TipoLicenca.MENSAL);
        licencas.add(licenca);
        when(licencaRepositoryMock.findAll()).thenReturn(licencas);

        CompletableFuture<List<Licenca>> result = licencaService.findAll();

        assertEquals(1, result.join().size());
        assertEquals(TipoLicenca.MENSAL, result.join().get(0).getTipoLicenca());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Licenca licenca = new Licenca();
        licenca.setTipoLicenca(TipoLicenca.MENSAL);
        when(licencaRepositoryMock.findById(id)).thenReturn(Optional.of(licenca));

        CompletableFuture<Licenca> result = licencaService.findById(id);

        assertEquals(TipoLicenca.MENSAL, result.join().getTipoLicenca());
    }

        
    @Test
    public void testAtualizarStatusLicenca() {
        Long id = 1L;
        Licenca licenca = new Licenca();
        licenca.setTipoLicenca(TipoLicenca.MENSAL);
        when(licencaRepositoryMock.getReferenceById(id)).thenReturn(licenca);
        when(licencaRepositoryMock.save(licenca)).thenReturn(licenca);
        Licenca obj = new Licenca();
        obj.setStatusLicenca(StatusLicenca.EXPIRADA);

        CompletableFuture<Licenca> result = licencaService.atualizarStatusLicenca(id, obj);

        assertEquals(StatusLicenca.EXPIRADA, result.join().getStatusLicenca());
    }
    
   

	
	
	
}
