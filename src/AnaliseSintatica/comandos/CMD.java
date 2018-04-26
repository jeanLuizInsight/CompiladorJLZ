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
public class CMD extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        Token t = tokenReader.tokenAtual();
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        //se SE
        if (t.getAtributo().toString().equals("se")) {
            naoTerminal.add(new SE().derivar());
        }else if(t.getAtributo().toString().equals("enquanto")) {
            naoTerminal.add(new ENQUANTO().derivar());
        }else if(t.getAtributo().toString().equals("retorno")
                || t.getAtributo().toString().equals("escreva")
                || t.getAtributo().toString().equals("leia")) {            
            
            naoTerminal.add(new CMD_SEQUENCIAL().derivar());
        }else 
            naoTerminal.add(erro(this.getClass().getSimpleName(),"Não é um comando válido"));
        return naoTerminal;
    }
    
}
