/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.operacoes;

import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import AnaliseSintatica.SimboloGramatical.Terminal;
import AnaliseSintatica.metodos.CHAMADA_METODO;
import AnaliseSintatica.metodos.VARIAVEL;

/**
 *
 * @author Jean Luiz
 */
public class EXP extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();        
        Token token = tokenReader.tokenAtual();        
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());        
        // Trata os !...
        if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().equals("(")) {            
            token = tokenReader.consome();
            thisNaoTerminal.add(new Terminal(token)); 
            token = tokenReader.tokenAtual();
            while(!token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && !token.getAtributo().equals(")")) {
                token = tokenReader.consome();
                thisNaoTerminal.add(new Terminal(token));
                token = tokenReader.tokenAtual();
            }            
        } else {
            return erro(this.getClass().getSimpleName(), "Declaração int mal formada");
        }        
        return thisNaoTerminal;        
    }
    //método que apenas verifica se o token é operador lógico
    public boolean isLOGIC(Token t) {        
        if (t.getTokenDefine().equals(TokenDefines.OPERADOR) && 
                (t.getAtributo().equals("||") || t.getAtributo().equals("&&"))) {
           return true;
        } else {
            return false;
        }
    }
    
}
