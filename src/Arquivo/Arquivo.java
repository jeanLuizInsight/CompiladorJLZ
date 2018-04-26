/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Arquivo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jean Luiz
 */
//classe contendo os métodos responsáveis por manipular o arquivo contendo o código fonte
//salvar código, recuperar código, etc..
public class Arquivo {
    //retorna uma coleção contendo cada linha do arquivo
    public static List<String> getLinhas(File arquivoDePalavras) throws FileNotFoundException {
        //instancia objeto Scanner para receber os tokens do arquivo
        Scanner scanner = new Scanner(arquivoDePalavras);
        //instancia objeto array list para armazenar as linhas do arquivo
        ArrayList<String> palavras = new ArrayList<String>();
        //percorre todo o arquivo até a ultima linha
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //se a linha for nula, mesmo assim continua até terminar
            if(line == null || line.equals("")){
                continue;
            }
            //adiciona a linha no array list
            palavras.add(line);
        }
        return palavras;
    }
    //método que escreve no arquivo
    public static void Write(File running, String str) throws IOException {
        //canal de saída para escrita dos dados em bytes
        FileOutputStream fos = new FileOutputStream(running);
        fos.write(str.getBytes());
        fos.write('\n');
        fos.close();
    }
    //anexa conteúdo ao arquivo
    public static void AnexarLinha(String nomeArquivo, String conteudo) throws IOException {
        FileOutputStream fos = new FileOutputStream(nomeArquivo, true);
        fos.write(conteudo.getBytes());
        fos.write('\n');
        fos.close();
    }
    //método responsavel por criar um novo arquivo
    public static void CriarArquivo(File arquivo) throws IOException {
        if(!arquivo.exists()) {
            //obtem o nome do diretório/arquivo
            File dir = new File(arquivo.getParent());
            //se não existe cria o diretório
            if(!dir.exists()){
                dir.mkdirs();
            }
            //cria o arquivo
            arquivo.createNewFile();
        }
    }
    //método que copia um arquivo em outro
    public static void Copiar(File arquivo, File novoArquivo) throws IOException {
        final int BUFF_SIZE = 100000;
        //instancia um vetor de 100000 posições
        final byte[] buffer = new byte[BUFF_SIZE];
        Arquivo.CriarArquivo(arquivo);
        boolean EOF;              //para indicar final do arquivo
        FileInputStream input;                  //obtem bytes de entrada a partir de um arquivo(Lê fluxos de bytes) 
        ObjectInputStream inputStream = null;   //armazena bytes de entrada
        FileOutputStream output;                //obtem bytes de saída
        ObjectOutputStream outputStream = null; //armazena bytes de saida
        try {
            EOF = false;
            input  = new FileInputStream(arquivo);
            inputStream = new ObjectInputStream(input);
            output = new FileOutputStream(novoArquivo);
            outputStream = new ObjectOutputStream(output);
            //enquando não chegar o fim do arquivo
            while(!EOF) {
                //sincronizando o uso meu vetor para poder ler e escrever ao mesmo tempo
                synchronized(buffer) {
                    //le o array de bytes
                    int lerQuantidade = inputStream.read(buffer);
                    if(lerQuantidade == -1) {
                        break;
                    }
                    //grava um sub conjunto de bytes repassando o tamanho 
                    outputStream.write(buffer, 0, lerQuantidade);
                }
            }
        } catch(EOFException e) {
            EOF = true;
        }
        if(outputStream == null) {
            output = new FileOutputStream(arquivo);
            outputStream = new ObjectOutputStream(output);
        }
        if(inputStream != null)
            inputStream.close();
    }
}
