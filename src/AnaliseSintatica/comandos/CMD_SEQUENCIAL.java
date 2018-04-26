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
import AnaliseSintatica.metodos.CHAMADA_METODO;
import AnaliseSintatica.operacoes.EXP_INC;

/**
 *
 * @author Jean Luiz
 */
public class CMD_SEQUENCIAL extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        Token t = tokenReader.tokenAtual();
       NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        if (t.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && t.getAtributo().equals("retorno")) {
            naoTerminal.add(new RETORNO().derivar());
        }else if(t.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && (t.getAtributo().toString().equals("escreva") || t.getAtributo().toString().equals("leia"))) {
            naoTerminal.add(new CHAMADA_METODO().derivar());
            t = tokenReader.tokenAtual();
            if(t.getAtributo().toString().equals(";")){
                tokenReader.consome();
                naoTerminal.add(new Terminal(t));
            }else 
                naoTerminal.add(erro(this.getClass().getSimpleName(),"Comando sequencial mal formado ';' esperado"));
        }else 
            naoTerminal.add(erro(this.getClass().getSimpleName(),"Comando sequencial mal formado 'escreva' ou 'leia' esperado"));
        return naoTerminal;
    }
    
}
