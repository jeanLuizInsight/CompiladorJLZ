/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseLexica.Automatos;

import AnaliseLexica.LeCodigoFonte;
import AnaliseLexica.Token;
import AnaliseLexica.TokenErro;

/**
 *
 * @author Jean Luiz
 */
public class AutomatoErro {
    private LeCodigoFonte ler;
    
    public AutomatoErro(LeCodigoFonte lcf) {
        this.ler = lcf;
    }
    
    public Token extraiToken() {
        ler.Limpar();        //posiciona cursor no inicio do arquivo
        ler.Proximo(); //pega o caracter de erro        
        ler.Proximo(); //avança para o caracter posterior        
        int linha = ler.getLinhaAtual();
        final String lexema = ler.FecharToken(); //armazena o token        
        Token token = null;
        lexema.trim();
        if(!lexema.isEmpty())
            token = new TokenErro(lexema,linha, "Caracter inicial não reconhecido");
        return token;
    }
}
