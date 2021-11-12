package app.util;

public class ConversaoSusSivep {
	
	public static String converterSexoSivepParaSus(String sexoSivep) {
		if(sexoSivep.equals("F")) {
			return "Feminino";
		} else {
			return "Masculino";
		}
	}

}
