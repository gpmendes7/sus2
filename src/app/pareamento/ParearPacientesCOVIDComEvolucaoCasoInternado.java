package app.pareamento;

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
import java.util.stream.Collectors;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SivepRedomeModificadoCSV;
import csv.SivepRedomeModificadoCSVHandler;
import csv.SusRedomeModificado2CSVHandler;
import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ParearPacientesCOVIDComEvolucaoCasoInternado {
	
	private static List<SivepRedomeModificadoCSV> registrosSivep;
	private static List<SusRedomeModificadoCSV> registrosSus;
	private static List<SusRedomeModificadoCSV> registrosSusAtualizado;
	private static File file;
	private static FileWriter fileWriter;
	
	public static void main(String[] args) throws Exception {
		registrosSivep = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/internado/SIVEP_REDOME(Modificado-INTERNADO).csv");
		registrosSus = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(AposUsoUTI).csv");
		registrosSusAtualizado = new ArrayList<>(registrosSus);
		
		parearPacientesEntreSivepESus(25, 
				                      44, 
			                          "./arquivos/txt/internado/RegistrosUsados(PacientesInternadoEntre25E44Anos).txt",
			                          "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre25E44AnosResultadoPositivo).csv", 
			                          "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre25E44AnosResultadoNegativo).csv",
			                          "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesUsadosEntre25E44Anos).csv",
			                          "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesNaoUsadosEntre25E44Anos).csv");
		
		parearPacientesEntreSivepESus(45, 
					                  56, 
					                  "./arquivos/txt/internado/RegistrosUsados(PacientesInternadoEntre45E56Anos).txt",
					                  "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre45E56AnosResultadoPositivo).csv", 
					                  "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre45E56AnosResultadoNegativo).csv",
					                  "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesUsadosEntre45E56Anos).csv",
					                  "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesNaoUsadosEntre45E56Anos).csv");
		
		parearPacientesEntreSivepESus(57, 
					                  81, 
					                  "./arquivos/txt/internado/RegistrosUsados(PacientesInternadoEntre57E81Anos).txt",
					                  "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre57E81AnosResultadoPositivo).csv", 
					                  "./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre57E81AnosResultadoNegativo).csv",
					                  "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesUsadosEntre57E81Anos).csv",
					                  "./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesNaoUsadosEntre57E81Anos).csv");			
		
		registrosSusAtualizado.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
                "cpf", "dataNascimento", "municipioNotificacao", 
                "racaCor", "etnia", "nomeMae", 
                "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
                "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));

        SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(AposUsoInternado).csv", registrosSusAtualizado);
 
	}
	
	public static void parearPacientesEntreSivepESus(int idadeMinima, 		
			                         				 int idadeMaxima, 
		                                             String arquivoTxt,
		                                             String csvResultadoPositivo, 
		                                             String csvResultadoNegativo, 
		                                             String csvSivepUsados,
		                                             String csvSivepNaoUsados) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {	
		List<SivepRedomeModificadoCSV> registrosSivepFiltrados = filtrarRegistrosSivepPorFaixaEtaria(registrosSivep, idadeMinima, idadeMaxima);
				
		List<SusRedomeModificadoCSV> registrosSusTotaisFiltradosComResultadoPositivo = new ArrayList<>();
		List<SusRedomeModificadoCSV> registrosSusTotaisFiltradosComResultadoNegativo = new ArrayList<>();
		
		file = new File(arquivoTxt);
		fileWriter = new FileWriter(file);
		
		fileWriter.write("***************************\n");
		fileWriter.write("Número de registros do sivep com evolução caso Obito na faixa etaria de " + idadeMinima + " a " + idadeMaxima + " anos usados para filtrar registros no sus: " + registrosSivepFiltrados.size() + "\n");
		fileWriter.write("***************************\n");
		
		List<SivepRedomeModificadoCSV> registrosSivepNaoUsados = new ArrayList<>();
		
		for (SivepRedomeModificadoCSV registroSivepFiltrado : registrosSivepFiltrados) {
			fileWriter.write("***************************\n");
			int registro =  (registrosSivepFiltrados.indexOf(registroSivepFiltrado) + 1);
			fileWriter.write("registro " + registro + "\n");
			
			fileWriter.write("registro do sivep com identificacao " + registroSivepFiltrado.getIdentificacao() + "\n");
			fileWriter.write("registro do sivep com nomeCompleto " + registroSivepFiltrado.getNomeCompleto() + "\n");
			fileWriter.write("registro do sivep com dataNascimento " + registroSivepFiltrado.getDataNascimento() + "\n");
			
			int filtragem = 1;
			int numeroSemanas = 1;
			List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivepComResultadoPositivo = new ArrayList<>();
			List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivepComResultadoNegativo = new ArrayList<>();
			
			while(filtragem < 8 
			      && (registrosSusFiltradosRegistroSivepComResultadoPositivo.size() < 5 || registrosSusFiltradosRegistroSivepComResultadoNegativo.size() < 5)) {
				fileWriter.write("---------------------------\n");
				fileWriter.write("Filtragem " + filtragem + "\n");
				
				List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivep = filtrarRegistrosSusNaoUsados(registrosSusAtualizado);
				
				if(filtragem < 10) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorFaixaEtaria(registrosSusFiltradosRegistroSivep, 31, 50);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por faixa etária\n");
				} else {
					fileWriter.write("Não filtrou registros do sus por faixa etária\n");
				}
				
				if(filtragem < 9) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorSexo(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por sexo\n");
				} else {
					fileWriter.write("Não filtrou registros do sus por sexo\n");
				}
				
				if(filtragem < 8) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorDataNotificacao(registrosSusFiltradosRegistroSivep, registroSivepFiltrado, numeroSemanas);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus com " + numeroSemanas + " semana(s) para trás e para frente por data de notificação\n");						
				}
				else {
					fileWriter.write("Não filtrou registros do sus por data de notificação\n");
				}
				
				if(filtragem < 4) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorMunicipio(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);	
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por município\n");
				} else {
					fileWriter.write("Não filtrou registros do sus por município\n");
				}
				
				if(filtragem == 4) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorAreaMunicipio(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por área\n");
				} 
				else {
					fileWriter.write("Não filtrou registros do sus por área\n");
				}
				
				if(filtragem < 3) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorRacaCor(registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por raça cor\n");
				} else {
					fileWriter.write("Não filtrou registros do sus por raça cor\n");
				}
				
				if(filtragem < 2) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorTipoTesteComCovid(registrosSusFiltradosRegistroSivep);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por tipo teste RTPCR, Antígeno e Anticorpo (nesta ordem)\n");
				} else {
					fileWriter.write("Não filtrou registros do sus por tipo teste RTPCR, Antígeno e Anticorpo (nesta ordem)\n");
				}
				
				int qtdRegistros;
				
				if(registrosSusFiltradosRegistroSivepComResultadoPositivo.size() < 5) {
					qtdRegistros = 5 - registrosSusFiltradosRegistroSivepComResultadoPositivo.size();
				    registrosSusFiltradosRegistroSivepComResultadoPositivo.addAll(obterRegistrosUsadosComResultadoPositivo(registrosSusFiltradosRegistroSivep, qtdRegistros));
				}
				
				if(registrosSusFiltradosRegistroSivepComResultadoNegativo.size() < 5) {	
					qtdRegistros = 5 - registrosSusFiltradosRegistroSivepComResultadoNegativo.size();
					registrosSusFiltradosRegistroSivepComResultadoNegativo.addAll(obterRegistrosUsadosComResultadoNegativo(registrosSusFiltradosRegistroSivep, qtdRegistros));
				}
				
				fileWriter.write("Número atual de registros do sus usados com resultado Positivo após filtragem " + filtragem + " : " + registrosSusFiltradosRegistroSivepComResultadoPositivo.size() + "\n");
				fileWriter.write("Número atual de registros do sus usados com resultado Negativo após filtragem " + filtragem + " : " + registrosSusFiltradosRegistroSivepComResultadoNegativo.size() + "\n");
							
				filtragem++;
				
				if(filtragem > 4 && filtragem < 8) {
					numeroSemanas++;
				}
			}
			
			fileWriter.write("---------------------------\n");
			fileWriter.write("Resultados finais após filtragem " + (filtragem-1) + "\n");
				
			if(registrosSusFiltradosRegistroSivepComResultadoPositivo.size() < 5 || registrosSusFiltradosRegistroSivepComResultadoNegativo.size() < 5) {
				fileWriter.write("Número de registros do sus com resultado Positivo e Negativo insuficientes! Registro " + registro + " não será usado!\n");
				fileWriter.write("Registros do sus filtrados usados vão ser desmarcados para uso posterior para filtro de outro registro sivep!\n");	
				
				registrosSusAtualizado.removeAll(registrosSusFiltradosRegistroSivepComResultadoPositivo);			
				registrosSusFiltradosRegistroSivepComResultadoPositivo.stream().forEach(r -> r.setObservacaoUso(""));		
				registrosSusFiltradosRegistroSivepComResultadoPositivo.stream().forEach(r -> r.setFiltroAreaMunicipio(""));
				registrosSusAtualizado.addAll(registrosSusFiltradosRegistroSivepComResultadoPositivo);	
				
				registrosSusAtualizado.removeAll(registrosSusFiltradosRegistroSivepComResultadoNegativo);			
				registrosSusFiltradosRegistroSivepComResultadoNegativo.stream().forEach(r -> r.setObservacaoUso(""));		
				registrosSusFiltradosRegistroSivepComResultadoNegativo.stream().forEach(r -> r.setFiltroAreaMunicipio(""));
				registrosSusAtualizado.addAll(registrosSusFiltradosRegistroSivepComResultadoNegativo);	
				
				registrosSivepNaoUsados.add(registroSivepFiltrado);
			} else {
				fileWriter.write("Número final de registros do sus usados com resultado Positivo: " + registrosSusFiltradosRegistroSivepComResultadoPositivo.size() + "\n");
				fileWriter.write("Número final de registros do sus usados com resultado Negativo: " + registrosSusFiltradosRegistroSivepComResultadoNegativo.size() + "\n");
				
				registrosSusTotaisFiltradosComResultadoPositivo.addAll(registrosSusFiltradosRegistroSivepComResultadoPositivo);
				registrosSusTotaisFiltradosComResultadoNegativo.addAll(registrosSusFiltradosRegistroSivepComResultadoNegativo);
			}
							
			fileWriter.write("***************************\n");
		}
	       
	    fileWriter.flush();
		fileWriter.close();
			
		registrosSusTotaisFiltradosComResultadoPositivo.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "filtroAreaMunicipio", "nomeCompleto", 
																	                "cpf", "dataNascimento", "municipioNotificacao", 
																	                "racaCor", "etnia", "nomeMae", 
																	                "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
																	                "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));
		SusRedomeModificado2CSVHandler.criarCSV(csvResultadoPositivo, registrosSusTotaisFiltradosComResultadoPositivo);
		
		
		registrosSusTotaisFiltradosComResultadoNegativo.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "filtroAreaMunicipio", "nomeCompleto", 
																			             "cpf", "dataNascimento", "municipioNotificacao", 
																			             "racaCor", "etnia", "nomeMae", 
																			             "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
																			             "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));
		
		SusRedomeModificado2CSVHandler.criarCSV(csvResultadoNegativo, registrosSusTotaisFiltradosComResultadoNegativo);
		
		registrosSivepFiltrados.removeAll(registrosSivepNaoUsados);
		
		registrosSivepFiltrados.add(0, new SivepRedomeModificadoCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", 
                "municipio", "campo1", "sexo", "racaCor", 
                "dataInternacao", "dataInternacaoRedome", 
                "dataEncerramento", "dataEncerramentoRedome", 
                "evolucaoCaso", "evolucaoCasoRedome",
                "dataNotificacao", "resultadoTeste"));

		
		SivepRedomeModificadoCSVHandler.criarCSV(csvSivepUsados, registrosSivepFiltrados);
		
		registrosSivepNaoUsados.add(0, new SivepRedomeModificadoCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", 
                "municipio", "campo1", "sexo", "racaCor", 
                "dataInternacao", "dataInternacaoRedome", 
                "dataEncerramento", "dataEncerramentoRedome", 
                "evolucaoCaso", "evolucaoCasoRedome",
                "dataNotificacao", "resultadoTeste"));
		
		SivepRedomeModificadoCSVHandler.criarCSV(csvSivepNaoUsados, registrosSivepNaoUsados);
	}

	private static List<SusRedomeModificadoCSV> obterRegistrosUsadosComResultadoNegativo(List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivep, int qtd) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoNegativo = filtrarRegistrosSusPorResultado(registrosSusFiltradosRegistroSivep, "Negativo");
		fileWriter.write("Filtrou " + registrosSusFiltradosComResultadoNegativo.size() + " registros do sus com resultado Negativo\n");
		
		registrosSusAtualizado.removeAll(registrosSusFiltradosComResultadoNegativo);

		registrosSusFiltradosComResultadoNegativo.stream().limit(qtd).forEach(r -> r.setObservacaoUso("Registro usado por Internado"));
		
		registrosSusAtualizado.addAll(registrosSusFiltradosComResultadoNegativo);
				
		List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoNegativoUsados = registrosSusFiltradosComResultadoNegativo.stream()
																																.filter(r -> r.getObservacaoUso() != null && !r.getObservacaoUso().equals(""))
																																.collect(Collectors.toList());
		if(registrosSusFiltradosComResultadoNegativoUsados.size() > 0) {
			fileWriter.write("Foram usados " + registrosSusFiltradosComResultadoNegativoUsados.size() + " registros do sus com resultado Negativo\n");
		}
		
		return registrosSusFiltradosComResultadoNegativoUsados;
	}

	private static List<SusRedomeModificadoCSV> obterRegistrosUsadosComResultadoPositivo(List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivep, int qtd) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoPositivo = filtrarRegistrosSusPorResultado(registrosSusFiltradosRegistroSivep, "Positivo");
		fileWriter.write("Filtrou " + registrosSusFiltradosComResultadoPositivo.size() + " registros do sus com resultado Positivo\n");
		
		registrosSusAtualizado.removeAll(registrosSusFiltradosComResultadoPositivo);
		
		registrosSusFiltradosComResultadoPositivo.stream().limit(qtd).forEach(r -> r.setObservacaoUso("Registro usado por Internado"));
		
		registrosSusAtualizado.addAll(registrosSusFiltradosComResultadoPositivo);	
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoPositivoUsados = registrosSusFiltradosComResultadoPositivo.stream()
																																.filter(r -> r.getObservacaoUso() != null && !r.getObservacaoUso().equals(""))
																																.collect(Collectors.toList());
		
		if(registrosSusFiltradosComResultadoPositivoUsados.size() > 0) {
			fileWriter.write("Foram usados " + registrosSusFiltradosComResultadoPositivoUsados.size() + " registros do sus com resultado Positivo\n");
		}
		
		return registrosSusFiltradosComResultadoPositivoUsados;
	}

}
