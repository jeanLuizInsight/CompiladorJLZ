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
public class VALOR_NUM extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        NaoTerminal VALOR_NUM = new NaoTerminal("VALOR_NUM");
        /*
        Token t = tokenReader.consome();
        VALOR_NUM.add(new EXP_ARIT().derivar());
        t = tokenReader.Próximo();
        if(t.getTokenDefine().equals(TokenDefines.OPERADOR) && t.getAtributo().equals("++")) {
            t = tokenReader.consome();
            VALOR_NUM.add(new OP_INC().derivar());
        }*/
        return VALOR_NUM;
    }    
}
