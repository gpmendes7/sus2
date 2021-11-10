package csv;

public class SusRedomeCSV {
	
	private String campo1;
	private String municipio;
	private String nomeCompleto;
	private String cpf;
	private String dataNascimento;
	private String municipioNotificacao;
	private String racaCor;
	private String etnia;
	private String nomeMae;
	
	public SusRedomeCSV() {
	}
	
	public SusRedomeCSV(String campo1, String municipio, String nomeCompleto, String cpf, String dataNascimento,
			String municipioNotificacao, String racaCor, String etnia, String nomeMae) {
		this.campo1 = campo1;
		this.municipio = municipio;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.municipioNotificacao = municipioNotificacao;
		this.racaCor = racaCor;
		this.etnia = etnia;
		this.nomeMae = nomeMae;
	}

	public String getCampo1() {
		return campo1;
	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1.trim();
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio.trim();
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto.trim();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.trim();
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento.trim();
	}

	public String getMunicipioNotificacao() {
		return municipioNotificacao;
	}

	public void setMunicipioNotificacao(String municipioNotificacao) {
		this.municipioNotificacao = municipioNotificacao.trim();
	}

	public String getRacaCor() {
		return racaCor;
	}

	public void setRacaCor(String racaCor) {
		this.racaCor = racaCor.trim();
	}

	public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia.trim();
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae.trim();
	}

	@Override
	public String toString() {
		return "SusRedomeCSV [campo1=" + campo1 + ", municipio=" + municipio + ", nomeCompleto=" + nomeCompleto
				+ ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", municipioNotificacao="
				+ municipioNotificacao + ", racaCor=" + racaCor + ", etnia=" + etnia + ", nomeMae=" + nomeMae + "]";
	}
	
}
