/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.comandos;

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
public class PAUSAR extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //chama o token reader para correr os tokens
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        Token token = null;
        //instancia o não terminal principal da produção em curso
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        //adiciona o pausar à árvore
        thisNaoTerminal.add(new Terminal(tokenReader.tokenAtual()));
        //retorna o token após pausar mas não move index
        //token = tokenReader.Próximo();
        if (token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().equals(";")) {
            tokenReader.consome();
            thisNaoTerminal.add(new Terminal(token));
        } else {
            return erro(this.getClass().getSimpleName(), "Comando" + this.getClass().getSimpleName()
                    + "mal formado - ';' não encontrado");
        }
        return thisNaoTerminal;
    }
    
}
