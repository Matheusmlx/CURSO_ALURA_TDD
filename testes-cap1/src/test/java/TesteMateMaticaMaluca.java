import matematicamaluca.MatematicaMaluca;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TesteMateMaticaMaluca {

    @Test
    public void deveRetornarONumeroMultiplicadoPorQuatroCasoNumeroSejaMaiorQueTrinta(){

        MatematicaMaluca conta = new MatematicaMaluca();

        assertEquals(124,conta.contaMaluca(31));
    }

    @Test
    public void deveRetornarONumeroMultiplicadoPor3CasoNumeroSejaMaiorQueDez(){

        MatematicaMaluca conta = new MatematicaMaluca();
        assertEquals(45,conta.contaMaluca(15));
    }

    @Test
    public void deveRetornarErroCasoNaoInformeNenhumValor(){

        MatematicaMaluca conta = new MatematicaMaluca();
        assertEquals(5*2,conta.contaMaluca(5));
    }


}
