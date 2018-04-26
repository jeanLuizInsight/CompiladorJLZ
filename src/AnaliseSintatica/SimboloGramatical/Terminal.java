/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.SimboloGramatical;

import AnaliseLexica.Token;

/**
 *
 * @author Jean Luiz
 */
//estrutura para voleres dos nodos terminais
public class Terminal implements SimboloGramatical {
    //declaração do meu simbolo próprio token
    private Token simbolo;

    //construtor
    public Terminal(Token simbolo) {
        this.simbolo = simbolo;
    }

    //get e set
    public Token getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Token simbolo) {
        this.simbolo = simbolo;
    }   
}
