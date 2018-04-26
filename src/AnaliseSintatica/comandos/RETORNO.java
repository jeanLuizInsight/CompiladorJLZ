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
import AnaliseSintatica.metodos.VALOR;

/**
 *
 * @author Jean Luiz
 */
public class RETORNO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        NaoTerminal naoTerminal = new NaoTerminal("RETORNO");
        Token t = tokenReader.tokenAtual();        
        if(t.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && t.getAtributo().toString().equals("retorno")) {
            tokenReader.consome();               //avança indice
            naoTerminal.add(new Terminal(t));    //armazena na arvore 
        }else{
            return erro(this.getClass().getSimpleName(),"erro: Método retorno mal informado");
        }
        //id, novo, escreva, leia,  '(', num, '!', '-'
        t = tokenReader.tokenAtual();
        if(t.getTokenDefine().equals(TokenDefines.IDENTIFICADOR) || t.getAtributo().toString().equals("novo")) {
                naoTerminal.add(new VALOR().derivar());
        } //não tem um else com erro, pois [ <VALOR> ] é opcional
        return naoTerminal;
    }    
}
