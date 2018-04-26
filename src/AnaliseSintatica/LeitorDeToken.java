/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica;

import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
//TokenReader
public class LeitorDeToken {
    //lista pra armazenar os tokens
    private List<Token> tokens;
    //declaração indice atual
    private int atual = 0;
    //instancia do tipo LeitorDeToken
    private static LeitorDeToken instance = null;
    
    //metodo responsavel por armazenar todos os tokens sem comentários padrão
    public static LeitorDeToken createInstance(List<Token> tokens) {        
        //instancia o LeitorDeToken repassando a lista dos tokens
        instance = new LeitorDeToken(tokens, true);
        instance.atual = 0;        //seta indice 0 e retorna
        return instance;
    }
    
    //metodo responsavel por armazenar os tokens sem os comentarios opção
    public static LeitorDeToken createInstance(List<Token> tokens, boolean removeComentario) {        
        instance = new LeitorDeToken(tokens, removeComentario);
        instance.atual = 0;        
        return instance;
    }

    //busca os tokens 
    public static LeitorDeToken getInstance(){
        if(instance == null){
            instance = new LeitorDeToken(new ArrayList<Token>(), true);
        }
        return instance;
    }

    //método responsável por remover os comentários
    private LeitorDeToken(List<Token> tokens, boolean removeComment) {
        this.tokens = tokens;                  //armazena os tokens
        ArrayList<Token> backup;               //Lista temporaria
        if(removeComment){                     //remove comentários
            backup = new ArrayList<Token>(tokens);
            for(Token t: backup){
                if(t.getTokenDefine().equals(TokenDefines.COMENTARIO))
                    tokens.remove(t);
            }
        }
    }

    //metodo verifica se ainda existe tokens no arquivo
    public boolean hasToken()  {
        return atual < tokens.size();
    }
    //metodo le token atual e incrementa para proximo
    public Token consome() {
        Token t = get(atual);
        atual++;
        return t;
    }
    
    //metodo le proximo token
    public Token Próximo() {
        Token t = this.get(atual + 1);
        return t;
    }

    /*
     * exibe o token que está a tantas posições do atual
     * quando pular = 1 equivale a Proximo()
     */  
    public Token Proximo(int pular) {
        Token t = this.get(atual + pular);
        return t;
    }
    
    public Token Anterior(int pular) {
        Token t = this.get(atual - pular);
        return t;
    }
    
    //metodo retorna o token atual
    public Token tokenAtual() { //seeCurrent
        Token t = this.get(atual);
        //Token t = tokens.get(atual);
        return t;
    }
    //metodo armazena tokens (identificadores, ... e palavras reservadas)
    public Token get(int i) {
        Token t = null;
        //System.out.println("GET(): "+i);
        final int size = tokens.size(); //busca o tamanho da Lista de tokens extraidos
        
       if(i < size) {
            //armazena token (identificadores, operadores ou delimitadores)
            t = tokens.get(i);
       } else {
            if(size > 0){
                //instancia o token passando tipo, valor, linha
                t = new Token(TokenDefines.PALAVRA_RESERVADA, "endOfFile", tokens.get(size-1).getLinha());
            }
            else{
                t = new Token(TokenDefines.PALAVRA_RESERVADA, "endOfFile", 0);
            }
           //t = null;
        }
        return t;
    }
}
