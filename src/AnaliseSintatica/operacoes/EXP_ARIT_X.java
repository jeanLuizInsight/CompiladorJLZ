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
public class EXP_ARIT_X extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();

        NaoTerminal EXP_ARIT_X = new NaoTerminal("EXP_ARIT_X");

        Token t = null;// tokenReader.Próximo();
        if(t.getTokenDefine().equals(TokenDefines.OPERADOR) &&
            (t.getAtributo().equals("+") || t.getAtributo().equals("-"))) {
            t = tokenReader.consome();
            EXP_ARIT_X.add(new OP_SOMA().derivar());
            t = tokenReader.consome();
            EXP_ARIT_X.add(new TERMO().derivar());
            t = tokenReader.consome();
            EXP_ARIT_X.add(new EXP_ARIT_X().derivar());
        }
        return EXP_ARIT_X;
    }    
}
