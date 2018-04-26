/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import AnaliseLexica.AnalisadorLexico;
import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseLexica.TokenErro;
import AnaliseSintatica.AnalisadorSintatico;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.SimboloGramatical.ArvoreSintatica;
import AnaliseSintatica.SimboloGramatical.ErroSintatico;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
public class Compilador {
    //variavel léxica
    private AnalisadorLexico analisadorLexico = null;
    //variavel sintatica
    private AnalisadorSintatico analisadorSintatico = null;
    //variavel leitorDeToken
    private LeitorDeToken tokenReader;
    //variavel lista erroSintatico
    private List<ErroSintatico> errosSintaticos;
    
    public Compilador() {
    }

    public void compilar(String codigo){
        analisadorLexico = new AnalisadorLexico(codigo);
        analisadorLexico.extraiTokens();
        
        //limpa os erros sintaticos
        errosSintaticos = new ArrayList<ErroSintatico>();

        //Faz uma cópia da lista de tokens para o analisador sintático
        List<Token> tokens = new ArrayList<Token>(analisadorLexico.getTokens());
        //List<Token> tokens = analisadorLexico.getTokens();

        //se não houve erros léxicos
        if(analisadorLexico.getErros().isEmpty() && !tokens.isEmpty()) {
            //Remove todos os comentários
            ArrayList<Integer> remComent = new ArrayList<Integer>();
            for(Token t : tokens) {
                if(t.getTokenDefine().equals(TokenDefines.COMENTARIO)) {
                    remComent.add(tokens.indexOf(t));
                }
            }
            for(int i : remComent) {
                tokens.remove(i);
            }
            //crio a instancia leitor de token
            tokenReader = LeitorDeToken.createInstance(tokens, true); //getInstance();
            
            //crio a instancia analisador sintatico
            analisadorSintatico = new AnalisadorSintatico(tokenReader);
            
            //analiso o código
            analisadorSintatico.analisar();
            
            //extraio os erros sintaticos (busca em profundidade pega a raiz)
            extractErrosSintaticos(getArvoreSintatica().getRaiz()); //retorna classeSG com os valores armazenados na arvore
        }
    }
    
    public List<Token> getTokens(){
        return analisadorLexico.getTokens();
    }
    
    public List<TokenErro> getErrosLexicos(){
        return analisadorLexico.getErros();
    }
    
    //get arvore
    public ArvoreSintatica getArvoreSintatica(){
        return analisadorSintatico.getArvoreSintatica();
    }
    
    //get erro sintatico
    public List<ErroSintatico> getErrosSintaticos(){
        return errosSintaticos;
    }
        
    //método busca em profundidade por erros na gramática
    private void extractErrosSintaticos(SimboloGramatical sg){
        List<SimboloGramatical> filhos;        
        if(sg instanceof NaoTerminal){
            NaoTerminal nt = (NaoTerminal) sg;            
            filhos = nt.getFilhos();            
            for(SimboloGramatical sgFilho: filhos){
                System.out.println("SG FILHO: "+sgFilho);                
                extractErrosSintaticos(sgFilho);                
            }            
        } else if(sg instanceof ErroSintatico){
            errosSintaticos.add((ErroSintatico) sg);
        }
    }
}
