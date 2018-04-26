/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AnaliseLexica.Automatos;

import AnaliseLexica.Alfabeto;
import AnaliseLexica.LeCodigoFonte;
import AnaliseLexica.Token;

/**
 *
 * @author Jean Luiz
 */
//classe responsável por implementar o automato para identificar quando existe espaço no código
public class AutomatoEspaco {
    private LeCodigoFonte ler;
    
    //construtor variavel LeCodigoFonte 
    public AutomatoEspaco(LeCodigoFonte lcf) {
        this.ler = lcf;
    }
    //método do tipo Token responsável ler o código e extrair os espaços
    public Token extraiToken() {
        boolean existeEspaco = false;
        boolean ehEspaco; //armazena retorno do alfabeto
        ler.Limpar();
        //enquando existir espaço faça
        do {            
            //le todos os caracteres do código um a um
            char c = ler.Proximo();
            //verifica se é espaço ou não(retorna true ou false)
            ehEspaco = Alfabeto.ehEspaco(c);
            if(ehEspaco)
                existeEspaco = true;
        } while (ehEspaco);
        //se existiu algum espaço, conta quantos tem e imprime
        if(existeEspaco) {
            //fecha o token espaço com todos os caracteres nulos
            String emBranco = ler.FecharToken();
            System.out.println("Espaços em branco: " +emBranco.length());
        }
        return null;
    }
}
