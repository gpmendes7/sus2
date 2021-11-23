package app.posprocessamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class SepararRegistrosSusRedomeUsadosENaoUsados {
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeModificadoCSV> registrosSus = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(AposUsoRecuperado).csv");
		System.out.println("registrosSus.size(): " + registrosSus.size());
		
		List<SusRedomeModificadoCSV> registrosSusUsados = new ArrayList<>();
		List<SusRedomeModificadoCSV> registrosSusNaoUsados = new ArrayList<>();
		
		for (SusRedomeModificadoCSV registroSus : registrosSus) {
			if(registroSus.getObservacaoUso() != null && !registroSus.getObservacaoUso().equals("")) {
				registrosSusUsados.add(registroSus);
			} else {
				registrosSusNaoUsados.add(registroSus);
			}	
		}
		
		System.out.println("registrosSusUsados.size(): " + registrosSusUsados.size());
		System.out.println("registrosSusNaoUsados.size(): " + registrosSusNaoUsados.size());
		
		registrosSusUsados.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
                "cpf", "dataNascimento", "municipioNotificacao", 
                "racaCor", "etnia", "nomeMae", 
                "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
                "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));

		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(UsadosNoPareamento).csv", registrosSusUsados);
		
		registrosSusNaoUsados.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
                "cpf", "dataNascimento", "municipioNotificacao", 
                "racaCor", "etnia", "nomeMae", 
                "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
                "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));

		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(NaoUsadosNoPareamento).csv", registrosSusNaoUsados);
	}

}
