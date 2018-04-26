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
import AnaliseSintatica.SimboloGramatical.Terminal;

/**
 *
 * @author Jean Luiz
 */
public class OP_AND extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
         //chama o token reader para correr os tokens
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        Token token = null;
        //instancia o não terminal principal da produção em curso
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        token = tokenReader.tokenAtual();

        if (token.getTokenDefine().equals(TokenDefines.OPERADOR) && (token.getAtributo().equals("&&"))) {
            tokenReader.consome();//avança index
            thisNaoTerminal.add(new Terminal(token)); //adiciona && à árvore
        } else {
            return erro(this.getClass().getSimpleName(), this.getClass().getSimpleName() + " mal formado");
        }
        return thisNaoTerminal;
    }    
}
