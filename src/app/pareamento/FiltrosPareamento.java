package app.pareamento;

import static app.util.ConversaoSusSivep.converterSexoSivepParaSus;
import static app.util.DataUtil.alterarDiasEmData;
import static app.util.DataUtil.dataEstaEmIntervalo;
import static app.util.StringUtil.normalizarString;

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
import modelo.RegioesAdministrativas;

public class FiltrosPareamento {
	
	public static List<SivepRedomeModificadoCSV> filtrarRegistrosSivepPorFaixaEtaria(List<SivepRedomeModificadoCSV> registros, int idadeMinima, int idadeMaxima) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> Integer.parseInt(r.getIdade()) >= idadeMinima && Integer.parseInt(r.getIdade()) <= idadeMaxima)
						 .collect(Collectors.toList());
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorFaixaEtaria(List<SusRedomeModificadoCSV> registrosSus, int idadeMinima, int idadeMaxima) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		//System.out.println("filtrarRegistrosSusPorFaixaEtaria");
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorFaixaEtaria = registrosSus.stream()
																					   .filter(r -> Integer.parseInt(r.getIdade()) >= idadeMinima 
																					           && Integer.parseInt(r.getIdade()) <= idadeMaxima)
																					   .collect(Collectors.toList());
		//System.out.println("registrosSusFiltradosPorFaixaEtaria.size(): " + registrosSusFiltradosPorFaixaEtaria.size());
		
		if(registrosSusFiltradosPorFaixaEtaria.size() >= 10) {
			return registrosSusFiltradosPorFaixaEtaria;
		}
		
		return registrosSus;
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorSexo(List<SusRedomeModificadoCSV> registrosSus, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		//System.out.println("filtrarRegistrosSusPorSexo");
		
		String sexoSus = converterSexoSivepParaSus(registroSivepFiltrado.getSexo());
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorSexo = registrosSus.stream()
																				.filter(r -> StringUtil.normalizarString(r.getSexo()).equals(StringUtil.normalizarString(sexoSus)))
																				.collect(Collectors.toList());		
		//System.out.println("registrosSusFiltradosPorSexo.size(): " + registrosSusFiltradosPorSexo.size());
		
		if(registrosSusFiltradosPorSexo.size() >= 10) {
			return registrosSusFiltradosPorSexo;
		}
		
		return registrosSus;
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorDataNotificacao(List<SusRedomeModificadoCSV> registrosSus, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		//System.out.println("registroSivepFiltrado.getDataNotificacao(): " + registroSivepFiltrado.getDataNotificacao());
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNotificacaoSivep = sdf1.parse(registroSivepFiltrado.getDataNotificacao());
		Date data1 = alterarDiasEmData(dataNotificacaoSivep, -7);
		Date data2 = alterarDiasEmData(dataNotificacaoSivep, 7);
		
		//System.out.println("dataNotificacaoSivep: " + dataNotificacaoSivep);
		//System.out.println("data1: " + data1);
		//System.out.println("data2: " + data2);
		
		//System.out.println("filtrarRegistrosSusPorDataNotificacao");
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorDataNotificacao = new ArrayList<SusRedomeModificadoCSV>();
		
		for (SusRedomeModificadoCSV registro : registrosSus) {
			Date dataNotificacaoSus = sdf2.parse(registro.getDataNotificacao());
			if(dataEstaEmIntervalo(data1, data2, dataNotificacaoSus)) {
				registrosSusFiltradosPorDataNotificacao.add(registro);
			}
		}
		
		//System.out.println("registrosSusFiltradosPorDataNotificacao.size(): " + registrosSusFiltradosPorDataNotificacao.size());
		
		if(registrosSusFiltradosPorDataNotificacao.size() >= 10) {
			return registrosSusFiltradosPorDataNotificacao;
		}
		
		return registrosSus;
	}
	
	
	private static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorMunicipio(List<SusRedomeModificadoCSV> registros, String municipio) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> StringUtil.normalizarString(r.getMunicipio()).equals(StringUtil.normalizarString(municipio)))
						 .collect(Collectors.toList());
	}
	
	private static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorAreaMunicipio(List<SusRedomeModificadoCSV> registros, String municipio) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		String[] regiao = RegioesAdministrativas.obterRegiaoMunicipio(municipio);

		List<SusRedomeModificadoCSV> registrosFiltro = new ArrayList<SusRedomeModificadoCSV>();
		
		for (SusRedomeModificadoCSV registro : registros) {
			String municipioRegistroNormalizado = normalizarString(registro.getMunicipio());
			
			for (String municipioRegiao : regiao) {
				String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
						
				if(municipioRegistroNormalizado.equals(municipioRegiaoNormalizado)) {
					registrosFiltro.add(registro);
				}
			}
			
		}
		
		return registrosFiltro;
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorMunicipioOuArea(List<SusRedomeModificadoCSV> registrosSus, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		//System.out.println("municipio: " + registroSivepFiltrado.getMunicipio());
		
		//System.out.println("filtrarRegistrosSusPorMunicipio");
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorMunicipio = filtrarRegistrosSusPorMunicipio(registrosSus, registroSivepFiltrado.getMunicipio());
		//System.out.println("registrosSusFiltradosPorMunicipio.size(): " + registrosSusFiltradosPorMunicipio.size());
		
		if(registrosSusFiltradosPorMunicipio.size() >= 10) {
			return registrosSusFiltradosPorMunicipio;
		} else {
			//System.out.println("filtrarRegistrosSusPorAreaMunicipio");
			
			List<SusRedomeModificadoCSV> registrosSusFiltradosPorAreaMunicipio = filtrarRegistrosSusPorAreaMunicipio(registrosSus, registroSivepFiltrado.getMunicipio());
			//System.out.println("registrosSusFiltradosPorAreaMunicipio.size(): " + registrosSusFiltradosPorAreaMunicipio.size());
			
			if(registrosSusFiltradosPorAreaMunicipio.size() >= 10) {
				return registrosSusFiltradosPorAreaMunicipio;
			}
		}
		
		return registrosSus;
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorRacaCor(List<SusRedomeModificadoCSV> registrosSus, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		//System.out.println("racaCor: " + registroSivepFiltrado.getRacaCor());
		
		//System.out.println("filtrarRegistrosSusPorRacaCor");
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorRacaCor = registrosSus.stream()
																					  .filter(r -> StringUtil.normalizarString(r.getRacaCor()).equals(StringUtil.normalizarString(registroSivepFiltrado.getRacaCor())))
																					  .collect(Collectors.toList());
		//System.out.println("registrosSusFiltradosSusPorRacaCor.size(): " + registrosSusFiltradosSusPorRacaCor.size());
		
		if(registrosSusFiltradosSusPorRacaCor.size() >= 10) {
			return registrosSusFiltradosSusPorRacaCor;
		}
		
		return registrosSus;
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorTipoTeste(List<SusRedomeModificadoCSV> registrosSus) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		//System.out.println("filtrarRegistrosSusPorTipoTeste");
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorRTPCR = registrosSus.stream()
																				    .filter(r -> StringUtil.normalizarString(r.getTipoTeste()).equals(StringUtil.normalizarString("RT-PCR")))
																				    .collect(Collectors.toList());
		//System.out.println("registrosSusFiltradosSusPorRTPCR.size(): " + registrosSusFiltradosSusPorRTPCR.size());
		
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorAntigeno = registrosSus.stream()
																					  .filter(r -> StringUtil.normalizarString(r.getTipoTeste()).equals(StringUtil.normalizarString("TESTE RÁPIDO - ANTÍGENO")))
																					  .collect(Collectors.toList());
		//System.out.println("registrosSusFiltradosSusPorAntigeno.size(): " + registrosSusFiltradosSusPorAntigeno.size());
		
		List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorAnticorpo =registrosSus.stream()
																					   .filter(r -> StringUtil.normalizarString(r.getTipoTeste()).equals(StringUtil.normalizarString("TESTE RÁPIDO - ANTICORPO")))
																					   .collect(Collectors.toList());
		//System.out.println("registrosSusFiltradosSusPorAnticorpo.size(): " + registrosSusFiltradosSusPorAnticorpo.size());
		
		List<SusRedomeModificadoCSV> registrosSusFitradosPorTipoTeste = new ArrayList<SusRedomeModificadoCSV>();
		registrosSusFitradosPorTipoTeste.addAll(registrosSusFiltradosSusPorRTPCR);
		registrosSusFitradosPorTipoTeste.addAll(registrosSusFiltradosSusPorAntigeno);
		registrosSusFitradosPorTipoTeste.addAll(registrosSusFiltradosSusPorAnticorpo);
		//System.out.println("registrosSusFitradosPorTipoTeste.size(): " + registrosSusFitradosPorTipoTeste.size());

		if(registrosSusFitradosPorTipoTeste.size() >= 10) {
			return registrosSusFitradosPorTipoTeste;
		}
		
		return registrosSus;
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorResultado(List<SusRedomeModificadoCSV> registros, String resultadoTeste) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> StringUtil.normalizarString(r.getResultadoTeste()).equals(StringUtil.normalizarString(resultadoTeste)))
						 .collect(Collectors.toList());
	}

}
