package app;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SivepRedomeModificadoCSV;
import csv.SivepRedomeModificadoCSVHandler;
import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ParearPacientesCOVIDComEvolucaoCasoObito {
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SivepRedomeModificadoCSV> registrosSivep = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/SIVEP_REDOME(Modificado-OBITO).csv");
		normalizarSexoSusSivep(registrosSivep);
		List<SusRedomeModificadoCSV> registrosSus = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(Modificado2).csv");
		selecionarPacientesEntre31E50Anos(registrosSivep, registrosSus);
	}
	
	public static void normalizarSexoSusSivep(List<SivepRedomeModificadoCSV> registrosSivep) {
		for (SivepRedomeModificadoCSV registroSivep : registrosSivep) {
			if(registroSivep.getSexo().equals("F")) {
				registroSivep.setSexo("Feminino");
			} else if(registroSivep.getSexo().equals("M")) {
				registroSivep.setSexo("Masculino");
			}
		}
	}
	
	public static void selecionarPacientesEntre31E50Anos(List<SivepRedomeModificadoCSV> registrosSivep, List<SusRedomeModificadoCSV> registrosSus) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		for (SivepRedomeModificadoCSV registroSivep : registrosSivep) {
			Integer idadeSivep = Integer.parseInt(registroSivep.getIdade());
			if(idadeSivep >= 31 && idadeSivep <= 50) {
				List<SusRedomeModificadoCSV> registrosSusFiltroSexo = registrosSus.stream()
																				  .filter(r -> r.getSexo().equals(registroSivep.getSexo()))
																				  .collect(Collectors.toList());
				System.out.println("registrosSusFiltroSexo.size(): " +  registrosSusFiltroSexo.size());
			}
		}
	}
	
	
}
