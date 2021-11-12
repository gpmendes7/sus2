package app.preprocessamento;

import static app.util.StringUtil.removeAcentos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

public class CriarCSVRedomeModificado {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) throws IOException, ParseException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeCSV> registros = SusRedomeCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(SemRegistrosDoSivep).csv");
		System.out.println("registros: " + registros.size());
	
		emf = Persistence.createEntityManagerFactory("sus2"); 
		em = emf.createEntityManager();
		
		List<Paciente> pacientesBaseSus = carregarPacientes(registros);
		
		em.close(); 
		emf.close();
        
        List<SusRedomeModificadoCSV> registrosModificados = new ArrayList<SusRedomeModificadoCSV>();
        
        int qtdSemDataNotificacao = 0;
        int qtdSemResultadoTeste = 0;
        int qtdObito = 0;
        int qtdInternado = 0;
        int qtdInternadoUTI = 0;
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        
		for (SusRedomeCSV registro : registros) {  
			 Paciente paciente = null;
			 
			 for (Paciente pacienteBaseSus : pacientesBaseSus) {
				 if(ehMesmoPaciente(pacienteBaseSus, registro)) {
					 paciente = pacienteBaseSus;
					 break;
				}
			 }
	  
		     if(paciente != null) { 		    	 
				 String dataNotificacao = paciente.getDataNotificacao() != null ? sdf.format(paciente.getDataNotificacao()) : null;
				 String dataTeste = paciente.getDataTeste() != null ? sdf.format(paciente.getDataTeste()) : null;
		 
				 String idade = paciente.getDataNotificacao() != null ? calcularIdade(paciente.getDataNotificacao(), paciente.getDataNascimento()) : "";
				 
				 String observacaoExclusao = "";
				 
				 if(paciente.getDataNotificacao() == null) {
					 observacaoExclusao =  "Registro sem dataNotificacao.";
					 qtdSemDataNotificacao++;
				 }				 
				 else if(paciente.getResultadoTeste() == null || paciente.getResultadoTeste().equals("")) {
					 observacaoExclusao =  "Registro sem resultadoTeste.";
					 qtdSemResultadoTeste++;
				 }			 				
				 else if(paciente.getEvolucaoCaso().equals("Óbito")) {
					observacaoExclusao = "Registro com evolucaoCaso Óbito.";		
					qtdObito++;
				 } else if(paciente.getEvolucaoCaso().equals("Internado")) {
					 observacaoExclusao = "Registro com evolucaoCaso Internado.";
					 qtdInternado++;
				 } else if(paciente.getEvolucaoCaso().equals("Internado em UTI")) {
					 observacaoExclusao = "Registro com evolucaoCaso Internado em UTI.";
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
		     }
		     
		     System.out.println("i: " + registros.indexOf(registro));
		}
		
	    System.out.println("registrosModificados.size(): " + registrosModificados.size()); 
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
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv", registrosModificados);
	}
	
	private static boolean ehMesmoPaciente(Paciente paciente1, SusRedomeCSV paciente2) throws ParseException {
		String nomePaciente1 = removeAcentos(paciente1.getNomeCompleto().trim());
		String nomePaciente2 = removeAcentos(paciente2.getNomeCompleto().trim());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		String dataNascimentoSusFormatada = sdf.format(paciente1.getDataNascimento());
		
		boolean temMesmoNome = nomePaciente1.equalsIgnoreCase(nomePaciente2);
		boolean temMesmaData = dataNascimentoSusFormatada.equals(paciente2.getDataNascimento());

		return temMesmoNome && temMesmaData;
	}
	
	private static List<Paciente> carregarPacientes(List<SusRedomeCSV> registros) throws ParseException {
		List<Paciente> pacientes = new ArrayList<Paciente>();
		List<Paciente> pacientesLote = new ArrayList<Paciente>();
		
		int offset = 0;
		
	    while ((pacientesLote = obterPacientes(registros, offset, 100000)).size() > 0) {
	    	for (Paciente pacienteLote : pacientesLote) {
				if(!pacientes.contains(pacienteLote)) {
					pacientes.add(pacienteLote);
				}
			}
	    	
	    	offset += pacientesLote.size(); 
	    	System.out.println("offset: " + offset);
	    }
	    
		return pacientes;
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
