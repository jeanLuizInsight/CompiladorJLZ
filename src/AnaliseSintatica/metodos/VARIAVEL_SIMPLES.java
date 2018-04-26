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
import AnaliseSintatica.operacoes.VALOR_NUM;

/**
 *
 * @author Jean Luiz
 */
public class VARIAVEL_SIMPLES extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //pega a instância do tokenReader
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        //consome o primeiro token
        Token t = tokenReader.tokenAtual();
        //cria um símbolo
        NaoTerminal naoTerminal = new NaoTerminal("VARIAVEL_SIMPLES");


        if (t.getTokenDefine().equals(TokenDefines.IDENTIFICADOR)) {
            naoTerminal.add(new NOME().derivar());
            if (tokenReader.tokenAtual().getTokenDefine().getNome().equals("[")) {
                naoTerminal.add(new Terminal(tokenReader.consome()));
                naoTerminal.add(new VALOR_NUM().derivar());
                if (tokenReader.tokenAtual().getTokenDefine().getNome().equals("]")) {
                    naoTerminal.add(new Terminal(tokenReader.consome()));
                } else {
                    naoTerminal.add(erro("VARIAVEL_SIMPLES", "Você deve ter esquecido o ]"));
                }
            }
        } else {
             naoTerminal.add(erro("VARIAVEL_SIMPLES", "Não sei qual foi o erro"));
        }
        return naoTerminal;
    }    
}
