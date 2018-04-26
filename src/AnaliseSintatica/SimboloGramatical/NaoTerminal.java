/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.SimboloGramatical;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
//estrutura para valores de nodos não terminais
public class NaoTerminal implements SimboloGramatical {
    //declaração da minha lista de filhos da arvore e nome
    private List<SimboloGramatical> filhos;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //construtor passando apenas o nome
    public NaoTerminal(String nome) {
        this.nome = nome;
        filhos = new ArrayList<SimboloGramatical>();
    }
    
    //construtor vazio instanciando apenas a lista de filhos
    public NaoTerminal() {
        this.filhos = new ArrayList<SimboloGramatical>();
    }

    //busca lista de filhos
    public List<SimboloGramatical> getFilhos() {
        return filhos;
    }
    
    //adiciona simbolo na lista de filhos
    public void add(SimboloGramatical s) {
        filhos.add(s);
    }    
}
