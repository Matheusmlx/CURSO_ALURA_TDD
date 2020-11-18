package leilao.servico;

import leilao.dominio.Lance;
import leilao.dominio.Leilao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double media  = 0 ;
    private double total  = 0 ;
    private List<Lance> maiores;

    public void avalia(Leilao leilao){

        if(leilao.getLances().isEmpty()){
            throw  new RuntimeException("Não é possivel avaliar um leião sem lances!!");
        }

        for(Lance lance : leilao.getLances()){
            if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
            total +=lance.getValor();
        }

        maiores = new ArrayList<>(leilao.getLances());
        //sort
        maiores.sort((valor1,valor2) ->{

            if(valor1.getValor() < valor2.getValor()) return 1;
            if (valor1.getValor() > valor2.getValor()) return -1;
            return 0;

        });

        maiores = maiores.subList(0,maiores.size() > 3 ? 3  : maiores.size());

        if(total == 0){
            media = 0;
            return;
        }

        media = total/leilao.getLances().size();

    }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorLance() { return menorDeTodos; }

    public double getMedia() { return media; }

    public List<Lance> getTresMaioresLances() {
        return maiores;
    }


}
