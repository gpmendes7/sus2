package app.pareamento;

import static app.pareamento.FiltrosPareamento.aplicarFiltro;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSivepPorFaixaEtaria;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusNaoUsados;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorAreaMunicipio;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorDataNotificacao;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorFaixaEtaria;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorMunicipio;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorRacaCor;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorResultado;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorSexo;
import static app.pareamento.FiltrosPareamento.filtrarRegistrosSusPorTipoTesteComCovid;

import java.io.File;
import java.io.FileWriter;
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
	private static File file;
	private static FileWriter fileWriter;
	
	public static void main(String[] args) throws Exception {
		registrosSivep = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/SIVEP_REDOME(Modificado-OBITO).csv");
		System.out.println("registrosSivep.size(): " + registrosSivep.size());
		
		registrosSus = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(SemRegistrosComObservacaoExclusao).csv");
		System.out.println("registrosSus.size(): " + registrosSus.size());
		
		file = new File("./arquivos/txt/RegistrosUsadosObito.txt");
		fileWriter = new FileWriter(file);
		selecionarPacientesEntre31E50Anos(registrosSivep, registrosSus);
		fileWriter.flush();
		fileWriter.close();
	}
	
	public static void selecionarPacientesEntre31E50Anos(List<SivepRedomeModificadoCSV> registrosSivep, List<SusRedomeModificadoCSV> registrosSus) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {	
		//System.out.println("************************************** ");
		//System.out.println("selecionarPacientesEntre31E50Anos");
		
		//System.out.println("filtrarRegistrosSivepPorFaixaEtaria");
		List<SivepRedomeModificadoCSV> registrosSivepFiltrados = filtrarRegistrosSivepPorFaixaEtaria(registrosSivep, 31, 50);
		System.out.println("registrosSivepFiltrados.size(): " + registrosSivepFiltrados.size());
			
		List<SusRedomeModificadoCSV> registrosSusAtualizado = new ArrayList<>(registrosSus);
		
		List<SusRedomeModificadoCSV> registrosSusTotaisFiltradosComResultadoPositivo = new ArrayList<>();
		List<SusRedomeModificadoCSV> registrosSusTotaisFiltradosComResultadoNegativo = new ArrayList<>();
		
		fileWriter.write("***************************\n");
		fileWriter.write("Número de registros do sivep com evolução caso Obito na faixa etaria de 31 a 50 anos usados para filtrar registros no sus: " + registrosSivepFiltrados.size() + "\n");
		fileWriter.write("***************************\n");
		
		for (SivepRedomeModificadoCSV registroSivepFiltrado : registrosSivepFiltrados) {
			fileWriter.write("***************************\n");
			fileWriter.write("registro " + (registrosSivepFiltrados.indexOf(registroSivepFiltrado) + 1) + "\n");
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivep = filtrarRegistrosSusNaoUsados(registrosSusAtualizado);
			
			fileWriter.write("registro do sivep com identificacao " + registroSivepFiltrado.getIdentificacao() + "\n");
			fileWriter.write("registro do sivep com nomeCompleto " + registroSivepFiltrado.getNomeCompleto() + "\n");
			fileWriter.write("registro do sivep com dataNascimento " + registroSivepFiltrado.getDataNascimento() + "\n");
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosPorFaixaEtaria = filtrarRegistrosSusPorFaixaEtaria(registrosSusFiltradosRegistroSivep, 31, 50);
			if(aplicarFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosPorFaixaEtaria)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFiltradosPorFaixaEtaria;
			   fileWriter.write("Filtrou registros do sus por faixa etária\n");
			   //System.out.println("Filtrado por faixa etária");
			}
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosPorSexo = filtrarRegistrosSusPorSexo(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			if(aplicarFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosPorSexo)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFiltradosPorSexo;
			   fileWriter.write("Filtrou registros do sus por sexo\n");
			   //System.out.println("Filtrado por sexo");
			}
				
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosPorDataNotificacao = filtrarRegistrosSusPorDataNotificacao(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			if(aplicarFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosPorDataNotificacao)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFiltradosPorDataNotificacao;
			   fileWriter.write("Filtrou registros do sus por data de notificação\n");
			   //System.out.println("Filtrado por 1 semana (para frente e trás) da data de notificação");
			}

			
			List<SusRedomeModificadoCSV> registrosSusFiltradosPorMunicipio = filtrarRegistrosSusPorMunicipio(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			if(aplicarFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosPorMunicipio)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFiltradosPorMunicipio;
			   fileWriter.write("Filtrou registros do sus por município\n");
			   //System.out.println("Filtrado por município");
			} else {	
				List<SusRedomeModificadoCSV> registrosSusFiltradosPorAreaMunicipio = filtrarRegistrosSusPorAreaMunicipio(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
				if(aplicarFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosPorAreaMunicipio)) {
				   registrosSusFiltradosRegistroSivep = registrosSusFiltradosPorAreaMunicipio;
				   fileWriter.write("Filtrou registros do sus por área ou município\n");
				   //System.out.println("Filtrado por área ou município");
				}	
			}
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorRacaCor = filtrarRegistrosSusPorRacaCor(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
			if(aplicarFiltro(registrosSusFiltradosRegistroSivep, registrosSusFiltradosSusPorRacaCor)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFiltradosSusPorRacaCor;
			   fileWriter.write("Filtrou registros do sus por raça cor\n");
			   //System.out.println("Filtrado por raça cor");
			}	
			
			List<SusRedomeModificadoCSV> registrosSusFitradosPorTipoTesteComCovid = filtrarRegistrosSusPorTipoTesteComCovid(registrosSusFiltradosRegistroSivep);
			if(aplicarFiltro(registrosSusFiltradosRegistroSivep, registrosSusFitradosPorTipoTesteComCovid)) {
			   registrosSusFiltradosRegistroSivep = registrosSusFitradosPorTipoTesteComCovid;
			   fileWriter.write("Filtrou registros do sus por tipo teste RTPCR, Antígeno e Anticorpo (nesta ordem)\n");
			   //System.out.println("Filtrado por tipo teste RTPCR, Antígeno e Anticorpo (nesta ordem)");
			}	
			
			fileWriter.write("Número de registros do sus filtrados (com resultado Positivo ou Negativo): " + registrosSusFiltradosRegistroSivep.size() + "\n");
			//System.out.println("registrosSusFiltradosRegistroSivep.size(): " + registrosSusFiltradosRegistroSivep.size());
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoPositivo = filtrarRegistrosSusPorResultado(registrosSusFiltradosRegistroSivep, "Positivo");
			registrosSusAtualizado.removeAll(registrosSusFiltradosComResultadoPositivo);
			registrosSusFiltradosComResultadoPositivo.stream().forEach(r -> r.setObservacaoUso("Registro usado por Obito"));
			registrosSusAtualizado.addAll(registrosSusFiltradosComResultadoPositivo);
			fileWriter.write("Número de registros do sus filtrados com resultado Positivo: " + registrosSusFiltradosComResultadoPositivo.size() + "\n");
			System.out.println("registrosSusFiltradosComResultadoPositivo.size(): " + registrosSusFiltradosComResultadoPositivo.size());
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoNegativo = filtrarRegistrosSusPorResultado(registrosSusFiltradosRegistroSivep, "Negativo");
			registrosSusAtualizado.removeAll(registrosSusFiltradosComResultadoNegativo);
			registrosSusFiltradosComResultadoNegativo.stream().forEach(r -> r.setObservacaoUso("Registro usado por Obito"));
			registrosSusAtualizado.addAll(registrosSusFiltradosComResultadoNegativo);
			fileWriter.write("Número de registros do sus filtrados com resultado Negativo: " + registrosSusFiltradosComResultadoNegativo.size() + "\n");
			System.out.println("registrosSusFiltradosComResultadoNegativo.size(): " + registrosSusFiltradosComResultadoNegativo.size());
			
			registrosSusTotaisFiltradosComResultadoPositivo.addAll(registrosSusFiltradosComResultadoPositivo);
			registrosSusTotaisFiltradosComResultadoNegativo.addAll(registrosSusFiltradosComResultadoNegativo);
			
			fileWriter.write("***************************\n");
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
