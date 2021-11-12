package app.contagem;

import java.io.IOException;
import java.util.List;

import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ContagemResultadoTeste {
	
	public static void main(String[] args) throws IOException {
		List<SusRedomeModificadoCSV> registros = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv");
		
		int qtd = 0;
		
		for (SusRedomeModificadoCSV registro : registros) {
			if(registro.getResultadoTeste() == null || registro.getResultadoTeste().equals("")) {
				qtd++;
			} 	
		}
		
		System.out.println("qtd: " + qtd);
	}

}
