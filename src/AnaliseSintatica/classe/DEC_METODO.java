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
import AnaliseSintatica.metodos.EXP_METODO;

/**
 *
 * @author Jean Luiz
 */
public class DEC_METODO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();        
        Token token = null;        
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());        
        token = tokenReader.tokenAtual();
        //se abre parenteses (OBRIGATORIO), se não retorna erro
        if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("(")) {            
            tokenReader.consome();                   //avança indice
            naoTerminal.add(new Terminal(token));    //armazena na arvore            
        } else {
            naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                    + " mal formada - '(' não encontrado"));
        }
        //le atual
        token = tokenReader.tokenAtual();
        //se fecha parenteses
        if((token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(")"))) {            
            tokenReader.consome();                 //avança indice
            naoTerminal.add(new Terminal(token));  //armazena na arvore            
        } else {
            naoTerminal.add(new DEC_PARAMETROS().derivar());
            //le atual
            token = tokenReader.tokenAtual();
            //se fecha parenteses
            if((token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(")"))) {
                //avança indice
                tokenReader.consome();
                //armazena token instanciando terminal
                naoTerminal.add(new Terminal(token));            
            } else {
                naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                        + " mal formada - ')' não encontrado"));
            }
        }        
        //le atual
        token = tokenReader.tokenAtual();
        //se abre chaves
        if((token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("{"))) {            
            tokenReader.consome();                 //avança indice
            naoTerminal.add(new Terminal(token));  //armazena token na arvore            
        } else {
            naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                    + " mal formada - '{' não encontrado"));        
        }
        token = tokenReader.tokenAtual();
        if((token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("}"))){            
            tokenReader.consome();
            naoTerminal.add(new Terminal(token));            
        } else {
            //adiciona na arvore derivando de EXP_METODO
            naoTerminal.add(new EXP_METODO().derivar());
            token = tokenReader.tokenAtual();
            if((token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("}"))){            
                tokenReader.consome();
                naoTerminal.add(new Terminal(token));            
            } else {
                naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName()
                        + " mal formada - '}' não encontrado"));
            }
        }           
        return naoTerminal;
    }
    
}
