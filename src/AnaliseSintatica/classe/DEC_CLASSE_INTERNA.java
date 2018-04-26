/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.classe;

import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import AnaliseSintatica.SimboloGramatical.Terminal;

/**
 *
 * @author Jean Luiz
 */
public class DEC_CLASSE_INTERNA extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        
        Token token = null;
        
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        
        token = tokenReader.tokenAtual();
        if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("classe")) {            
            tokenReader.consome();
            naoTerminal.add(new Terminal(token));            
        }
        else
            return erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                    + " mal formada - 'classe' não encontrado");
        
        naoTerminal.add(new NOME().derivar());
        
        token = tokenReader.tokenAtual();
        if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("{")){
            
            tokenReader.consome();
            naoTerminal.add(new Terminal(token));
            
        }
        else
            return erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                    + " mal formada - '{' não encontrado");
        
        if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("}")){
            tokenReader.consome();
            naoTerminal.add(new Terminal(token));
            return naoTerminal;
        }
        
        while(!(token.getTokenDefine().equals(TokenDefines.DELIMITADOR)) && !(token.getAtributo().toString().equals("}")) && tokenReader.hasToken()){            
            naoTerminal.add(new DEC_ATRIBUTO().derivar());            
        }        
        return naoTerminal;
    }
    
}
