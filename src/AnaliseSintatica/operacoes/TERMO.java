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
public class TERMO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        NaoTerminal TERMO = new NaoTerminal("TERMO");        
        Token t = tokenReader.consome();
        TERMO.add(new FATOR().derivar());
        t = tokenReader.consome();
        TERMO.add(new TERMO_X().derivar());
        return TERMO;
    }
    
}
