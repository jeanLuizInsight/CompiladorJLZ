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
//enum para definir as palavras reservadas do meu compilador
public enum TabelaPalavrasReservadas {
    //definindo as palavras reservadas
    PAUSAR("pausar"), 
    CLASSE("classe"), 
    CONSTANTE("constante"), 
    SENAO("senao"), 
    IMPRIMA("imprima"),
    SE("se"), 
    NOVO("novo"), 
    LEIA("leia"), 
    RETORNO("retorno"), 
    VAZIO("vazio"), 
    ENQUANTO("enquanto"),  
    ESCREVA("escreva"), 
    CHAR("char"), 
    INT("int"),
    STRING("String"),   
    PUBLICO("publico"),
    PRIVADO("privado"),
    IMPORTE("importe"),
    PRINCIPAL("Principal"),
    ESTATICO("estatico"),
    ENDOFFILE("endOfFile");
    
    //variavel
    private String palavraReservada;
    
    //const
    private TabelaPalavrasReservadas(String pr) {
        this.palavraReservada = pr;
    }
    //get
    private String getPalavraReservada() {
        return palavraReservada;
    } 
    //função que checa a palavra, unidade do léxico definido(se é reservada ou não)
    public static TabelaPalavrasReservadas ChecaPR(String lexema) {
        //foreach retornando a matriz(através de values()) contendo todos os valores do enum
        for(TabelaPalavrasReservadas pr : TabelaPalavrasReservadas.values()) {
            if(pr.getPalavraReservada().compareToIgnoreCase(lexema) == 0) //compara palavra reservada com lexema (sem diferença, verdadeiro)
                return pr;
        }
        return null;
    }
    //retorna a representação String do objeto palavra reservada
    @Override
    public String toString() {
        return this.palavraReservada;
    }
}
