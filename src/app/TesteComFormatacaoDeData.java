package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteComFormatacaoDeData {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		
	    Date dataNotificacao = sdf1.parse("2020-12-29 00:00:00.000000");
			
	    System.out.println(sdf2.format(dataNotificacao));
	}

}
