/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.metodos;

import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import AnaliseSintatica.SimboloGramatical.Terminal;

/**
 *
 * @author Jean Luiz
 */
public class VARIAVEL extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //pega a instância do tokenReader
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        //consome o primeiro token
        Token t = tokenReader.tokenAtual();
        //cria um símbolo
        NaoTerminal naoTerminal = new NaoTerminal("VARIAVEL");
        if (t.getTokenDefine().equals(TokenDefines.IDENTIFICADOR)) {
            naoTerminal.add(new VARIAVEL_SIMPLES().derivar());
            if (tokenReader.tokenAtual().getTokenDefine().getNome().equals(".")) {
                naoTerminal.add(new Terminal(tokenReader.consome()));
                naoTerminal.add(new VARIAVEL_SIMPLES().derivar());
            }
        } else {
            naoTerminal.add(erro("VARIAVEL", "não sei"));
        }
        return naoTerminal;
    }    
}
