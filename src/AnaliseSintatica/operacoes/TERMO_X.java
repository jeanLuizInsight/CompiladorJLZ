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
public class TERMO_X extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();

        NaoTerminal TERMO_X = new NaoTerminal("TERMO_X");
        /*
        Token t = tokenReader.Próximo();
        if(t.getTokenDefine().equals(TokenDefines.OPERADOR) &&
            (t.getAtributo().equals("*") || t.getAtributo().equals("/"))) {
            t = tokenReader.consome();
            TERMO_X.add(new OP_MULT().derivar());
            t = tokenReader.consome();
            TERMO_X.add(new FATOR().derivar());
            t = tokenReader.consome();
            TERMO_X.add(new TERMO_X().derivar());
        }*/
        return TERMO_X;
    }    
}
