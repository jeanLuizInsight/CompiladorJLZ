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
public class AutomatoString {
    private LeCodigoFonte ler;
    private Token token;
    private char c;
    
    public AutomatoString(LeCodigoFonte lcf) {
        this.ler = lcf;
    }
    
    public Token extraiToken() {
        ler.Limpar();                               //posiciona cursor no inicio do arquivo
        c = ler.Proximo();                          //le proximo caracter
        if(Alfabeto.ehAspa(c)) {                             
            while(c != ler.EOF) {               //enquanto os dados não acabarem
                c = ler.Proximo();              //le proximo
                if(Alfabeto.ehAspa(c)) {              //se caracter igual a barra fim do comentário 
                    int linha = ler.getLinhaAtual();
                    ler.Proximo();
                    //leio o token dentro do loop
                    token = new Token(TokenDefines.IDENTIFICADOR, ler.FecharToken(), linha);
                    return token;
                }                
            }
            ler.Proximo();
            int linha = ler.getLinhaAtual();
            token = new TokenErro(ler.FecharToken(), linha, "Faltou fechar ASPAS na String!");
            return token;            
        }
        return null;
    }
}
