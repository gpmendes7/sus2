package app.posprocessamento;

import static app.util.ConversaoSusSivep.converterDataNotificacaoSusParaSivep;
import static app.util.ConversaoSusSivep.converterSexoSivepParaSus;
import static app.util.StringUtil.normalizarString;
import static modelo.RegioesAdministrativas.obterNomeRegiaoMunicipio;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.PareamentoCSV;
import csv.PareamentoCSVHandler;
import csv.SivepRedomeModificadoCSV;
import csv.SivepRedomeModificadoCSVHandler;
import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler2;
import modelo.RegioesAdministrativas;

public class GerarCSVPareamento {
	
	private static List<PareamentoCSV> registrosPareamento;
	
	public static void main(String[] args) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		registrosPareamento = new ArrayList<>();
		
		carregarDadosObito();
		carregarDadosUti();
		carregarDadosInternado();
		carregarDadosRecuperado();
		
		registrosPareamento.add(0, new PareamentoCSV("campo1", "identificacao", "nomeCompleto", 
												     "cpf", "municipio", "regiao", "filtroAreaMunicipio", 
												     "dataNotificacao", "sexo", "idade", 
												     "racaCor", "tipoTeste", "resultadoTeste", 												  
												     "desfecho", "origem", "evolucaoCaso", "intervalo"));
		
