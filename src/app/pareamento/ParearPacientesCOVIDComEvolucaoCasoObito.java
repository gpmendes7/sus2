package app.pareamento;

import static app.pareamento.FiltrosPareamento.*;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorResultado;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorSexo;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorTipoTesteComCovid;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SivepRedomeModificadoCSV;
import csv.SivepRedomeModificadoCSVHandler;
import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ParearPacientesCOVIDComEvolucaoCasoObito {
	
	private static List<SivepRedomeModificadoCSV> registrosSivep;
	private static List<SusRedomeModificadoCSV> registrosSus;
	
	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		registrosSivep = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/SIVEP_REDOME(Modificado-OBITO).csv");
		System.out.println("registrosSivep.size(): " + registrosSivep.size());
		
		registrosSus = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(SemRegistrosComObservacaoExclusao).csv");
		System.out.println("registrosSus.size(): " + registrosSus.size());
		
		selecionarPacientesEntre31E50Anos(registrosSivep, registrosSus);
	}
	
	public static void selecionarPacientesEntre31E50Anos(List<SivepRedomeModificadoCSV> registrosSivep, List<SusRedomeModificadoCSV> registrosSus) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {	
		//System.out.println("************************************** ");
		//System.out.println("selecionarPacientesEntre31E50Anos");
		
		//System.out.println("filtrarRegistrosSivepPorFaixaEtaria");
		List<SivepRedomeModificadoCSV> registrosSivepFiltrados = filtrarRegistrosSivepPorFaixaEtaria(registrosSivep, 31, 50);
		//System.out.println("registrosSivepFiltrados.size(): " + registrosSivepFiltrados.size());
		
		List<SusRedomeModificadoCSV> registrosSusAtualizado = new ArrayList<>(registrosSus);
		
		List<SusRedomeModificadoCSV> registrosSusTotaisFiltradosComResultadoPositivo = new ArrayList<>();
		List<SusRedomeModificadoCSV> registrosSusTotaisFiltradosComResultadoNegativo = new ArrayList<>();
		
		for (SivepRedomeModificadoCSV registroSivepFiltrado : registrosSivepFiltrados) {
			List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivep = filtrarRegistrosSusNaoUsados(registrosSusAtualizado);
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosPorFaixaEtaria = filtrarRegistrosSusPorFaixaEtaria(registrosSusFiltradosRegistroSivep, 31, 50);
			if(aplicouFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosPorFaixaEtaria)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFiltradosPorFaixaEtaria;
			   System.out.println("Filtrado por faixa etária");
			}
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosPorSexo = filtrarRegistrosSusPorSexo(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			if(aplicouFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosPorSexo)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFiltradosPorSexo;
			   System.out.println("Filtrado por sexo");
			}
				
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosPorDataNotificacao = filtrarRegistrosSusPorDataNotificacao(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			if(aplicouFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosPorDataNotificacao)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFiltradosPorDataNotificacao;
			   System.out.println("Filtrado por 1 semana (para frente e trás) da data de notificação");
			}

			
			List<SusRedomeModificadoCSV> registrosSusFiltradosPorMunicipio = filtrarRegistrosSusPorMunicipio(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			if(aplicouFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosPorMunicipio)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFiltradosPorMunicipio;
			   System.out.println("Filtrado por município");
			} else {	
				List<SusRedomeModificadoCSV> registrosSusFiltradosPorAreaMunicipio = filtrarRegistrosSusPorAreaMunicipio(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
				if(aplicouFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosPorAreaMunicipio)) {
				   registrosSusFiltradosRegistroSivep = registrosSusFiltradosPorAreaMunicipio;
				   System.out.println("Filtrado por área ou município");
				}	
			}
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorRacaCor = filtrarRegistrosSusPorRacaCor(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			if(aplicouFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosSusPorRacaCor)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFiltradosSusPorRacaCor;
			   System.out.println("Filtrado por raça cor");
			}	
			
			List<SusRedomeModificadoCSV> registrosSusFitradosPorTipoTesteComCovid = filtrarRegistrosSusPorTipoTesteComCovid(registrosSusFiltradosRegistroSivep);
			if(aplicouFiltro(registrosSusFiltradosRegistroSivep, registrosSusFitradosPorTipoTesteComCovid)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFitradosPorTipoTesteComCovid;
			   System.out.println("Filtrado por tipo teste RTPCR, Antígeno e Anticorpo (nesta ordem)");
			}	
			
			System.out.println("registrosSusFiltradosRegistroSivep.size(): " + registrosSusFiltradosRegistroSivep.size());
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoPositivo = filtrarRegistrosSusPorResultado(registrosSusFiltradosRegistroSivep, "Positivo");
			registrosSusAtualizado.removeAll(registrosSusFiltradosComResultadoPositivo);
			registrosSusFiltradosComResultadoPositivo.stream().forEach(r -> r.setObservacaoUso("Registro usado por Obito"));
			registrosSusAtualizado.addAll(registrosSusFiltradosComResultadoPositivo);
			System.out.println("registrosSusFiltradosComResultadoPositivo.size(): " + registrosSusFiltradosComResultadoPositivo.size());
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoNegativo = filtrarRegistrosSusPorResultado(registrosSusFiltradosRegistroSivep, "Negativo");
			registrosSusAtualizado.removeAll(registrosSusFiltradosComResultadoNegativo);
			registrosSusFiltradosComResultadoNegativo.stream().forEach(r -> r.setObservacaoUso("Registro usado por Obito"));
			registrosSusAtualizado.addAll(registrosSusFiltradosComResultadoNegativo);
			System.out.println("registrosSusFiltradosComResultadoNegativo.size(): " + registrosSusFiltradosComResultadoNegativo.size());
			
			registrosSusTotaisFiltradosComResultadoPositivo.addAll(registrosSusFiltradosComResultadoPositivo);
			registrosSusTotaisFiltradosComResultadoNegativo.addAll(registrosSusFiltradosComResultadoNegativo);
			
			break;
		}
		
		registrosSusTotaisFiltradosComResultadoPositivo.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
																	                "cpf", "dataNascimento", "municipioNotificacao", 
																	                "racaCor", "etnia", "nomeMae", 
																	                "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
																	                "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(PacientesObitoEntre31E50AnosResultadoPositivo).csv", registrosSusTotaisFiltradosComResultadoPositivo);
		
		registrosSusTotaisFiltradosComResultadoNegativo.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
																			             "cpf", "dataNascimento", "municipioNotificacao", 
																			             "racaCor", "etnia", "nomeMae", 
																			             "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
																			             "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(PacientesObitoEntre31E50AnosResultadoNegativo).csv", registrosSusTotaisFiltradosComResultadoNegativo);
		
		registrosSusAtualizado.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
												                 "cpf", "dataNascimento", "municipioNotificacao", 
												                 "racaCor", "etnia", "nomeMae", 
												                 "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
												                 "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(AposUsoObito).csv", registrosSusAtualizado);
		
		//System.out.println("************************************** ");
	}

}
