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
import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;
import csv.SusRedomeModificadoCSVHandler2;

public class Pareamento {

	private List<SivepRedomeModificadoCSV> registrosSivep;
	private List<SusRedomeModificadoCSV> registrosSus;
	private List<SusRedomeModificadoCSV> registrosSusAtualizado;
	private File file;
	private FileWriter fileWriter;
	private String situacao;
	
	public Pareamento(String situacao) {
		this.situacao = situacao;
	}

	public void carregarArquivosCSV(String csvSivep, String csvSus) throws IOException {
		registrosSivep = SivepRedomeModificadoCSVHandler.carregarCSV(csvSivep);
		registrosSus = SusRedomeModificadoCSVHandler.carregarCSV(csvSus);
		registrosSusAtualizado = new ArrayList<>(registrosSus);
	}
	
	public void criarArquivosCSVSusAtualizado(String csvSusAtualizado) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		registrosSusAtualizado.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
												                 "cpf", "dataNascimento", "municipioNotificacao", 
												                 "racaCor", "etnia", "nomeMae", 
												                 "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
												                 "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));

        SusRedomeModificadoCSVHandler.criarCSV(csvSusAtualizado, registrosSusAtualizado); 
	}

	public void parearPacientesEntreSivepESus(int idadeMinima, int idadeMaxima, String arquivoTxt,
			String csvResultadoPositivo, String csvResultadoNegativo, String csvSivepUsados, String csvSivepNaoUsados)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		List<SivepRedomeModificadoCSV> registrosSivepFiltrados = filtrarRegistrosSivepPorFaixaEtaria(registrosSivep,
				idadeMinima, idadeMaxima);

		List<SusRedomeModificadoCSV> registrosSusTotaisFiltradosComResultadoPositivo = new ArrayList<>();
		List<SusRedomeModificadoCSV> registrosSusTotaisFiltradosComResultadoNegativo = new ArrayList<>();

		file = new File(arquivoTxt);
		fileWriter = new FileWriter(file);

		fileWriter.write("***************************\n");
		fileWriter.write("N�mero de registros do sivep com evolu��o caso " + situacao + " na faixa etaria de " + idadeMinima
				+ " a " + idadeMaxima + " anos usados para filtrar registros no sus: " + registrosSivepFiltrados.size()
				+ "\n");
		fileWriter.write("***************************\n");

		List<SivepRedomeModificadoCSV> registrosSivepNaoUsados = new ArrayList<>();

		for (SivepRedomeModificadoCSV registroSivepFiltrado : registrosSivepFiltrados) {
			fileWriter.write("***************************\n");
			int registro = (registrosSivepFiltrados.indexOf(registroSivepFiltrado) + 1);
			fileWriter.write("registro " + registro + "\n");

			fileWriter.write("registro do sivep com identificacao " + registroSivepFiltrado.getIdentificacao() + "\n");
			fileWriter.write("registro do sivep com nomeCompleto " + registroSivepFiltrado.getNomeCompleto() + "\n");
			fileWriter
					.write("registro do sivep com dataNascimento " + registroSivepFiltrado.getDataNascimento() + "\n");

			int filtragem = 1;
			int numeroSemanas = 1;
			List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivepComResultadoPositivo = new ArrayList<>();
			List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivepComResultadoNegativo = new ArrayList<>();

			while (filtragem < 9 && (registrosSusFiltradosRegistroSivepComResultadoPositivo.size() < 5
					              || registrosSusFiltradosRegistroSivepComResultadoNegativo.size() < 5)) {
				fileWriter.write("---------------------------\n");
				fileWriter.write("Filtragem " + filtragem + "\n");

				List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivep = filtrarRegistrosSusNaoUsados(
						registrosSusAtualizado);

				if (filtragem < 10) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorFaixaEtaria(
							registrosSusFiltradosRegistroSivep, 31, 50);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por faixa et�ria\n");
				} else {
					fileWriter.write("N�o filtrou registros do sus por faixa et�ria\n");
				}

				if (filtragem < 9) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorSexo(registrosSusFiltradosRegistroSivep,
							registroSivepFiltrado);
					fileWriter.write(
							"Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por sexo\n");
				} else {
					fileWriter.write("N�o filtrou registros do sus por sexo\n");
				}

				if (filtragem < 8) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorDataNotificacao(
							registrosSusFiltradosRegistroSivep, registroSivepFiltrado, numeroSemanas);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus com "
							+ numeroSemanas + " semana(s) para tr�s e para frente por data de notifica��o\n");
				} else {
					fileWriter.write("N�o filtrou registros do sus por data de notifica��o\n");
				}

				if (filtragem < 4) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorMunicipio(
							registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por munic�pio\n");
				} else {
					fileWriter.write("N�o filtrou registros do sus por munic�pio\n");
				}

				if (filtragem == 4) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorAreaMunicipio(
							registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por �rea\n");
				} else {
					fileWriter.write("N�o filtrou registros do sus por �rea\n");
				}

				if (filtragem < 3) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorRacaCor(
							registrosSusFiltradosRegistroSivep, registroSivepFiltrado);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por ra�a cor\n");
				} else {
					fileWriter.write("N�o filtrou registros do sus por ra�a cor\n");
				}

				if (filtragem < 2) {
					registrosSusFiltradosRegistroSivep = filtrarRegistrosSusPorTipoTesteComCovid(
							registrosSusFiltradosRegistroSivep);
					fileWriter.write("Filtrou " + registrosSusFiltradosRegistroSivep.size() + " registros do sus por tipo teste RTPCR, Ant�geno e Anticorpo (nesta ordem)\n");
				} else {
					fileWriter.write("N�o filtrou registros do sus por tipo teste RTPCR, Ant�geno e Anticorpo (nesta ordem)\n");
				}

				int qtdRegistros;

				if (registrosSusFiltradosRegistroSivepComResultadoPositivo.size() < 5) {
					qtdRegistros = 5 - registrosSusFiltradosRegistroSivepComResultadoPositivo.size();
					registrosSusFiltradosRegistroSivepComResultadoPositivo.addAll(
							obterRegistrosUsadosComResultadoPositivo(registrosSusFiltradosRegistroSivep, qtdRegistros));
				}

				if (registrosSusFiltradosRegistroSivepComResultadoNegativo.size() < 5) {
					qtdRegistros = 5 - registrosSusFiltradosRegistroSivepComResultadoNegativo.size();
					registrosSusFiltradosRegistroSivepComResultadoNegativo.addAll(
							obterRegistrosUsadosComResultadoNegativo(registrosSusFiltradosRegistroSivep, qtdRegistros));
				}

				fileWriter.write("N�mero atual de registros do sus usados com resultado Positivo ap�s filtragem "
						+ filtragem + " : " + registrosSusFiltradosRegistroSivepComResultadoPositivo.size() + "\n");
				fileWriter.write("N�mero atual de registros do sus usados com resultado Negativo ap�s filtragem "
						+ filtragem + " : " + registrosSusFiltradosRegistroSivepComResultadoNegativo.size() + "\n");

				filtragem++;

				if (filtragem > 4 && filtragem < 8) {
					numeroSemanas++;
				}
			}

			fileWriter.write("---------------------------\n");
			fileWriter.write("Resultados finais ap�s filtragem " + (filtragem - 1) + "\n");

			if (registrosSusFiltradosRegistroSivepComResultadoPositivo.size() < 5
					|| registrosSusFiltradosRegistroSivepComResultadoNegativo.size() < 5) {
				fileWriter.write("N�mero de registros do sus com resultado Positivo e Negativo insuficientes! Registro "
						+ registro + " n�o ser� usado!\n");
				fileWriter.write(
						"Registros do sus filtrados usados v�o ser desmarcados para uso posterior para filtro de outro registro sivep!\n");

				registrosSusAtualizado.removeAll(registrosSusFiltradosRegistroSivepComResultadoPositivo);
				registrosSusFiltradosRegistroSivepComResultadoPositivo.stream().forEach(r -> r.setObservacaoUso(""));
				registrosSusFiltradosRegistroSivepComResultadoPositivo.stream()
						.forEach(r -> r.setFiltroAreaMunicipio(""));
				registrosSusAtualizado.addAll(registrosSusFiltradosRegistroSivepComResultadoPositivo);

				registrosSusAtualizado.removeAll(registrosSusFiltradosRegistroSivepComResultadoNegativo);
				registrosSusFiltradosRegistroSivepComResultadoNegativo.stream().forEach(r -> r.setObservacaoUso(""));
				registrosSusFiltradosRegistroSivepComResultadoNegativo.stream()
						.forEach(r -> r.setFiltroAreaMunicipio(""));
				registrosSusAtualizado.addAll(registrosSusFiltradosRegistroSivepComResultadoNegativo);

				registrosSivepNaoUsados.add(registroSivepFiltrado);
			} else {
				fileWriter.write("N�mero final de registros do sus usados com resultado Positivo: "
						+ registrosSusFiltradosRegistroSivepComResultadoPositivo.size() + "\n");
				fileWriter.write("N�mero final de registros do sus usados com resultado Negativo: "
						+ registrosSusFiltradosRegistroSivepComResultadoNegativo.size() + "\n");

				registrosSusTotaisFiltradosComResultadoPositivo
						.addAll(registrosSusFiltradosRegistroSivepComResultadoPositivo);
				registrosSusTotaisFiltradosComResultadoNegativo
						.addAll(registrosSusFiltradosRegistroSivepComResultadoNegativo);
			}

			fileWriter.write("***************************\n");
		}

		fileWriter.flush();
		fileWriter.close();

		registrosSusTotaisFiltradosComResultadoPositivo.add(0,
				new SusRedomeModificadoCSV("campo1", "municipio", "filtroAreaMunicipio", "nomeCompleto", "cpf",
						"dataNascimento", "municipioNotificacao", "racaCor", "etnia", "nomeMae", "dataNotificacao",
						"idade", "resultadoTeste", "dataTeste", "tipoTeste", "estadoTeste", "evolucaoCaso",
						"observacaoExclusao", "sexo", "observacaoUso"));
		SusRedomeModificadoCSVHandler2.criarCSV(csvResultadoPositivo, registrosSusTotaisFiltradosComResultadoPositivo);

		registrosSusTotaisFiltradosComResultadoNegativo.add(0,
				new SusRedomeModificadoCSV("campo1", "municipio", "filtroAreaMunicipio", "nomeCompleto", "cpf",
						"dataNascimento", "municipioNotificacao", "racaCor", "etnia", "nomeMae", "dataNotificacao",
						"idade", "resultadoTeste", "dataTeste", "tipoTeste", "estadoTeste", "evolucaoCaso",
						"observacaoExclusao", "sexo", "observacaoUso"));

		SusRedomeModificadoCSVHandler2.criarCSV(csvResultadoNegativo, registrosSusTotaisFiltradosComResultadoNegativo);

		registrosSivepFiltrados.removeAll(registrosSivepNaoUsados);

		registrosSivepFiltrados.add(0,
				new SivepRedomeModificadoCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", "municipio",
						"campo1", "sexo", "racaCor", "dataInternacao", "dataInternacaoRedome", "dataEncerramento",
						"dataEncerramentoRedome", "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao",
						"resultadoTeste"));

		SivepRedomeModificadoCSVHandler.criarCSV(csvSivepUsados, registrosSivepFiltrados);

		registrosSivepNaoUsados.add(0,
				new SivepRedomeModificadoCSV("identificacao", "nomeCompleto", "dataNascimento", "idade", "municipio",
						"campo1", "sexo", "racaCor", "dataInternacao", "dataInternacaoRedome", "dataEncerramento",
						"dataEncerramentoRedome", "evolucaoCaso", "evolucaoCasoRedome", "dataNotificacao",
						"resultadoTeste"));

		SivepRedomeModificadoCSVHandler.criarCSV(csvSivepNaoUsados, registrosSivepNaoUsados);
	}

	private List<SusRedomeModificadoCSV> obterRegistrosUsadosComResultadoNegativo(List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivep, int qtd)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoNegativo = filtrarRegistrosSusPorResultado(
				registrosSusFiltradosRegistroSivep, "Negativo");
		fileWriter.write("Filtrou " + registrosSusFiltradosComResultadoNegativo.size()
				+ " registros do sus com resultado Negativo\n");

		registrosSusAtualizado.removeAll(registrosSusFiltradosComResultadoNegativo);

		registrosSusFiltradosComResultadoNegativo.stream().limit(qtd)
				.forEach(r -> r.setObservacaoUso("Registro usado por " + situacao));

		registrosSusAtualizado.addAll(registrosSusFiltradosComResultadoNegativo);

		List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoNegativoUsados = registrosSusFiltradosComResultadoNegativo
				.stream().filter(r -> r.getObservacaoUso() != null && !r.getObservacaoUso().equals(""))
				.collect(Collectors.toList());
		if (registrosSusFiltradosComResultadoNegativoUsados.size() > 0) {
			fileWriter.write("Foram usados " + registrosSusFiltradosComResultadoNegativoUsados.size()
					+ " registros do sus com resultado Negativo\n");
		}

		return registrosSusFiltradosComResultadoNegativoUsados;
	}

	private List<SusRedomeModificadoCSV> obterRegistrosUsadosComResultadoPositivo(List<SusRedomeModificadoCSV> registrosSusFiltradosRegistroSivep, int qtd) 
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoPositivo = filtrarRegistrosSusPorResultado(
				registrosSusFiltradosRegistroSivep, "Positivo");
		fileWriter.write("Filtrou " + registrosSusFiltradosComResultadoPositivo.size()
				+ " registros do sus com resultado Positivo\n");

		registrosSusAtualizado.removeAll(registrosSusFiltradosComResultadoPositivo);

		registrosSusFiltradosComResultadoPositivo.stream().limit(qtd)
				.forEach(r -> r.setObservacaoUso("Registro usado por " + situacao));

		registrosSusAtualizado.addAll(registrosSusFiltradosComResultadoPositivo);

		List<SusRedomeModificadoCSV> registrosSusFiltradosComResultadoPositivoUsados = registrosSusFiltradosComResultadoPositivo
				.stream().filter(r -> r.getObservacaoUso() != null && !r.getObservacaoUso().equals(""))
				.collect(Collectors.toList());

		if (registrosSusFiltradosComResultadoPositivoUsados.size() > 0) {
			fileWriter.write("Foram usados " + registrosSusFiltradosComResultadoPositivoUsados.size()
					+ " registros do sus com resultado Positivo\n");
		}

		return registrosSusFiltradosComResultadoPositivoUsados;
	}

}
