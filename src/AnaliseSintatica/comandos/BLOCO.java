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
import AnaliseSintatica.classe.DEC_VARIAVEL;
import AnaliseSintatica.classe.NOME;

/**
 *
 * @author Jean Luiz
 * <BLOCO> ::= ‘{‘ { <DEC_VARIAVEL> | <CMD> } ‘}’ | <DEC_VARIAVEL> | <CMD>
 */
public class BLOCO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //chama o token reader para correr os tokens
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        Token token = null;
        //instancia o não terminal principal da produção em curso
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());        
        token = tokenReader.tokenAtual();
        if (token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("{")) {
            tokenReader.consome();                    //avança index
            thisNaoTerminal.add(new Terminal(token)); //adiciona { à árvore  
        } else {
            thisNaoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                    + "mal formado - '{' não encontrado"));
        }

        token = tokenReader.tokenAtual();
        while(token.getAtributo().toString().equals("char") || 
                token.getAtributo().toString().equals("int") || 
                token.getAtributo().toString().equals("String") ||
                token.getAtributo().toString().equals("enquanto") || 
                token.getAtributo().toString().equals("escreva") || 
                token.getAtributo().toString().equals("leia") || 
                token.getAtributo().toString().equals("retorno") ||
                token.getTokenDefine().equals(TokenDefines.IDENTIFICADOR)) {
            if(token.getAtributo().toString().equals("char") || token.getAtributo().toString().equals("int") || token.getAtributo().toString().equals("String")) {
                tokenReader.consome();
                thisNaoTerminal.add(new Terminal(token));
                thisNaoTerminal.add(new NOME().derivar());
                thisNaoTerminal.add(new DEC_VARIAVEL().derivar());
            }
            if(token.getAtributo().toString().equals("enquanto") || token.getAtributo().toString().equals("escreva") || token.getAtributo().toString().equals("leia") || token.getAtributo().toString().equals("retorno")) {
                thisNaoTerminal.add(new CMD().derivar());
            }      
            if(token.getTokenDefine().equals(TokenDefines.IDENTIFICADOR)) {
                tokenReader.consome();
                thisNaoTerminal.add(new Terminal(token));
                token = tokenReader.tokenAtual();
                if(token.getTokenDefine().equals(TokenDefines.OPERADOR) && (token.getAtributo().toString().equals("++") || token.getAtributo().toString().equals("--"))) {
                    tokenReader.consome();
                    thisNaoTerminal.add(new Terminal(token));
                    token = tokenReader.tokenAtual();
                    if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(";")) {
                        tokenReader.consome();
                        thisNaoTerminal.add(new Terminal(token));
                    } else {
                        thisNaoTerminal.add(erro(this.getClass().getSimpleName(), "Erro sintático - delimitador ';' não encontrado"));
                    }
                }
            }
            token = tokenReader.tokenAtual();
        }
        token = tokenReader.tokenAtual();
        if (token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("}")) {
            tokenReader.consome();                    //avança index 
            thisNaoTerminal.add(new Terminal(token)); //adiciona } à árvore
        } else {
            thisNaoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                    + "mal formado - '}' não encontrado"));
        }
        return thisNaoTerminal;
    }
    
}
