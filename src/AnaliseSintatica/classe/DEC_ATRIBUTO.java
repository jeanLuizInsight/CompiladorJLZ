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

/**
 *
 * @author Jean Luiz
 */
public class DEC_ATRIBUTO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        
        Token token = null;
        Token next = null;
        
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        
        token = tokenReader.tokenAtual();
        
        if(token.getTokenDefine().equals(TokenDefines.IDENTIFICADOR) && token.getAtributo().toString().equals("char")){
            naoTerminal.add(new DEC_VARIAVEL().derivar());
        } else if(next.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("String")){
            naoTerminal.add(new DEC_VARIAVEL().derivar());
        } else if(next.getTokenDefine().equals(TokenDefines.IDENTIFICADOR) && token.getAtributo().toString().equals("int")){
            naoTerminal.add(new DEC_VARIAVEL().derivar());
        } else
            return erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                        + " mal formado");
        
        return naoTerminal;
    }
    
}
