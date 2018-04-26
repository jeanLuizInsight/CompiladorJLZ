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
public class Identificador {
    private String nome;          //armazena nome do identificador

    //cosntrutor
    public Identificador(String nome) {
        this.nome = nome;
    }
    //busca
    public String getNome() {
        return nome;
    }
    //seta
    public void setNome(String nome) {
        this.nome = nome;
    }

    //retorna a representação String do objeto nome do identificador
    @Override
    public String toString() {
        return nome;
    }
}
