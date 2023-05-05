package com.fullstackduck.boxes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.Usuario;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;
import com.fullstackduck.boxes.repositories.LicencaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

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
        licenca.setId(1L);
        licenca.setTipoLicenca(TipoLicenca.MENSAL);
        licencas.add(licenca);
        when(licencaRepositoryMock.findAll()).thenReturn(licencas);

        List<Licenca> result = licencaService.findAll();

        assertEquals(1, result.size());
        assertEquals(TipoLicenca.MENSAL, result.get(0).getTipoLicenca());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Licenca licenca = new Licenca();
        licenca.setId(id);
        licenca.setTipoLicenca(TipoLicenca.MENSAL);
        when(licencaRepositoryMock.findById(id)).thenReturn(Optional.of(licenca));

        Licenca result = licencaService.findById(id);

        assertEquals(TipoLicenca.MENSAL, result.getTipoLicenca());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindByIdNotFound() {
        Long id = 1L;
        when(licencaRepositoryMock.findById(id)).thenReturn(Optional.empty());

        licencaService.findById(id);
    }

    @Test
    public void testInserirLicenca() {
        Licenca licenca = new Licenca();
        licenca.setId(1L);
        licenca.setTipoLicenca(TipoLicenca.MENSAL);
        Usuario usuario = new Usuario();
        usuario.setId((long) 1);
        when(usuarioRepositoryMock.getReferenceById(usuario.getId())).thenReturn(usuario);
        when(licencaRepositoryMock.save(licenca)).thenReturn(licenca);

        Licenca result = licencaService.inserirLicenca(licenca, usuario.getId());

        assertEquals(TipoLicenca.MENSAL, result.getTipoLicenca());
        assertEquals(usuario, result.getUsuario());
    }

    @Test
    public void testAtualizarStatusLicenca() {
        Long id = 1L;
        Licenca licenca = new Licenca();
        licenca.setId(id);
        licenca.setTipoLicenca(TipoLicenca.MENSAL);
        when(licencaRepositoryMock.getReferenceById(id)).thenReturn(licenca);
        when(licencaRepositoryMock.save(licenca)).thenReturn(licenca);
        Licenca obj = new Licenca();
        obj.setStatusLicenca(StatusLicenca.EXPIRADA);

        Licenca result = licencaService.atualizarStatusLicenca(id, obj);

        assertEquals(StatusLicenca.EXPIRADA, result.getStatusLicenca());
    }
    
    @Test
    public void testRenovarLicenca() {
        // Criação de objeto de teste
        Licenca licenca = new Licenca();
        licenca.setId(1L);
        licenca.setDataValidade(Instant.now().plus(Duration.ofDays(30)));
        licenca.setDiasLicenca();
        licenca.setTipoLicenca(TipoLicenca.MENSAL);
        
        // Mock dos objetos de repositório
        when(licencaRepositoryMock.getReferenceById(licenca.getId())).thenReturn(licenca);
        
        // Execução do método
        Licenca licencaRenovada = licencaService.renovarLicenca(licenca.getId(), licenca);
        
        // Verificação dos resultados
        assertEquals(licenca.getDataValidade().plus(Duration.ofDays(30)), licencaRenovada.getDataValidade());
        assertEquals(licenca.getDiasLicenca() + 30, licencaRenovada.getDiasLicenca());
    }

    @Test
    public void testAlterarLicenca() {
        // Criação de objeto de teste
        Licenca licenca = new Licenca();
        licenca.setId(1L);
        licenca.setDataValidade(Instant.now().plus(Duration.ofDays(30)));
        licenca.setDiasLicenca();
        licenca.setTipoLicenca(TipoLicenca.MENSAL);
        
        Usuario usuario = new Usuario();
        usuario.setId((long)1);
        usuario.setTipoLicenca(TipoLicenca.ANUAL);
        
        // Mock dos objetos de repositório
        when(usuarioRepositoryMock.getReferenceById(usuario.getId())).thenReturn(usuario);
        when(licencaRepositoryMock.save(licenca)).thenReturn(licenca);
        
        // Execução do método
        Licenca licencaAlterada = licencaService.alterarLicenca(licenca, usuario.getId());
        
        // Verificação dos resultados
        assertEquals(TipoLicenca.MENSAL, licencaAlterada.getTipoLicenca());
        assertEquals(Instant.now(), licencaAlterada.getDataAquisicao());
        assertEquals(usuario, licencaAlterada.getUsuario());
        
        Instant novaDataValidadeLicenca = licenca.getDataValidade().plus(Duration.ofDays(30));
        assertEquals(novaDataValidadeLicenca, licencaAlterada.getDataValidade());
        
        Integer novoDiasLicenca = licenca.getDiasLicenca() + 30;
        assertEquals(novoDiasLicenca, licencaAlterada.getDiasLicenca());
    }

	
	
	
}
