/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.SimboloGramatical;

/**
 *
 * @author Jean Luiz
 */
//estrutura para buscar e setar valor para raiz
public class ArvoreSintatica {
    //declaração da raiz 
    private SimboloGramatical raiz;

    //get e set
    public SimboloGramatical getRaiz() {
        return raiz;
    }

    public void setRaiz(SimboloGramatical raiz) {
        this.raiz = raiz;
    }    
}
