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

/**
 *
 * @author Jean Luiz
 */
public class EXP_REL extends Producao {

    /** Creates a new instance of EXP_REL */
    public EXP_REL() {
    }
    
    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();        
        Token token = null;        
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());        
        // primeiro lado da deriva��o
        thisNaoTerminal.add(new EXP_ARIT().derivar());
        
        if (isREL(tokenReader.tokenAtual())){
            // olha se � uma rela��o atra�s do operador relacional:
            thisNaoTerminal.add(new OP_REL().derivar());            
            // segundo lado da deriva��o
            thisNaoTerminal.add(new EXP_ARIT().derivar());
        }         
        return thisNaoTerminal;        
    }
    //método que verifica se é operador relacional
    public boolean isREL(Token t) {        
        if (t.getTokenDefine().equals(TokenDefines.OPERADOR) && 
                (t.getAtributo().equals("==") || t.getAtributo().equals("!=") ||
                t.getAtributo().equals(">") || t.getAtributo().equals(">=") ||
                t.getAtributo().equals("<") || t.getAtributo().equals("<="))) {
           return true;
        } else {
            return false;
        }
    }    
}
