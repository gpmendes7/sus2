package app.pareamento;

import static app.pareamento.FiltrosPareamento.aplicarFiltroPorDataNotificacao;
import static app.pareamento.FiltrosPareamento.aplicarFiltroPorFaixaEtaria;
import static app.pareamento.FiltrosPareamento.aplicarFiltroPorMunicipioOuArea;
import static app.pareamento.FiltrosPareamento.aplicarFiltroPorRacaCor;
import static app.pareamento.FiltrosPareamento.aplicarFiltroPorSexo;
import static app.pareamento.FiltrosPareamento.*;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSivepPorFaixaEtaria;

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
	
	public static List<SusRedomeModificadoCSV> selecionarPacientesEntre31E50Anos(List<SivepRedomeModificadoCSV> registrosSivep, List<SusRedomeModificadoCSV> registrosSus) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {	
		//System.out.println("************************************** ");
		//System.out.println("selecionarPacientesEntre31E50Anos");
		
		//System.out.println("filtrarRegistrosSivepPorFaixaEtaria");
		List<SivepRedomeModificadoCSV> registrosSivepFiltrados = filtrarRegistrosSivepPorFaixaEtaria(registrosSivep, 31, 50);
		//System.out.println("registrosSivepFiltrados.size(): " + registrosSivepFiltrados.size());
		
		List<SusRedomeModificadoCSV> registrosSusFiltrados = registrosSus;
		
		for (SivepRedomeModificadoCSV registroSivepFiltrado : registrosSivepFiltrados) {
			registrosSusFiltrados = aplicarFiltroPorFaixaEtaria(registrosSusFiltrados, 31, 50);
			registrosSusFiltrados = aplicarFiltroPorSexo(registrosSusFiltrados, registroSivepFiltrado);
			registrosSusFiltrados = aplicarFiltroPorDataNotificacao(registrosSusFiltrados, registroSivepFiltrado);
			registrosSusFiltrados = aplicarFiltroPorMunicipioOuArea(registrosSusFiltrados, registroSivepFiltrado);
			registrosSusFiltrados = aplicarFiltroPorRacaCor(registrosSusFiltrados, registroSivepFiltrado);
			
			System.out.println("registrosSusFiltrados.size(): " + registrosSusFiltrados.size());
			
			System.out.println("filtrarRegistrosSusPorResultado (Positivo)");
			List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoPositivo = filtrarRegistrosSusPorResultado(registrosSusFiltrados, "Positivo");
			System.out.println("registrosSusFiltradosComResultadoPositivo.size(): " + registrosSusFiltradosComResultadoPositivo.size());
			
			System.out.println("filtrarRegistrosSusPorResultado (Negativo)");
			List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoNegativo = filtrarRegistrosSusPorResultado(registrosSusFiltrados, "Negativo");
			System.out.println("registrosSusFiltradosComResultadoNegativo.size(): " + registrosSusFiltradosComResultadoNegativo.size());
			
			//List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoPositivoETipoTeste  = aplicarFiltroPorTipoTeste(registrosSusFiltrados);
			
			break;
		}
		
		//System.out.println("************************************** ");
		
		return registrosSusFiltrados;
	}

}
