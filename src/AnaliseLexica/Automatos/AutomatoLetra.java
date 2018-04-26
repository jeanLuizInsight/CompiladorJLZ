/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseLexica.Automatos;

import AnaliseLexica.Identificador;
import AnaliseLexica.LeCodigoFonte;
import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;

/**
 *
 * @author Jean Luiz
 */
public class AutomatoLetra {
    private LeCodigoFonte ler;
    private char c;
    Token token;

    public AutomatoLetra() {
    }

    public AutomatoLetra(LeCodigoFonte lcf) {
        this.ler = lcf;
    }

    public Token extraiToken() {        
        ler.Limpar();
        c = ler.Proximo();        
        try {
            if(Character.isLetter(c)) { //verifica se o caracter é letra
                c = ler.Proximo();
                int linha = ler.getLinhaAtual();
                token = new Token(TokenDefines.IDENTIFICADOR, new Identificador(ler.FecharToken()),linha);
                return token;
            } else {                
                return null;
            }
        } catch (NullPointerException e) {            
            return null;
        }
    }
}
