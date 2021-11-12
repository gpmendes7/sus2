package app.contagem;

import java.io.IOException;
import java.util.List;

import csv.SusRedomeModificadoCSV;
import csv.SusRedomeModificadoCSVHandler;

public class ContagemTipoTeste {
	
	public static void main(String[] args) throws IOException {
		List<SusRedomeModificadoCSV> registros = SusRedomeModificadoCSVHandler.carregarCSV("./arquivos/csv/Sus_REDOME(Modificado).csv");
		
		int qtdRTPCR = 0;
		int qtdTesteRapidoAntigeno = 0;
		
		for (SusRedomeModificadoCSV registro : registros) {
			if(registro.getTipoTeste().equals("RT-PCR")) {
				qtdRTPCR++;
			} else if(registro.getTipoTeste().equals("TESTE RÁPIDO - ANTÍGENO")) {
				qtdTesteRapidoAntigeno++;
			}		
		}
		
		System.out.println("qtdRTPCR: " + qtdRTPCR);
		System.out.println("qtdTesteRapidoAntigeno: " + qtdTesteRapidoAntigeno);
	}

}
