/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.operacoes;

import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import AnaliseSintatica.SimboloGramatical.Terminal;
import AnaliseSintatica.metodos.CHAMADA_METODO;
import AnaliseSintatica.metodos.VARIAVEL;

/**
 *
 * @author Jean Luiz
 */
public class NRO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        Token t = tokenReader.consome();        
        //Símbolo que será retornado
        SimboloGramatical simbolo = null;
        //Flag de erro
        boolean erro = false;
        NaoTerminal NRO = new NaoTerminal("NRO");
        //Identifica primeiro se tem o menos
        if(t.getTokenDefine().equals(TokenDefines.OPERADOR) && t.getAtributo().equals("-")) {
            NRO.add(new Terminal(t));
            t = tokenReader.consome();
        }
        if(t.getTokenDefine().equals(TokenDefines.NUMERO)) {
                NRO.add(new Terminal(t));
        }
        else if (t.getAtributo().equals(TokenDefines.IDENTIFICADOR)) { //TODO mudar
            //Aqui é um LL2
            Token proximo = null;// = tokenReader.Próximo();
            if(proximo.getAtributo().equals("(")) {
                //Pode ser uma chamada de método
                Producao chamadaMetodo = new CHAMADA_METODO();
                NRO.add(chamadaMetodo.derivar());
            } else {
                //Pode ser uma variável
                Producao variavel = new VARIAVEL();
                NRO.add(variavel.derivar());
            }
        }
        else erro = true;
        //Se não aconteceu um erro, retornar o token NRO
        if(!erro) simbolo = NRO;
        else simbolo = erro("NRO", "Valor próximo a '"+t.getAtributo().toString()+"' não pôde ser reconhecido. Linha "+t.getLinha()+".");
        return simbolo;
    }    
}
