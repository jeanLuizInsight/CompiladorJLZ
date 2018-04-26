/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica;

import AnaliseSintatica.SimboloGramatical.ArvoreSintatica;
import AnaliseSintatica.SimboloGramatical.ErroSintatico;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import AnaliseSintatica.classe.CLASSE;

/**
 *
 * @author Jean Luiz
 */
public class AnalisadorSintatico {
    
    private LeitorDeToken tokenReader;

    private ArvoreSintatica arvoreSintatica;
    
    public AnalisadorSintatico(LeitorDeToken tokenReader) {
        this.tokenReader = tokenReader;        
        arvoreSintatica = new ArvoreSintatica();
    }
    
    public void analisar(){
        //a análise sintática parte da classe CLASSE, a partir dele verifica todas as possibilidade em classe, comandos, metodos e operações
        //instancia produção
        Producao classe = new CLASSE();
        //instancia simbolo gramatical (arvore sintatica) nulo, será derivado de CLASSE e armazenado posteriormente 
        SimboloGramatical classeSG = null;        
        try {
            classeSG = classe.derivar();
        } catch (Exception ex) {
            System.err.println(ex);
        }

        if(tokenReader.hasToken()) {
            ErroSintatico erroSint =  new ErroSintatico();
            while(tokenReader.hasToken()){
                erroSint.add(tokenReader.consome());
            }            
            erroSint.setDescricao("Símbolos a partir de '"+ erroSint.getTokens().get(0).getAtributo() +"' não pertencem a nenhuma classe.");            
            if(classeSG != null){
                if(classeSG instanceof NaoTerminal)
                        ((NaoTerminal) classeSG).add(erroSint);
            }else {
                classeSG = erroSint;
            }
        }
        //
        arvoreSintatica.setRaiz(classeSG);
    }

    public ArvoreSintatica getArvoreSintatica() {
        return arvoreSintatica;
    }
}
