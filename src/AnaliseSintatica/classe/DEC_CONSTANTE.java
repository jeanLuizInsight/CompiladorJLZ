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
import AnaliseSintatica.metodos.ATRIBUIR_VALOR;

/**
 *
 * @author Jean Luiz
 */
public class DEC_CONSTANTE extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        
        Token token = null;
        Token next = null;
        
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        
        token = tokenReader.tokenAtual();
        if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().equals("vazio")){
            
            tokenReader.consome();
            naoTerminal.add(new Terminal(token));
            
        }
        else
            return erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                        + " mal formada - 'constante' nao encontrado");
        
        naoTerminal.add(new TIPO_PRIMITIVO().derivar());
        
        token = tokenReader.tokenAtual();
        naoTerminal.add(new NOME().derivar());
        
        token = tokenReader.tokenAtual();
        naoTerminal.add(new ATRIBUIR_VALOR().derivar());
        
        token = tokenReader.tokenAtual();
        if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().equals(";")){
            
            tokenReader.consome();
            naoTerminal.add(new Terminal(token));
            
        }
        else
            return erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                        + " mal formada - ';' nao encontrado");
        
        return naoTerminal;
    }
    
}
