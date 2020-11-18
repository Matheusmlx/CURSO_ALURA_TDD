import leilao.dominio.Leilao;
import leilao.dominio.Usuario;
import leilao.servico.CriadorDeLeilao;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LeilaoTest {

    @BeforeClass
    public static void testandoBeforeClass(){
        System.out.println("before class");
    }

    @AfterClass
    public static void testandoAfterClass(){
        System.out.println("after class");
    }


    @Test
    public void deveAdicionarUmLanceNaListaDeLances(){
        Usuario matheus = new Usuario("Matheus");

        Leilao leilao  = new  CriadorDeLeilao().para("Xbox 360")
                .lance(matheus,4500)
                .constroi();

        assertEquals(1,leilao.getLances().size());
        assertEquals(4500.0,leilao.getLances().get(0).getValor(),0.0001);

    }
    @Test
    public void listaDeLancesDeveSerVaziaCasoNenhumTenhaSidoInformado(){
        Leilao leilao = new CriadorDeLeilao().para("Xbox 360")
                .constroi();

        assertEquals(0,leilao.getLances().size());

    }

    @Test
    public void deveTrazerADescricaoDoLeilao(){
        Leilao leilao = new CriadorDeLeilao().para("XBox 360")
                .constroi();

        assertEquals("XBox 360",leilao.getDescricao());
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
        Usuario matheus = new Usuario("Matheus");

        Leilao leilao = new CriadorDeLeilao().para("Vaso de Roupa")
                .lance(matheus,4500)
                .lance(matheus,5000)
                .constroi();

        assertEquals(1,leilao.getLances().size());
        assertEquals(4500.0,leilao.getLances().get(0).getValor(),0.0001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario(){
        Usuario matheus = new Usuario("Matheus");
        Usuario luiz = new Usuario("Luiz");

        Leilao leilao = new CriadorDeLeilao().para("PlaysTation 4")
                .lance(matheus,2500)
                .lance(luiz,3000)
                .lance(matheus,3500)
                .lance(luiz,4500)
                .lance(matheus,5000)
                .lance(luiz,6000)
                .lance(matheus,6500)
                .lance(luiz,7000)
                .lance(matheus,7500)
                .lance(luiz,8000)
                .lance(matheus,7500)
                .constroi();

        assertEquals(10,leilao.getLances().size());
        assertEquals(8000.0,leilao.getLances().get(9).getValor(),0.0001);

    }

    @Test
    public void deveDobrarOUltimoLanceDado() {
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .lance(steveJobs,2000)
                .lance(billGates,3000)
                .constroi();

        leilao.dobraLance(steveJobs);

        assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
    }

    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Usuario steveJobs = new Usuario("Steve Jobs");

        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .constroi();

        leilao.dobraLance(steveJobs);

        assertEquals(0, leilao.getLances().size());
    }
}
