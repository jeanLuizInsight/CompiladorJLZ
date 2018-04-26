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
import AnaliseSintatica.comandos.CMD;
import AnaliseSintatica.metodos.EXP_METODO;

/**
 *
 * @author Jean Luiz
 */
public class EXP_CLASSE extends Producao {

    @Override
    public SimboloGramatical derivar() throws Exception {
        //chama leitor de tokens para correr os tokens
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();        
        Token token = null;
        //instancia o não terminal principal da produção em curso (armazena tokens filhos)
        NaoTerminal naoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        //armazena o atual
        token = tokenReader.tokenAtual();
        //se encontrou fecha chave termina
        if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("}"))
            return naoTerminal;
        //enquanto não encontra fecha chave
        
        while(!(token.getTokenDefine().equals(TokenDefines.DELIMITADOR)) && !(token.getAtributo().toString().equals("}")) && tokenReader.hasToken()){
            //se for palavra reservada char
            if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("char")){
                tokenReader.consome();
                naoTerminal.add(new Terminal(token));
                naoTerminal.add(new NOME().derivar());
                token = tokenReader.tokenAtual();
                //pode ser variavel ou método
                if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("(")) {
                    naoTerminal.add(new DEC_METODO().derivar());
                    token = tokenReader.tokenAtual();
                } else {
                    naoTerminal.add(new DEC_VARIAVEL().derivar());
                    token = tokenReader.tokenAtual();
                }              
            } else if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("int")){
                tokenReader.consome();
                naoTerminal.add(new Terminal(token));
                naoTerminal.add(new NOME().derivar());
                token = tokenReader.tokenAtual();
                if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("(")){
                    naoTerminal.add(new DEC_METODO().derivar());
                    token = tokenReader.tokenAtual();
                } else {
                    naoTerminal.add(new DEC_VARIAVEL().derivar());
                    token = tokenReader.tokenAtual();
                }
            } else if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("String")){
                tokenReader.consome();
                naoTerminal.add(new Terminal(token));
                naoTerminal.add(new NOME().derivar());
                token = tokenReader.tokenAtual();
                if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("(")){
                    naoTerminal.add(new DEC_METODO().derivar());
                    token = tokenReader.tokenAtual();
                }
                else {
                    naoTerminal.add(new DEC_VARIAVEL().derivar());
                    token = tokenReader.tokenAtual();
                }            
            } else if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("vazio")) {
                tokenReader.consome();
                naoTerminal.add(new Terminal(token)); 
                naoTerminal.add(new NOME().derivar());
                token = tokenReader.tokenAtual();
                //se abre parenteses (OBRIGATORIO), se não retorna erro
                if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("(")) {            
                    naoTerminal.add(new DEC_METODO().derivar());    
                    token = tokenReader.tokenAtual();
                } else
                    naoTerminal.add(erro(this.getClass().getSimpleName(), "Método mal formado - '(' não encontrado"));
                
            } else if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("classe")) {
                naoTerminal.add(new DEC_CLASSE_INTERNA().derivar());
                token = tokenReader.tokenAtual();
            } else if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && (token.getAtributo().toString().equals("se") || token.getAtributo().toString().equals("enquanto") || token.getAtributo().toString().equals("escreva") || token.getAtributo().toString().equals("leia"))) {
                naoTerminal.add(new CMD().derivar());
                token = tokenReader.tokenAtual();
            } else {
                naoTerminal.add(erro(this.getClass().getSimpleName(), "EXP_CLASSE, nenhum token ou comando válido encontrado"));
            }
        }        
        return naoTerminal;
    }
    
}
