/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.classe;

import AnaliseLexica.Token;
import AnaliseLexica.TokenDefines;
import AnaliseSintatica.LeitorDeToken;
import AnaliseSintatica.Producao;
import AnaliseSintatica.SimboloGramatical.NaoTerminal;
import AnaliseSintatica.SimboloGramatical.SimboloGramatical;
import AnaliseSintatica.SimboloGramatical.Terminal;
import AnaliseSintatica.comandos.CMD;
import javax.swing.JOptionPane;

/**
 *
 * @author Jean Luiz
 */
public class CLASSE extends Producao {

    //método abstrato importado de Produção
    @Override
    public SimboloGramatical derivar() throws Exception {
        //chama o leitor de token para correr os tokens
        LeitorDeToken tokenReader = LeitorDeToken.getInstance();
        Token token = null;        
        //instancia o não terminal principal da produção em curso
        NaoTerminal thisNaoTerminal = new NaoTerminal(this.getClass().getSimpleName());
        //busca token do indice atual
        token = tokenReader.tokenAtual();
        //JOptionPane.showMessageDialog(null, token.getTokenDefine() + " | "+ token.getAtributo());
        System.out.println(token.getAtributo().toString());
        
        //se tem diretivas
        if (token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("importe")) {
            tokenReader.consome();
            Terminal t = new Terminal(token);   //instancia o terminal
            thisNaoTerminal.add(t);             //adiciona class à árvore
            token = tokenReader.tokenAtual();
            if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals("(")) {
                tokenReader.consome();
                thisNaoTerminal.add(new Terminal(token));             //adiciona class à árvore
                token = tokenReader.tokenAtual();
                if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(")")) {
                    tokenReader.consome();
                    thisNaoTerminal.add(new Terminal(token));             //adiciona class à árvore
                    token = tokenReader.tokenAtual();
                    if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().toString().equals(";")) {
                         tokenReader.consome();
                        thisNaoTerminal.add(new Terminal(token));             //adiciona class à árvore
                        token = tokenReader.tokenAtual();
                    } else {
                        return erro(this.getClass().getSimpleName(), "Erro Sintático - ';' não encontrado");
                    }
                } else {
                    return erro(this.getClass().getSimpleName(), "Diretiva mal formada - ')' não encontrado");
                }
            } else {
                return erro(this.getClass().getSimpleName(), "Diretiva mal formada - '(' não encontrado");
            }
        }
        
        //classe principal, sem ela não funciona
        if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("publico")) {
            tokenReader.consome();              //avança indice
            Terminal t = new Terminal(token);   //instancia o terminal
            thisNaoTerminal.add(t);             //adiciona class à árvore
            //proximo token deve ser Principal
            token = tokenReader.tokenAtual();
            if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("estatico")) {
                tokenReader.consome();              //avança indice
                thisNaoTerminal.add(new Terminal(token));             //adiciona class à árvore
                //proximo token deve ser Principal
                token = tokenReader.tokenAtual();
                if (token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("vazio")) {
                    //System.out.println("IF");
                    tokenReader.consome();              //avança indice
                    thisNaoTerminal.add(new Terminal(token));             //adiciona class à árvore
                    //proximo token deve ser Principal
                    token = tokenReader.tokenAtual();
                    if(token.getTokenDefine().equals(TokenDefines.PALAVRA_RESERVADA) && token.getAtributo().toString().equals("Principal")) {
                        tokenReader.consome();              //avança indice
                        thisNaoTerminal.add(new Terminal(token));             //adiciona class à árvore
                        //próximo deve ser {
                        token = tokenReader.tokenAtual();
                        //se token delimitador e abre chave
                        if (token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().equals("(")) {
                            tokenReader.consome();//avança index
                            thisNaoTerminal.add(new Terminal(token)); //adiciona ( à árvore            
                            token = tokenReader.tokenAtual();
                            if(token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().equals(")")) {
                                tokenReader.consome();//avança index
                                thisNaoTerminal.add(new Terminal(token)); //adiciona ) à árvore
                                //próximo deve ser {
                                token = tokenReader.tokenAtual();
                                //se token delimitador e abre chave
                                if (token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().equals("{")) {
                                    tokenReader.consome();//avança index
                                    thisNaoTerminal.add(new Terminal(token)); //adiciona { à árvore   

                                    //verifica (trata) o que está dentro da classe principal e adiciona tudo na arvore
                                    thisNaoTerminal.add(new EXP_CLASSE().derivar());                                    

                                    token = tokenReader.tokenAtual();
                                    //se } fim da classe adiciona token terminal a arvore
                                    if (token.getTokenDefine().equals(TokenDefines.DELIMITADOR) && token.getAtributo().equals("}")) {
                                        tokenReader.consome();//avança index
                                        thisNaoTerminal.add(new Terminal(token)); //adiciona } à árvore                                    
                                    } else {
                                        return erro(this.getClass().getSimpleName(),"Método Principal mal formado - '}' não encontrado");
                                    }        
                                    return thisNaoTerminal;                                            
                                } else {
                                    return erro(this.getClass().getSimpleName(),"Método Principal mal formado - '{' não encontrado");
                                }
                            } else {
                                return erro(this.getClass().getSimpleName(), "Método Principal mal formado - ')' não encontrado");
                            }
                        } else {
                            return erro(this.getClass().getSimpleName(),"Método Principal mal formado - '(' não encontrado");
                        }
                    } else {
                        return erro(this.getClass().getSimpleName(), "Método Principal não reconhecido - 'Principal' não encontrado");
                    }       
                } else {
                    //System.out.println("ELSE");
                    return erro(this.getClass().getSimpleName(), "Método Principal não reconhecido - 'vazio' não encontrado");
                }              
            } else {
                return erro(this.getClass().getSimpleName(), "Método Principal não reconhecido - 'estatico' não encontrado");
            }
        } else {
            return erro(this.getClass().getSimpleName(), "Método Principal não reconhecido - 'publico' não encontrado");
        }         
    }        
}
