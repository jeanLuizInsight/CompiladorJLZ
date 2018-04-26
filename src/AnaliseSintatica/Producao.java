/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica;

import AnaliseLexica.Token;
import AnaliseSintatica.SimboloGramatical.ErroSintatico;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
public abstract class Producao {
    
    //método abstrato importado na classe CLASSE
    public abstract SimboloGramatical derivar() throws Exception;

    /* caso padrão, o método do Desespero procura por algum token que pertença
     * aos seguintes do não-terminal onde houve o erro
     */ 
    protected SimboloGramatical erro(String simbolo, String descricao) {
        List<Token> seguintes = TabelaSeguintes.getSeguintes(simbolo);        
        return erro(simbolo, descricao, seguintes);
    }
    
    /* caso especifico, o método do Desespero procura por algum token que pertença
     * à lista passada como parametro
     */ 
    protected SimboloGramatical erro(String simbolo, String descricao,  List<Token> tokensProcurados){
        ErroSintatico erro = new ErroSintatico();
        erro.setDescricao(descricao);        
        return metodoDoDesespero(erro, tokensProcurados);
    }

    //método  
    private SimboloGramatical metodoDoDesespero(ErroSintatico erro, List<Token> tokensProcurados) {
        Token encontrado = LeitorDeToken.getInstance().tokenAtual();       //armazena token atual
        while (LeitorDeToken.getInstance().hasToken()) {                   //enquanto existir tokens
            erro.add(encontrado);
            for (Token seg : tokensProcurados) {
                if (encontrado.getTokenDefine().equals(seg.getTokenDefine())) {
                    if (seg.getAtributo() == null) {
                        return erro;
                    } else if (encontrado.getAtributo().equals(seg.getAtributo())) {
                        return erro;
                    }
                }
            }
            encontrado = LeitorDeToken.getInstance().consome();
        }
        return erro;
    }
}
