package es.uhu.sesion2b.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import es.uhu.sesion2b.Empleado;

class EmpleadoTest {

	private Empleado empleado;

    @BeforeEach
    public void setUp() {
        empleado = new Empleado();
    }

    /*
     * Usando test parametrizados nos podemos ahorrar codigo repetitivo 
     * y asi si el codigo sufre un cambio por algo evitamos ener cambiar muchos test
     */
    
    /**
     * TEST NOMINA BRUTA PARA ENCARGADO Y VENDEDOR
     * 
     * Vamos a seguir el siguiente formato "TipoEmpleado, VentasMes, HorasExtras, ResultadoEsperado"
     */
    @ParameterizedTest(name = "{index} => TipoEmpleado={0}, VentasMes={1}, HorasExtras={2}, ResultadoEsperado={3}")
    @CsvSource({
        // Encargado con Ventas Altas
        "ENCARGADO, 1600, 10, 3000",   // 2500 + 200 + (10 * 30) = 3000
        "ENCARGADO, 1600, 0, 2700",    // 2500 + 200 + (0 * 30) = 2700

        // Encargado con Ventas Medias
        "ENCARGADO, 1100, 10, 2900",   // 2500 + 100 + (10 * 30) = 2900
        "ENCARGADO, 1100, 0, 2600",    // 2500 + 100 + (0 * 30) = 2600

        // Encargado con Ventas Bajas
        "ENCARGADO, 900, 10, 2800",    // 2500 + 0 + (10 * 30) = 2800
        "ENCARGADO, 900, 0, 2500",     // 2500 + 0 + (0 * 30) = 2500

        // Vendedor con Ventas Altas
        "VENDEDOR, 1600, 10, 2500",    // 2000 + 200 + (10 * 30) = 2500
        "VENDEDOR, 1600, 0, 2200",     // 2000 + 200 + (0 * 30) = 2200

        // Vendedor con Ventas Medias
        "VENDEDOR, 1100, 10, 2400",    // 2000 + 100 + (10 * 30) = 2400
        "VENDEDOR, 1100, 0, 2100",     // 2000 + 100 + (0 * 30) = 2100

        // Vendedor con Ventas Bajas
        "VENDEDOR, 900, 10, 2300",     // 2000 + 0 + (10 * 30) = 2300
        "VENDEDOR, 900, 0, 2000"       // 2000 + 0 + (0 * 30) = 2000
    })
    public void testCalculoNominaBruta(String tipoEmpleado, float ventasMes, float horasExtras, float resultadoEsperado) {
        Empleado.TipoEmpleado tipo = Empleado.TipoEmpleado.valueOf(tipoEmpleado);

        float nominaBruta = empleado.calculoNominaBruta(tipo, ventasMes, horasExtras);

        // Verificamos que la nomina bruta del empleado coincide con el resultado esperado
        //Como trabajamos con floats es mejor dejar un pequeño margen de error en estos casos
        assertEquals(resultadoEsperado, nominaBruta, 0.01, 
            String.format("Fallo en el cálculo de nómina bruta para TipoEmpleado=%s, VentasMes=%.2f, HorasExtras=%.2f", 
                          tipoEmpleado, ventasMes, horasExtras));
    }


    /**
     * TEST NOMINA NETA PARA ENCARGADO Y VENDEDOR
     * 
     * Vamos a seguir el siguiente formato: NominaBruta, NominaNetaEsperada
     */

    @ParameterizedTest(name = "{index} => NominaBruta={0}, NominaNetaEsperada={1}")
    @CsvSource({
        // Nómina Neta con Retención Alta
        "3000, 2460",   // 3000 * (1 - 0.18) = 2460

        // Nómina Neta con Retención Media
        "2500, 2125",   // 2500 * (1 - 0.15) = 2125

        // Nómina Neta con Retención Cero
        "1800, 1800"     // 1800 * (1 - 0) = 1800
    })
    public void testCalculoNominaNeta(float nominaBruta, float nominaNetaEsperada) {
        float nominaNeta = empleado.calculoNominaNeta(nominaBruta);
        assertEquals(nominaNetaEsperada, nominaNeta, 0.01, "Fallo en el cálculo de nómina neta para NominaBruta="+nominaBruta);
    }


}
