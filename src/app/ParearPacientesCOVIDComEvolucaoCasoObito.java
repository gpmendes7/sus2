package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ParearPacientesCOVIDComEvolucaoCasoObito {
	
	private static int MAX = 185;
	
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeModificadoCSV> registros = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv");
		selecionarPacientesEntre31E50Anos(registros);
		selecionarPacientesEntre51E59Anos(registros);
		selecionarPacientesEntre60E75Anos(registros);
	}
	
	public static void selecionarPacientesEntre31E50Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
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
			
			boolean ehObito = evolucaoCaso.equals("Óbito");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 31 && idade <= 50 && ehObito) {
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
		
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre31E50AnosComCOVID-GrupoObito).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre31E50AnosSemCOVID-GrupoObito).csv", selecionadosSemCovid);
	}
	
	public static void selecionarPacientesEntre51E59Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
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
			
			boolean ehObito = evolucaoCaso.equals("Óbito");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 51 && idade <= 59 && ehObito) {
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
		
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre51E59AnosComCOVID-GrupoObito).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre51E59AnosSemCOVID-GrupoObito).csv", selecionadosSemCovid);
	}
	
	public static void selecionarPacientesEntre60E75Anos(List<SusRedomeModificadoCSV> registros) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
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
			
			boolean ehObito = evolucaoCaso.equals("Óbito");
			
			boolean comCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Positivo");	
			boolean selecaoComCovidCompleto = selecionadosComCovid.size() == MAX;
			
			boolean semCOVID = (tipoTeste.equals("RT-PCR") || tipoTeste.equals("TESTE RÁPIDO - ANTÍGENO")) && resultadoTeste.equals("Negativo");
			boolean selecaoSemCovidCompleto = selecionadosSemCovid.size() == MAX;
			
			
			if(idade >= 60 && idade <= 75 && ehObito) {
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
		
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre60E75AnosComCOVID-GrupoObito).csv", selecionadosComCovid);
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado-PacienteEntre60E75AnosSemCOVID-GrupoObito).csv", selecionadosSemCovid);
	}
	
}
