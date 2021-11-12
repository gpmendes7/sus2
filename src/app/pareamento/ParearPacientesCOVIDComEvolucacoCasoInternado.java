package app.pareamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ParearPacientesCOVIDComEvolucacoCasoInternado {
	
	private static int MAX = 134;
	
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeModificadoCSV> registros = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv");
		selecionarPacientesEntre25E44Anos(registros);
		selecionarPacientesEntre45E56Anos(registros);
		selecionarPacientesEntre57E81Anos(registros);
	}
	
	public static void selecionarPacientesEntre25E44Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<SusRedomeModificadoCSV> selecionadosComCovid = new ArrayList<SusRedomeModificadoCSV>();
		List<SusRedomeModificadoCSV> selecionadosSemCovid = new ArrayList<SusRedomeModificadoCSV>();
		
		for (SusRedomeModificadoCSV registro : registros) {
			if(!registro.getObservacaoExclusao().isBlank()) {
				continue;
			}

			Integer idade = Integer.parseInt(registro.getIdade());
			String evolucaoCaso = registro.getEvolucaoCaso();
			String resultadoTeste = registro.getResultadoTeste();
			String tipoTeste = registro.getTipoTeste();
			
			boolean ehInternado = evolucaoCaso.equals("Internado");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 25 && idade <= 44 && ehInternado) {
				 if(comCOVID && !selecaoComCovidCompleto) {
					 selecionadosComCovid.add(registro);
				 } else if(semCOVID && !selecaoSemCovidCompleto) {
					 selecionadosSemCovid.add(registro);
				 }
			}
			
			if(selecaoComCovidCompleto && selecaoSemCovidCompleto) {
				break;
			}
 			
		}
		
		/*
		 * selecionadosComCovid.add(0, new SusRedomeModificadoCSV("campo1", "municipio",
		 * "nomeCompleto", "cpf", "dataNascimento", "municipioNotificacao", "racaCor",
		 * "etnia", "nomeMae", "dataNotificacao", "idade", "resultadoTeste",
		 * "dataTeste", "tipoTeste", "estadoTeste", "evolucaoCaso",
		 * "observacaoExclusao"));
		 * 
		 * selecionadosSemCovid.add(0, new SusRedomeModificadoCSV("campo1", "municipio",
		 * "nomeCompleto", "cpf", "dataNascimento", "municipioNotificacao", "racaCor",
		 * "etnia", "nomeMae", "dataNotificacao", "idade", "resultadoTeste",
		 * "dataTeste", "tipoTeste", "estadoTeste", "evolucaoCaso",
		 * "observacaoExclusao"));
		 */
		
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre25E44AnosComCOVID-GrupoInternado).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre25E44AnosSemCOVID-GrupoInternado).csv", selecionadosSemCovid);
	}
	
	public static void selecionarPacientesEntre45E56Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<SusRedomeModificadoCSV> selecionadosComCovid = new ArrayList<SusRedomeModificadoCSV>();
		List<SusRedomeModificadoCSV> selecionadosSemCovid = new ArrayList<SusRedomeModificadoCSV>();
		
		for (SusRedomeModificadoCSV registro : registros) {
			if(!registro.getObservacaoExclusao().isBlank()) {
				continue;
			}

			Integer idade = Integer.parseInt(registro.getIdade());
			String evolucaoCaso = registro.getEvolucaoCaso();
			String resultadoTeste = registro.getResultadoTeste();
			String tipoTeste = registro.getTipoTeste();
			
			boolean ehInternado = evolucaoCaso.equals("Internado");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 45 && idade <= 56 && ehInternado) {
				 if(comCOVID && !selecaoComCovidCompleto) {
					 selecionadosComCovid.add(registro);
				 } else if(semCOVID && !selecaoSemCovidCompleto) {
					 selecionadosSemCovid.add(registro);
				 }
			}
			
			if(selecaoComCovidCompleto && selecaoSemCovidCompleto) {
				break;
			}
 			
		}
		
		/*
		 * selecionadosComCovid.add(0, new SusRedomeModificadoCSV("campo1", "municipio",
		 * "nomeCompleto", "cpf", "dataNascimento", "municipioNotificacao", "racaCor",
		 * "etnia", "nomeMae", "dataNotificacao", "idade", "resultadoTeste",
		 * "dataTeste", "tipoTeste", "estadoTeste", "evolucaoCaso",
		 * "observacaoExclusao"));
		 * 
		 * selecionadosSemCovid.add(0, new SusRedomeModificadoCSV("campo1", "municipio",
		 * "nomeCompleto", "cpf", "dataNascimento", "municipioNotificacao", "racaCor",
		 * "etnia", "nomeMae", "dataNotificacao", "idade", "resultadoTeste",
		 * "dataTeste", "tipoTeste", "estadoTeste", "evolucaoCaso",
		 * "observacaoExclusao"));
		 */
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre45E56AnosComCOVID-GrupoInternado).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre45E56AnosSemCOVID-GrupoInternado).csv", selecionadosSemCovid);
	}
	
	public static void selecionarPacientesEntre57E81Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<SusRedomeModificadoCSV> selecionadosComCovid = new ArrayList<SusRedomeModificadoCSV>();
		List<SusRedomeModificadoCSV> selecionadosSemCovid = new ArrayList<SusRedomeModificadoCSV>();
		
		for (SusRedomeModificadoCSV registro : registros) {
			if(!registro.getObservacaoExclusao().isBlank()) {
				continue;
			}

			Integer idade = Integer.parseInt(registro.getIdade());
			String evolucaoCaso = registro.getEvolucaoCaso();
			String resultadoTeste = registro.getResultadoTeste();
			String tipoTeste = registro.getTipoTeste();
			
			boolean ehInternado = evolucaoCaso.equals("Internado");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 57 && idade <= 81 && ehInternado) {
				 if(comCOVID && !selecaoComCovidCompleto) {
					 selecionadosComCovid.add(registro);
				 } else if(semCOVID && !selecaoSemCovidCompleto) {
					 selecionadosSemCovid.add(registro);
				 }
			}
			
			if(selecaoComCovidCompleto && selecaoSemCovidCompleto) {
				break;
			}
 			
		}
		
		/*
		selecionadosComCovid.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
                "cpf", "dataNascimento", "municipioNotificacao", 
                "racaCor", "etnia", "nomeMae", 
                "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
                "estadoTeste", "evolucaoCaso", "observacaoExclusao"));
		
		selecionadosSemCovid.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
                "cpf", "dataNascimento", "municipioNotificacao", 
                "racaCor", "etnia", "nomeMae", 
                "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
                "estadoTeste", "evolucaoCaso", "observacaoExclusao"));*/
		
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre57E81AnosComCOVID-GrupoInternado).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre57E81AnosSemCOVID-GrupoInternado).csv", selecionadosSemCovid);
	}
}
