/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AnaliseLexica.Automatos;

import AnaliseLexica.Alfabeto;
import AnaliseLexica.LeCodigoFonte;
import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;

/**
 *
 * @author Jean Luiz
 */
//classe responsavel por implementar o automato para identificar os delimitadores no código fonte
public class AutomatoDelimitador {
    private LeCodigoFonte ler;
    private Token token;
    private char c;
    private String retorno;
    
    //construt
    public AutomatoDelimitador(LeCodigoFonte lcf) {
        this.ler = lcf;
    }
    //responsável por ler o código e extrair os simbolos delimitadores
    public Token extraiToken() {
        ler.Limpar();
        c = ler.Proximo();
        if(ehDelimitador(c)) {
            retorno = retorno.valueOf(c);
            ler.Proximo();
            int linha = ler.getLinhaAtual();
            ler.FecharToken();
            token = new Token(TokenDefines.DELIMITADOR, retorno, linha);
            return token;
        }
        return null;
    }
    //verifica se é mesmo delimitador
    public boolean ehDelimitador(char teste) {
        if(teste == ';')
            return true;
        if(teste == '(')
            return true;
        if(teste == ')')
            return true;
        if(teste == '{')
            return true;
        if(teste == '}')
            return true;
        if(teste == ',')
            return true;
        return false;
    }
}
