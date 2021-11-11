package csv;

public class PacienteSivepCSV {
	
	private String nomeCompleto;
	private String dataNascimento;
	
	public PacienteSivepCSV() {
	}
	
	public PacienteSivepCSV(String nomeCompleto, String dataNascimento) {
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto.trim();
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento.trim();
	}

}
