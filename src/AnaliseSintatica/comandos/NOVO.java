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
import AnaliseSintatica.classe.TIPO;
import AnaliseSintatica.metodos.VALOR;

/**
 *
 * @author Jean Luiz
 */
public class NOVO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        Token t = tokenReader.consome();
        NaoTerminal naoTerminal = new NaoTerminal("NOVO");

        if(t.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && t.getAtributo().equals("novo")) {
            naoTerminal.add(new Terminal(t));
            t = tokenReader.consome();
        }else{
            return erro(this.getClass().getSimpleName(),"comando novo mal formado. 'novo' esperado");
        }
        naoTerminal.add(new TIPO().derivar());
        t = tokenReader.consome();
        if(t.getTokenDefine().equals(TokenDefines.DELIMITADOR) && t.getAtributo().equals("[")) {
            naoTerminal.add(new Terminal(t));
            t = tokenReader.consome();
            naoTerminal.add(new VALOR().derivar());
            t = tokenReader.consome();            
            if(t.getTokenDefine().equals(TokenDefines.DELIMITADOR) && t.getAtributo().equals("]")){
                naoTerminal.add(new Terminal(t));
                t = tokenReader.consome();
            }else{
                return erro(this.getClass().getSimpleName()," comando novo mal formado. ']' esperado");
            }
        } //não tem um else com erro, pois [ <VALOR> ] é opcional
        return naoTerminal;
    }
    
}
