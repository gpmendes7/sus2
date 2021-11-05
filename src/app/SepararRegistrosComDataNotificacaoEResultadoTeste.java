package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class SepararRegistrosComDataNotificacaoEResultadoTeste {
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeModificadoCSV> registros = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv");
		
		List<SusRedomeModificadoCSV> registrosComDataNotificacaoEResultadoTeste = new ArrayList<SusRedomeModificadoCSV>();
		
		int qtdRegistrosSemDataNotificacaoEResultadoTeste = 0;
		
		for (SusRedomeModificadoCSV registro : registros) {
			boolean temDataNotificacao = registro.getDataNotificacao() != null && !registro.getDataNotificacao().isBlank() ? true : false;
			boolean temResultadoTeste = registro.getResultadoTeste() != null && !registro.getResultadoTeste().isBlank() ? true : false;
			
			if(temDataNotificacao && temResultadoTeste) {
				registrosComDataNotificacaoEResultadoTeste.add(registro);
			} 
			else if(!temDataNotificacao && !temResultadoTeste){
				qtdRegistrosSemDataNotificacaoEResultadoTeste++;
			}
		}
		
		System.out.println("qtdRegistrosSemDataNotificacaoEResultadoTeste: " + qtdRegistrosSemDataNotificacaoEResultadoTeste);
		
		registrosComDataNotificacaoEResultadoTeste.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
                "cpf", "dataNascimento", "municipioNotificacao", 
                "racaCor", "etnia", "nomeMae", 
                "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
                "estadoTeste", "evolucaoCaso", "observacaoExclusao"));
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-ComDataNotificacaoEResultadoTeste).csv", registrosComDataNotificacaoEResultadoTeste);
	}

}
