/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica;

import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseSintatica.cfg.AnalisadorSintaticoProperties;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
//
public class TabelaSeguintes {
    
    public static List<Token> getSeguintes(String naoTerminal) {
        //declaração da lista para armazenar os objetos tipos tokens
        List<Token> tokensSeguintes = new ArrayList<Token>();
        //declaração e armazena todos os valores da propriedade recebida
        String[][] seguintes = AnalisadorSintaticoProperties.getPropertiesMatriz(naoTerminal+".SEG");
        //percorro o vetor 
        for(String[] seg : seguintes) {
            String tokenCl = seg[0].toUpperCase(); //extrai o tipo do token
            String atributo = seg[1];              //extrai seu valor
            Token token = new Token(TokenDefines.valueOf(tokenCl));  //instancio um tokenDefine passando seu tipo
            if(atributo != null && !atributo.isEmpty()) {            //verifica o atributo, se existe
                token.setAtributo(atributo);                         //armazena seu valor
            }
            tokensSeguintes.add(token);    //armazena o tipo do token na lista
        }
        //retorna a lista com os tokensDefines
        return tokensSeguintes;        
    }
    
    public static void main(String[] arg){
        System.out.println("OP_OR");
        List<Token> seguintes = getSeguintes("OP_OR");
        int x = 0;
        for(Token t : seguintes){
            System.out.println("TokenDefine " + t.getTokenDefine() + " atributo " + t.getAtributo());
        }
        
        System.out.println("VALOR_NUM");
        seguintes = getSeguintes("VALOR_NUM");
        for(Token t : seguintes){
            System.out.println("TokenDefine " + t.getTokenDefine() + " atributo " + t.getAtributo());
        }
    }
}
