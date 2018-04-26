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
public class DEC_PARAMETROS extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();        
        Token token = null;        
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());        
        //armazena derivando de TIPO novamente
        naoTerminal.add(new TIPO().derivar());        
        naoTerminal.add(new NOME().derivar());        
        token = tokenReader.tokenAtual();
        //faz a verificação para parametros
        while((token.getTokenDefine().equals(TokenDefines.OPERADOR)) && (token.getAtributo().toString().equals(",")) && tokenReader.hasToken()){
            if(token.getTokenDefine().equals(TokenDefines.OPERADOR) && token.getAtributo().toString().equals(",")) {
                tokenReader.consome();
                naoTerminal.add(new Terminal(token));
                //deriva de TIPO
                naoTerminal.add(new TIPO().derivar());
                naoTerminal.add(new NOME().derivar());
                token = tokenReader.tokenAtual();
            } else {
                naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                    + " mal formada"));
            }            
        }        
        return naoTerminal;
    }    
}
