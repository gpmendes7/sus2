package modelo;

import static app.util.StringUtil.normalizarString;

public class RegioesAdministrativas {
	
	private static final String[] BAIA_ILHA_GRANDE = {"Angra dos Reis", "Mangaratiba", "Paraty", "Parati"};
	
	private static final String[] BAIXADA_LITORANEA = {"Araruama", "Arma��o dos B�zios", "Arraial do Cabo", "Cabo Frio", 
													   "Casimiro de Abreu", "Iguaba Grande", "Rio das Ostras", 
													   "S�o Pedro da Aldeia", "Saquarema"};
	
	private static final String[] CENTRO_SUl = {"Areal", "Comendador Levy Gasparian", "Engenheiro Paulo de Frontin", 
												"Mendes", "Miguel Pereira", "Paracambi", "Para�ba do Sul",
												"Paty do Alferes", "Sapucaia", "Tr�s Rios", "Vassouras"};
	
	private static final String[] MEDIO_PARAIBA = {"Barra do Pira�", "Barra Mansa", "Itatiaia",
												   "Pinheiral", "Pira�", "Porto Real", "Quatis",
												   "Resende", "Rio Claro", "Rio das Flores",
												   "Valen�a", "Volta Redonda"};
	
	private static final String[] METROPOLITANA1 = {"Belford Roxo", "Duque de Caxias", "Itagua�",
													"Japeri", "Mag�", "Mesquita", "Nil�polis", 
													"Nova Igua�u", "Queimados", "Rio de Janeiro", 
													"S�o Jo�o de Meriti", "Serop�dica"};
	
	private static final String[] METROPOLITANA2 = {"Itabora�", "Maric�", "Niter�i", 
													"Rio Bonito", "S�o Gon�alo", "Silva Jardim", 
													"Tangu�"};
	
	private static final String[] NOROESTE = {"Aperib�", "Bom Jesus de Itabapoana", "Bom Jesus do Itabapoana", "Cambuci", 
											  "Cardoso Moreira", "Italva", "Itaocara",
											  "Itaperuna", "Laje do Muria�", "Miracema",
											  "Natividade", "Porci�ncula", "Santo Ant�nio de P�dua", 
											  "S�o Jos� de Ub�", "Varre-Sai"};
	
	private static final String[] NORTE = {"Campos dos Goytacazes", "Carapebus", "Concei��o de Macabu", 
								           "Maca�", "Quissam�", "S�o Fid�lis", 
								           "S�o Francisco de Itabapoana", "S�o Jo�o da Barra"};
	
	private static final String[] SERRANA = {"Bom Jardim", "Cachoeiras de Macacu", "Cantagalo", 
											 "Carmo", "Cordeiro", "Duas Barras",
											 "Guapimirim", "Macuco", "Nova Friburgo",
											 "Petr�polis", "Santa Maria Madalena", "S�o Jos� do Vale do Rio Preto", 
											 "S�o Sebasti�o do Alto", "Sumidouro", "Teres�polis", 
											 "Trajano de Moraes"};
	
	public static String obterNomeRegiaoMunicipio(String municipio) {
		String municipioNormalizado = normalizarString(municipio);
				
	    for (String municipioRegiao : BAIA_ILHA_GRANDE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Ba�a da Ilha Grande";
	        }
	    }
	    
	    for (String municipioRegiao : BAIXADA_LITORANEA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Baixada Litor�nea";
	        }
	    }
	    
	    for (String municipioRegiao : CENTRO_SUl) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Centro-Sul";
	        }
	    }
	    
	    for (String municipioRegiao : MEDIO_PARAIBA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "M�dio Para�ba";
	        }
	    }
	    
	    for (String municipioRegiao : METROPOLITANA1) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Metropolitana I";
	        }
	    }
	    
	    for (String municipioRegiao : METROPOLITANA2) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Metropolitana II";
	        }
	    }
	    
	    for (String municipioRegiao : NOROESTE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Noroeste";
	        }
	    }
	    
	    for (String municipioRegiao : NORTE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Norte";
	        }
	    }
	    
	    for (String municipioRegiao : SERRANA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return "Serrana";
	        }
	    }
	    
	    return null;
	}
	
	public static String[] obterRegiaoMunicipio(String municipio) {
		String municipioNormalizado = normalizarString(municipio);
				
	    for (String municipioRegiao : BAIA_ILHA_GRANDE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return BAIA_ILHA_GRANDE;
	        }
	    }
	    
	    for (String municipioRegiao : BAIXADA_LITORANEA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return BAIXADA_LITORANEA;
	        }
	    }
	    
	    for (String municipioRegiao : CENTRO_SUl) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return CENTRO_SUl;
	        }
	    }
	    
	    for (String municipioRegiao : MEDIO_PARAIBA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return MEDIO_PARAIBA;
	        }
	    }
	    
	    for (String municipioRegiao : METROPOLITANA1) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return METROPOLITANA1;
	        }
	    }
	    
	    for (String municipioRegiao : METROPOLITANA2) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return METROPOLITANA2;
	        }
	    }
	    
	    for (String municipioRegiao : NOROESTE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return NOROESTE;
	        }
	    }
	    
	    for (String municipioRegiao : NORTE) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return NORTE;
	        }
	    }
	    
	    for (String municipioRegiao : SERRANA) {
	    	String municipioRegiaoNormalizado = normalizarString(municipioRegiao);
	        
	    	if (municipioNormalizado.equals(municipioRegiaoNormalizado)) {
	            return SERRANA;
	        }
	    }
	    
	    return null;
	}

}
