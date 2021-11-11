package app;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

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
		System.out.println("************************************** ");
		System.out.println("selecionarPacientesEntre31E50Anos");
		
		List<SivepRedomeModificadoCSV> registrosSivepFiltrados = filtrarRegistrosSivepPorFaixaEtaria(registrosSivep, 31, 50);
		System.out.println("filtrarRegistrosSivepPorFaixaEtaria");
		System.out.println("registrosSivepFiltrados.size(): " + registrosSivepFiltrados.size());
		
		for (SivepRedomeModificadoCSV registroSivepFiltrado : registrosSivepFiltrados) {
			System.out.println("filtrarRegistrosSusPorFaixaEtaria");
			List<SusRedomeModificadoCSV> registrosSusFiltrados = filtrarRegistrosSusPorFaixaEtaria(registrosSus, 31, 50);
			System.out.println("registrosSusFiltrados.size(): " + registrosSusFiltrados.size());
			
			System.out.println("filtrarRegistrosSusPorSexo");
			registrosSusFiltrados = filtrarRegistrosSusPorSexo(registrosSusFiltrados, converterSexoSivepParaSus(registroSivepFiltrado.getSexo()));
			System.out.println("registrosSusFiltrados.size(): " + registrosSusFiltrados.size());
			
			//System.out.println("registroSivepFiltrado.getDataNotificacao(): " + registroSivepFiltrado.getDataNotificacao());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dataNotificacao = sdf.parse(registroSivepFiltrado.getDataNotificacao());
			Date data1 = alterarDiasEmData(dataNotificacao, -7);
			Date data2 = alterarDiasEmData(dataNotificacao, 7);
		
			//System.out.println("dataNotificacao: " + dataNotificacao);
			//System.out.println("data1: " + data1);
			//System.out.println("data2: " + data2);
			
			System.out.println("filtrarRegistrosSusPorDataNotificacao");
			registrosSusFiltrados = filtrarRegistrosSusPorDataNotificacao(registrosSusFiltrados, data1, data2);
			System.out.println("registrosSusFiltrados.size(): " + registrosSusFiltrados.size());
		
			//System.out.println("dataNotificacao: " + registrosSusFiltrados.get(0).getDataNotificacao());
			
			break;
		}
		
		System.out.println("************************************** ");
	}
	
	public static List<SivepRedomeModificadoCSV> filtrarRegistrosSivepPorFaixaEtaria(List<SivepRedomeModificadoCSV> registros, int idadeMinima, int idadeMaxima) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> Integer.parseInt(r.getIdade()) >= idadeMinima && Integer.parseInt(r.getIdade()) <= idadeMaxima)
						 .collect(Collectors.toList());
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorFaixaEtaria(List<SusRedomeModificadoCSV> registros, int idadeMinima, int idadeMaxima) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> Integer.parseInt(r.getIdade()) >= idadeMinima && Integer.parseInt(r.getIdade()) <= idadeMaxima)
						 .collect(Collectors.toList());
	}
	
	public static String converterSexoSivepParaSus(String sexoSivep) {
		if(sexoSivep.equals("F")) {
			return "Feminino";
		} else {
			return "Masculino";
		}
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorSexo(List<SusRedomeModificadoCSV> registros, String sexo) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> r.getSexo().equals(sexo))
						 .collect(Collectors.toList());
	}
	
	public static Date alterarDiasEmData(final Date data, int qtdDias) {
	    Date newDate = new Date(data.getTime());

	    GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(newDate);
	    calendar.add(Calendar.DATE, qtdDias);
	    newDate.setTime(calendar.getTime().getTime());

	    return newDate;
	}
	
	public static boolean dataEstaEmIntervalo(Date data1, final Date data2, final Date data){
	    return !(data.before(data1) || data.after(data2));
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorDataNotificacao(List<SusRedomeModificadoCSV> registros, Date data1, Date data2) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		
		List<SusRedomeModificadoCSV> registrosFiltro = new ArrayList<SusRedomeModificadoCSV>();
		
		for (SusRedomeModificadoCSV registro : registros) {
			Date dataNotificacao = sdf.parse(registro.getDataNotificacao());
			if(dataEstaEmIntervalo(data1, data2, dataNotificacao)) {
				registrosFiltro.add(registro);
			}
		}
		 
		return registrosFiltro;
	}
	
}
