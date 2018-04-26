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
public class LeCodigoFonte {
    //variaveis
    public static final char EOF = (char) 0;    //me indica fim do lexema(condição atingida quando não há mais dados a serem lidos)
    public static final boolean DEBUG = false;  //apenas pra mim debugar e verificar a lógica
    private String codigoFonte; //armazena toda a string de palavras com quebra de linhas, etc.
    private int atual;          //armazena o indice atual da string (posição do caracter)
    private int inicioToken;    //armazena o indice inicial do token 
    private int linha;          //armazena o numero de linhas
    
    //construtor
    public LeCodigoFonte(String cf) {
        this.codigoFonte = cf;
    }
    //verifica se ainda existe caracter na string atraves do indice repassado 
    private boolean hasChar(int index) {
        return index < codigoFonte.length(); //retorna verdadeiro apenas se o indice for menor que toda a string
    }
    //repassa o indice atual, para verificar se ainda existe caracter na string
    public boolean hasChar() {
        return hasChar(atual);
    }
    //incrementa indice do caracter da string
    public char Proximo() {
        this.atual++;           //incrementa o indice 
        return getChar(atual);  //retorna o caracter atual
    }
    //decrementa indice do caracter da string
    public char Anterior() {
        this.atual--;
        return getChar(atual);
    }
    //
    public void Limpar() {
        this.atual = this.inicioToken - 1;
    }
    //
    public void LimparTudo() {
        this.inicioToken = 0;
        this.atual = this.inicioToken - 1;
    }
    //set p/ codigoFonte
    public void setCodigoFonte(String cf) {
        this.codigoFonte = cf;  //
        this.linha = 1;         // primeira linha
        this.inicioToken = 0;   // inicio token
        this.atual = -1;        // indice inicial(vetor inicia em 0, para igualar valores deve ser atribuiido -1)
    }
    //busca caracter
    private char getChar(int index) {
        char caracter;
        if(hasChar(index))
            caracter = this.codigoFonte.charAt(index); //busca caracter do indice repassado
        else
            caracter = EOF; //não há dados a serem lidos
        return caracter;
    }
    //chama caracter pelo indice
    public char charAT(int index) {
        return getChar(index);
    }
    //busca linha
    public int getLinhaAtual() {
        return linha;
    }
    //função que será utilizada para verificar e fechar cada token
    public String FecharToken() {
        String token = "";
        int fimToken = atual;
        int debugInicioToken = inicioToken; //apenas pra testes
        if(fimToken > codigoFonte.length())  //
            fimToken = codigoFonte.length(); //fim dele é o tamanho da string
        if(fimToken > inicioToken) {
            token = codigoFonte.substring(inicioToken, fimToken);  //define o token pegando os caracteres do inicio ao fim dele
            this.inicioToken = fimToken;  //inicio é o atual
        }
        //foreach percorrendo todo o token (por array de char)
        for(char c : token.toCharArray()) {
            if(c == '\n')      //se nova linha
                this.linha++;  //incrementa 
        }
        if(DEBUG) //se modo debug v imprime 
            System.out.println("Token ("+token+") InicioToken "+debugInicioToken+" fimToken "+fimToken);
        return token;
    }    
    /*
    public static void main (String[] args){
        String codigoFonte = "inicio \n int 567 \n jean \n zanatta \n String nome = 'sou nota 10'";
        //String codigoFonte = "@";
        
        System.out.println(codigoFonte);
        LeCodigoFonte ler = new LeCodigoFonte(codigoFonte);
        
        System.out.println(ler.hasChar());
        ler.Limpar();
        System.out.println(ler.Proximo()+ " " + ler.atual);
        System.out.println(ler.Proximo()+ " " + ler.atual);
        System.out.println(ler.Proximo()+ " " + ler.atual);
        System.out.println(ler.Proximo()+ " " + ler.atual);
        System.out.println(ler.Proximo()+ " " + ler.atual);
        System.out.println(ler.Proximo()+ " " + ler.atual);
        System.out.println(ler.FecharToken()+ " " + ler.atual);
        
        ler.LimparTudo();
        while(ler.hasChar()){ //enquanto existir caracter
            char charAt = ler.Proximo();
            System.out.print(" (" + charAt);
            System.out.print(" " + ler.getLinhaAtual()+ ")");
            
            if(charAt == ' '){  //verifica se tem espaço dentro da string(para fechar o token)
                ler.Proximo();
                String token = ler.FecharToken();
                System.out.print("{" + token + " "+ ler.getLinhaAtual()+ "} ");
                ler.Limpar();
            }
        }
        
    }*/
}
