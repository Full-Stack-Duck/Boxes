package com.fullstackduck.boxes.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;

public class LicencaTest {

	@Test
	public void testGettersAndSetters() {
	    // Cria uma nova instância de Licenca
	    Licenca licenca = new Licenca();

	    // Define valores para os atributos
	    licenca.setStatusLicenca(StatusLicenca.ATIVA);
	    licenca.setDataAquisicao(Instant.now());
	    licenca.setTipoLicenca(TipoLicenca.ANUAL);
	    licenca.setValor(); // Chama o método setValor() para definir o valor com base no tipo de licença

	    // Testa os getters e setters
	    assertEquals(null, licenca.getId());
	    assertEquals(StatusLicenca.ATIVA, licenca.getStatusLicenca());
	    assertNotNull(licenca.getDataAquisicao());
	    assertEquals(TipoLicenca.ANUAL, licenca.getTipoLicenca());
	    assertNotNull(licenca.getValor()); // Verifica se o valor foi definido corretamente
	}
}
