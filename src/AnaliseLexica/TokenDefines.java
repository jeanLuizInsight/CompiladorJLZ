/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AnaliseLexica;

/**
 *
 * @author Jean Luiz
 */
 /*
         * Palavras reservadas: pausar, classe, const, senao, se, novo, leia, retorno, void, enquato, escreva, int, char
         *
         * identificadores: Letra { Letra | Dígito | “_” }
         *
         * Número: Dígito { Dígito }
         *
         * Letra: a..z ∪ A..Z
         * 
         * Dígito: 0..9
         * 
         * Operadores: + - * / == != > >= < <= && || = ++ -- ,
         * 
         * Delimitadores: ; ( ) { }
         * 
         * Comentários: De / * até * /
         * 
         */
//enum para definir as identificações(tipos de tokens)
public enum TokenDefines {
    PALAVRA_RESERVADA ("Palavra Reservada"),
    IDENTIFICADOR     ("Identificador"),
    //LETRA             ("Letra"),
    CHARCONST         ("CharConst"),
    //DIGITO            ("Digito"),
    NUMERO            ("Numero"),
    OPERADOR          ("Operador"),
    DELIMITADOR       ("Delimitador"),
    ERRO_LEXICO       ("Erro Léxico"),
    COMENTARIO        ("Comentario");
    
    //variavel global
    private String nome;
    //construtor
    private TokenDefines(String n) {
        this.nome = n;
    }
    //geter
    public String getNome() {
        return nome;
    }
    //retorna a representação String do objeto nome (tipo de token)
    @Override
    public String toString() {
        return this.nome;
    }
}