		PareamentoCSVHandler.criarCSV("./arquivos/csv/PareamentoTotalSivepSus.csv", registrosPareamento);
	}
	
	private static void carregarDadosObito() throws IOException, ParseException {
		List<SivepRedomeModificadoCSV> registrosSivepFaixa1 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/obito/SIVEP_REDOME(OBITO-PacientesUsadosEntre31E50Anos).csv");
		List<SivepRedomeModificadoCSV> registrosSivepFaixa2 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/obito/SIVEP_REDOME(OBITO-PacientesUsadosEntre51E59Anos).csv");
		List<SivepRedomeModificadoCSV> registrosSivepFaixa3 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/obito/SIVEP_REDOME(OBITO-PacientesUsadosEntre60E75Anos).csv");
			
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa1) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(converterSexoSivepParaSus(registroSivep.getSexo())), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "31-50"));
		}
		
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa2) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(converterSexoSivepParaSus(registroSivep.getSexo())), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "51-59"));
		}
		
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa3) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(converterSexoSivepParaSus(registroSivep.getSexo())), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "60-75"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa1 = new ArrayList<>();
		registrosSusFaixa1.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre31E50AnosResultadoPositivo).csv"));
		registrosSusFaixa1.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre31E50AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa1) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "OBITO", "31-50"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa2 = new ArrayList<>();
		registrosSusFaixa2.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre51E59AnosResultadoPositivo).csv"));
		registrosSusFaixa2.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre51E59AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa2) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "OBITO", "51-59"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa3 = new ArrayList<>();
		registrosSusFaixa3.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre60E75AnosResultadoPositivo).csv"));
		registrosSusFaixa3.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/obito/Sus_REDOME(PacientesObitoEntre60E75AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa3) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "OBITO", "60-75"));
		}
		
	}
	
	private static void carregarDadosUti() throws IOException, ParseException {
		List<SivepRedomeModificadoCSV> registrosSivepFaixa1 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/uti/SIVEP_REDOME(UTI-PacientesUsadosEntre26E42Anos).csv");
		List<SivepRedomeModificadoCSV> registrosSivepFaixa2 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/uti/SIVEP_REDOME(UTI-PacientesUsadosEntre43E53Anos).csv");
		List<SivepRedomeModificadoCSV> registrosSivepFaixa3 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/uti/SIVEP_REDOME(UTI-PacientesUsadosEntre54E67Anos).csv");
			
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa1) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(converterSexoSivepParaSus(registroSivep.getSexo())), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "26-42"));
		}
		
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa2) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(converterSexoSivepParaSus(registroSivep.getSexo())), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "43-53"));
		}
		
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa3) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(converterSexoSivepParaSus(registroSivep.getSexo())), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "54-67"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa1 = new ArrayList<>();
		registrosSusFaixa1.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre26E42AnosResultadoPositivo).csv"));
		registrosSusFaixa1.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre26E42AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa1) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "UTI", "26-42"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa2 = new ArrayList<>();
		registrosSusFaixa2.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre43E53AnosResultadoPositivo).csv"));
		registrosSusFaixa2.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre43E53AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa2) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "UTI", "43-53"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa3 = new ArrayList<>();
		registrosSusFaixa3.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre54E67AnosResultadoPositivo).csv"));
		registrosSusFaixa3.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/uti/Sus_REDOME(PacientesUtiEntre54E67AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa3) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "UTI", "54-67"));
		}
		
	}
	
	private static void carregarDadosInternado() throws IOException, ParseException {
		List<SivepRedomeModificadoCSV> registrosSivepFaixa1 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesUsadosEntre25E44Anos).csv");
		List<SivepRedomeModificadoCSV> registrosSivepFaixa2 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesUsadosEntre45E56Anos).csv");
		List<SivepRedomeModificadoCSV> registrosSivepFaixa3 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/internado/SIVEP_REDOME(INTERNADO-PacientesUsadosEntre57E81Anos).csv");
			
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa1) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(converterSexoSivepParaSus(registroSivep.getSexo())), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "25-44"));
		}
		
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa2) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(converterSexoSivepParaSus(registroSivep.getSexo())), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "45-56"));
		}
		
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa3) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(converterSexoSivepParaSus(registroSivep.getSexo())), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "57-81"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa1 = new ArrayList<>();
		registrosSusFaixa1.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre25E44AnosResultadoPositivo).csv"));
		registrosSusFaixa1.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre25E44AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa1) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "INTERNADO", "25-44"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa2 = new ArrayList<>();
		registrosSusFaixa2.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre45E56AnosResultadoPositivo).csv"));
		registrosSusFaixa2.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre45E56AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa2) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "INTERNADO", "45-56"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa3 = new ArrayList<>();
		registrosSusFaixa3.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre57E81AnosResultadoPositivo).csv"));
		registrosSusFaixa3.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/internado/Sus_REDOME(PacientesInternadoEntre57E81AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa3) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "INTERNADO", "57-81"));
		}
		
	}
	
	private static void carregarDadosRecuperado() throws IOException, ParseException {
		List<SivepRedomeModificadoCSV> registrosSivepFaixa1 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/recuperado/SIVEP_REDOME(RECUPERADO-PacientesUsadosEntre22E42Anos).csv");
		List<SivepRedomeModificadoCSV> registrosSivepFaixa2 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/recuperado/SIVEP_REDOME(RECUPERADO-PacientesUsadosEntre43E53Anos).csv");
		List<SivepRedomeModificadoCSV> registrosSivepFaixa3 = SivepRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/recuperado/SIVEP_REDOME(RECUPERADO-PacientesUsadosEntre54E74Anos).csv");
			
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa1) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(converterSexoSivepParaSus(registroSivep.getSexo())), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "22-42"));
		}
		
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa2) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(registroSivep.getSexo()), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "43-53"));
		}
		
		for (SivepRedomeModificadoCSV registroSivep : registrosSivepFaixa3) {
			registrosPareamento.add(new PareamentoCSV(registroSivep.getCampo1(), registroSivep.getIdentificacao(), registroSivep.getNomeCompleto(), 
													  "", normalizarString(registroSivep.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSivep.getMunicipio())), "", 
													  registroSivep.getDataNotificacao(), converterSexoSivepParaSus(registroSivep.getSexo()), registroSivep.getIdade(), 
													  registroSivep.getRacaCor(), "", registroSivep.getResultadoTeste(), 
													  registroSivep.getEvolucaoCaso(), "sivep", registroSivep.getEvolucaoCaso(), "54-74"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa1 = new ArrayList<>();
		registrosSusFaixa1.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre22E42AnosResultadoPositivo).csv"));
		registrosSusFaixa1.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre22E42AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa1) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(normalizarString(registroSus.getMunicipio())), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "RECUPERADO", "22-42"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa2 = new ArrayList<>();
		registrosSusFaixa2.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre43E53AnosResultadoPositivo).csv"));
		registrosSusFaixa2.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre43E53AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa2) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "RECUPERADO", "43-53"));
		}
		
		List<SusRedomeModificadoCSV> registrosSusFaixa3 = new ArrayList<>();
		registrosSusFaixa3.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre54E74AnosResultadoPositivo).csv"));
		registrosSusFaixa3.addAll(SusRedomeModificadoCSVHandler2.carregarCSV("./arquivos/csv/recuperado/Sus_REDOME(PacientesRecuperadoEntre54E74AnosResultadoNegativo).csv"));
		
		for (SusRedomeModificadoCSV registroSus : registrosSusFaixa3) {
			registrosPareamento.add(new PareamentoCSV(registroSus.getCampo1(), "", registroSus.getNomeCompleto(), 
											          registroSus.getCpf(), normalizarString(registroSus.getMunicipio()), normalizarString(RegioesAdministrativas.obterNomeRegiaoMunicipio(registroSus.getMunicipio())), normalizarString(registroSus.getFiltroAreaMunicipio()), 
											          converterDataNotificacaoSusParaSivep(registroSus.getDataNotificacao()), registroSus.getSexo(), registroSus.getIdade(), 
											          registroSus.getRacaCor(), registroSus.getTipoTeste(), registroSus.getResultadoTeste(), 
											          "", "esus", "RECUPERADO", "54-74"));
		}
		
	}

}
