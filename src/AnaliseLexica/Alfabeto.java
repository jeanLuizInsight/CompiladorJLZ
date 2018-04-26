/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AnaliseLexica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
//classe alfabeto
//contem: caracteres DELIMITADORES e OPERADORES
//        funções de verificação
public class Alfabeto {
    //List character:arraylist com array de caracteres
    public static List<Character> C_DELIMITADOR = new ArrayList<Character>();
    static {
        C_DELIMITADOR.add(';');
        C_DELIMITADOR.add('(');
        C_DELIMITADOR.add(')');
        C_DELIMITADOR.add('{');
        C_DELIMITADOR.add('}');
    }
    public static List<Character> C_OPERADOR = new ArrayList<Character>();
    static {
        C_OPERADOR.add('+');
        C_OPERADOR.add('-');
        C_OPERADOR.add('*');
        C_OPERADOR.add('/');
        C_OPERADOR.add('>');
        C_OPERADOR.add('<');
        C_OPERADOR.add('=');
        C_OPERADOR.add('&');
        C_OPERADOR.add('|');
        C_OPERADOR.add('!');
        C_OPERADOR.add(',');
    }
    //construtor vazio
    public Alfabeto() {        
    }
    //verifica se é digito
    public static boolean ehDigito(char caracter) {
        return Character.isDigit(caracter);
    }
    //verifica se é letra
    public static boolean ehLetra(char caracter) {
        return Character.isLetter(caracter);
    }
    //verifica se é aspas
    public static boolean ehAspa(char caracter) {
        return caracter == '\'';
    }
    //verifica caracteres em frases (PARA FRASES)
    public static boolean ehValido(char caracter) {
        final int charInt = (int) caracter;
        if(32 <= charInt && charInt <= 126)     //ASCII 32 ... 126
            return true;
        else
            return false;
    }
    //verifica se é operador
    public static boolean ehOperador(char caracter) {
        return C_OPERADOR.contains(caracter);
    }
    //verifica se é delimitador
    public static boolean ehDelimitador(char caracter) {
        return C_DELIMITADOR.contains(caracter);
    }
    //verifica se tem espaço
    public static boolean ehEspaco(char caracter) {
        if(caracter == ' ' || caracter == '\n' || caracter == '\r' || caracter == '\t' || caracter == '\f')
            return true;
        else
            return false;
                    
    }
}
