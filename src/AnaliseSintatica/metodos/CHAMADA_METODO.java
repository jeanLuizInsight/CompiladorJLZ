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
import AnaliseSintatica.comandos.ESCREVA;
import AnaliseSintatica.comandos.LEIA;

/**
 *
 * @author Jean Luiz
 */
public class CHAMADA_METODO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //pega a instância do tokenReader
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        //consome o primeiro token
        Token t = tokenReader.tokenAtual();
        //cria um símbolo
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());

        if (tokenReader.tokenAtual().getTokenDefine().equals(TokenDefines.IDENTIFICADOR)) {
            naoTerminal.add(new NOME().derivar());
            if (tokenReader.tokenAtual().getAtributo().toString().equals("(")) {
                naoTerminal.add(new Terminal(tokenReader.consome()));
                if (tokenReader.tokenAtual().getAtributo().toString().equals("novo") ||
                        tokenReader.tokenAtual().getAtributo().toString().equals("escreva") ||
                        tokenReader.tokenAtual().getAtributo().toString().equals("leia") ||
                        tokenReader.tokenAtual().getAtributo().toString().equals("-") ||
                        tokenReader.tokenAtual().getAtributo().toString().equals("(") ||
                        tokenReader.tokenAtual().getAtributo().toString().equals("!") ||
                        tokenReader.tokenAtual().getTokenDefine().equals(TokenDefines.NUMERO) ||
                        tokenReader.tokenAtual().getTokenDefine().equals(TokenDefines.IDENTIFICADOR)) {
                    
                    naoTerminal.add(new PARAM().derivar());
                    
                    if (tokenReader.tokenAtual().getAtributo().equals(")")) {
                        naoTerminal.add(new Terminal(tokenReader.consome()));
                    } else {
                        naoTerminal.add(erro(this.getClass().getSimpleName(), "Esqueceu o )"));
                    }
                } else {
                    naoTerminal.add(erro(this.getClass().getSimpleName(), "não sei o que escrever aquí"));
                }
            } else {
                naoTerminal.add(erro(this.getClass().getSimpleName(), "Esqueceu o ("));
            }
        }else if(tokenReader.tokenAtual().getAtributo().toString().equals("escreva")){
            naoTerminal.add(new ESCREVA().derivar());
            
        }else if(tokenReader.tokenAtual().getAtributo().toString().equals("leia")){
            naoTerminal.add(new LEIA().derivar());        
        }else {
            naoTerminal.add(erro(this.getClass().getSimpleName(), "Chamada de Método inválida"));
        }
        //retorna um símbolo
        return naoTerminal;
    }
    
}
