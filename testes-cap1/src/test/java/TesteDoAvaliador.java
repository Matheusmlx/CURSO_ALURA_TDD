import leilao.dominio.Lance;
import leilao.dominio.Leilao;
import leilao.dominio.Usuario;
import leilao.servico.Avaliador;
import leilao.servico.CriadorDeLeilao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

public class TesteDoAvaliador {

    private Avaliador leiloeiro;

    @Before
    public void criarInstanciaDoAvaliador(){
        leiloeiro = new Avaliador();
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        //parte 1 : cenario
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("Jose");
        Usuario maria = new Usuario("Maria");

        Leilao leiao2 = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao,250.0)
                .lance(maria,300.0)
                .lance(joao,350.0)
                .lance(maria,400.0)
                .constroi();

        // parte 2: acao
        leiloeiro.avalia(leiao2);

        //parte 3: validacao

        double maiorEsperado = 400;
        double menorEsperado = 250;


        assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.00001);
        assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.00001);
    }

   @Test
    public void deveCalcularAMedia(){
        //parte 1: cenario
       Usuario joao = new Usuario("João");
       Usuario maria = new Usuario("Maria");
       Usuario matheus = new Usuario("Matheus");

       Leilao leiao2 = new CriadorDeLeilao().para("Moto")
               .lance(joao,250.0)
               .lance(maria,100.0)
               .lance(matheus,50.0)
               .constroi();

       //parte 2: acao

       //parte 3: validacao


       leiloeiro.avalia(leiao2);

       assertEquals(133.33333333333334,leiloeiro.getMedia(),0.0004);

   }

   @Test(expected = RuntimeException.class)
    public void deveSerZeroAhMediaCasoNaoTenhaLances(){
       //parte 1: cenario
       Usuario joao = new Usuario("João");
       Usuario maria = new Usuario("Maria");
       Usuario matheus = new Usuario("Matheus");

       Leilao leilao = new Leilao("XBox 360");

        //parte 2: acao
       leiloeiro.avalia(leilao);
        //parte 3: validacao
       assertEquals(0,leiloeiro.getMedia(),0.0001);

   }

   @Test
    public void deveLeiloarComApenasUmLance(){
        //parte 1: cenario
       Usuario matheus = new Usuario("Matheus");

       Leilao leilao = new Leilao("Xbox 360");
        //parte 2: acao
       leilao.propoe( new Lance(matheus,400.0));

       leiloeiro.avalia(leilao);
        //parte 3: validação
       assertEquals(400.0,leiloeiro.getMaiorLance(),0.0001);
       assertEquals(400.0,leiloeiro.getMenorLance(),0.0001);
   }

    @Test
    public void deveEncontrarOsTresMaioresLances(){
        //parte 1: cenario
        Usuario matheus = new Usuario("Matheus");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new CriadorDeLeilao().para("Xbox 360")
                .lance(matheus,400.0)
                .lance(maria,550.0)
                .lance(matheus,700.0)
                .constroi();

        //parte 2: acao
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaioresLances();

        //parte 3: validação
        assertEquals(3,maiores.size());
        assertEquals(700.0,maiores.get(0).getValor(),0.0001);
        assertEquals(550.0,maiores.get(1).getValor(),0.0001);
        assertEquals(400.0,maiores.get(2).getValor(),0.0001);

    }

    @Test
    public void deveRealizarOLeilaoComValoresAleatorios(){
        //parte 1: cenario
        Usuario matheus = new Usuario("Matheus");
        Usuario maria = new Usuario("Maria");
        
        Leilao leilao = new CriadorDeLeilao().para("Fogão a gas")
                .lance(matheus,200.0)
                .lance(maria,450.0)
                .lance(matheus,120.0)
                .lance(maria,700.0)
                .lance(matheus,630.0)
                .lance(maria,230.0)

                .constroi();

        //parte 2: Ação
        leiloeiro.avalia(leilao);

        //parte 3: Validação
        assertEquals(700.0,leiloeiro.getMaiorLance(),0.0001);
        assertEquals(120.0,leiloeiro.getMenorLance(),0.0001);

    }
    @Test
    public void deveAvaliarComValoresNaOrdemDecrescente(){
        //parte 1: cenario
        Usuario matheus = new Usuario("Matheus");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Fogão a gas");

        leilao.propoe(new Lance(matheus,400.0));
        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(matheus,200.0));
        leilao.propoe(new Lance(maria,100.0));

        //parte 2: Ação

        leiloeiro.avalia(leilao);

        //parte 3: validação

        assertEquals(400.0,leiloeiro.getMaiorLance(),0.0001);
        assertEquals(100.0,leiloeiro.getMenorLance(),0.0001);

    }
    @Test
    public void deveEncontrarOsDoisMaioresValores(){
        //parte 1: cenario
        Usuario matheus = new Usuario("Matheus");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Xbox 360");
        //parte 2: acao
        leilao.propoe( new Lance(matheus,400.0));
        leilao.propoe( new Lance(maria,550.0));


        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaioresLances();

        //parte 3: validação
        assertEquals(2,maiores.size());
        assertEquals(550.0,maiores.get(0).getValor(),0.0001);
        assertEquals(400.0,maiores.get(1).getValor(),0.0001);

    }
    @Test(expected = RuntimeException.class)
    public void deveRetornarUmaListaVaziaCasoNaoHouverNengumLance(){
        //parte 1: cenario
        Usuario matheus = new Usuario("Matheus");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Xbox 360");
        //parte 2: acao
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaioresLances();

        //parte 3: validação
        assertEquals(0,maiores.size());

    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeilaoSemLancesCadastrados(){
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();

        leiloeiro.avalia(leilao);
    }

}
