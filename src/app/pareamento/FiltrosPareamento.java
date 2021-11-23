package app.pareamento;

import static app.util.ConversaoSusSivep.converterSexoSivepParaSus;
import static app.util.DataUtil.alterarDiasEmData;
import static app.util.DataUtil.dataEstaEmIntervalo;
import static app.util.StringUtil.normalizarString;
import static modelo.RegioesAdministrativas.obterNomeRegiaoMunicipio;
import static modelo.RegioesAdministrativas.obterRegiaoMunicipio;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import app.util.StringUtil;
import csv.SivepRedomeModificadoCSV;
import csv.SusRedomeModificadoCSV;

public class FiltrosPareamento {
	
	public static List<SivepRedomeModificadoCSV> filtrarRegistrosSivepPorFaixaEtaria(List<SivepRedomeModificadoCSV> registros, int idadeMinima, int idadeMaxima) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> Integer.parseInt(r.getIdade()) >= idadeMinima && Integer.parseInt(r.getIdade()) <= idadeMaxima)
						 .collect(Collectors.toList());
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusNaoUsados(List<SusRedomeModificadoCSV> registrosSus) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		return registrosSus.stream()
							.filter(r -> r.getObservacaoUso() == null || r.getObservacaoUso().equals(""))
							.collect(Collectors.toList());
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorFaixaEtaria(List<SusRedomeModificadoCSV> registrosSus, int idadeMinima, int idadeMaxima) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		return registrosSus.stream()
						   .filter(r -> Integer.parseInt(r.getIdade()) >= idadeMinima && Integer.parseInt(r.getIdade()) <= idadeMaxima)
						   .collect(Collectors.toList());
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorSexo(List<SusRedomeModificadoCSV> registrosSus, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		String sexoSus = converterSexoSivepParaSus(registroSivepFiltrado.getSexo());
		
		return registrosSus.stream()
						   .filter(r -> StringUtil.normalizarString(r.getSexo()).equals(StringUtil.normalizarString(sexoSus)))
						   .collect(Collectors.toList());		
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorDataNotificacao(List<SusRedomeModificadoCSV> registrosSus, SivepRedomeModificadoCSV registroSivepFiltrado, int numeroSemanas) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNotificacaoSivep = sdf1.parse(registroSivepFiltrado.getDataNotificacao());
		Date data1 = alterarDiasEmData(dataNotificacaoSivep, numeroSemanas * -7);
		Date data2 = alterarDiasEmData(dataNotificacaoSivep, numeroSemanas * 7);

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorDataNotificacao = new ArrayList<SusRedomeModificadoCSV>();
		
		for (SusRedomeModificadoCSV registro : registrosSus) {
			Date dataNotificacaoSus = sdf2.parse(registro.getDataNotificacao());
			if(dataEstaEmIntervalo(data1, data2, dataNotificacaoSus)) {
				registrosSusFiltradosPorDataNotificacao.add(registro);
			}
		}
		
		return registrosSusFiltradosPorDataNotificacao;
	}
	
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorMunicipio(List<SusRedomeModificadoCSV> registrosSus, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorMunicipio = registrosSus.stream()
						   .filter(r -> StringUtil.normalizarString(r.getMunicipio()).equals(StringUtil.normalizarString(registroSivepFiltrado.getMunicipio())))
						   .collect(Collectors.toList());
		
		registrosSusFiltradosPorMunicipio.stream().forEach(r -> r.setFiltroAreaMunicipio(r.getMunicipio()));
		
		return registrosSusFiltradosPorMunicipio;
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorAreaMunicipio(List<SusRedomeModificadoCSV> registrosSus, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorAreaMunicipio = new ArrayList<SusRedomeModificadoCSV>();
		
		String[] regiao = obterRegiaoMunicipio(registroSivepFiltrado.getMunicipio());
		
		if(regiao != null) {
			for (SusRedomeModificadoCSV registroSus : registrosSus) {
				String municipioRegistroNormalizado = normalizarString(registroSus.getMunicipio());
				
				for (String municipioRegiao : regiao) {
					String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
							
					if(municipioRegistroNormalizado.equals(municipioRegiaoNormalizado)) {
						registrosSusFiltradosPorAreaMunicipio.add(registroSus);
					}
				}
				
			}
			
			registrosSusFiltradosPorAreaMunicipio.stream().forEach(r -> r.setFiltroAreaMunicipio(obterNomeRegiaoMunicipio(r.getMunicipio())));
		}

		return registrosSusFiltradosPorAreaMunicipio;
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorRacaCor(List<SusRedomeModificadoCSV> registrosSus, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		return registrosSus.stream()
						   .filter(r -> StringUtil.normalizarString(r.getRacaCor()).equals(StringUtil.normalizarString(registroSivepFiltrado.getRacaCor())))
						   .collect(Collectors.toList());
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorTipoTesteComCovid(List<SusRedomeModificadoCSV> registrosSus) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorRTPCR = registrosSus.stream()
																				    .filter(r -> StringUtil.normalizarString(r.getTipoTeste()).equals(StringUtil.normalizarString("RT-PCR")))
																				    .collect(Collectors.toList());
				
		List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorAntigeno = registrosSus.stream()
																					  .filter(r -> StringUtil.normalizarString(r.getTipoTeste()).equals(StringUtil.normalizarString("TESTE RÁPIDO - ANTÍGENO")))
																					  .collect(Collectors.toList());
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorAnticorpo = registrosSus.stream()
																					   .filter(r -> StringUtil.normalizarString(r.getTipoTeste()).equals(StringUtil.normalizarString("TESTE RÁPIDO - ANTICORPO")))
																					   .collect(Collectors.toList());
		
		List<SusRedomeModificadoCSV> registrosSusFitradosPorTipoTesteComCovid = new ArrayList<SusRedomeModificadoCSV>();
		registrosSusFitradosPorTipoTesteComCovid.addAll(registrosSusFiltradosSusPorRTPCR);
		registrosSusFitradosPorTipoTesteComCovid.addAll(registrosSusFiltradosSusPorAntigeno);
		registrosSusFitradosPorTipoTesteComCovid.addAll(registrosSusFiltradosSusPorAnticorpo);
		
		return registrosSusFitradosPorTipoTesteComCovid;
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorResultado(List<SusRedomeModificadoCSV> registrosSus, String resultadoTeste) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registrosSus.stream()
					       .filter(r -> StringUtil.normalizarString(r.getResultadoTeste()).equals(StringUtil.normalizarString(resultadoTeste)))
					       .collect(Collectors.toList());
	}
	
}
