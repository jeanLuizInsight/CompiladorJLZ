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
//classe Token: contem funções para dados do token(tipo, valor, linha)
public class Token {
    //variaveis 
    private TokenDefines tipo; //armazena tipo do token
    private Object atributo;     //valor
    private int linha;           //num linha
    
    //constru vazio
    protected Token(){
    }    
    //construtor passando a classe do tipo tokenDefines, instanciada em tabelasSeguintes (sintatico), saber o tipo do token
    public Token(TokenDefines token) {
        this.tipo = token;
    }
    //construtor (léxica)
    public Token(TokenDefines def, Object atr, int lin) {
        this.tipo = def;
        this.atributo = atr;
        this.linha = lin;
    }    
    //get tipo do token
    public TokenDefines getTokenDefine() {
        return tipo;
    }    
    //seta o valor 
    public void setAtributo(Object atr) {
        this.atributo = atr;
    }    
    //get valor
    public Object getAtributo() {
        return atributo;
    }
    //get linha
    public int getLinha() {
        return linha;
    }
    //seta linha
    public void setLinha(int lin) {
        this.linha = lin;
    }
    
    //retorna a representação String dos dados(tipo, valor, linha)
    public String toSring() {
        StringBuilder builder = new StringBuilder();
        builder.append("[TokenDefines: "+this.getTokenDefine());
        builder.append(" Atributo: "+this.getAtributo());
        builder.append(" Linha: "+this.getLinha());
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Token other = (Token) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.atributo, other.atributo)) {
            return false;
        }
        if (this.linha != other.linha) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        hash = 97 * hash + Objects.hashCode(this.atributo);
        hash = 97 * hash + this.linha;
        return hash;
    }    
}
