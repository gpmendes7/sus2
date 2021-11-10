package app;

import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SusRedomeCSV;
import csv.SusRedomeCSVHandler;
import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;
import modelo.Paciente;

public class CriarCSVRedomeModificadoAtualizado {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) throws IOException, ParseException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeCSV> registros = SusRedomeCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME.csv");
		System.out.println("registros: " + registros.size());
		
		List<SusRedomeCSV> registrosSemCopia = obterRegistrosSemCopias(registros);
		System.out.println("registrosSemCopia: " + registrosSemCopia.size());
		
		emf = Persistence.createEntityManagerFactory("sus2"); 
		em = emf.createEntityManager();
		
		List<Paciente> pacientesBaseSus = carregarPacientes(registros);
		
		em.close(); 
		emf.close();
		    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        
        List<SusRedomeModificadoCSV> registrosModificados = new ArrayList<SusRedomeModificadoCSV>();
        
        int qtdSemDataNotificacao = 0;
        int qtdSemResultadoTeste = 0;
        int qtdObito = 0;
        int qtdInternado = 0;
        int qtdInternadoUTI = 0;
		
		for (SusRedomeCSV registro : registrosSemCopia) {  
			 Date dataNascimentoRedome = sdf.parse(registro.getDataNascimento());
			 String dataNascimentoBanco = sdf.format(dataNascimentoRedome);
				 
			 List<Paciente> pacientesBaseSusFiltrados = pacientesBaseSus.stream().filter(p -> (removeAcentos(p.getNomeCompleto()).equalsIgnoreCase(registro.getNomeCompleto())
			    		 										   						     && sdf.format(p.getDataNascimento()).equals(dataNascimentoBanco)))
			    		 								 							 .collect(Collectors.toList());
			  
		     if(!pacientesBaseSusFiltrados.isEmpty()) { 		    	 
		    	 Paciente paciente = pacientesBaseSusFiltrados.get(0);
				 
				 String dataNotificacao = paciente.getDataNotificacao() != null ? sdf.format(paciente.getDataNotificacao()) : null;
				 String dataTeste = paciente.getDataTeste() != null ? sdf.format(paciente.getDataTeste()) : null;
		 
				 String idade = paciente.getDataNotificacao() != null ? calcularIdade(paciente.getDataNotificacao(), paciente.getDataNascimento()) : "";
				 
				 String observacaoExclusao = "";
				 
				 if(paciente.getDataNotificacao() == null) {
					 observacaoExclusao =  "Registro sem dataNotificacao.";
					 qtdSemDataNotificacao++;
				 }
				 
				 if(paciente.getResultadoTeste() == null || paciente.getResultadoTeste().equals("")) {
					 observacaoExclusao +=  "Registro sem resultadoTeste.";
					 qtdSemResultadoTeste++;
				 }
				 				
				 if(paciente.getEvolucaoCaso().equals("Óbito")) {
					observacaoExclusao += "Registro com evolucaoCaso Óbito.";		
					qtdObito++;
				 } else if(paciente.getEvolucaoCaso().equals("Internado")) {
					 observacaoExclusao += "Registro com evolucaoCaso Internado.";
					 qtdInternado++;
				 } else if(paciente.getEvolucaoCaso().equals("Internado em UTI")) {
					 observacaoExclusao += "Registro com evolucaoCaso Internado em UTI.";
					 qtdInternadoUTI++;
				 }								
				 
			     SusRedomeModificadoCSV registroModificado = new SusRedomeModificadoCSV(registro.getCampo1(), registro.getMunicipio(), registro.getNomeCompleto(), 
			    		 																registro.getCpf(), registro.getDataNascimento(), registro.getMunicipioNotificacao(), 		    		 																
			    		 																registro.getRacaCor(), registro.getEtnia(), registro.getNomeMae(), 
			    		 																dataNotificacao, idade, 	    		 															
			    		 																paciente.getResultadoTeste(), dataTeste, paciente.getTipoTeste(),
			    		 																paciente.getEstadoTeste(), paciente.getEvolucaoCaso(), observacaoExclusao,
			    		 																paciente.getSexo(), "");
			     
			     registrosModificados.add(registroModificado);	
			     System.out.println("registrosModificados.size(): " + registrosModificados.size());  
		     }		    	     
		}
		
		System.out.println("qtdSemDataNotificacao: " + qtdSemDataNotificacao);  
		System.out.println("qtdSemResultadoTeste: " + qtdSemResultadoTeste); 
		System.out.println("qtdObito: " + qtdObito); 
		System.out.println("qtdInternado: " + qtdInternado);
		System.out.println("qtdInternadoUTI: " + qtdInternadoUTI); 
		
		registrosModificados.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
				                                               "cpf", "dataNascimento", "municipioNotificacao", 
				                                               "racaCor", "etinia", "nomeMae", 
				                                               "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
				                                               "estadoTeste", "evolucaoCaso", "observacaoExclusao", "sexo", "observacaoUso"));
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado2).csv", registrosModificados);
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
	
	private static List<SusRedomeCSV> obterRegistrosSemCopias(List<SusRedomeCSV> registros) throws ParseException {
		Set<String> chaves = new HashSet<>(); 
		List<SusRedomeCSV> registrosSemCopia = new ArrayList<SusRedomeCSV>();
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		  
		for (SusRedomeCSV registro : registros) { 
			Date dataNascimentoRedome = sdf.parse(registro.getDataNascimento());
			String dataNascimentoBanco = sdf.format(dataNascimentoRedome);
			String nomeCompleto = removeAcentos(registro.getNomeCompleto().toUpperCase()); 
			
			String chave = nomeCompleto + dataNascimentoBanco;
		  
		    if(!chaves.contains(chave)) { 
		    	chaves.add(chave);
		    	registrosSemCopia.add(registro); 
		    } 
		}
		
		return registrosSemCopia;
	}
	
	private static String removeAcentos(String string) {
		return Normalizer.normalize(string, Normalizer.Form.NFD)
						 .replaceAll("[^\\p{ASCII}]", "");
	}
	
	private static String calcularIdade(Date dataNotificacao, Date dataNascimento) throws ParseException {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(dataNotificacao);
		int anoDataNotificacao = cal.get(Calendar.YEAR);
		
		cal.setTime(dataNascimento);
		int anoDataNascimento = cal.get(Calendar.YEAR);
		
		return "" + (anoDataNotificacao - anoDataNascimento);
	}
	
	private static List<Paciente> obterPacientes(List<SusRedomeCSV> registros, int offset, int max) throws ParseException {
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("\n from Paciente p ");
		jpql.append("\n where (p.nomeCompleto, p.dataNascimento) ");
		jpql.append("\n in ( ");

		for (SusRedomeCSV registro : registros) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
			Date dataNascimentoRedome = sdf1.parse(registro.getDataNascimento());
			String dataNascimentoBanco = sdf2.format(dataNascimentoRedome);

			jpql.append("\n ('" + registro.getNomeCompleto() + "','" + dataNascimentoBanco + "') ");

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
