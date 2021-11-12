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
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorFaixaEtaria(List<SusRedomeModificadoCSV> registros, int idadeMinima, int idadeMaxima) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> Integer.parseInt(r.getIdade()) >= idadeMinima && Integer.parseInt(r.getIdade()) <= idadeMaxima)
						 .collect(Collectors.toList());
	}
	
	public static List<SusRedomeModificadoCSV> aplicarFiltroPorFaixaEtaria(List<SusRedomeModificadoCSV> registrosSusFiltrados, int idadeMinima, int idadeMaxima) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		//System.out.println("filtrarRegistrosSusPorFaixaEtaria");
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorFaixaEtaria = filtrarRegistrosSusPorFaixaEtaria(registrosSusFiltrados, idadeMinima, idadeMaxima);
		//System.out.println("registrosSusFiltradosPorFaixaEtaria.size(): " + registrosSusFiltradosPorFaixaEtaria.size());
		
		if(registrosSusFiltradosPorFaixaEtaria.size() >= 10) {
			registrosSusFiltrados = registrosSusFiltradosPorFaixaEtaria;
		}
		
		return registrosSusFiltrados;
	}
	
	private static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorSexo(List<SusRedomeModificadoCSV> registros, String sexo) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> StringUtil.normalizarString(r.getSexo()).equals(StringUtil.normalizarString(sexo)))
						 .collect(Collectors.toList());
	}
	
	public static List<SusRedomeModificadoCSV> aplicarFiltroPorSexo(List<SusRedomeModificadoCSV> registrosSusFiltrados, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		//System.out.println("filtrarRegistrosSusPorSexo");
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorSexo = filtrarRegistrosSusPorSexo(registrosSusFiltrados, converterSexoSivepParaSus(registroSivepFiltrado.getSexo()));
		//System.out.println("registrosSusFiltradosPorSexo.size(): " + registrosSusFiltradosPorSexo.size());
		
		if(registrosSusFiltradosPorSexo.size() >= 10) {
			registrosSusFiltrados = registrosSusFiltradosPorSexo;
		}
		
		return registrosSusFiltrados;
	}
	
	private static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorDataNotificacao(List<SusRedomeModificadoCSV> registros, Date data1, Date data2) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {	
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
	
	public static List<SusRedomeModificadoCSV> aplicarFiltroPorDataNotificacao(List<SusRedomeModificadoCSV> registrosSusFiltrados, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		//System.out.println("registroSivepFiltrado.getDataNotificacao(): " + registroSivepFiltrado.getDataNotificacao());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNotificacao = sdf.parse(registroSivepFiltrado.getDataNotificacao());
		Date data1 = alterarDiasEmData(dataNotificacao, -7);
		Date data2 = alterarDiasEmData(dataNotificacao, 7);
	
		//System.out.println("dataNotificacao: " + dataNotificacao);
		//System.out.println("data1: " + data1);
		//System.out.println("data2: " + data2);
		
		//System.out.println("filtrarRegistrosSusPorDataNotificacao");
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorDataNotificacao = filtrarRegistrosSusPorDataNotificacao(registrosSusFiltrados, data1, data2);
		//System.out.println("registrosSusFiltradosPorDataNotificacao.size(): " + registrosSusFiltradosPorDataNotificacao.size());
		
		if(registrosSusFiltradosPorDataNotificacao.size() >= 10) {
			registrosSusFiltrados = registrosSusFiltradosPorDataNotificacao;
		}
		
		return registrosSusFiltrados;
	}
	
	
	private static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorMunicipio(List<SusRedomeModificadoCSV> registros, String municipio) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> StringUtil.normalizarString(r.getMunicipio()).equals(StringUtil.normalizarString(municipio)))
						 .collect(Collectors.toList());
	}
	
	private static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorAreaMunicipio(List<SusRedomeModificadoCSV> registros, String municipio) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		String[] regiao = RegioesAdministrativas.obterRegiaoMunicipio(municipio);

		//System.out.println("regiao :" + regiao);
		
		/*
		for (String municipioRegiao : regiao) {
			System.out.println("municipioRegiao: " + municipioRegiao);
		}*/
		
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
	
	public static List<SusRedomeModificadoCSV> aplicarFiltroPorMunicipioOuArea(List<SusRedomeModificadoCSV> registrosSusFiltrados, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		//System.out.println("municipio: " + registroSivepFiltrado.getMunicipio());
		
		//System.out.println("filtrarRegistrosSusPorMunicipio");
		List<SusRedomeModificadoCSV> registrosSusFiltradosPorMunicipio = filtrarRegistrosSusPorMunicipio(registrosSusFiltrados, registroSivepFiltrado.getMunicipio());
		//System.out.println("registrosSusFiltradosPorMunicipio.size(): " + registrosSusFiltradosPorMunicipio.size());
		
		if(registrosSusFiltradosPorMunicipio.size() >= 10) {
			registrosSusFiltrados = registrosSusFiltradosPorMunicipio;
		} else {
			//System.out.println("filtrarRegistrosSusPorAreaMunicipio");
			List<SusRedomeModificadoCSV> registrosSusFiltradosPorAreaMunicipio = filtrarRegistrosSusPorAreaMunicipio(registrosSusFiltrados, registroSivepFiltrado.getMunicipio());
			//System.out.println("registrosSusFiltradosPorAreaMunicipio.size(): " + registrosSusFiltradosPorAreaMunicipio.size());
			
			if(registrosSusFiltradosPorAreaMunicipio.size() >= 10) {
				registrosSusFiltrados = registrosSusFiltradosPorAreaMunicipio;
			}
		}
		
		return registrosSusFiltrados;
	}
	
	private static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorRacaCor(List<SusRedomeModificadoCSV> registros, String racaCor) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> StringUtil.normalizarString(r.getRacaCor()).equals(StringUtil.normalizarString(racaCor)))
						 .collect(Collectors.toList());
	}
	
	public static List<SusRedomeModificadoCSV> aplicarFiltroPorRacaCor(List<SusRedomeModificadoCSV> registrosSusFiltrados, SivepRedomeModificadoCSV registroSivepFiltrado) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		//System.out.println("racaCor: " + registroSivepFiltrado.getRacaCor());
		
		//System.out.println("filtrarRegistrosSusPorRacaCor");
		List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorRacaCor = filtrarRegistrosSusPorRacaCor(registrosSusFiltrados, registroSivepFiltrado.getRacaCor());
		//System.out.println("registrosSusFiltradosSusPorRacaCor.size(): " + registrosSusFiltradosSusPorRacaCor.size());
		
		if(registrosSusFiltradosSusPorRacaCor.size() >= 10) {
			registrosSusFiltrados = registrosSusFiltradosSusPorRacaCor;
		}
		
		return registrosSusFiltrados;
	}
	
	private static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorTipoTeste(List<SusRedomeModificadoCSV> registros, String tipoTeste) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> StringUtil.normalizarString(r.getTipoTeste()).equals(StringUtil.normalizarString(tipoTeste)))
						 .collect(Collectors.toList());
	}
	
	public static List<SusRedomeModificadoCSV> aplicarFiltroPorTipoTeste(List<SusRedomeModificadoCSV> registrosSusFiltrados) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		//System.out.println("filtrarRegistrosSusPorTipoTeste (RT-PCR)");
		List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorTipoTeste1 = filtrarRegistrosSusPorTipoTeste(registrosSusFiltrados, "RT-PCR");
		//System.out.println("registrosSusFiltradosSusPorTipoTeste1.size(): " + registrosSusFiltradosSusPorTipoTeste1.size());
		
		if(registrosSusFiltradosSusPorTipoTeste1.size() >= 10) {
			registrosSusFiltrados = registrosSusFiltradosSusPorTipoTeste1;
		} else {
			//System.out.println("filtrarRegistrosSusPorTipoTeste (TESTE RÁPIDO - ANTÍGENO)");
			List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorTipoTeste2 = filtrarRegistrosSusPorTipoTeste(registrosSusFiltrados, "TESTE RÁPIDO - ANTÍGENO");
			//System.out.println("registrosSusFiltradosSusPorTipoTeste2.size(): " + registrosSusFiltradosSusPorTipoTeste2.size());
			
			if(registrosSusFiltradosSusPorTipoTeste1.size() >= 10) {
				registrosSusFiltrados = registrosSusFiltradosSusPorTipoTeste2;
			} else {
				//System.out.println("filtrarRegistrosSusPorTipoTeste (TESTE RÁPIDO - ANTICORPO)");
				List<SusRedomeModificadoCSV> registrosSusFiltradosSusPorTipoTeste3 = filtrarRegistrosSusPorTipoTeste(registrosSusFiltrados, "TESTE RÁPIDO - ANTICORPO");
				//System.out.println("registrosSusFiltradosSusPorTipoTeste3.size(): " + registrosSusFiltradosSusPorTipoTeste3.size());
				
				if(registrosSusFiltradosSusPorTipoTeste3.size() >= 10) {
					registrosSusFiltrados = registrosSusFiltradosSusPorTipoTeste3;
				}
			}
		}
		
		return registrosSusFiltrados;
	}
	
	public static List<SusRedomeModificadoCSV> filtrarRegistrosSusPorResultado(List<SusRedomeModificadoCSV> registros, String resultadoTeste) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		return registros.stream()
						 .filter(r -> StringUtil.normalizarString(r.getResultadoTeste()).equals(StringUtil.normalizarString(resultadoTeste)))
						 .collect(Collectors.toList());
	}

}
