package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Paciente {
	
	@Id
	private Long id;
	private Date dataNascimento;
	private Date dataNotificacao;
	private Date dataInicioSintomas;
	private Date dataTeste;
	@Column(columnDefinition="TEXT")
	private String pUsuario;
	@Column(columnDefinition="TEXT")
	private String estrangeiro;
	@Column(columnDefinition="TEXT")
	private String profissionalSaude;
	@Column(columnDefinition="TEXT")
	private String profissionalSeguranca;
	@Column(columnDefinition="TEXT")
	private String cbo;
	@Column(columnDefinition="TEXT")
	private String cpf;
	@Column(columnDefinition="TEXT")
	private String cns;
	@Column(columnDefinition="TEXT")
	private String nomeCompleto;
	@Column(columnDefinition="TEXT")
	private String nomeMae;
	@Column(columnDefinition="TEXT")
	private String paisOrigem;
	@Column(columnDefinition="TEXT")
	private String sexo;
	@Column(columnDefinition="TEXT")
	private String racaCor;
	@Column(columnDefinition="TEXT")
	private String etnia;
	@Column(columnDefinition="TEXT")
	private String cep;
	@Column(columnDefinition="TEXT")
	private String passaporte;
	@Column(columnDefinition="TEXT")
	private String logradouro;
	@Column(columnDefinition="TEXT")
	private String numero;
	@Column(columnDefinition="TEXT")
	private String complemento;
	@Column(columnDefinition="TEXT")
	private String bairro;
	@Column(columnDefinition="TEXT")
	private String estado;
	@Column(columnDefinition="TEXT")
	private String municipio;
	@Column(columnDefinition="TEXT")
	private String telefoneContato;
	@Column(columnDefinition="TEXT")
	private String telefone;
	@Column(columnDefinition="TEXT")
	private String sintomas;
	@Column(columnDefinition="TEXT")
	private String outrosSintomas;
	@Column(columnDefinition="TEXT")
	private String condicoes;
	@Column(columnDefinition="TEXT")
	private String estadoTeste;
	@Column(columnDefinition="TEXT")
	private String tipoTeste;
	@Column(columnDefinition="TEXT")
	private String testeSorologico;
	private Date dataTesteSorologico;
	@Column(columnDefinition="TEXT")
	private String resultadoTeste;
	@Column(columnDefinition="TEXT")
	private String tipoTesteSorologico;
	@Column(columnDefinition="TEXT")
	private String resultadoTesteSorologicoIgA;
	@Column(columnDefinition="TEXT")
	private String resultadoTesteSorologicoIgG;
	@Column(columnDefinition="TEXT")
	private String resultadoTesteSorologicoIgM;
	@Column(columnDefinition="TEXT")
	private String resultadoTesteSorologicoTotais;
	@Column(columnDefinition="TEXT")
	private String numeroNotificacao;
	@Column(columnDefinition="TEXT")
	private String cnes;
	@Column(columnDefinition="TEXT")
	private String estadoNotificacao;
	@Column(columnDefinition="TEXT")
	private String municipioNotificacao;
	@Column(columnDefinition="TEXT")
	private String origem;
	@Column(columnDefinition="TEXT")
	private String nomeCompletoDesnormalizado;
	private Date createdAt;
	private Date updatedAt;
	@Column(columnDefinition="TEXT")
	private String sourceId;
	@Column(columnDefinition="TEXT")
	private String idade;
	@Column(columnDefinition="TEXT")
	private String classificacaoFinal;
	@Column(columnDefinition="TEXT")
	private String evolucaoCaso;
	private Date dataEncerramento;
	@Column(columnDefinition="TEXT")
	private String descricaoRacaCor;
	@Column(columnDefinition="TEXT")
	private String pUsuarioAlteracao;
	@Column(columnDefinition="TEXT")
	private String rpa;
	@Column(columnDefinition="TEXT")
	private String idOrigem;
	@Column(columnDefinition="TEXT")
	private String desnormalizarNome;
	@Column(columnDefinition="TEXT")
	private String timestampNotificacao;
	@Column(columnDefinition="TEXT")
	private String estadoIBGE;
	@Column(columnDefinition="TEXT")
	private String estadoNotificacaoIBGE;
	@Column(columnDefinition="TEXT")
	private String municipioIBGE;
	@Column(columnDefinition="TEXT")
	private String municipioNotificacaoIBGE;
	@Column(length = 80)
	private String notificadorCpf;
	@Column(length = 80)
	private String notificadorEmail;
	@Column(length = 80)
	private String notificadorNome;
	@Column(columnDefinition="TEXT")
	private String notificadorCNPJ;
	@Column(columnDefinition="TEXT")
	private String codigoClassificacaoFinal;
	@Column(columnDefinition="TEXT")
	private String codigoEvolucaoCaso;
	@Column(columnDefinition="TEXT")
	private String codigoEstadoTeste;
	@Column(columnDefinition="TEXT")
	private String labCnes;
	@Column(columnDefinition="TEXT")
	private String codigoCondicoes;
	@Column(columnDefinition="TEXT")
	private String codigoResultadoTeste;
	@Column(columnDefinition="TEXT")
	private String codigoSintomas;
	@Column(columnDefinition="TEXT")
	private String email;
	@Column(columnDefinition="TEXT")
	private String comunidadeTradicional;
	@Column(columnDefinition="TEXT")
	private String contemComunidadeTradicional;
	@Column(columnDefinition="TEXT")
	private String versaoFormulario;
	@Column(columnDefinition="TEXT")
	private String codigoResultadoTesteSorologicoIgM;
	@Column(columnDefinition="TEXT")
	private String codigoResultadoTesteSorologicoIgG;
	@Column(columnDefinition="TEXT")
	private String codigoTipoTesteSorologico;
	@Column(columnDefinition="TEXT")
	private String codigoTesteSorologico;
	@Column(columnDefinition="TEXT")
	private String codigoTipoTeste;
	@Column(columnDefinition="TEXT")
	private String codigoProfissionalSeguranca;
	@Column(columnDefinition="TEXT")
	private String codigoProfissionalSaude;
	@Column(columnDefinition="TEXT")
	private String codigoTemCpf;
	@Column(columnDefinition="TEXT")
	private String codigoSexo;
	@Column(columnDefinition="TEXT")
	private String codigoEstrangeiro;
	@Column(columnDefinition="TEXT")
	private String codigoCbo;
	@Column(columnDefinition="TEXT")
	private String codigoPaisOrigem;
	@Column(columnDefinition="TEXT")
	private String codigoResultadoTesteSorologicoTotais;
	@Column(columnDefinition="TEXT")
	private String codigoResultadoTesteSorologicoIgA;
	@Column(columnDefinition="TEXT")
	private String codigoComunidadeTradicional;
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<Notificacao> notificacoes;
	
	public Paciente() {
		notificacoes = new ArrayList<>();
	}
	

	public Paciente(Date dataNascimento, String cpf, String nomeCompleto, String municipioNotificacao) {
		super();
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.nomeCompleto = nomeCompleto;
		this.municipioNotificacao = municipioNotificacao;
	}


	public Paciente(Date dataNascimento, Date dataNotificacao, Date dataInicioSintomas, Date dataTeste, String pUsuario,
			String estrangeiro, String profissionalSaude, String profissionalSeguranca, String cbo, String cpf,
			String cns, String nomeCompleto, String nomeMae, String paisOrigem, String sexo, String racaCor,
			String etnia, String cep, String passaporte, String logradouro, String numero, String complemento,
			String bairro, String estado, String municipio, String telefoneContato, String telefone, String sintomas,
			String outrosSintomas, String condicoes, String estadoTeste, String tipoTeste, String testeSorologico,
			Date dataTesteSorologico, String resultadoTeste, String tipoTesteSorologico,
			String resultadoTesteSorologicoIgA, String resultadoTesteSorologicoIgG, String resultadoTesteSorologicoIgM,
			String resultadoTesteSorologicoTotais, String numeroNotificacao, String cnes, String estadoNotificacao,
			String municipioNotificacao, String origem, String nomeCompletoDesnormalizado, Date createdAt,
			Date updatedAt, String sourceId, String idade, String classificacaoFinal, String evolucaoCaso,
			Date dataEncerramento, String descricaoRacaCor, String pUsuarioAlteracao, String rpa, String idOrigem,
			String desnormalizarNome, String timestampNotificacao, String estadoIBGE, String estadoNotificacaoIBGE,
			String municipioIBGE, String municipioNotificacaoIBGE, String notificadorCpf, String notificadorEmail,
			String notificadorNome, String notificadorCNPJ, String codigoClassificacaoFinal, String codigoEvolucaoCaso,
			String codigoEstadoTeste, String labCnes, String codigoCondicoes, String codigoResultadoTeste,
			String codigoSintomas, String email, String comunidadeTradicional, String contemComunidadeTradicional,
			String versaoFormulario, String codigoResultadoTesteSorologicoIgM, String codigoResultadoTesteSorologicoIgG,
			String codigoTipoTesteSorologico, String codigoTesteSorologico, String codigoTipoTeste,
			String codigoProfissionalSeguranca, String codigoProfissionalSaude, String codigoTemCpf, String codigoSexo,
			String codigoEstrangeiro, String codigoCbo, String codigoPaisOrigem,
			String codigoResultadoTesteSorologicoTotais, String codigoResultadoTesteSorologicoIgA,
			String codigoComunidadeTradicional) {
		super();
		this.dataNascimento = dataNascimento;
		this.dataNotificacao = dataNotificacao;
		this.dataInicioSintomas = dataInicioSintomas;
		this.dataTeste = dataTeste;
		this.pUsuario = pUsuario;
		this.estrangeiro = estrangeiro;
		this.profissionalSaude = profissionalSaude;
		this.profissionalSeguranca = profissionalSeguranca;
		this.cbo = cbo;
		this.cpf = cpf;
		this.cns = cns;
		this.nomeCompleto = nomeCompleto;
		this.nomeMae = nomeMae;
		this.paisOrigem = paisOrigem;
		this.sexo = sexo;
		this.racaCor = racaCor;
		this.etnia = etnia;
		this.cep = cep;
		this.passaporte = passaporte;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.estado = estado;
		this.municipio = municipio;
		this.telefoneContato = telefoneContato;
		this.telefone = telefone;
		this.sintomas = sintomas;
		this.outrosSintomas = outrosSintomas;
		this.condicoes = condicoes;
		this.estadoTeste = estadoTeste;
		this.tipoTeste = tipoTeste;
		this.testeSorologico = testeSorologico;
		this.dataTesteSorologico = dataTesteSorologico;
		this.resultadoTeste = resultadoTeste;
		this.tipoTesteSorologico = tipoTesteSorologico;
		this.resultadoTesteSorologicoIgA = resultadoTesteSorologicoIgA;
		this.resultadoTesteSorologicoIgG = resultadoTesteSorologicoIgG;
		this.resultadoTesteSorologicoIgM = resultadoTesteSorologicoIgM;
		this.resultadoTesteSorologicoTotais = resultadoTesteSorologicoTotais;
		this.numeroNotificacao = numeroNotificacao;
		this.cnes = cnes;
		this.estadoNotificacao = estadoNotificacao;
		this.municipioNotificacao = municipioNotificacao;
		this.origem = origem;
		this.nomeCompletoDesnormalizado = nomeCompletoDesnormalizado;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.sourceId = sourceId;
		this.idade = idade;
		this.classificacaoFinal = classificacaoFinal;
		this.evolucaoCaso = evolucaoCaso;
		this.dataEncerramento = dataEncerramento;
		this.descricaoRacaCor = descricaoRacaCor;
		this.pUsuarioAlteracao = pUsuarioAlteracao;
		this.rpa = rpa;
		this.idOrigem = idOrigem;
		this.desnormalizarNome = desnormalizarNome;
		this.timestampNotificacao = timestampNotificacao;
		this.estadoIBGE = estadoIBGE;
		this.estadoNotificacaoIBGE = estadoNotificacaoIBGE;
		this.municipioIBGE = municipioIBGE;
		this.municipioNotificacaoIBGE = municipioNotificacaoIBGE;
		this.notificadorCpf = notificadorCpf;
		this.notificadorEmail = notificadorEmail;
		this.notificadorNome = notificadorNome;
		this.notificadorCNPJ = notificadorCNPJ;
		this.codigoClassificacaoFinal = codigoClassificacaoFinal;
		this.codigoEvolucaoCaso = codigoEvolucaoCaso;
		this.codigoEstadoTeste = codigoEstadoTeste;
		this.labCnes = labCnes;
		this.codigoCondicoes = codigoCondicoes;
		this.codigoResultadoTeste = codigoResultadoTeste;
		this.codigoSintomas = codigoSintomas;
		this.email = email;
		this.comunidadeTradicional = comunidadeTradicional;
		this.contemComunidadeTradicional = contemComunidadeTradicional;
		this.versaoFormulario = versaoFormulario;
		this.codigoResultadoTesteSorologicoIgM = codigoResultadoTesteSorologicoIgM;
		this.codigoResultadoTesteSorologicoIgG = codigoResultadoTesteSorologicoIgG;
		this.codigoTipoTesteSorologico = codigoTipoTesteSorologico;
		this.codigoTesteSorologico = codigoTesteSorologico;
		this.codigoTipoTeste = codigoTipoTeste;
		this.codigoProfissionalSeguranca = codigoProfissionalSeguranca;
		this.codigoProfissionalSaude = codigoProfissionalSaude;
		this.codigoTemCpf = codigoTemCpf;
		this.codigoSexo = codigoSexo;
		this.codigoEstrangeiro = codigoEstrangeiro;
		this.codigoCbo = codigoCbo;
		this.codigoPaisOrigem = codigoPaisOrigem;
		this.codigoResultadoTesteSorologicoTotais = codigoResultadoTesteSorologicoTotais;
		this.codigoResultadoTesteSorologicoIgA = codigoResultadoTesteSorologicoIgA;
		this.codigoComunidadeTradicional = codigoComunidadeTradicional;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setResultadoTeste(String resultadoTeste) {
		this.resultadoTeste = resultadoTeste;
	}
	
	public String getResultadoTeste() {
		return resultadoTeste;
	}
	
	public List<Notificacao> getNotificacoes() {
		return notificacoes;
	}
	
	public Date getDataNotificacao() {
		return dataNotificacao;
	}

	public void setDataNotificacao(Date dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}

	public Date getDataInicioSintomas() {
		return dataInicioSintomas;
	}

	public void setDataInicioSintomas(Date dataInicioSintomas) {
		this.dataInicioSintomas = dataInicioSintomas;
	}

	public Date getDataTeste() {
		return dataTeste;
	}

	public void setDataTeste(Date dataTeste) {
		this.dataTeste = dataTeste;
	}

	public String getpUsuario() {
		return pUsuario;
	}

	public void setpUsuario(String pUsuario) {
		this.pUsuario = pUsuario;
	}

	public String getEstrangeiro() {
		return estrangeiro;
	}

	public void setEstrangeiro(String estrangeiro) {
		this.estrangeiro = estrangeiro;
	}

	public String getProfissionalSaude() {
		return profissionalSaude;
	}

	public void setProfissionalSaude(String profissionalSaude) {
		this.profissionalSaude = profissionalSaude;
	}

	public String getProfissionalSeguranca() {
		return profissionalSeguranca;
	}

	public void setProfissionalSeguranca(String profissionalSeguranca) {
		this.profissionalSeguranca = profissionalSeguranca;
	}

	public String getCbo() {
		return cbo;
	}

	public void setCbo(String cbo) {
		this.cbo = cbo;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getRacaCor() {
		return racaCor;
	}

	public void setRacaCor(String racaCor) {
		this.racaCor = racaCor;
	}

	public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getOutrosSintomas() {
		return outrosSintomas;
	}

	public void setOutrosSintomas(String outrosSintomas) {
		this.outrosSintomas = outrosSintomas;
	}

	public String getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(String condicoes) {
		this.condicoes = condicoes;
	}

	public String getEstadoTeste() {
		return estadoTeste;
	}

	public void setEstadoTeste(String estadoTeste) {
		this.estadoTeste = estadoTeste;
	}

	public String getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(String tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

	public String getTesteSorologico() {
		return testeSorologico;
	}

	public void setTesteSorologico(String testeSorologico) {
		this.testeSorologico = testeSorologico;
	}

	public Date getDataTesteSorologico() {
		return dataTesteSorologico;
	}

	public void setDataTesteSorologico(Date dataTesteSorologico) {
		this.dataTesteSorologico = dataTesteSorologico;
	}

	public String getTipoTesteSorologico() {
		return tipoTesteSorologico;
	}

	public void setTipoTesteSorologico(String tipoTesteSorologico) {
		this.tipoTesteSorologico = tipoTesteSorologico;
	}

	public String getResultadoTesteSorologicoIgA() {
		return resultadoTesteSorologicoIgA;
	}

	public void setResultadoTesteSorologicoIgA(String resultadoTesteSorologicoIgA) {
		this.resultadoTesteSorologicoIgA = resultadoTesteSorologicoIgA;
	}

	public String getResultadoTesteSorologicoIgG() {
		return resultadoTesteSorologicoIgG;
	}

	public void setResultadoTesteSorologicoIgG(String resultadoTesteSorologicoIgG) {
		this.resultadoTesteSorologicoIgG = resultadoTesteSorologicoIgG;
	}

	public String getResultadoTesteSorologicoIgM() {
		return resultadoTesteSorologicoIgM;
	}

	public void setResultadoTesteSorologicoIgM(String resultadoTesteSorologicoIgM) {
		this.resultadoTesteSorologicoIgM = resultadoTesteSorologicoIgM;
	}

	public String getResultadoTesteSorologicoTotais() {
		return resultadoTesteSorologicoTotais;
	}

	public void setResultadoTesteSorologicoTotais(String resultadoTesteSorologicoTotais) {
		this.resultadoTesteSorologicoTotais = resultadoTesteSorologicoTotais;
	}

	public String getNumeroNotificacao() {
		return numeroNotificacao;
	}

	public void setNumeroNotificacao(String numeroNotificacao) {
		this.numeroNotificacao = numeroNotificacao;
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	public String getEstadoNotificacao() {
		return estadoNotificacao;
	}

	public void setEstadoNotificacao(String estadoNotificacao) {
		this.estadoNotificacao = estadoNotificacao;
	}

	public String getMunicipioNotificacao() {
		return municipioNotificacao;
	}

	public void setMunicipioNotificacao(String municipioNotificacao) {
		this.municipioNotificacao = municipioNotificacao;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getNomeCompletoDesnormalizado() {
		return nomeCompletoDesnormalizado;
	}

	public void setNomeCompletoDesnormalizado(String nomeCompletoDesnormalizado) {
		this.nomeCompletoDesnormalizado = nomeCompletoDesnormalizado;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getClassificacaoFinal() {
		return classificacaoFinal;
	}

	public void setClassificacaoFinal(String classificacaoFinal) {
		this.classificacaoFinal = classificacaoFinal;
	}

	public String getEvolucaoCaso() {
		return evolucaoCaso;
	}

	public void setEvolucaoCaso(String evolucaoCaso) {
		this.evolucaoCaso = evolucaoCaso;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getDescricaoRacaCor() {
		return descricaoRacaCor;
	}

	public void setDescricaoRacaCor(String descricaoRacaCor) {
		this.descricaoRacaCor = descricaoRacaCor;
	}

	public String getpUsuarioAlteracao() {
		return pUsuarioAlteracao;
	}

	public void setpUsuarioAlteracao(String pUsuarioAlteracao) {
		this.pUsuarioAlteracao = pUsuarioAlteracao;
	}

	public String getRpa() {
		return rpa;
	}

	public void setRpa(String rpa) {
		this.rpa = rpa;
	}

	public String getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(String idOrigem) {
		this.idOrigem = idOrigem;
	}

	public String getDesnormalizarNome() {
		return desnormalizarNome;
	}

	public void setDesnormalizarNome(String desnormalizarNome) {
		this.desnormalizarNome = desnormalizarNome;
	}

	public String getTimestampNotificacao() {
		return timestampNotificacao;
	}

	public void setTimestampNotificacao(String timestampNotificacao) {
		this.timestampNotificacao = timestampNotificacao;
	}

	public String getEstadoIBGE() {
		return estadoIBGE;
	}

	public void setEstadoIBGE(String estadoIBGE) {
		this.estadoIBGE = estadoIBGE;
	}

	public String getEstadoNotificacaoIBGE() {
		return estadoNotificacaoIBGE;
	}

	public void setEstadoNotificacaoIBGE(String estadoNotificacaoIBGE) {
		this.estadoNotificacaoIBGE = estadoNotificacaoIBGE;
	}

	public String getMunicipioIBGE() {
		return municipioIBGE;
	}

	public void setMunicipioIBGE(String municipioIBGE) {
		this.municipioIBGE = municipioIBGE;
	}

	public String getMunicipioNotificacaoIBGE() {
		return municipioNotificacaoIBGE;
	}

	public void setMunicipioNotificacaoIBGE(String municipioNotificacaoIBGE) {
		this.municipioNotificacaoIBGE = municipioNotificacaoIBGE;
	}

	public String getNotificadorCpf() {
		return notificadorCpf;
	}

	public void setNotificadorCpf(String notificadorCpf) {
		this.notificadorCpf = notificadorCpf;
	}

	public String getNotificadorEmail() {
		return notificadorEmail;
	}

	public void setNotificadorEmail(String notificadorEmail) {
		this.notificadorEmail = notificadorEmail;
	}

	public String getNotificadorNome() {
		return notificadorNome;
	}

	public void setNotificadorNome(String notificadorNome) {
		this.notificadorNome = notificadorNome;
	}

	public String getNotificadorCNPJ() {
		return notificadorCNPJ;
	}

	public void setNotificadorCNPJ(String notificadorCNPJ) {
		this.notificadorCNPJ = notificadorCNPJ;
	}

	public String getCodigoClassificacaoFinal() {
		return codigoClassificacaoFinal;
	}

	public void setCodigoClassificacaoFinal(String codigoClassificacaoFinal) {
		this.codigoClassificacaoFinal = codigoClassificacaoFinal;
	}

	public String getCodigoEvolucaoCaso() {
		return codigoEvolucaoCaso;
	}

	public void setCodigoEvolucaoCaso(String codigoEvolucaoCaso) {
		this.codigoEvolucaoCaso = codigoEvolucaoCaso;
	}

	public String getCodigoEstadoTeste() {
		return codigoEstadoTeste;
	}

	public void setCodigoEstadoTeste(String codigoEstadoTeste) {
		this.codigoEstadoTeste = codigoEstadoTeste;
	}

	public String getLabCnes() {
		return labCnes;
	}

	public void setLabCnes(String labCnes) {
		this.labCnes = labCnes;
	}

	public String getCodigoCondicoes() {
		return codigoCondicoes;
	}

	public void setCodigoCondicoes(String codigoCondicoes) {
		this.codigoCondicoes = codigoCondicoes;
	}

	public String getCodigoResultadoTeste() {
		return codigoResultadoTeste;
	}

	public void setCodigoResultadoTeste(String codigoResultadoTeste) {
		this.codigoResultadoTeste = codigoResultadoTeste;
	}

	public String getCodigoSintomas() {
		return codigoSintomas;
	}

	public void setCodigoSintomas(String codigoSintomas) {
		this.codigoSintomas = codigoSintomas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComunidadeTradicional() {
		return comunidadeTradicional;
	}

	public void setComunidadeTradicional(String comunidadeTradicional) {
		this.comunidadeTradicional = comunidadeTradicional;
	}

	public String getContemComunidadeTradicional() {
		return contemComunidadeTradicional;
	}

	public void setContemComunidadeTradicional(String contemComunidadeTradicional) {
		this.contemComunidadeTradicional = contemComunidadeTradicional;
	}

	public String getVersaoFormulario() {
		return versaoFormulario;
	}

	public void setVersaoFormulario(String versaoFormulario) {
		this.versaoFormulario = versaoFormulario;
	}

	public String getCodigoResultadoTesteSorologicoIgM() {
		return codigoResultadoTesteSorologicoIgM;
	}

	public void setCodigoResultadoTesteSorologicoIgM(String codigoResultadoTesteSorologicoIgM) {
		this.codigoResultadoTesteSorologicoIgM = codigoResultadoTesteSorologicoIgM;
	}

	public String getCodigoResultadoTesteSorologicoIgG() {
		return codigoResultadoTesteSorologicoIgG;
	}

	public void setCodigoResultadoTesteSorologicoIgG(String codigoResultadoTesteSorologicoIgG) {
		this.codigoResultadoTesteSorologicoIgG = codigoResultadoTesteSorologicoIgG;
	}

	public String getCodigoTipoTesteSorologico() {
		return codigoTipoTesteSorologico;
	}

	public void setCodigoTipoTesteSorologico(String codigoTipoTesteSorologico) {
		this.codigoTipoTesteSorologico = codigoTipoTesteSorologico;
	}

	public String getCodigoTesteSorologico() {
		return codigoTesteSorologico;
	}

	public void setCodigoTesteSorologico(String codigoTesteSorologico) {
		this.codigoTesteSorologico = codigoTesteSorologico;
	}

	public String getCodigoTipoTeste() {
		return codigoTipoTeste;
	}

	public void setCodigoTipoTeste(String codigoTipoTeste) {
		this.codigoTipoTeste = codigoTipoTeste;
	}

	public String getCodigoProfissionalSeguranca() {
		return codigoProfissionalSeguranca;
	}

	public void setCodigoProfissionalSeguranca(String codigoProfissionalSeguranca) {
		this.codigoProfissionalSeguranca = codigoProfissionalSeguranca;
	}

	public String getCodigoProfissionalSaude() {
		return codigoProfissionalSaude;
	}

	public void setCodigoProfissionalSaude(String codigoProfissionalSaude) {
		this.codigoProfissionalSaude = codigoProfissionalSaude;
	}

	public String getCodigoTemCpf() {
		return codigoTemCpf;
	}

	public void setCodigoTemCpf(String codigoTemCpf) {
		this.codigoTemCpf = codigoTemCpf;
	}

	public String getCodigoSexo() {
		return codigoSexo;
	}

	public void setCodigoSexo(String codigoSexo) {
		this.codigoSexo = codigoSexo;
	}

	public String getCodigoEstrangeiro() {
		return codigoEstrangeiro;
	}

	public void setCodigoEstrangeiro(String codigoEstrangeiro) {
		this.codigoEstrangeiro = codigoEstrangeiro;
	}

	public String getCodigoCbo() {
		return codigoCbo;
	}

	public void setCodigoCbo(String codigoCbo) {
		this.codigoCbo = codigoCbo;
	}

	public String getCodigoPaisOrigem() {
		return codigoPaisOrigem;
	}

	public void setCodigoPaisOrigem(String codigoPaisOrigem) {
		this.codigoPaisOrigem = codigoPaisOrigem;
	}

	public String getCodigoResultadoTesteSorologicoTotais() {
		return codigoResultadoTesteSorologicoTotais;
	}

	public void setCodigoResultadoTesteSorologicoTotais(String codigoResultadoTesteSorologicoTotais) {
		this.codigoResultadoTesteSorologicoTotais = codigoResultadoTesteSorologicoTotais;
	}

	public String getCodigoResultadoTesteSorologicoIgA() {
		return codigoResultadoTesteSorologicoIgA;
	}

	public void setCodigoResultadoTesteSorologicoIgA(String codigoResultadoTesteSorologicoIgA) {
		this.codigoResultadoTesteSorologicoIgA = codigoResultadoTesteSorologicoIgA;
	}

	public String getCodigoComunidadeTradicional() {
		return codigoComunidadeTradicional;
	}

	public void setCodigoComunidadeTradicional(String codigoComunidadeTradicional) {
		this.codigoComunidadeTradicional = codigoComunidadeTradicional;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setNotificacoes(List<Notificacao> notificacoes) {
		this.notificacoes = notificacoes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((nomeCompleto == null) ? 0 : nomeCompleto.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (nomeCompleto == null) {
			if (other.nomeCompleto != null)
				return false;
		} else if (!nomeCompleto.equals(other.nomeCompleto))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Paciente [id=" + id + ", dataNascimento=" + dataNascimento + ", cpf=" + cpf + ", nomeCompleto="
				+ nomeCompleto + "]";
	}

}
