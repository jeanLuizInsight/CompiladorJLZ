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
public class AutomatoNumero {
    LeCodigoFonte ler;
    Token token;

    public AutomatoNumero(LeCodigoFonte lcf){
        this.ler = lcf;
    }
    /**
     * Supõe que já tenha sido executado AutomatoDigito.extraiToken()
     * @return o token numero com o atributo Inteiro
     */
    public Token extraiToken(){
        ler.Limpar();
        char c = ler.Proximo();        
        //se o primeiro for dígito
        if(Character.isDigit(c)) {    
            //pula todos os dígitos
            do {
                c = ler.Proximo();
            }  while(Character.isDigit(c));
            //Se depois que acabar os digitos, vier uma letra ou underline...
            if(Character.isLetter(c) || c == '_') {
                //percorre todo o token até o fim
                do{
                    c = ler.Proximo();
                }while(Character.isLetter(c) || c == '_');
            }
            //Senão, retorna o token numero correto
            else {  
                String num = ler.FecharToken();
                int linha  = ler.getLinhaAtual();
                token = new Token(TokenDefines.NUMERO, num, linha);
                return token;
            }            
            //Por fim, retorna um token erro.
            return new TokenErro(ler.FecharToken(), ler.getLinhaAtual(), "Número mal-formado");
        }
        //se o primeiro não for dígito, já era.
        return null;
    }
}
