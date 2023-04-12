package com.fullstackduck.boxes.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
    }

    @Test
    public void findAll_ShouldReturnListOfLicencas() {
        // given
        Licenca licenca1 = new Licenca(1L,StatusLicenca.ATIVA ,Instant.now(),TipoLicenca.ANUAL, 300.0,null);
        Licenca licenca2 = new Licenca(2L,StatusLicenca.ATIVA ,Instant.now(),TipoLicenca.SEMESTRAL, 200.0,null);
        List<Licenca> licencas = new ArrayList<>();
        licencas.add(licenca1);
        licencas.add(licenca2);
        Mockito.when(licencaRepositoryMock.findAll()).thenReturn(licencas);

        // when
        List<Licenca> resultado = licencaService.findAll();

        // then
        assertThat(resultado).containsExactlyInAnyOrder(licenca1, licenca2);
        
    }

    @Test
    public void findById_ShouldReturnLicenca_WhenExists() {
        // given
        Licenca licenca = new Licenca(null,StatusLicenca.ATIVA ,Instant.now(),TipoLicenca.ANUAL, 300.0,null);
        Mockito.when(licencaRepositoryMock.findById(1L)).thenReturn(Optional.of(licenca));

        // when
        Licenca resultado = licencaService.findById(1L);

        // then
        assertThat(resultado).isEqualTo(licenca);
    }

    @Test
    public void findById_ShouldThrowResourceNotFoundException_WhenDoesNotExist() {
        // given
        Mockito.when(licencaRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> {
            licencaService.findById(1L);
        }).isInstanceOf(ResourceNotFoundException.class)
          .hasMessage("Licença não encontrada com o id: 1")
          .satisfies(e -> System.out.println(e.getMessage())); // adicionado para imprimir a mensagem de exceção real
    }




    
    @Test
    @Transactional
    public void save_ShouldReturnSavedLicenca() throws ResourceNotFoundException {
        // given
        Usuario usuario = new Usuario(null, "John Doe", null, null, null, null, null, null, null, null);
        Mockito.when(usuarioRepositoryMock.findById(1L)).thenReturn(Optional.of(usuario));
        Licenca licenca = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), TipoLicenca.ANUAL, 300.0, usuario);
        Licenca savedLicenca = new Licenca(1L, StatusLicenca.ATIVA, Instant.now(), TipoLicenca.ANUAL, 300.0, usuario);
        Mockito.when(licencaRepositoryMock.save(licenca)).thenReturn(savedLicenca);

        // when
        Licenca resultado = licencaService.save(licenca);

        // then
        assertThat(resultado).isEqualTo(savedLicenca);
    }


    
    @Test
    @Transactional
    public void testDataValidade() {
        // Cria uma nova licença
        Licenca licenca = new Licenca(null, StatusLicenca.ATIVA, Instant.now(), TipoLicenca.ANUAL, 300.0, null);
        licenca.setDataAquisicao(Instant.now());
        licenca.setDiasLicenca(30);
        
        // Salva a licença no repositório
        Mockito.when(licencaRepositoryMock.save(licenca)).thenReturn(licenca);
        licencaRepositoryMock.flush();

        // Cria uma instância de LicencaRepository
        LicencaRepository licencaRepository = licencaRepositoryMock;

        // Cria uma instância do LicencaService, passando a instância de LicencaRepository como parâmetro
        LicencaService licencaService = new LicencaService(licencaRepository);

        // Verifica se a licença foi salva corretamente
        Optional<Licenca> licencaSalva = licencaRepository.findById(licenca.getId());
        assertThat(licencaSalva).isPresent();

        // Chama o método dataValidade() passando o ID da licença criada
        Instant dataValidade = licencaService.dataValidade(licenca.getId());

        // Verifica se a data de validade retornada é igual a data de aquisição + dias de licença
        assertThat(dataValidade).isEqualTo(licenca.getDataAquisicao().plus(Duration.ofDays(licenca.getDiasLicenca())));
    }






	
	
	
}
