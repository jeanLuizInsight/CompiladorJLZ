/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.classe;

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
public class NOME extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //chama o leitor de token para correr os tokens
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        Token token = null;

        //instancia o não terminal principal da produção em curso
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());        
        token = tokenReader.tokenAtual();
        //verifica se o token é um identificador (num, letra, underline) 
        if (token.getTokenDefine().equals(TokenDefines.IDENTIFICADOR) && !token.getTokenDefine().equals(TokenDefines.OPERADOR) && !token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && !token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA)) {
            tokenReader.consome();                    //avança index
            thisNaoTerminal.add(new Terminal(token)); //adiciona token à árvore
        } else {
            thisNaoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                    + "- mal formado, insira um nome ao tipo ou método atribuido")); 
        }        
        return thisNaoTerminal;
    }    
}
