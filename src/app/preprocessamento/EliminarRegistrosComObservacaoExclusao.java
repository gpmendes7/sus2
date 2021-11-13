package app.preprocessamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class EliminarRegistrosComObservacaoExclusao {
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeModificadoCSV> registros = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv");
		System.out.println("registros: " + registros.size());
		
		List<SusRedomeModificadoCSV> registrosSelecionados = new ArrayList<SusRedomeModificadoCSV>();
		
		int qtdRegistrosComObservacaoExclusao = 0;
		
		for (SusRedomeModificadoCSV registro : registros) {
			if(registro.getObservacaoExclusao() != null && !registro.getObservacaoExclusao().equals("")) {
				qtdRegistrosComObservacaoExclusao++;
			} else {
				registrosSelecionados.add(registro);;
			}
		}
		
		System.out.println("qtdComObservacaoExclusao: " + qtdRegistrosComObservacaoExclusao);
		System.out.println("registrosSelecionados.size(): " + registrosSelecionados.size());
		
		registrosSelecionados.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
												                "cpf", "dataNascimento", "municipioNotificacao", 
												                "racaCor", "etnia", "nomeMae", 
												                "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
												                "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));

		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(SemRegistrosComObservacaoExclusao).csv", registrosSelecionados);
	}

}
