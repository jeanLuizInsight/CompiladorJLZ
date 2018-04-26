/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseLexica.Automatos;

import AnaliseLexica.LeCodigoFonte;
import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseLexica.TokenErro;

/**
 *
 * @author Jean Luiz
 */
public class AutomatoDigito {
    private LeCodigoFonte ler;

    public AutomatoDigito(LeCodigoFonte lcf) {
        this.ler = lcf;
    }

    public Token extraiToken() {        
        ler.Limpar();
        char caracter = ler.Proximo();
        char nextCaracter = ler.Proximo();        
        final int linha = ler.getLinhaAtual();
        Token token = null;
        if (Character.isDigit(caracter)) { //verifica se é digito
            if(Character.isLetter(nextCaracter) || nextCaracter == '_') { //verifica se o proximo é letro ou underline(ERRO)
                do{
                    caracter = ler.Proximo();
                }while(Character.isLetter(caracter) || caracter == '_'); //e enquanto for passa                    
                token = new TokenErro(ler.FecharToken(), linha, "Dígito mal-formado");    //armazena erro            
            }else if (!Character.isDigit(nextCaracter)){ //se proximo tbm for digito armazena digito
                token = new Token(TokenDefines.NUMERO, Integer.parseInt(ler.FecharToken()),linha);
            }
        }        
        return token;
    }
}
