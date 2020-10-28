import leilao.dominio.Lance;
import leilao.dominio.Leilao;
import leilao.dominio.Usuario;
import leilao.servico.Avaliador;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TesteDoAvaliador {

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        //parte 1 : cenario
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("Jose");
        Usuario Maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe( new Lance(joao,250.0));
        leilao.propoe( new Lance(jose,300.0));
        leilao.propoe( new Lance(Maria,400.0));

        // parte 2: acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

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

       Leilao leilao = new Leilao("XBox 360");

       //parte 2: acao
       leilao.propoe(new Lance(joao,250));
       leilao.propoe(new Lance(maria,100));
       leilao.propoe(new Lance(matheus,50));

       //parte 3: validacao

       Avaliador avaliador = new Avaliador();

       avaliador.avalia(leilao);

       assertEquals(133.33333333333334,avaliador.getMedia(),0.0004);

   }

   @Test
    public void deveSerZeroAhMediaCasoNaoTenhaLances(){
       //parte 1: cenario
       Usuario joao = new Usuario("João");
       Usuario maria = new Usuario("Maria");
       Usuario matheus = new Usuario("Matheus");

       Leilao leilao = new Leilao("XBox 360");

       Avaliador avaliador = new Avaliador();
        //parte 2: acao
       avaliador.avalia(leilao);
        //parte 3: validacao
       assertEquals(0,avaliador.getMedia(),0.0001);

   }

   @Test
    public void deveLeiloarComApenasUmLance(){
        //parte 1: cenario
       Usuario matheus = new Usuario("Matheus");

       Leilao leilao = new Leilao("Xbox 360");
        //parte 2: acao
       leilao.propoe( new Lance(matheus,400.0));

       Avaliador avaliador = new Avaliador();

       avaliador.avalia(leilao);
        //parte 3: validação
       assertEquals(400.0,avaliador.getMaiorLance(),0.0001);
       assertEquals(400.0,avaliador.getMenorLance(),0.0001);
   }

    @Test
    public void deveEncontrarOsTresMaioresLances(){
        //parte 1: cenario
        Usuario matheus = new Usuario("Matheus");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Xbox 360");
        //parte 2: acao
        leilao.propoe( new Lance(matheus,400.0));
        leilao.propoe( new Lance(maria,550.0));
        leilao.propoe( new Lance(matheus,700.0));

        Avaliador avaliador = new Avaliador();

        avaliador.avalia(leilao);

        List<Lance> maiores = avaliador.getTresMaioresLances();

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

        Leilao leilao = new Leilao("Fogão a gas");

        leilao.propoe(new Lance(matheus,200.0));
        leilao.propoe(new Lance(maria,450.0));
        leilao.propoe(new Lance(matheus,120.0));
        leilao.propoe(new Lance(maria,700.0));
        leilao.propoe(new Lance(matheus,630.0));
        leilao.propoe(new Lance(maria,230.0));

        //parte 2: Ação
        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        //parte 3: Validação
        assertEquals(700.0,avaliador.getMaiorLance(),0.0001);
        assertEquals(120.0,avaliador.getMenorLance(),0.0001);

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
        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        //parte 3: validação

        assertEquals(400.0,avaliador.getMaiorLance(),0.0001);
        assertEquals(100.0,avaliador.getMenorLance(),0.0001);

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


        Avaliador avaliador = new Avaliador();

        avaliador.avalia(leilao);

        List<Lance> maiores = avaliador.getTresMaioresLances();

        //parte 3: validação
        assertEquals(2,maiores.size());
        assertEquals(550.0,maiores.get(0).getValor(),0.0001);
        assertEquals(400.0,maiores.get(1).getValor(),0.0001);

    }
    @Test
    public void deveRetornarUmaListaVaziaCasoNaoHouverNengumLance(){
        //parte 1: cenario
        Usuario matheus = new Usuario("Matheus");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Xbox 360");
        //parte 2: acao



        Avaliador avaliador = new Avaliador();

        avaliador.avalia(leilao);

        List<Lance> maiores = avaliador.getTresMaioresLances();

        //parte 3: validação
        assertEquals(0,maiores.size());

    }


}
