/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.classe;

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
public class TIPO_PRIMITIVO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //pega a instância do tokenReader
        LeitorDeToken tokenReader =  LeitorDeToken.getInstance();
        //consome o primeiro token
        Token token = tokenReader.tokenAtual();
        //cria um símbolo
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        //verifica novamente se char ou int e adiciona na arvore token 
        if(token.getAtributo().toString().equals("char")) {
            naoTerminal.add(new Terminal(tokenReader.consome()));
        }else if(token.getAtributo().toString().equals("int")){
            naoTerminal.add(new Terminal(tokenReader.consome()));
        }else if(token.getAtributo().toString().equals("String")){
            naoTerminal.add(new Terminal(tokenReader.consome()));
        }else{
            naoTerminal.add(erro(this.getClass().getSimpleName(), "Deve ser char, int ou String."));
        }
        return naoTerminal;
    }
    
}
