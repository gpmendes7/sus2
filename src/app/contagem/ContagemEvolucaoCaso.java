package app.contagem;

import java.io.IOException;
import java.util.List;

import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ContagemEvolucaoCaso {
	
	public static void main(String[] args) throws IOException {
		List<SusRedomeModificadoCSV> registros = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv");
		
		int qtdEmBranco = 0;
		int qtdEmTratamentoDomiciliar = 0;
		int qtdCura = 0;
		int qtdCancelado = 0;
		int qtdIgnorado = 0;
		int qtdObito = 0;
		int qtdInternado = 0;
		int qtdInternadoUTI = 0;
		
		for (SusRedomeModificadoCSV registro : registros) {
			if(registro.getEvolucaoCaso() == null || registro.getEvolucaoCaso().equals("")) {
				qtdEmBranco++;
			}
			else if(registro.getEvolucaoCaso().equals("Em tratamento domiciliar")) {
				qtdEmTratamentoDomiciliar++;
			}
			else if(registro.getEvolucaoCaso().equals("Cura")) {
				qtdCura++;
			}
			else if(registro.getEvolucaoCaso().equals("Cancelado")) {
				qtdCancelado++;
			}
			else if(registro.getEvolucaoCaso().equals("Ignorado")) {
				qtdIgnorado++;
			}
			else if(registro.getEvolucaoCaso().equals("Óbito")) {
				qtdObito++;
			} else if(registro.getEvolucaoCaso().equals("Internado")) {
				qtdInternado++;
			}	
			else if(registro.getEvolucaoCaso().equals("Internado em UTI")) {
				qtdInternadoUTI++;
			}		
		}
		
		System.out.println("qtdEmBranco: " + qtdEmBranco);
		System.out.println("qtdEmTratamentoDomiciliar: " + qtdEmTratamentoDomiciliar);
		System.out.println("qtdCura: " + qtdCura);
		System.out.println("qtdCancelado: " + qtdCancelado);
		System.out.println("qtdIgnorado: " + qtdIgnorado);
		System.out.println("qtdObito: " + qtdObito);
		System.out.println("qtdInternado: " + qtdInternado);
		System.out.println("qtdInternadoUTI: " + qtdInternadoUTI);
	}

}
