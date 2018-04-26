/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AnaliseLexica.Automatos;

import AnaliseLexica.LeCodigoFonte;
import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseLexica.TokenErro;

/**
 *
 * @author Jean Luiz
 */
public class AutomatoComentario {
    private LeCodigoFonte ler;
    private Token token;
    private char c;
    
    //construtor
    public AutomatoComentario(LeCodigoFonte lcf) {
        this.ler = lcf;
    }
    ////método do tipo Token responsável ler o código e extrair os comentários
    public Token extraiToken() {
        ler.Limpar();                               //posiciona cursor no inicio do arquivo
        c = ler.Proximo();                          //le proximo caracter
        if(c == '/') {                              //se for barra (primeiro caracter que indica comen)
            c = ler.Proximo();                      //le proximo
            if(c == '*') {                          //se for asterisco (segundo caracter que indica coment é bloco de comentário)
                while(c != ler.EOF) {               //enquanto os dados não acabarem
                    c = ler.Proximo();              //le proximo
                    while(c == '*') {               //enquanto caracter igual asterisco
                        c = ler.Proximo();
                        if(c == '/') {              //se caracter igual a barra fim do comentário 
                            int linha = ler.getLinhaAtual();
                            ler.Proximo();
                            //leio o token dentro do loop
                            token = new Token(TokenDefines.COMENTARIO, ler.FecharToken(), linha);
                            return token;
                        }
                    }
                }
                ler.Proximo();
                int linha = ler.getLinhaAtual();
                token = new TokenErro(ler.FecharToken(), linha, "Faltou fechar o comentário!");
                return token;
            }
        }
        return null;
    }
}
