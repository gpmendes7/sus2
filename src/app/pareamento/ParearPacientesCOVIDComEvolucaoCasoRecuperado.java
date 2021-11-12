package app.pareamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ParearPacientesCOVIDComEvolucaoCasoRecuperado {
	
	private static int MAX = 811;
	
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeModificadoCSV> registros = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv");
		selecionarPacientesEntre22E42Anos(registros);
		selecionarPacientesEntre43E53Anos(registros);
	    selecionarPacientesEntre54E74Anos(registros);
	}
	
	public static void selecionarPacientesEntre22E42Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
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
			
			boolean ehCurado = evolucaoCaso.equals("Cura");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 22 && idade <= 42 && ehCurado) {
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
                "estadoTeste", "evolucaoCaso", "observacaoExclusao"));
		*/
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre22E42AnosComCOVID-GrupoRecuperado).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre22E42AnosSemCOVID-GrupoRecuperado).csv", selecionadosSemCovid);
	}
	
	public static void selecionarPacientesEntre43E53Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
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
			
			boolean ehCurado = evolucaoCaso.equals("Cura");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 43 && idade <= 53 && ehCurado) {
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
                "estadoTeste", "evolucaoCaso", "observacaoExclusao"));
	*/
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre43E53AnosComCOVID-GrupoRecuperado).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre43E53AnosSemCOVID-GrupoRecuperado).csv", selecionadosSemCovid);
	}
	
	public static void selecionarPacientesEntre54E74Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
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
			
			boolean ehCurado = evolucaoCaso.equals("Cura");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 54 && idade <= 74 && ehCurado) {
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
                "estadoTeste", "evolucaoCaso", "observacaoExclusao"));
		*/
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre54E74AnosComCOVID-GrupoRecuperado).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre54E74AnosSemCOVID-GrupoRecuperado).csv", selecionadosSemCovid);
	}
}
