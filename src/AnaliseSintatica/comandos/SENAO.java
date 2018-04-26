/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.comandos;

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
public class SENAO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //chama o token reader para correr os tokens
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        Token token = null;
        //instancia o não terminal principal da produção em curso
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        tokenReader.consome();
        //adiciona o senao à árvore
        thisNaoTerminal.add(new Terminal(tokenReader.tokenAtual()));
        //adiciona eventuais teminais não terminais oriundos da derivação de bloco
        thisNaoTerminal.add(new BLOCO().derivar());
        return thisNaoTerminal;
    }
    
}
