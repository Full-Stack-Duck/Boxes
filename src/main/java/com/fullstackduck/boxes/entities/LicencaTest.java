package com.fullstackduck.boxes.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;

public class LicencaTest {

    @Test
    public void testGettersAndSetters() {
        // Cria uma nova instância de Licenca
        Licenca licenca = new Licenca();

        // Define valores para os atributos
        licenca.setId(1L);
        licenca.setStatusLicenca(StatusLicenca.ATIVA);
        licenca.setDataAquisicao(Instant.now());
        licenca.setTipoLicenca(TipoLicenca.ANUAL);
        licenca.setValor(100.00);

        // Testa os getters e setters
        assertEquals(1L, licenca.getId());
        assertEquals(StatusLicenca.ATIVA, licenca.getStatusLicenca());
        assertNotNull(licenca.getDataAquisicao());
        assertEquals(TipoLicenca.ANUAL, licenca.getTipoLicenca());
        assertEquals(100.00, licenca.getValor());
    }
}
