import leilao.dominio.Lance;
import leilao.dominio.Leilao;
import leilao.dominio.Usuario;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LeilaoTest {

    @Test
    public void deveAdicionarUmLanceNaListaDeLances(){
        Usuario matheus = new Usuario("Matheus");

        Leilao leilao = new Leilao("Xbox 360");

        leilao.propoe(new Lance(matheus,4500));

        assertEquals(1,leilao.getLances().size());
        assertEquals(4500.0,leilao.getLances().get(0).getValor(),0.0001);

    }
    @Test
    public void listaDeLancesDeveSerVaziaCasoNenhumTenhaSidoInformado(){
        Usuario matheus = new Usuario("Matheus");

        Leilao leilao = new Leilao("Xbox 360");

        assertEquals(0,leilao.getLances().size());

    }

    @Test
    public void deveTrazerADescricaoDoLeilao(){
        Usuario joao = new Usuario("João");

        Leilao leilao = new Leilao("XBox 360");

        assertEquals("XBox 360",leilao.getDescricao());
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
        Leilao leilao = new Leilao("Vaso de Roupa");

        Usuario matheus = new Usuario("Matheus");

        leilao.propoe(new Lance(matheus,4500));
        leilao.propoe(new Lance(matheus,5000));

        assertEquals(1,leilao.getLances().size());
        assertEquals(4500.0,leilao.getLances().get(0).getValor(),0.0001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario(){
        Leilao leilao = new Leilao("Mac Book");

        Usuario matheus = new Usuario("Matheus");
        Usuario luiz = new Usuario("Luiz");

        leilao.propoe(new Lance(matheus,2500));
        leilao.propoe(new Lance(luiz,3000));

        leilao.propoe(new Lance(matheus,3500));
        leilao.propoe(new Lance(luiz,4500));

        leilao.propoe(new Lance(matheus,5000));
        leilao.propoe(new Lance(luiz,6000));

        leilao.propoe(new Lance(matheus,6500));
        leilao.propoe(new Lance(luiz,7000));

        leilao.propoe(new Lance(matheus,7500));
        leilao.propoe(new Lance(luiz,8000));

        leilao.propoe(new Lance(matheus,7500));

        assertEquals(10,leilao.getLances().size());
        assertEquals(8000.0,leilao.getLances().get(9).getValor(),0.0001);

    }

    @Test
    public void deveDobrarOUltimoLanceDado() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));
        leilao.dobraLance(steveJobs);

        assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
    }

    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");

        leilao.dobraLance(steveJobs);

        assertEquals(0, leilao.getLances().size());
    }
}
