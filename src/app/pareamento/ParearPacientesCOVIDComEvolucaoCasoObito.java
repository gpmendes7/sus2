package app.pareamento;

import static app.pareamento.FiltrosPareamento.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SivepRedomeModificadoCSV;
import csv.SivepRedomeModificadoCSVHandler;
import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ParearPacientesCOVIDComEvolucaoCasoObito {
	
	//private static int TAMANHO_REGISTROS_FAIXA = 185;
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		List<SivepRedomeModificadoCSV> registrosSivep = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/SIVEP_REDOME(Modificado-OBITO).csv");
		System.out.println("registrosSivep.size(): " + registrosSivep.size());
		
		List<SusRedomeModificadoCSV> registrosSus = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(SemRegistrosComObservacaoExclusao).csv");
		System.out.println("registrosSus.size(): " + registrosSus.size());
		
		selecionarPacientesEntre31E50Anos(registrosSivep, registrosSus);
	}
	
	public static void selecionarPacientesEntre31E50Anos(List<SivepRedomeModificadoCSV> registrosSivep, List<SusRedomeModificadoCSV> registrosSus) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {	
		//System.out.println("************************************** ");
		//System.out.println("selecionarPacientesEntre31E50Anos");
		
		//System.out.println("filtrarRegistrosSivepPorFaixaEtaria");
		List<SivepRedomeModificadoCSV> registrosSivepFiltrados = filtrarRegistrosSivepPorFaixaEtaria(registrosSivep, 31, 50);
		//System.out.println("registrosSivepFiltrados.size(): " + registrosSivepFiltrados.size());
		
		List<SusRedomeModificadoCSV> registrosSusFiltrados = registrosSus;
		
		for (SivepRedomeModificadoCSV registroSivepFiltrado : registrosSivepFiltrados) {
			registrosSusFiltrados = filtrarRegistrosSusPorFaixaEtaria(registrosSusFiltrados, 31, 50);
			registrosSusFiltrados = filtrarRegistrosSusPorSexo(registrosSusFiltrados, registroSivepFiltrado);
			registrosSusFiltrados = filtrarRegistrosSusPorDataNotificacao(registrosSusFiltrados, registroSivepFiltrado);
			registrosSusFiltrados = filtrarRegistrosSusPorMunicipioOuArea(registrosSusFiltrados, registroSivepFiltrado);
			registrosSusFiltrados = filtrarRegistrosSusPorRacaCor(registrosSusFiltrados, registroSivepFiltrado);
			registrosSusFiltrados = filtrarRegistrosSusPorTipoTeste(registrosSusFiltrados);
			
			System.out.println("registrosSusFiltrados.size(): " + registrosSusFiltrados.size());
			
			/*
			
			System.out.println("filtrarRegistrosSusPorResultado (Positivo)");
			List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoPositivo = filtrarRegistrosSusPorResultado(registrosSusFiltrados, "Positivo");
			System.out.println("registrosSusFiltradosComResultadoPositivo.size(): " + registrosSusFiltradosComResultadoPositivo.size());
			
			registrosSusFiltradosComResultadoPositivo.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
	                "cpf", "dataNascimento", "municipioNotificacao", 
	                "racaCor", "etnia", "nomeMae", 
	                "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
	                "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));
			SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(PacientesObitoEntre31E50AnosResultadoPositivo).csv", registrosSusFiltradosComResultadoPositivo);
			
			System.out.println("filtrarRegistrosSusPorResultado (Negativo)");
			List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoNegativo = filtrarRegistrosSusPorResultado(registrosSusFiltrados, "Negativo");
			System.out.println("registrosSusFiltradosComResultadoNegativo.size(): " + registrosSusFiltradosComResultadoNegativo.size());
			*/
			//List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoPositivoETipoTeste  = aplicarFiltroPorTipoTeste(registrosSusFiltrados);
			
			break;
		}
		
		//System.out.println("************************************** ");
		
		
	}

}
