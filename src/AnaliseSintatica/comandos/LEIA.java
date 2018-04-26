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
public class LEIA extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        Token t = tokenReader.tokenAtual();
        String tokenDiretiva = (String) tokenReader.get(0).getAtributo().toString();
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        if(t.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && t.getAtributo().toString().equals("leia")) {
            if(tokenDiretiva.equals("importe")) {
                tokenReader.consome();
                naoTerminal.add(new Terminal(t));
            } else {
                naoTerminal.add(erro(this.getClass().getSimpleName(),"Método Leia - Diretiva 'Importe()' não encontrada"));
            }
        }else{
            naoTerminal.add(erro(this.getClass().getSimpleName(),"erro: leia esperado"));
        }
        t = tokenReader.tokenAtual();
        if(t.getTokenDefine().equals(TokenDefines.DELIMITADOR) && t.getAtributo().toString().equals("(")) {
            tokenReader.consome();
            naoTerminal.add(new Terminal(t));            
        }else{
            naoTerminal.add(erro(this.getClass().getSimpleName(),"erro: ( esperado"));
        }

        //quem fizer valor, que se lasqu pra verificar se da pau, ou nao        
        naoTerminal.add(new VALOR().derivar());
        t = tokenReader.tokenAtual();
        if(t.getTokenDefine().equals(TokenDefines.DELIMITADOR) && t.getAtributo().equals(")")) {
            tokenReader.consome();
            naoTerminal.add(new Terminal(t));
        }else{
            naoTerminal.add(erro(this.getClass().getSimpleName(),"erro: ) esperado"));
        }
        return naoTerminal;
    }
    
}
