package app;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import app.util.StringUtil;
import csv.PacienteSusCSV;
import csv.PacienteSusCSVHandler;
import csv.SusRedomeCSV;
import csv.SusRedomeCSVHandler2;
import modelo.Paciente;

public class BuscarRegistrosSus {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) throws IOException, ParseException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeCSV> registros = SusRedomeCSVHandler2.carregarCSV("./arquivos/csv/Esus_Seq.csv");
		System.out.println("registros: " + registros.size());
	
		emf = Persistence.createEntityManagerFactory("sus2"); 
		em = emf.createEntityManager();
		
		List<Paciente> pacientesBaseSus = carregarPacientes(registros);
		System.out.println("pacientesBaseSus.size(): " + pacientesBaseSus.size());
		
		em.close(); 
		emf.close();
		
		List<SusRedomeCSV> registrosComCopia = new ArrayList<>();
		while(!registros.isEmpty()) {
			SusRedomeCSV registro = registros.get(0);
				
			List<SusRedomeCSV> registrosFiltro = registros.stream()
					                                   .filter(p -> StringUtil.normalizarString(p.getNomeCompleto()).equals(StringUtil.normalizarString(registro.getNomeCompleto()))
					                                		   && p.getDataNascimento().equals(registro.getDataNascimento())
		                                                       && p.getCpf().equals(registro.getCpf()))
					                                   .collect(Collectors.toList());
			if(registrosFiltro.size() >= 2) {
				registrosComCopia.add(registro);
			}	
			
			registros.remove(registro);
			registros.removeAll(registrosFiltro);
		}
		
		System.out.println("registrosComCopia.size(): " + registrosComCopia.size());
		
		SusRedomeCSVHandler2.criarCSV("./arquivos/csv/Esus_Seq(Redundantes).csv", registrosComCopia);
		
		List<PacienteSusCSV> pacientesSusCSV = new ArrayList<>();
		
		for (Paciente paciente : pacientesBaseSus) {
			 String id = paciente.getId() != null ? paciente.getId().toString() : "";
			 String dataNascimento = paciente.getDataNascimento() != null ? paciente.getDataNascimento().toString() : "";
			 String dataNotificacao = paciente.getDataNotificacao() != null ? paciente.getDataNotificacao().toString() : "";
			 String dataInicioSintomas = paciente.getDataInicioSintomas() != null ? paciente.getDataInicioSintomas().toString() : "";
			 String dataTeste = paciente.getDataTeste() != null ? paciente.getDataTeste().toString() : "";
			 String createdAt = paciente.getCreatedAt() != null ? paciente.getCreatedAt().toString() : "";
			 String updatedAt = paciente.getUpdatedAt() != null ? paciente.getUpdatedAt().toString() : "";
			 String dataEncerramento = paciente.getDataEncerramento() != null ? paciente.getDataEncerramento().toString() : "";
			 String dataTesteSorologico = paciente.getDataTesteSorologico() != null ? paciente.getDataTesteSorologico().toString() : "";
			 
             PacienteSusCSV pacienteSusCSV = new PacienteSusCSV(id, dataNascimento, dataNotificacao, 
            		 											dataInicioSintomas, dataTeste, paciente.getpUsuario(), 
																paciente.getEstrangeiro(), paciente.getProfissionalSaude(), paciente.getProfissionalSeguranca(),
																paciente.getCbo(), paciente.getCpf(), paciente.getCns(),
																paciente.getNomeCompleto(), paciente.getNomeMae(), paciente.getPaisOrigem(),
																paciente.getSexo(), paciente.getRacaCor(), paciente.getEtnia(),
																paciente.getCep(), paciente.getPassaporte(), paciente.getLogradouro(),
																paciente.getNumero(), paciente.getComplemento(), paciente.getBairro(),
																paciente.getEstado(), paciente.getMunicipio(), paciente.getTelefoneContato(),
																paciente.getTelefone(), paciente.getSintomas(), paciente.getOutrosSintomas(),
																paciente.getCondicoes(), paciente.getEstadoTeste(), paciente.getTipoTeste(),
																paciente.getTesteSorologico(), dataTesteSorologico, paciente.getResultadoTeste(),
																paciente.getTipoTesteSorologico(), paciente.getResultadoTesteSorologicoIgA(), paciente.getResultadoTesteSorologicoIgG(),
																paciente.getResultadoTesteSorologicoIgM(), paciente.getResultadoTesteSorologicoTotais(), paciente.getNumeroNotificacao(),
																paciente.getCnes(), paciente.getEstadoNotificacao(), paciente.getMunicipioNotificacao(),
																paciente.getOrigem(), paciente.getNomeCompletoDesnormalizado(), createdAt,
																updatedAt, paciente.getSourceId(), paciente.getIdade(),
																paciente.getClassificacaoFinal(), paciente.getEvolucaoCaso(), dataEncerramento,
																paciente.getDescricaoRacaCor(), paciente.getpUsuarioAlteracao(), paciente.getRpa(),
																paciente.getIdOrigem(), paciente.getDesnormalizarNome(), paciente.getTimestampNotificacao(),
																paciente.getEstadoIBGE(), paciente.getEstadoNotificacaoIBGE(), paciente.getMunicipioIBGE(),
																paciente.getMunicipioNotificacaoIBGE(), paciente.getNotificadorCpf(), paciente.getNotificadorEmail(),
																paciente.getNotificadorNome(), paciente.getNotificadorCNPJ(), paciente.getCodigoClassificacaoFinal(),
																paciente.getCodigoEvolucaoCaso(), paciente.getCodigoEstadoTeste(), paciente.getLabCnes(),
																paciente.getCodigoCondicoes(), paciente.getCodigoResultadoTeste(), paciente.getCodigoSintomas(),
																paciente.getEmail(), paciente.getComunidadeTradicional(), paciente.getContemComunidadeTradicional(),
																paciente.getVersaoFormulario(), paciente.getCodigoResultadoTesteSorologicoIgM(), paciente.getCodigoResultadoTesteSorologicoIgG(),
																paciente.getCodigoTipoTesteSorologico(), paciente.getCodigoTesteSorologico(), paciente.getCodigoTipoTeste(),
																paciente.getCodigoProfissionalSeguranca(), paciente.getCodigoProfissionalSaude(), paciente.getCodigoTemCpf(),
																paciente.getCodigoSexo(), paciente.getCodigoEstrangeiro(), paciente.getCodigoCbo(),
																paciente.getCodigoPaisOrigem(), paciente.getCodigoResultadoTesteSorologicoTotais(), paciente.getCodigoResultadoTesteSorologicoIgA(),
															    paciente.getCodigoComunidadeTradicional());		
             
             pacientesSusCSV.add(pacienteSusCSV);
		}
		
		
		
		pacientesSusCSV.add(0, new PacienteSusCSV("id", "dataNascimento", "dataNotificacao", 
								               "dataInicioSintomas", "dataTeste", "pUsuario", 
								               "estrangeiro", "profissionalSaude", "profissionalSeguranca", 
								               "cbo", "cpf", "cns", 
								               "nomeCompleto", "nomeMae", "paisOrigem", 
								               "sexo", "racaCor", "etnia", 
								               "cep", "passaporte", "logradouro", 
								               "numero", "complemento", "bairro", 
								               "estado", "municipio", "telefoneContato", 
								               "telefone", "sintomas", "outrosSintomas", 
								               "condicoes", "estadoTeste", "tipoTeste", 
								               "testeSorologico", "dataTesteSorologico", "resultadoTeste", 
								               "tipoTesteSorologico", "resultadoTesteSorologicoIgA", "resultadoTesteSorologicoIgG", 
								               "resultadoTesteSorologicoIgM", "resultadoTesteSorologicoTotais", "numeroNotificacao", 
								               "cnes", "estadoNotificacao", "municipioNotificacao", 
								               "origem", "nomeCompletoDesnormalizado", "createdAt", 
								               "updatedAt", "sourceId", "idade", 
								               "classificacaoFinal", "evolucaoCaso", "dataEncerramento", 
								               "descricaoRacaCor", "pUsuarioAlteracao", "rpa", 
								               "idOrigem", "desnormalizarNome", "timestampNotificacao", 
								               "estadoIBGE", "estadoNotificacaoIBGE", "municipioIBGE", 
								               "municipioNotificacaoIBGE", "notificadorCpf", "notificadorEmail", 
								               "notificadorNome", "notificadorCNPJ", "codigoClassificacaoFinal", 
								               "codigoEvolucaoCaso", "codigoEstadoTeste", "labCnes", 
								               "codigoCondicoes", "codigoResultadoTeste", "codigoSintomas",
								               "email", "comunidadeTradicional", "contemComunidadeTradicional", 
								               "versaoFormulario", "codigoResultadoTesteSorologicoIgM", "codigoResultadoTesteSorologicoIgG", 
								               "codigoTipoTesteSorologico", "codigoTesteSorologico", "codigoTipoTeste", 
								               "codigoProfissionalSeguranca", "codigoProfissionalSaude", "codigoTemCpf", 
								               "codigoSexo", "codigoEstrangeiro", "codigoCbo", 
								               "codigoPaisOrigem", "codigoResultadoTesteSorologicoTotais", "codigoResultadoTesteSorologicoIgA", 
								               "codigoComunidadeTradicional"));
		
		System.out.println("pacientesSusCSV.size(): " + pacientesSusCSV.size());
		
		PacienteSusCSVHandler.criarCSV("./arquivos/csv/Esus_Seq(Completo).csv", pacientesSusCSV);
	}
	
	private static List<Paciente> carregarPacientes(List<SusRedomeCSV> registros) throws ParseException {
		List<Paciente> pacientes = new ArrayList<Paciente>();
		List<Paciente> pacientesLote = new ArrayList<Paciente>();
		
		int offset = 0;
		
	    while ((pacientesLote = obterPacientes(registros, offset, 100000)).size() > 0) {
	    	pacientes.addAll(pacientesLote);
	    	
	    	offset += pacientesLote.size(); 
	    	System.out.println("offset: " + offset);
	    }
	    
		return pacientes;
	}
	
	private static List<Paciente> obterPacientes(List<SusRedomeCSV> registros, int offset, int max) throws ParseException {
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("\n from Paciente p ");
		jpql.append("\n where (p.nomeCompleto, DATE_FORMAT(p.dataNascimento,'%d/%m/%Y'), p.cpf) ");
		jpql.append("\n in ( ");

		for (SusRedomeCSV registro : registros) {
			jpql.append("\n ('" + registro.getNomeCompleto() + "','" + registro.getDataNascimento() + "','" + registro.getCpf() + "') ");

			if (registros.indexOf(registro) != registros.size() - 1) {
				jpql.append(" , ");
			}
		}
		
		jpql.append("\n )");
		
		TypedQuery<Paciente> query = em.createQuery(jpql.toString(), Paciente.class);
		
		return query.setFirstResult(offset)
				    .setMaxResults(max)
				    .getResultList();
	}

}
