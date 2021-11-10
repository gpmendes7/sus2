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

public class SusRedomeModificadoCSVHandler {
	
	private static ColumnPositionMappingStrategy<SusRedomeModificadoCSV> strategy;
	
	static {
		strategy = new ColumnPositionMappingStrategy<SusRedomeModificadoCSV>();
		strategy.setType(SusRedomeModificadoCSV.class);

		String[] colunas = { "campo1", "municipio", "nomeCompleto", 
				             "cpf", "dataNascimento", "municipioNotificacao",  
				             "racaCor", "etnia",  "nomeMae",
				             "dataNotificacao", "idade", "resultadoTeste",
				             "dataTeste", "tipoTeste",
				             "estadoTeste", "evolucaoCaso", "observacaoExclusao",
				             "sexo", "observacaoUso"
				             };
 
		strategy.setColumnMapping(colunas);
	}
	
	public static List<SusRedomeModificadoCSV> carregarCSV(String nomeArquivo) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));) {
			CsvToBean<SusRedomeModificadoCSV> csvToBean = new CsvToBeanBuilder<SusRedomeModificadoCSV>(reader).withMappingStrategy(strategy)
					.withType(SusRedomeModificadoCSV.class).withSeparator(',').withSkipLines(1).build();
			List<SusRedomeModificadoCSV> registros = csvToBean.parse();
			return registros;
		}
	}
	
	public static void criarCSV(String nomeArquivo, List<SusRedomeModificadoCSV> registros) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(nomeArquivo));) {
			StatefulBeanToCsv<SusRedomeModificadoCSV> beanToCsv = new StatefulBeanToCsvBuilder<SusRedomeModificadoCSV>(writer)
														.withMappingStrategy(strategy)
														.withSeparator(',')
														.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
														.build();
			beanToCsv.write(registros);
		}
	}

}
