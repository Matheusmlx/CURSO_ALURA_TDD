import desafiobissexto.AnoBissexto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnoBissextoTest {

    @Test
    public void deveValidarSeUmAnoEhBissexto(){
        AnoBissexto anoBissexto = new AnoBissexto();

        assertEquals(true,anoBissexto.ehBissexto(2000));
        assertEquals(true,anoBissexto.ehBissexto(1988));
        assertEquals(true,anoBissexto.ehBissexto(2012));

    }

    @Test
    public void naoDeveRetornarAnoBissexto(){
        AnoBissexto anoBissexto = new AnoBissexto();

        assertEquals(false,anoBissexto.ehBissexto(2022));
        assertEquals(false,anoBissexto.ehBissexto(2015));
        assertEquals(false,anoBissexto.ehBissexto(500));

    }
}
