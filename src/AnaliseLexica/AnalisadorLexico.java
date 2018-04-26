/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseLexica;

import AnaliseLexica.Automatos.AutomatoComentario;
import AnaliseLexica.Automatos.AutomatoDelimitador;
import AnaliseLexica.Automatos.AutomatoDigito;
import AnaliseLexica.Automatos.AutomatoErro;
import AnaliseLexica.Automatos.AutomatoEspaco;
import AnaliseLexica.Automatos.AutomatoIdentificador;
import AnaliseLexica.Automatos.AutomatoLetra;
import AnaliseLexica.Automatos.AutomatoNumero;
import AnaliseLexica.Automatos.AutomatoOperador;
import AnaliseLexica.Automatos.AutomatoString;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
public class AnalisadorLexico {
    private LeCodigoFonte ler;

    private List<Token> tokens;
    private List<TokenErro> erros;
    private AutomatoEspaco espaco;
    private AutomatoComentario comentario;
    private AutomatoDelimitador delimitador;
    private AutomatoDigito digito;
    private AutomatoIdentificador identificador;
    private AutomatoLetra letra;
    private AutomatoNumero numero;
    private AutomatoOperador operador;
    private AutomatoErro erroLexico;
    private AutomatoString string;
    private Simbolos simbolos;

    
    public AnalisadorLexico(String code) {
        this.ler = new LeCodigoFonte(code);        
        this.tokens = new ArrayList<Token>();
        this.erros = new ArrayList<TokenErro>();        
        this.simbolos = new Simbolos();        
        this.espaco = new AutomatoEspaco(ler);
        this.comentario = new AutomatoComentario(ler);
        this.delimitador = new AutomatoDelimitador(ler);
        this.digito = new AutomatoDigito(ler);
        this.identificador = new AutomatoIdentificador(ler);
        this.letra = new AutomatoLetra(ler);
        this.numero = new AutomatoNumero(ler);
        this.operador = new AutomatoOperador(ler);        
        this.erroLexico = new AutomatoErro(ler);
        this.string = new AutomatoString(ler);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public List<TokenErro> getErros() {
        return erros;
    }
    
    public Simbolos getSimbolos() {
        return simbolos;
    }

    public void extraiTokens(){   
        //enquanto existir dados no arquivo
        while(ler.hasChar()){
            //System.out.println("Start Token " + ler.next());            
            //seta nulo a cada loop para atender todos os automatos
            Token token = null;
            espaco.extraiToken();            
            token = comentario.extraiToken();
            if(token == null)
                token = delimitador.extraiToken();
            if(token == null)
                token = identificador.extraiToken();
            if(token == null)
                token = letra.extraiToken();
            if(token == null)
                token = digito.extraiToken();
            if(token == null)
                token = numero.extraiToken();
            if(token == null)
                token = operador.extraiToken();            
            if(token == null)
                token = string.extraiToken(); 
            if(token == null)
                token = erroLexico.extraiToken();
            insereToken(token);
        }        
    }    
    
    private void insereToken(Token token){
        if(token == null)
            return;        
        System.out.println(token);  
        //se existir erro léxico armazena erro
        if(token.getTokenDefine().equals(TokenDefines.ERRO_LEXICO)){
            TokenErro tokenErro = null;            
            if(token instanceof TokenErro)
                tokenErro = (TokenErro) token;
            else
                tokenErro =  new TokenErro(token.getAtributo(), token.getLinha(), "Erro Léxico");            
            erros.add(tokenErro);
        //se tudo ok armazena token
        }else{
            tokens.add(token);
        }
        //se token é um identificador (letra, digito, underline)
        if(token.getTokenDefine().equals(TokenDefines.IDENTIFICADOR)){
            Identificador id = null;
            //se o objeto pertence a classe identificador
            if(token.getAtributo() instanceof Identificador)
                id = (Identificador)token.getAtributo();
            else
                id = new Identificador(token.getAtributo().toString());
            
            /*
             * caso o identificador já exista na tabela de símbolos
             * o atributo do token passa a referenciar o atributo que existe
             * na tabela
             * 
             * caso contrário o identificador é adicionado à tabela de símbolos
             */
            
            if(simbolos.ContemChave(id.getNome()))
                token.setAtributo(simbolos.get(id.getNome()));
            else
                simbolos.Adicionar(id);
        }        
    }
    /*
    public static void main (String[] args){
        String code = "13s 13 s _1s1s\n\n" +
                " blabla class /*hdsdghdgshghsd 'dshgdhds' \n" +
                "1212s1if+/-0.1\n"+
                "'frase ds eet ytyte w 343 rgt'";
        
        System.out.println("CODE: \n" + code + "\nEND_CODE\n");
        
        AnalisadorLexico anLex = new AnalisadorLexico(code);
        anLex.extraiTokens();
        
        System.out.println("TOKENS");
        for(Token t : anLex.getTokens()){
            System.out.println(t);
        }
        
        System.out.println("ERROS LÉXICOS");
        for(Token t : anLex.getErros()){
            System.out.println(t);
        }
        
        System.out.println("SÍMBOLOS");
        for(String key : anLex.getSimbolos().setChave()){
            System.out.println(anLex.getSimbolos().get(key));
        }
    } */
}
