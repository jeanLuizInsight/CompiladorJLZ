/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.operacoes;

import AnaliseLexica.Token;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;

/**
 *
 * @author Jean Luiz
 */
public class EXP_INC extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        NaoTerminal EXP_INC = new NaoTerminal("EXP_INC");
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();        
        Token t = tokenReader.consome();
        EXP_INC.add(new EXP_ARIT().derivar());
        t = tokenReader.consome();
        EXP_INC.add(new OP_INC().derivar());
        return EXP_INC;
    }    
}
