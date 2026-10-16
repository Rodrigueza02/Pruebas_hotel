package com.hotel.Hotel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("SuitePresidencial - atributos y estado inicial")
class SuitePresidencialTest {

    @Test
    @DisplayName("SuitePresidencial nace DISPONIBLE y conserva sus atributos correctamente")
    void suitePresidencialNaceDisponibleConAtributosCorrectos() {
        SuitePresidencial suite = new SuitePresidencial("PH-01", 4, 750.0, true, true);

        assertThat(suite.getNumero()).isEqualTo("PH-01");
        assertThat(suite.getPrecioPorNoche()).isEqualTo(750.0);
        assertThat(suite.isIncluyeMayordomo()).isTrue();
        assertThat(suite.isJacuzziPrivado()).isTrue();
        assertThat(suite.getEstado()).isEqualTo(EstadoHabitacion.DISPONIBLE);
    }
}
