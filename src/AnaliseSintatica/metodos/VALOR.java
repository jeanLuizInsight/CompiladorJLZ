/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.metodos;

import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import AnaliseSintatica.SimboloGramatical.Terminal;
import AnaliseSintatica.classe.NOME;
import AnaliseSintatica.comandos.NOVO;
import AnaliseSintatica.operacoes.EXP;

/**
 *
 * @author Jean Luiz
 */
public class VALOR extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //chama o leitor de token para correr os tokens
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        //consome o primeiro token
        Token t = tokenReader.tokenAtual();
        //cria um novo símbolo 
        NaoTerminal VALOR = new NaoTerminal(this.getClass().getSimpleName());
        Token tokenAnt = null;
        tokenAnt = tokenReader.Anterior(3);
        if(tokenAnt.getAtributo().toString().equals("char")) {
            if((t.getTokenDefine().equals(TokenDefines.IDENTIFICADOR) || t.getTokenDefine().equals(TokenDefines.NUMERO))) {
                //adiciona na arvore 
                VALOR.add(new NOME().derivar()); //operação EXP
            } else {
                VALOR.add(erro(this.getClass().getSimpleName(),"ATRIBUIÇÃO CHAR inválida"));
            }
        } else if(tokenAnt.getAtributo().toString().equals("int")) {
            if(t.getTokenDefine().equals(TokenDefines.NUMERO)){
                tokenReader.consome();
                VALOR.add(new Terminal(t));               
            } else {
                VALOR.add(erro(this.getClass().getSimpleName(),"ATRIBUIÇÃO INT inválida"));
            }
        } else if(tokenAnt.getAtributo().toString().equals("String")) {
            if(t.getTokenDefine().equals(TokenDefines.IDENTIFICADOR) || t.getTokenDefine().equals(TokenDefines.NUMERO)){
                while(!t.getAtributo().toString().equals(";")) {
                    //adiciona na arvore 
                    tokenReader.consome();
                    VALOR.add(new Terminal(t));
                    t = tokenReader.tokenAtual();
                    if(t.getTokenDefine().equals(TokenDefines.OPERADOR) || t.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) || t.getAtributo().toString().equals("}") || t.getAtributo().toString().equals("{")) {
                        break;
                    }
                }                
            } else {
                VALOR.add(erro(this.getClass().getSimpleName(),"ATRIBUIÇÃO STRING inválida"));
            }
        } else if(t.getAtributo().equals("novo")){
            //adiciona na arvore
            VALOR.add(new NOVO().derivar()); //comando NOVO deriva e armazena        
        } else if(t.getTokenDefine().equals(TokenDefines.IDENTIFICADOR) || t.getTokenDefine().equals(TokenDefines.NUMERO)) {
            tokenReader.consome();
            VALOR.add(new Terminal(t));
        } else {
            //se não adiciona erro na arvore
            VALOR.add(erro(this.getClass().getSimpleName(),"ATRIBUIÇÃO inválida"));
        }
        //retorna os simbolos
        return  VALOR;
    }
    
}
