/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.operacoes;

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
public class FATOR extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        Token t = tokenReader.consome();
        SimboloGramatical simbolo = null;
        SimboloGramatical erro = null;
        NaoTerminal FATOR = new NaoTerminal("FATOR");
        if(t.getAtributo().equals("(")) {
            FATOR.add(new Terminal(t));
            t = tokenReader.consome();
            FATOR.add(new EXP().derivar());
            t = tokenReader.consome();
            if(t.getAtributo().equals(")")) {
                FATOR.add(new Terminal(t));
            } else {
                erro = erro("FATOR",
                        "Fechamento de parêntesis não encontrado próximo a "
                        +t.getAtributo().toString()+". Linha "+t.getLinha()+".");
            }
        } else {
            FATOR.add(new NRO().derivar());
        }
        if(erro != null) 
            simbolo = erro;
        else 
            simbolo = FATOR;
        return simbolo;
    }
    
}
