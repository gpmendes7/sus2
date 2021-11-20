package csv;

public class SusRedomeModificadoCSV {
	
	private String campo1;
	private String municipio;
	private String filtroAreaMunicipio;
	private String nomeCompleto;
	private String cpf;
	private String dataNascimento;
	private String municipioNotificacao;
	private String racaCor;
	private String etnia;
	private String nomeMae;
	private String dataNotificacao;
	private String idade;
	private String resultadoTeste;
	private String dataTeste;
	private String tipoTeste;
	private String estadoTeste;
	private String evolucaoCaso;
	private String observacaoExclusao;
	private String sexo;
	private String observacaoUso;
	
	public SusRedomeModificadoCSV() {
	}
	
	public SusRedomeModificadoCSV(String campo1, String municipio, String nomeCompleto, String cpf,
			String dataNascimento, String municipioNotificacao, String racaCor, String etnia, String nomeMae,
			String dataNotificacao, String idade, String resultadoTeste, String dataTeste, String tipoTeste,
			String estadoTeste, String evolucaoCaso, String observacaoExclusao, String sexo, String observacaoUso) {
		this.campo1 = campo1;
		this.municipio = municipio;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.municipioNotificacao = municipioNotificacao;
		this.racaCor = racaCor;
		this.etnia = etnia;
		this.nomeMae = nomeMae;
		this.dataNotificacao = dataNotificacao;
		this.idade = idade;
		this.resultadoTeste = resultadoTeste;
		this.dataTeste = dataTeste;
		this.tipoTeste = tipoTeste;
		this.estadoTeste = estadoTeste;
		this.evolucaoCaso = evolucaoCaso;
		this.observacaoExclusao = observacaoExclusao;
		this.sexo = sexo;
		this.observacaoUso = observacaoUso;
	}
	
	public SusRedomeModificadoCSV(String campo1, String municipio, String filtroAreaMunicipio, String nomeCompleto, String cpf,
			String dataNascimento, String municipioNotificacao, String racaCor, String etnia, String nomeMae,
			String dataNotificacao, String idade, String resultadoTeste, String dataTeste, String tipoTeste,
			String estadoTeste, String evolucaoCaso, String observacaoExclusao, String sexo, String observacaoUso) {
		this.campo1 = campo1;
		this.municipio = municipio;
		this.filtroAreaMunicipio = filtroAreaMunicipio;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.municipioNotificacao = municipioNotificacao;
		this.racaCor = racaCor;
		this.etnia = etnia;
		this.nomeMae = nomeMae;
		this.dataNotificacao = dataNotificacao;
		this.idade = idade;
		this.resultadoTeste = resultadoTeste;
		this.dataTeste = dataTeste;
		this.tipoTeste = tipoTeste;
		this.estadoTeste = estadoTeste;
		this.evolucaoCaso = evolucaoCaso;
		this.observacaoExclusao = observacaoExclusao;
		this.sexo = sexo;
		this.observacaoUso = observacaoUso;
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
	
	public String getFiltroAreaMunicipio() {
		return filtroAreaMunicipio;
	}

	public void setFiltroAreaMunicipio(String filtroAreaMunicipio) {
		this.filtroAreaMunicipio = filtroAreaMunicipio;
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

	public String getDataNotificacao() {
		return dataNotificacao;
	}

	public void setDataNotificacao(String dataNotificacao) {
		this.dataNotificacao = dataNotificacao.trim();
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade.trim();
	}

	public String getResultadoTeste() {
		return resultadoTeste;
	}

	public void setResultadoTeste(String resultadoTeste) {
		this.resultadoTeste = resultadoTeste.trim();
	}

	public String getDataTeste() {
		return dataTeste;
	}

	public void setDataTeste(String dataTeste) {
		this.dataTeste = dataTeste.trim();
	}

	public String getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(String tipoTeste) {
		this.tipoTeste = tipoTeste.trim();
	}

	public String getEstadoTeste() {
		return estadoTeste;
	}

	public void setEstadoTeste(String estadoTeste) {
		this.estadoTeste = estadoTeste.trim();
	}

	public String getEvolucaoCaso() {
		return evolucaoCaso;
	}

	public void setEvolucaoCaso(String evolucaoCaso) {
		this.evolucaoCaso = evolucaoCaso.trim();
	}

	public String getObservacaoExclusao() {
		return observacaoExclusao;
	}

	public void setObservacaoExclusao(String observacaoExclusao) {
		this.observacaoExclusao = observacaoExclusao.trim();
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getObservacaoUso() {
		return observacaoUso;
	}

	public void setObservacaoUso(String observacaoUso) {
		this.observacaoUso = observacaoUso;
	}
}
