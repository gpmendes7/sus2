package app;

import java.io.IOException;
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

public class CriarCSVRedomeModificado {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) throws IOException, ParseException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeCSV> registros = SusRedomeCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME.csv");
		
		List<Paciente> pacientes = new ArrayList<Paciente>();
		List<Paciente> pacientesBanco = new ArrayList<Paciente>();
		
		int offset = 0;
		
		emf = Persistence.createEntityManagerFactory("sus2"); 
		em = emf.createEntityManager();
		  
	    while ((pacientesBanco = obterPacientes(registros, offset, 100000)).size() > 0) {
	    	pacientes.addAll(pacientesBanco);
	    	offset += pacientesBanco.size(); 
	    	System.out.println("offset: " + offset);
	    }
		  
		em.close(); 
		emf.close();
		
		System.out.println("registros: " + registros.size());
		  
		Set<String> chaves = new HashSet<>(); 
		List<SusRedomeCSV> registrosSemCopia = new ArrayList<SusRedomeCSV>();
	
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		  
		for (SusRedomeCSV registro : registros) { 
			Date dataNascimentoRedome = sdf1.parse(registro.getDataNascimento());
			String dataNascimentoBanco = sdf2.format(dataNascimentoRedome);
			
			String chave = registro.getNomeCompleto().toUpperCase().trim() + dataNascimentoBanco;
		  
		    if(!chaves.contains(chave)) { 
		    	chaves.add(chave);
		    	registrosSemCopia.add(registro); 
		    } 
		}
		
		
		List<SusRedomeModificadoCSV> registrosModificados = new ArrayList<SusRedomeModificadoCSV>();
		
		int excluidos = 0;
				
		for (SusRedomeCSV registro : registrosSemCopia) {   
		     List<Paciente> result = pacientes.stream().filter(p -> p.getNomeCompleto().equalsIgnoreCase(registro.getNomeCompleto())
		    		 											&& sdf1.format(p.getDataNascimento()).equals(registro.getDataNascimento()))
		    		 								 	.collect(Collectors.toList());
		  
		     if(result.isEmpty()) { 
		    	 SusRedomeModificadoCSV registroModificado = new SusRedomeModificadoCSV(registro.getCampo1(), registro.getMunicipio(), registro.getNomeCompleto(), 
							registro.getCpf(), registro.getDataNascimento(), registro.getMunicipioNotificacao(), 		    		 																
							registro.getRacaCor(), registro.getEtnia(), registro.getNomeMae(), 
							"", "", "", "", "", "", "", "Registro excluído por não ter sido identificado do arquivo sus original");				
		    	 registrosModificados.add(registroModificado);
		    	 excluidos++;
		     } else {
		    	 Paciente paciente = result.get(0);
				 
				 String dataNotificacao = paciente.getDataNotificacao() != null ? sdf1.format(paciente.getDataNotificacao()) : null;
				 String dataTeste = paciente.getDataTeste() != null ? sdf1.format(paciente.getDataTeste()) : null;
		 
				 String idade = paciente.getDataNotificacao() != null ? calcularIdade(paciente.getDataNotificacao(), paciente.getDataNascimento()) : "";
				 
				 String observacaoExclusao = paciente.getDataNotificacao() == null ? "Registro excluído por não ter dataNotificacao" : "";
				 
			     SusRedomeModificadoCSV registroModificado = new SusRedomeModificadoCSV(registro.getCampo1(), registro.getMunicipio(), registro.getNomeCompleto(), 
			    		 																registro.getCpf(), registro.getDataNascimento(), registro.getMunicipioNotificacao(), 		    		 																
			    		 																registro.getRacaCor(), registro.getEtnia(), registro.getNomeMae(), 
			    		 																dataNotificacao, idade, 	    		 															
			    		 																paciente.getResultadoTeste(), dataTeste, paciente.getTipoTeste(),
			    		 																paciente.getEstadoTeste(), paciente.getEvolucaoCaso(), observacaoExclusao);
			     
			     if(!observacaoExclusao.isBlank()) {
			    	 excluidos++;
			     }
			     
			     registrosModificados.add(registroModificado);
		    	 
		     }
		    
		}
		
		registrosModificados.add(0, new SusRedomeModificadoCSV("campo1", "municipio", "nomeCompleto", 
				                                               "cpf", "dataNascimento", "municipioNotificacao", 
				                                               "racaCor", "etinia", "nomeMae", 
				                                               "dataNotificacao", "idade", "resultadoTeste", "dataTeste", "tipoTeste",
				                                               "estadoTeste", "evolucaoCaso", "observacaoExclusao"));
		
		System.out.println("excluidos: " + excluidos);
		System.out.println("registrosSemCopia (Identificados): " + registrosSemCopia.size());
		
		SusRedomeModificadoCSVHandler.criarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv", registrosModificados);
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
			Date dataNascimentoRedome = sdf1.parse(registro.getDataNascimento());

			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			String dataNascimentoTemp = sdf2.format(dataNascimentoRedome);
			
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
			String dataNascimentoBanco = sdf3.format(dataNascimentoRedome);

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
