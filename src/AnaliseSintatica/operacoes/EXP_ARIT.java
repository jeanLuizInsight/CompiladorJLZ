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
public class EXP_ARIT extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();      
        NaoTerminal simbNT = new NaoTerminal("EXP_ARIT");
        Token tokenR = tokenReader.consome();
        simbNT.add(new TERMO().derivar());
        tokenR = tokenReader.consome();
        Producao expAritX = new EXP_ARIT_X();
        simbNT.add(expAritX.derivar());
        return simbNT;
    }    
}
