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
public class SE extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //chama o token reader para correr os tokens
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        Token token = null;
        //instancia o não terminal principal da produção em curso
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        //adiciona o SE à árvore        
        token = tokenReader.tokenAtual();
        thisNaoTerminal.add(new Terminal(token));
        tokenReader.consome();
        //retorna o token após SE mas não move index
        token = tokenReader.tokenAtual();
        //se abre parentese
        if (token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("(")) {
            tokenReader.consome();//avança index para (
            thisNaoTerminal.add(new Terminal(token)); //adiciona ( à árvore
            token = tokenReader.tokenAtual();
            //-----verificação dentro dos parenteses do SE----
            while(!(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(")"))) {
                tokenReader.consome();
                thisNaoTerminal.add(new Terminal(token));
                token = tokenReader.tokenAtual();
            }
            //------------------------------------------------ 
            token = tokenReader.tokenAtual();
            if (token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(")")) {
                tokenReader.consome();                    //avança index para )
                thisNaoTerminal.add(new Terminal(token)); //adiciona ) à árvore
                //retorna o token após ')' (caso exista)            
            } else {
                thisNaoTerminal.add(erro(this.getClass().getSimpleName(), "Comando condicional" +
                        this.getClass().getSimpleName() + "mal formado - ')' não encontrado"));
            }
        //se não encontrou o abre parenteses retorna erro
        } else {
            thisNaoTerminal.add(erro(this.getClass().getSimpleName(), "Comando condicional"
                    + this.getClass().getSimpleName() + "mal formado - '(' não encontrado"));
        }        
        //adiciona os elementos oriundos da derivação de BLOCO
        thisNaoTerminal.add(new BLOCO().derivar()); //
        token = tokenReader.tokenAtual();
        if (token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("senao")) {
            thisNaoTerminal.add(new SENAO().derivar());
        }
        return thisNaoTerminal;
    }    
}
