package app.pareamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ParearPacientesCOVIDComEvolucaoCasoUti {
	
	private static int MAX = 113;
	
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeModificadoCSV> registros = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv");
		selecionarPacientesEntre26E42Anos(registros);
		selecionarPacientesEntre43E53Anos(registros);
		selecionarPacientesEntre54E67Anos(registros);
	}
	
	public static void selecionarPacientesEntre26E42Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
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
			
			boolean ehUti = evolucaoCaso.equals("Internado em UTI");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 26 && idade <= 42 && ehUti) {
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
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre26E42AnosComCOVID-GrupoUti).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre26E42AnosSemCOVID-GrupoUti).csv", selecionadosSemCovid);
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
			
			boolean ehUti = evolucaoCaso.equals("Internado em UTI");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 43 && idade <= 53 && ehUti) {
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
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre43E53AnosComCOVID-GrupoUti).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre43E53AnosSemCOVID-GrupoUti).csv", selecionadosSemCovid);
	}
	
	public static void selecionarPacientesEntre54E67Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
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
			
			boolean ehUti = evolucaoCaso.equals("Internado em UTI");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 54 && idade <= 67 && ehUti) {
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
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre54E67AnosComCOVID-GrupoUti).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre54E67AnosSemCOVID-GrupoUti).csv", selecionadosSemCovid);
	}
}
