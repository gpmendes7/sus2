package app.preprocessamento;

import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.PacienteSivepCSV;
import csv.PacienteSivepCSVHandler;
import csv.SusRedomeCSV;
import csv.SusRedomeCSVHandler;

public class EliminarRegistrosPresentesNoSivep {
	
	public static void main(String[] args) throws IOException, ParseException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeCSV> registrosSus = SusRedomeCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(SemCopias).csv");
		System.out.println("registrosSus.size(): " + registrosSus.size());
		
		List<SusRedomeCSV> registrosSusSemCopia = obterRegistrosSemCopias(registrosSus);
		System.out.println("registrosSusSemCopia.size(): " + registrosSusSemCopia.size());
		
		List<PacienteSivepCSV> registrosSivep = PacienteSivepCSVHandler.carregarCSV("./arquivos/csv/Pacientes(SIVEP).csv");
		
		List<SusRedomeCSV> registrosSusSemRegistrosDoSivep = new ArrayList<SusRedomeCSV>();
					
		for (SusRedomeCSV registroSusSemCopia : registrosSusSemCopia) {  
			boolean registroSusPresenteNoSivep = false;
			
			for (PacienteSivepCSV registroSivep : registrosSivep) {
				if(ehMesmoPaciente(registroSivep, registroSusSemCopia)) {
					registroSusPresenteNoSivep = true;
					break;
				}
			}
			
			if(!registroSusPresenteNoSivep) {
				registrosSusSemRegistrosDoSivep.add(registroSusSemCopia);
			}

			//System.out.println("i: " + registrosSusSemCopia.indexOf(registroSusSemCopia));
		}
		
		System.out.println("registrosSusSemRegistrosDoSivep.size(): " + registrosSusSemRegistrosDoSivep.size());
		
		registrosSusSemRegistrosDoSivep.add(0, new SusRedomeCSV("campo1", "municipio", "nomeCompleto", "cpf", 
				                                                "dataNascimento", "municipioNotificacao", "racaCor", 
				                                                "etnia", "nomeMae"));
		
		SusRedomeCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(SemRegistrosDoSivep).csv", registrosSusSemRegistrosDoSivep);		
	}
	
	private static List<SusRedomeCSV> obterRegistrosSemCopias(List<SusRedomeCSV> registros) throws ParseException {
		Set<String> chaves = new HashSet<>(); 
		List<SusRedomeCSV> registrosSemCopia = new ArrayList<SusRedomeCSV>();
 
		for (SusRedomeCSV registro : registros) { 
			String nomeCompleto = removeAcentos(registro.getNomeCompleto().toUpperCase()); 
			
			String chave = nomeCompleto + registro.getDataNascimento();
		  
		    if(!chaves.contains(chave)) { 
		    	chaves.add(chave);
		    	registrosSemCopia.add(registro); 
		    } 
		}
		
		return registrosSemCopia;
	}
	
	private static boolean ehMesmoPaciente(PacienteSivepCSV paciente1, SusRedomeCSV paciente2) throws ParseException {
		String nomePaciente1 = removeAcentos(paciente1.getNomeCompleto().trim());
		String nomePaciente2 = removeAcentos(paciente2.getNomeCompleto().trim());
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimentoSus = sdf1.parse(paciente2.getDataNascimento());
		String dataNascimentoSusFormatada = sdf2.format(dataNascimentoSus);
				
		boolean temMesmoNome = nomePaciente1.equalsIgnoreCase(nomePaciente2);
		boolean temMesmaData = paciente1.getDataNascimento().equals(dataNascimentoSusFormatada);

		return temMesmoNome && temMesmaData;
	}
	
	
	private static String removeAcentos(String string) {
		return Normalizer.normalize(string, Normalizer.Form.NFD)
						 .replaceAll("[^\\p{ASCII}]", "");
	}

}
