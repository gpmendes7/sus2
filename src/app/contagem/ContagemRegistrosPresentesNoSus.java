package app.contagem;

import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import csv.SusRedomeCSV;
import csv.SusRedomeCSVHandler;
import modelo.Paciente;

public class ContagemRegistrosPresentesNoSus {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	public static void main(String[] args) throws IOException, ParseException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		List<SusRedomeCSV> registrosSus = SusRedomeCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME.csv");
		System.out.println("registrosSus.size(): " + registrosSus.size());
		
		List<SusRedomeCSV> registrosSusSemCopia = obterRegistrosSemCopias(registrosSus);
		System.out.println("registrosSusSemCopia.size(): " + registrosSusSemCopia.size());
		
		emf = Persistence.createEntityManagerFactory("sus2"); 
		em = emf.createEntityManager();
		
		List<Paciente> pacientesBaseSus = carregarPacientes(registrosSusSemCopia);
		System.out.println("pacientesBaseSus.size(): " + pacientesBaseSus.size());
		
		em.close(); 
		emf.close();
		
		int qtdRegistrosPresentesNoSus = 0;
		
		for (SusRedomeCSV registroSusSemCopia : registrosSusSemCopia) {
			for (Paciente paciente : pacientesBaseSus) {
				if(ehMesmoPaciente(paciente, registroSusSemCopia)) {
					qtdRegistrosPresentesNoSus++;
					break;
				}
			}
			
			System.out.println("i: " + registrosSusSemCopia.indexOf(registroSusSemCopia));
		}
		
		System.out.println("qtdRegistrosPresentesNoSus: " + qtdRegistrosPresentesNoSus);
		
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
	
	private static List<SusRedomeCSV> obterRegistrosSemCopias(List<SusRedomeCSV> registros) throws ParseException {
		Set<String> chaves = new HashSet<>(); 
		List<SusRedomeCSV> registrosSemCopia = new ArrayList<SusRedomeCSV>();

		for (SusRedomeCSV registro : registros) { 
			String nomeCompleto = removeAcentos(registro.getNomeCompleto().toUpperCase()); 
			
			String chave = nomeCompleto + registro.getDataNascimento();
		  
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

}
