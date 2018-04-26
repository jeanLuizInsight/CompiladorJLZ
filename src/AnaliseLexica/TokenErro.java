/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AnaliseLexica;

import java.util.Objects;

/**
 *
 * @author Jean Luiz
 */
//classe tokenErro: contem função responsável por reportar o erro do token
public class TokenErro extends Token {
    //armazena descrição do erro ocorrido
    private String descricao;
    
    //construtor
    public TokenErro(Object atr, int lin, String desc) {
        //super(): referenciando a classe pai(Token()), invocando o construtor dela
        super(TokenDefines.ERRO_LEXICO, atr, lin);
        this.descricao = desc;
    }
    //get 
    public String getDescricao() {
        return descricao;
    }
    //set
    public void setDescricao(String desc) {
        this.descricao = desc;
    }
    //retorna a representação String dos objetos dados com descrição do erro no token
    @Override
    public String toString() {
        //StringBuilder:
        StringBuilder builder = new StringBuilder();  //declaro objeto
        builder.append("[TokenDefines: "+this.getTokenDefine()); //vou dando append sem precisar alocar novos
        builder.append(" Atributo: '"+this.getAtributo()+"'");
        builder.append(" Descrição: "+this.getDescricao());
        builder.append(" Linha: "+this.getLinha()+"]");
        return builder.toString();             //retornon todas
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TokenErro other = (TokenErro) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.descricao);
        return hash;
    }
    
    
}
