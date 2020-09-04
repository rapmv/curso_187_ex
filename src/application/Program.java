package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		//para leitura de cada linha do arquivo
		String linhaArq;

		//separar os itens do arquivo
		String separador = ",";

		System.out.print("Digite o caminho do arquivo que deseja ler: ");
		String arq = sc.nextLine();

		//no try já vai ler os arquivos.
		try (BufferedReader br = new BufferedReader(new FileReader(arq))){		
			
			//enquanto tiver linhas ele vai repetir
			while ((linhaArq = br.readLine())!=null) {	
				
				//debug
				System.out.println(linhaArq);
				
				gravar(linhaArq, arq);											
			}		
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		finally {
			sc.close();
		}
	}
	
	//escrita no novo arquivo
	public static void gravar(String linhaArq, String arq) {
		
		//separando cada item da linha num vetor(ARRAY)
		String[] itens = linhaArq.split(",");
		
		Double total = mult(itens[1], itens[2]);	
		
		String arq3 = caminho(arq);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(arq3, true))) {
			bw.write(itens[0]+", "+ String.format("%.2f", total));	
		}
		catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}	
		
	}
	
	//resultado do valor final
	public static double mult(String num1, String num2) {
		
		//convertendo pra double pra depois fazer a operação
		double num_1 = Double.parseDouble(num1);
		double num_2 = Double.parseDouble(num2);
		
		return num_1*num_2;
	}
	
	public static String caminho(String arq) {
		
		File arq2 = new File(arq);
		
		String aux = arq2.getParent();
		                            
		boolean success = new File(aux+"\\out").mkdir();
		
		return aux + "\\out\\summary.csv";
		
	}

}