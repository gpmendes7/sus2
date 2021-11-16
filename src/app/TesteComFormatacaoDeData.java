package app;

import static app.util.DataUtil.dataEstaEmIntervalo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteComFormatacaoDeData {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		Date dataNascimentoRedome1 = sdf1.parse("1990-12-12T02:00:00.000Z");
		String dataNascimentoBanco1 = sdf1.format(dataNascimentoRedome1);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date dataNascimentoRedome2 = sdf2.parse("1990-12-11 23:00:00.0");
		String dataNascimentoBanco2 = sdf2.format(dataNascimentoRedome2);
		
		
		System.out.println(dataNascimentoRedome1);
		System.out.println(dataNascimentoBanco1);
		System.out.println(dataNascimentoRedome2);
		System.out.println(dataNascimentoBanco2);
		
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		Date data1 = sdf3.parse("2000-01-01");
		Date data2 = sdf3.parse("2000-01-31");
		Date data3 = sdf3.parse("2000-01-15");
		Date data4 = sdf3.parse("2000-03-01");
		
		System.out.println(dataEstaEmIntervalo(data1, data2, data3));
		System.out.println(dataEstaEmIntervalo(data1, data2, data4));
	}

}
