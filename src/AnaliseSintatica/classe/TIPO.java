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

/**
 *
 * @author Jean Luiz
 */
//classe retorna um simbolo (tipo primitivo ou identificador)
public class TIPO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //pega a instância do tokenReader
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        //consome o primeiro token
        Token token = tokenReader.tokenAtual();
        //cria um símbolo
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        //se char ou int
        if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && 
                (token.getAtributo().toString().equals("char") || token.getAtributo().toString().equals("int") || token.getAtributo().toString().equals("String"))) {
            //adiciano derivando de TIPO_PRIMITIVO
            naoTerminal.add(new TIPO_PRIMITIVO().derivar());
        } else {
            naoTerminal.add(erro(this.getClass().getSimpleName(), "TIPO Deve ser char, int ou String."));
        }
        //retorna um símbolo
        return naoTerminal;
    }
    
}
