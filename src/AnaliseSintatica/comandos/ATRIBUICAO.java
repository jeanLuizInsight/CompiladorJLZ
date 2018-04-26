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
import AnaliseSintatica.metodos.ATRIBUIR_VALOR;
import AnaliseSintatica.metodos.VARIAVEL;

/**
 *
 * @author Jean Luiz
 */
public class ATRIBUICAO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        Token t = tokenReader.consome();
        NaoTerminal naoTerminal = new NaoTerminal("ATRIBUICAO");
        naoTerminal.add(new VARIAVEL().derivar());
        //usa o see para não terminal
        t = tokenReader.tokenAtual();
        naoTerminal.add(new ATRIBUIR_VALOR().derivar());
        //consome, para os terminais
        t = tokenReader.consome();

        if(t.getTokenDefine().equals(TokenDefines.DELIMITADOR) && t.getAtributo().equals(";")){
            naoTerminal.add(new Terminal(t));
            t = tokenReader.consome();
        }else{
            return erro("atribuição","Atribuição mal formada. ';' esperado");
        }
        return naoTerminal;
    }    
}
