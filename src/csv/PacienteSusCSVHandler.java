package csv;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class PacienteSusCSVHandler {
	
	private static ColumnPositionMappingStrategy<PacienteSusCSV> strategy;
	
	static {
		strategy = new ColumnPositionMappingStrategy<PacienteSusCSV>();
		strategy.setType(PacienteSusCSV.class);

		String[] colunas = { "nomeCompleto", "dataNascimento", "bairro",
				             "cbo", "cep", "classificacaoFinal", 
				             "cnes", "cns", "codigoCbo", 
				             "codigoClassificacaoFinal", "codigoComunidadeTradicional", 
				             "codigoCondicoes", "codigoEstadoTeste", "codigoEstrangeiro", 
				             "codigoEvolucaoCaso", "codigoPaisOrigem", "codigoProfissionalSaude",
				             "codigoProfissionalSeguranca", "codigoResultadoTeste", "codigoResultadoTesteSorologicoIgA",
				             "codigoResultadoTesteSorologicoIgG", "codigoResultadoTesteSorologicoIgM", "codigoResultadoTesteSorologicoTotais",
				             "codigoSexo", "codigoSintomas", "codigoTemCpf", "codigoTesteSorologico", 
				             "codigoTipoTeste", "codigoTipoTesteSorologico", "complemento", 
				             "comunidadeTradicional", "condicoes", "contemComunidadeTradicional",
				             "cpf", "createdAt", "dataEncerramento", "dataInicioSintomas", "dataNotificacao",
				             "dataTeste", "dataTesteSorologico", "descricaoRacaCor", "desnormalizarNome",
				             "email", "estado", "estadoIBGE", "estadoNotificacao", "estadoNotificacaoIBGE",
				             "estadoTeste", "estrangeiro", "etnia", "evolucaoCaso",
				             "idOrigem", "idade", "labCnes",
				             "logradouro", "municipio", "municipioIBGE", 
				             "municipioNotificacao", "municipioNotificacaoIBGE", 
				             "nomeCompletoDesnormalizado", "nomeMae", "notificadorCNPJ", 
				             "notificadorCpf", "notificadorEmail", "notificadorNome",
				             "numero", "numeroNotificacao", "origem",
				             "outrosSintomas", "pUsuario", "pUsuarioAlteracao", 
				             "paisOrigem", "passaporte", "profissionalSaude",
				             "profissionalSeguranca", "racaCor", "resultadoTeste",
				             "resultadoTesteSorologicoIgA", "resultadoTesteSorologicoIgG", "resultadoTesteSorologicoIgM",
				             "resultadoTesteSorologicoTotais", "rpa", "sexo",
				             "sintomas", "sourceId", "telefone", 
				             "telefoneContato", "testeSorologico", "timestampNotificacao",
				             "tipoTeste", "tipoTesteSorologico", "updatedAt", "versaoFormulario"};
 
		strategy.setColumnMapping(colunas);
	}
	
	public static List<PacienteSusCSV> carregarCSV(String nomeArquivo) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));) {
			CsvToBean<PacienteSusCSV> csvToBean = new CsvToBeanBuilder<PacienteSusCSV>(reader).withMappingStrategy(strategy)
					.withType(PacienteSusCSV.class).withSeparator(',').withSkipLines(1).build();
			List<PacienteSusCSV> registros = csvToBean.parse();
			return registros;
		}
	}
	
	public static void criarCSV(String nomeArquivo, List<PacienteSusCSV> registros) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(nomeArquivo));) {
			StatefulBeanToCsv<PacienteSusCSV> beanToCsv = new StatefulBeanToCsvBuilder<PacienteSusCSV>(writer)
														.withMappingStrategy(strategy)
														.withSeparator(',')
														.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
														.build();
			beanToCsv.write(registros);
		}
	}

}
