/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.SimboloGramatical;

import AnaliseLexica.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
//classe estrutura para montar os erros sintaticos
public class ErroSintatico implements SimboloGramatical {
    //declaração para lista de tokens e descrição do erro
    private List<Token> tokens;
    private String descricao = "";

    //construtor vazio instanciando lista de tokens
    public ErroSintatico() {
        this.tokens = new ArrayList<Token>();
    }

    public ErroSintatico(List tokens) {
        this.tokens = tokens;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Token> getTokens() {
        return tokens;
    }
    
    public void add(Token t) {
        tokens.add(t);
    }
}
