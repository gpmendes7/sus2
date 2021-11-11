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

public class PacienteSivepCSVHandler {
	
	private static ColumnPositionMappingStrategy<PacienteSivepCSV> strategy;
	
	static {
		strategy = new ColumnPositionMappingStrategy<PacienteSivepCSV>();
		strategy.setType(PacienteSivepCSV.class);

		String[] colunas = { "nomeCompleto", "dataNascimento"};
 
		strategy.setColumnMapping(colunas);
	}
	
	public static List<PacienteSivepCSV> carregarCSV(String nomeArquivo) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));) {
			CsvToBean<PacienteSivepCSV> csvToBean = new CsvToBeanBuilder<PacienteSivepCSV>(reader).withMappingStrategy(strategy)
					.withType(PacienteSivepCSV.class).withSeparator(',').withSkipLines(1).build();
			List<PacienteSivepCSV> registros = csvToBean.parse();
			return registros;
		}
	}
	
	public static void criarCSV(String nomeArquivo, List<PacienteSivepCSV> registros) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(nomeArquivo));) {
			StatefulBeanToCsv<PacienteSivepCSV> beanToCsv = new StatefulBeanToCsvBuilder<PacienteSivepCSV>(writer)
														.withMappingStrategy(strategy)
														.withSeparator(',')
														.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
														.build();
			beanToCsv.write(registros);
		}
	}

}
