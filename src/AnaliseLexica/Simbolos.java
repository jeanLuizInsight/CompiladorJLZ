/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AnaliseLexica;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Jean Luiz
 */
//classe responsavel por conter métodos para manipular oos simbolos em uma tabela hash
public class Simbolos {
    //armazena o simbolo (tipo lista hash map)
    private HashMap<String, Identificador> identificadores;

    //construtor instanciando objeto hash (elemento contem chave(string) e valor(identificador) associado)
    public Simbolos() {
        this.identificadores = new HashMap<String, Identificador>();
    }
    //busca simbola através da chave
    public Identificador get(String chave){
        return identificadores.get(chave.toUpperCase()); //apenas maiusculas
    }
    //adiciona valor do tipo identificador na lista
    public void Adicionar(Identificador identificador){
        identificadores.put(identificador.getNome().toUpperCase(), identificador);
    }
    //verifica se contem a chave especificada na lista
    public boolean ContemChave(String chave){
        //se existir matricula chave dentro do mapeamento retorna true
        return identificadores.containsKey(chave.toUpperCase()); //apenas maiusculas
    }
    //seta uma chave para o identificador
    //o método é do tipo String de interface Set(não preciso ter dado duplicado na coleção)
    public Set<String> setChave(){
        return identificadores.keySet();
    }
}
