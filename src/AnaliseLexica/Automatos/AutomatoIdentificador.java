/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseLexica.Automatos;

import AnaliseLexica.*;

/**
 *
 * @author Jean Luiz
 */
public class AutomatoIdentificador {
    //Expressão regular de definição: Letra{Letra|Digito|"_"}
    private final int ESTADO_INICIAL = 7; //estado inicial
    private final int ESTADO_ACEITACAO = 9; //estado de aceitação
    private final int ESTADO_REJEICAO = -1; //estado de rejeição
    
    private Token token;
    private Character c;
    private String lexemaAceito;
    private LeCodigoFonte ler;
    private int indice;
    private int estado;

    public AutomatoIdentificador() {
        this.indice = 0;
        this.c = null;
        this.token = null;
        this.lexemaAceito = "";
        this.estado = ESTADO_INICIAL;
    }

    public AutomatoIdentificador(LeCodigoFonte lcf) {
        this.ler = lcf;
        this.indice = 0;
        this.c = null;
        this.token = null;
        this.lexemaAceito = "";
        this.estado = ESTADO_INICIAL;
    }

    public Token extraiToken() {        
        resetAutomato();//O resetAutomato não era chamado se o automato retornasse null
        ler.Limpar();
        //enquanto existir caracter e estado != de rejeição
        while (ler.hasChar() && estado != ESTADO_REJEICAO) {
            c = ler.Proximo();
            //
            if (Character.isLetter(c)) {
                if (estado == ESTADO_INICIAL) {
                    estado = 8;
                } else if (estado == 8 || estado == ESTADO_ACEITACAO) {
                    estado = ESTADO_ACEITACAO;
                }
            } else if (Character.isDigit(c) || c.equals((Character) '_')) {
                if (estado == ESTADO_INICIAL) {
                    estado = ESTADO_REJEICAO;
                    return null;
                } else if (estado == 8 || estado == ESTADO_ACEITACAO) {
                    estado = ESTADO_ACEITACAO;
                }
            } else if(estado == ESTADO_INICIAL || estado == 8){
                estado = ESTADO_REJEICAO;
                return null;
            }else{
                 estado = ESTADO_REJEICAO;
            }
        }
        lexemaAceito = ler.FecharToken();        
        //checa se o token é palavra reservada
        TabelaPalavrasReservadas pr = TabelaPalavrasReservadas.ChecaPR(lexemaAceito);                 
        if (pr != null) {
            token = new Token(TokenDefines.PALAVRA_RESERVADA, pr,ler.getLinhaAtual());
        } else {
            token = new Token(TokenDefines.IDENTIFICADOR, new Identificador(lexemaAceito),ler.getLinhaAtual());
        }
        //resetAutomata();
        return token;
    }

    public void resetAutomato() {
        indice = 0;
        c = null;
        lexemaAceito = "";
        estado = ESTADO_INICIAL;
    }
}
