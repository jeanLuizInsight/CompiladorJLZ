/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.metodos;

import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;

/**
 *
 * @author Jean Luiz
 */
public class PARAM extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
         //pega a instância do tokenReader
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        //consome o primeiro token
        Token t = tokenReader.tokenAtual();
        //cria um símbolo
        NaoTerminal naoTerminal = new NaoTerminal("PARAM");
        if (tokenReader.tokenAtual().getAtributo().equals("novo") ||
                tokenReader.tokenAtual().getAtributo().equals("escreva") ||
                tokenReader.tokenAtual().getAtributo().equals("leia") ||
                tokenReader.tokenAtual().getAtributo().equals("-") ||
                tokenReader.tokenAtual().getAtributo().equals("(") ||
                tokenReader.tokenAtual().getAtributo().equals("!") ||
                tokenReader.tokenAtual().getTokenDefine().equals(TokenDefines.NUMERO) ||
                tokenReader.tokenAtual().getTokenDefine().equals(TokenDefines.IDENTIFICADOR)) {            
            naoTerminal.add(new VALOR().derivar());
            naoTerminal.add(new PARAM_X().derivar());        
        } else {
            naoTerminal.add(erro("PARAM", "não sei o que escrever aquí"));
        }
        return naoTerminal;
    }   
}
