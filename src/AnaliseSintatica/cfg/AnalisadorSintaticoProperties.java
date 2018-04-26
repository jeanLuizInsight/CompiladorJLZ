/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnaliseSintatica.cfg;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 *
 * @author Jean Luiz
 */
public class AnalisadorSintaticoProperties {
    //declaração do diretório do meu arquivo properties
    private static final String BUNDLE_NAME = "AnaliseSintatica.cfg.AnalisadorSintaticoProperties";
    //armazena os dados no resourcebundle (pacote de recursos)
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    
    //construtor vazio
    public AnalisadorSintaticoProperties() {
    }
    
    //metodo retorna String(propriedades), com todos os dados contidos na chave
    public static String getPropertie(String chave) {
        try {
            return RESOURCE_BUNDLE.getString(chave);
        } catch (MissingResourceException e) {
            return null;
        }
    }
    
    //metodo retorna uma lista(propriedades), contendo todos os dados da chave, porem separados
    public static String[] getProperties(String chave) {
        String[] array = null;
        try {
            String resource = RESOURCE_BUNDLE.getString(chave);       //busca os dados da chave
            StringTokenizer st = new StringTokenizer(resource, ",");  //encontro todos os tokens separados por virgula
            int tokens = st.countTokens();       //conta quantos tokens tem
            array = new String[tokens];          //instancia o array com o tamanho necessário
            //percorre toda a chave
            for(int i = 0; i < tokens; i++) {
                array[i] = st.nextToken().trim();  //armazena um token em cada posição da lista
            }
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
        return array;
    }
    
    /*
     * chave = [resource,data]
     */
    public static String[][] getPropertiesMatriz(String chave) {
        String[][] array =  null; //declaração votro bidimencional
        try {
            String resource = RESOURCE_BUNDLE.getString(chave);   //busca os dados da chave         
            resource = resource.replaceAll("']'", "#");           //definindo o tipo da saída, agora cada fim de chave separados por tralha
            resource = resource.substring(0, resource.lastIndexOf(']')); //removendo espaços após o ']'
            StringTokenizer st = new StringTokenizer(resource, "]");     //encontro todos os tokens separados por fim de chaves
            int tokens = st.countTokens();                //conta quantos tokens tem
            array = new String[tokens][2];                //instancio o vetor com tamanho necessário
            //percorre todos
            for (int i = 0; i < tokens; i++) {
                String token = st.nextToken();           //le um por vez                
                token = token.replaceAll("#", "]");      //agora cada fim de tralha é separado por fim de chave           
                int index = token.indexOf(',');          //me retorna qual o indice o pedaço sa String corresponde(conta quantas virgulas tem), pra mim pegar os pedaços posteriormente
                array[i][0] = token.substring(token.indexOf('[') + 1,index).trim(); //coluna 0 armazena o tokenDefine (tipo do token)
                array[i][1] = token.substring(index + 1).trim();                    //coluna 1 armazena o valor dele (], int, leia, ...)
            }
        } catch (Exception e) {
            System.err.print(e.getMessage());              
        }
        return array;
    }
    
    //main teste
    public static void main(String args[]){
        System.out.println(AnalisadorSintaticoProperties.getPropertie("VALOR_NUM.SEG"));
        
        String[] properties = AnalisadorSintaticoProperties.getProperties("VALOR_NUM.SEG");
        for(int x = 0; x < properties.length; x++) {
            System.out.println(x + " " + properties[x]);
        }
        
        String[][] propertiesMatriz = AnalisadorSintaticoProperties.getPropertiesMatriz("VALOR_NUM.SEG");
        for(int i = 0; i < propertiesMatriz.length; i++)
            System.out.println(i + " " + propertiesMatriz[i][0] + " # " + propertiesMatriz[i][1]);
    }
}
