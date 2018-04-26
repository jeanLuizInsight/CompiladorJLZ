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
import AnaliseSintatica.metodos.VALOR;

/**
 *
 * @author Jean Luiz
 */
public class DEC_VARIAVEL extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();        
        Token token = null;        
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());        
        //le atual
        token = tokenReader.tokenAtual();
        //se ponto e virgula indicando fim da linha avança indice e armazena na arvore
        if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(";")) {            
            tokenReader.consome();
            naoTerminal.add(new Terminal(token));            
        } //se igual indica atribuição avança indice e armazena na arvore
        else if(token.getTokenDefine().equals(TokenDefines.OPERADOR) && token.getAtributo().toString().equals("=")) {            
            tokenReader.consome();
            naoTerminal.add(new Terminal(token));    
            naoTerminal.add(new VALOR().derivar());
            token = tokenReader.tokenAtual();
            if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(";")) {
                tokenReader.consome();
                naoTerminal.add(new Terminal(token));
            } else {
                return erro(this.getClass().getSimpleName(), "DEC_VARIAVEL mal formada, faltou ';'");
            }            
        } //se não percorre todo arquivo até encontrar ponto e virgula
        else if(token.getAtributo().toString().equals(",")) {
            tokenReader.consome();
            naoTerminal.add(new Terminal(token));
            token = tokenReader.tokenAtual();
            if(token.getTokenDefine().equals(TokenDefines.IDENTIFICADOR)) {
                naoTerminal.add(new NOME().derivar()); //deriva de NOME e armazena na arvore
                token = tokenReader.tokenAtual();
                while(!(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(";")) && tokenReader.hasToken()){
                    //depois do ponto e virgula verifica se virgula
                    if(token.getAtributo().toString().equals(",")){
                        //avança indice e armazena
                        tokenReader.consome();
                        naoTerminal.add(new Terminal(token));
                        token = tokenReader.tokenAtual();      //le atual 
                        naoTerminal.add(new NOME().derivar()); //deriva de NOME e armazena na arvore
                        token = tokenReader.tokenAtual();
                    } //se não retorna o erro
                    else
                        naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                        + " mal formada, faltou delimitadores ',' ou ';'"));                    
                }
                if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(";")) {
                    tokenReader.consome();
                    naoTerminal.add(new Terminal(token));
                } else {
                    naoTerminal.add(erro(this.getClass().getSimpleName(), "DEC_VARIAVEL mal formada, faltou ';'"));
                }
            } else {
                
                naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                        + " mal formada, faltou definir um nome para o tipo atribuido"));
            }            
        } else {
            naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                    + " mal formada, somente é válido ',' ou ';' ou '='"));
        }                    
        return naoTerminal;
    }
    
}
