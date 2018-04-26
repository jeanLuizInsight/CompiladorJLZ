/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.operacoes;

import AnaliseLexica.TokenDefines;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;

/**
 *
 * @author Jean Luiz
 */
public class OP_LOGIC extends Producao {

    /** Creates a new instance of OP_LOGIC */
    public OP_LOGIC() {
    }
    
    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        /*
        if (tokenReader.Próximo().getTokenDefine().equals(TokenDefines.OPERADOR) && tokenReader.Próximo().getAtributo().equals("&&")) {
            thisNaoTerminal.add(new OP_AND().derivar());
        }
        
        if (tokenReader.Próximo().getTokenDefine().equals(TokenDefines.OPERADOR) && tokenReader.Próximo().getAtributo().equals("||")) {
            thisNaoTerminal.add(new OP_OR().derivar());
        }*/
        return thisNaoTerminal;
    }    
}
