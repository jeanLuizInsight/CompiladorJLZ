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
import AnaliseLexica.TokenErro;

/**
 *
 * @author Jean Luiz
 */
public class AutomatoOperador {
    private LeCodigoFonte ler;
    private char c;
    Token token;
    
    //construtor
    public AutomatoOperador(LeCodigoFonte lcf){
        this.ler = lcf;
    }
    
    public Token extraiToken() {
        token = null;
        ler.Limpar();  //cursor no inicio do arquivo
        c = ler.Proximo(); //
        int linha = ler.getLinhaAtual();
        if(Alfabeto.ehOperador(c)){    //só entra se caracter for do tipo operador
            boolean haErro = false;
            char cProximo = ler.Proximo();            
            switch(c){                   //switch pra mim saber qual é o tipo do operador
                case '+':
                    if(cProximo == '+')
                        ler.Proximo();
                    break;                
                case '-':
                    if(cProximo == '-')
                        ler.Proximo();
                    break;                
                case '!':
                    if(cProximo == '=')
                        ler.Proximo();
                   break;                    
                case '|':
                    if(cProximo == '|')
                        ler.Proximo();
                    else{
                        haErro = true;
                    }
                    break;
                case '&':
                    if(cProximo == '&')
                        ler.Proximo();
                    else{
                        haErro = true;
                    }
                    break;                    
                case '<':
                    if(cProximo == '=')
                        ler.Proximo();
                    break;                
                case '>':
                    if(cProximo == '=')
                        ler.Proximo();
                    break;                    
                case '=':
                    if(cProximo == '=')
                        ler.Proximo();
                    break;
            }
            if(haErro == false) {
                String lexema = ler.FecharToken();
                token = new Token(TokenDefines.OPERADOR, lexema, linha);                
            } else {
                String lexema = ler.FecharToken();
                token = new TokenErro(lexema, linha, "O Operador foi mal formado!");                
            }
        }
        return token;                
    }
}
