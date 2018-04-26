/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.classe;

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
public class TIPO_RETORNO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();        
        Token token = null;        
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());        
        token = tokenReader.tokenAtual();
        if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().equals("char")) {
            //adiciona na arvore derivando de TIPO (apenas para char ou int)
            naoTerminal.add(new TIPO().derivar()); 
        }
        else if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().equals("int")) {
            //
            naoTerminal.add(new TIPO().derivar());
        } //se vazio apenas adiciona token instanciando Terminal
        else if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().equals("vazio")) {            
            tokenReader.consome();
            naoTerminal.add(new Terminal(token));            
        } //se nenhuma opção retorna erro
        else
            return erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                    + " mal formada - 'tipo de retorno' não encontrado");
        
        return naoTerminal;
    }
    
}
