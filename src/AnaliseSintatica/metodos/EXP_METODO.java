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
import AnaliseSintatica.classe.DEC_VARIAVEL;
import AnaliseSintatica.classe.NOME;
import AnaliseSintatica.comandos.CMD;

/**
 *
 * @author Jean Luiz
 */
public class EXP_METODO extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //pega a instância do tokenReader
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        //consome o primeiro token
        Token t = tokenReader.tokenAtual();
        //cria um símbolo
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        //se token identificador
        
        if (t.getAtributo().toString().equals("char") || t.getAtributo().toString().equals("int") || t.getAtributo().toString().equals("String")) {            
            tokenReader.consome();
            naoTerminal.add(new Terminal(t));
            naoTerminal.add(new NOME().derivar());
            t = tokenReader.tokenAtual();
            if(!t.getAtributo().toString().equals("(")) {
                naoTerminal.add(new DEC_VARIAVEL().derivar());
                naoTerminal.add(new EXP_METODO().derivar());
            } else {
                naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName() +"Impossível declarar um método dentro de outro interno ao Principal."));
            }                        
        } else if (t.getAtributo().toString().equals("se") ||
                t.getAtributo().toString().equals("enquanto") ||
                t.getAtributo().toString().equals("retorno") ||
                t.getAtributo().toString().equals("escreva") ||
                t.getAtributo().toString().equals("leia")) {            
            naoTerminal.add(new CMD().derivar());
            naoTerminal.add(new EXP_METODO().derivar());
        } else if(t.getAtributo().toString().equals("}")) {
            return naoTerminal;
        } else if(!t.getAtributo().toString().equals("}")) {
            naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName() + " mal formada - '}' não encontrado"));
        } else {
            naoTerminal.add(erro(this.getClass().getSimpleName(), this.getClass().getSimpleName() + " mal formada, nenhum token ou comando válido encontrado"));
        }
        //retorna um símbolo
        return naoTerminal;
    }    
}
