/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.metodos;

import AnaliseLexica.Token;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import AnaliseSintatica.SimboloGramatical.Terminal;

/**
 *
 * @author Jean Luiz
 */
public class PARAM_X extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //pega a instância do tokenReader
            LeitorDeToken tokenReader = LeitorDeToken.getInstance();
            //consome o primeiro token
            Token t = tokenReader.tokenAtual();
            //cria um símbolo
            NaoTerminal naoTerminal = new NaoTerminal("PARAM");
            if (t.getAtributo().equals(",")) {
                naoTerminal.add(new Terminal(tokenReader.consome()));
                naoTerminal.add(new VALOR().derivar());
            }
            return naoTerminal;
    }    
}
