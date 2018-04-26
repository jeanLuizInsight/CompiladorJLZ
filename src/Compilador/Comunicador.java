/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import AnaliseLexica.Token;
import AnaliseLexica.TokenErro;
import AnaliseSintatica.SimboloGramatical.ErroSintatico;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
//classe responsável por comunicar compilador com a interface
public class Comunicador {
    private Compilador compilador;  

    /**
     * Construtor que recebe o codigo-fonte
     * do programa a ser Analisado Lexicamente
     * 
     * @param codigo
     */
    public Comunicador(String codigo) {
        compilador = new Compilador();        
        compilador.compilar(codigo);
    }

    /**
     * Metodo que coleta os tokens gerados pelo
     * Analisador Lexico do codigo-fonte.
     * 
     * @return uma String com todos os tokens
     */
    public String getTokens() {
       StringBuilder tokensStBuilder = new StringBuilder();       
       List<Token> tokens = compilador.getTokens();       
       if(tokens == null || tokens.isEmpty()){
           tokensStBuilder.append("Não há tokens!");
       
       } else {
           for(Token t : tokens) {
               tokensStBuilder.append("<"+t.getTokenDefine().toString()+", '"
                                        +t.getAtributo()+"'> (linha "+t.getLinha()+")\n");
           }
       }       
       return tokensStBuilder.toString();
    }
    
    /**
     * Metodo que coleta os erros lexicos
     * gerados pelo Analisador Lexico
     * 
     * @return uma String com todos os erros lexicos
     */
    public String getErros() {        
        StringBuilder errosStBuilder = new StringBuilder();
        
        List<TokenErro> errosLexicos = compilador.getErrosLexicos();        
        if(errosLexicos == null || errosLexicos.isEmpty()) {
            errosStBuilder.append("Não há erros léxicos!\n");
 
            //lógica para verificar erros sintáticos
            List<ErroSintatico> errosSintaticos = compilador.getErrosSintaticos();
            //System.out.println("ERROS Sint: "+errosSintaticos.get(0).getTokens());
            if(errosSintaticos == null || errosSintaticos.isEmpty()) {
                //System.out.println("IF S: ");
                errosStBuilder.append("Não há erros sintáticos!");
            } else {
                //percorre os erros sintáticos e mostra
                for(ErroSintatico er : errosSintaticos) {
                    //System.out.println("ER.GETTOKENS: "+er.getTokens());
                    int linha = er.getTokens().get(0).getLinha();
                    errosStBuilder.append(er.getDescricao() + " (linha "+ linha +")\n"); //++
                    //errosStBuilder.append(er.getDescricao() + "\n");
                }
            }            
        } else {
            //percorre os erros léxicos e mostra
            for(TokenErro er : errosLexicos) {
                errosStBuilder.append("<" + er.getTokenDefine().toString()+ ", '" + 
                                 er.getAtributo() + "', " +
                                 er.getDescricao()+ "> (linha "+er.getLinha()+")\n");
            }
        
        }
        return errosStBuilder.toString();
    }
    
    /**
     * Pega a lista de tokens gerados
     * pelo analisador, sem alteracao.
     * 
     * @return um List<> com os Tokens
     */
    public List<Token> getListTokens(){
        return compilador.getTokens();
    }
    
    /**
     * Pega a lista de erros lexicos gerados
     * pelo analisador, sem alteracao.
     * 
     * @return um List<> com os Tokens
     */
    public List<TokenErro> getListErrosLexicos() {
        return compilador.getErrosLexicos();
    }

}
