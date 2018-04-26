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
public class ENQUANTO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //chama o token reader para correr os tokens
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();     
        Token token = null;
        //instancia o não terminal principal da produção em curso
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        //adiciona o enquanto à árvore
        token = tokenReader.tokenAtual();
        tokenReader.consome();
        thisNaoTerminal.add(new Terminal(token));
        //retorna o token após while mas não move index
        token = tokenReader.tokenAtual();

        if (token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("(")) {
            tokenReader.consome();                    //avança index
            thisNaoTerminal.add(new Terminal(token)); //adiciona ( à árvore   
            token = tokenReader.tokenAtual();
            while((token.getTokenDefine().equals(TokenDefines.IDENTIFICADOR) || token.getTokenDefine().equals(TokenDefines.OPERADOR) || token.getTokenDefine().equals(TokenDefines.NUMERO) || token.getTokenDefine().equals(TokenDefines.DELIMITADOR)) && !token.getAtributo().toString().equals(")")) {
                tokenReader.consome();
                thisNaoTerminal.add(new Terminal(token));
                token = tokenReader.tokenAtual();
            }
        } else {
            thisNaoTerminal.add(erro(this.getClass().getSimpleName(), "Comando condicional" + this.getClass().getSimpleName() + "mal formado - '(' não encontrado"));
        }
        token = tokenReader.tokenAtual();        
        if (token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(")")) {
            tokenReader.consome();                    //avança index
            thisNaoTerminal.add(new Terminal(token)); //adiciona ) à árvore
        } else {
            thisNaoTerminal.add(erro(this.getClass().getSimpleName(), "Comando condicional" + this.getClass().getSimpleName() + "mal formado - ')' não encontrado"));
        }
        //adiciona os elementos oriundos da derivação de BLOCO
        thisNaoTerminal.add(new BLOCO().derivar()); //
        return thisNaoTerminal;
    }    
}
