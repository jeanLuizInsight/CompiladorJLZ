/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.operacoes;

import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import AnaliseSintatica.SimboloGramatical.Terminal;

/**
 *
 * @author Jean Luiz
 */
public class OP_OR extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        Token t = tokenReader.consome();
        SimboloGramatical simbolo = null;
        if(t.getTokenDefine().equals(TokenDefines.OPERADOR) && t.getAtributo().equals("||")) {
            simbolo = new Terminal(t);
        }else{
            simbolo = erro(this.getClass().getSimpleName(), this.getClass().getSimpleName() + " mal formado");
        }
        return simbolo;
    }    
}
