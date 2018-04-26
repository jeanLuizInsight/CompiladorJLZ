/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.metodos;

import AnaliseLexica.Token;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import AnaliseSintatica.SimboloGramatical.Terminal;

/**
 *
 * @author Jean Luiz
 */
public class ATRIBUIR_VALOR extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //pega a instância do tokenReader
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        //consome o primeiro token
        Token t = tokenReader.tokenAtual();
        //cria um símbolo
        NaoTerminal ATRIBUIR_VALOR = new NaoTerminal("ATRIBUIR_VALOR");

        if(t.getAtributo().equals("=")){
            ATRIBUIR_VALOR.add(new Terminal(tokenReader.consome()));
            ATRIBUIR_VALOR.add(new VALOR().derivar());
        }else{
            ATRIBUIR_VALOR.add(erro("ATRIBUIR_VALOR", "não sei o que escrever aquí"));
        }
        return ATRIBUIR_VALOR;
    }
    
}
