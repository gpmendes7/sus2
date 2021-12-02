package csv;

public class PareamentoCSV {
	
	private String campo1;
	private String identificacao;
	private String nomeCompleto;
	private String cpf;
	private String municipio;
	private String regiao;
	private String filtroAreaMunicipio;
	private String dataNotificacao;
	private String sexo;
	private String idade;
	private String racaCor;
	private String tipoTeste;
	private String resultadoTeste;
	private String desfecho;
	private String origem;
	private String evolucaoCaso;
	private String intervalo;
	
	public PareamentoCSV() {
	
	}
	
	public PareamentoCSV(String campo1, String identificacao, String nomeCompleto, String cpf, String municipio, String regiao,
			String filtroAreaMunicipio, String dataNotificacao, String sexo, String idade, String racaCor,
			String tipoTeste, String resultadoTeste, String desfecho, String origem, String evolucaoCaso,
			String intervalo) {
		this.campo1 = campo1;
		this.identificacao = identificacao;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.municipio = municipio;
		this.regiao = regiao;
		this.filtroAreaMunicipio = filtroAreaMunicipio;
		this.dataNotificacao = dataNotificacao;
		this.sexo = sexo;
		this.idade = idade;
		this.racaCor = racaCor;
		this.tipoTeste = tipoTeste;
		this.resultadoTeste = resultadoTeste;
		this.desfecho = desfecho;
		this.origem = origem;
		this.evolucaoCaso = evolucaoCaso;
		this.intervalo = intervalo;
	}

	public String getCampo1() {
		return campo1;
	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getFiltroAreaMunicipio() {
		return filtroAreaMunicipio;
	}

	public void setFiltroAreaMunicipio(String filtroAreaMunicipio) {
		this.filtroAreaMunicipio = filtroAreaMunicipio;
	}

	public String getDataNotificacao() {
		return dataNotificacao;
	}

	public void setDataNotificacao(String dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getRacaCor() {
		return racaCor;
	}
	
	public void setRacaCor(String racaCor) {
		this.racaCor = racaCor;
	}
	
	public String getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(String tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

	public String getResultadoTeste() {
		return resultadoTeste;
	}

	public void setResultadoTeste(String resultadoTeste) {
		this.resultadoTeste = resultadoTeste;
	}

	public String getDesfecho() {
		return desfecho;
	}

	public void setDesfecho(String desfecho) {
		this.desfecho = desfecho;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getEvolucaoCaso() {
		return evolucaoCaso;
	}

	public void setEvolucaoCaso(String evolucaoCaso) {
		this.evolucaoCaso = evolucaoCaso;
	}

	public String getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}
}
