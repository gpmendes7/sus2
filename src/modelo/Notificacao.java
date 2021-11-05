package modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notificacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Column(columnDefinition="TEXT")
	private String notificadorCpf;
	@Column(columnDefinition="TEXT")
	private String notificadorEmail;
	@Column(columnDefinition="TEXT")
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
	@ManyToOne
	private Paciente paciente;
	
	public Notificacao() {
	}
	
	public Notificacao(Date dataNascimento, Date dataNotificacao, Date dataInicioSintomas, Date dataTeste,
			String pUsuario, String estrangeiro, String profissionalSaude, String profissionalSeguranca, String cbo,
			String cpf, String cns, String nomeCompleto, String nomeMae, String paisOrigem, String sexo, String racaCor,
			String etnia, String cep, String passaporte, String logradouro, String numero, String complemento,
			String bairro, String estado, String municipio, String telefoneContato, String telefone, String sintomas,
			String outrosSintomas, String condicoes, String estadoTeste, String tipoTeste, String testeSorologico,
			Date dataTesteSorologico, String resultadoTeste, String tipoTesteSorologico,
			String resultadoTesteSorologicoIgA, String resultadoTesteSorologicoIgG, String resultadoTesteSorologicoIgM,
			String resultadoTesteSorologicoTotais, String numeroNotificacao, String cnes, String estadoNotificacao,
			String municipioNotificacao, String origem, String nomeCompletoDesnormalizado, Date createdAt,
			Date updatedAt, String sourceId, String idade, String classificacaoFinal, String evolucaoCaso,
			Date dataEncerramento, String descricaoRacaCor, String pUsuarioAlteracao, String rpa, String idOrigem,
			String desnormalizarNome, String timestamp, String estadoIBGE, String estadoNotificacaoIBGE,
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
		this.timestampNotificacao = timestamp;
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
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public Date getDataNotificacao() {
		return dataNotificacao;
	}

	public Date getDataInicioSintomas() {
		return dataInicioSintomas;
	}

	public Date getDataTeste() {
		return dataTeste;
	}

	public String getpUsuario() {
		return pUsuario;
	}

	public String getEstrangeiro() {
		return estrangeiro;
	}

	public String getProfissionalSaude() {
		return profissionalSaude;
	}

	public String getProfissionalSeguranca() {
		return profissionalSeguranca;
	}

	public String getCbo() {
		return cbo;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCns() {
		return cns;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public String getSexo() {
		return sexo;
	}

	public String getRacaCor() {
		return racaCor;
	}

	public String getEtnia() {
		return etnia;
	}

	public String getCep() {
		return cep;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getEstado() {
		return estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getSintomas() {
		return sintomas;
	}

	public String getOutrosSintomas() {
		return outrosSintomas;
	}

	public String getCondicoes() {
		return condicoes;
	}

	public String getEstadoTeste() {
		return estadoTeste;
	}

	public String getTipoTeste() {
		return tipoTeste;
	}

	public String getTesteSorologico() {
		return testeSorologico;
	}

	public Date getDataTesteSorologico() {
		return dataTesteSorologico;
	}

	public String getResultadoTeste() {
		return resultadoTeste;
	}

	public String getTipoTesteSorologico() {
		return tipoTesteSorologico;
	}

	public String getResultadoTesteSorologicoIgA() {
		return resultadoTesteSorologicoIgA;
	}

	public String getResultadoTesteSorologicoIgG() {
		return resultadoTesteSorologicoIgG;
	}

	public String getResultadoTesteSorologicoIgM() {
		return resultadoTesteSorologicoIgM;
	}

	public String getResultadoTesteSorologicoTotais() {
		return resultadoTesteSorologicoTotais;
	}

	public String getNumeroNotificacao() {
		return numeroNotificacao;
	}

	public String getCnes() {
		return cnes;
	}

	public String getEstadoNotificacao() {
		return estadoNotificacao;
	}

	public String getMunicipioNotificacao() {
		return municipioNotificacao;
	}

	public String getOrigem() {
		return origem;
	}

	public String getNomeCompletoDesnormalizado() {
		return nomeCompletoDesnormalizado;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public String getSourceId() {
		return sourceId;
	}

	public String getIdade() {
		return idade;
	}

	public String getClassificacaoFinal() {
		return classificacaoFinal;
	}

	public String getEvolucaoCaso() {
		return evolucaoCaso;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public String getDescricaoRacaCor() {
		return descricaoRacaCor;
	}

	public String getpUsuarioAlteracao() {
		return pUsuarioAlteracao;
	}

	public String getRpa() {
		return rpa;
	}

	public String getIdOrigem() {
		return idOrigem;
	}

	public String getDesnormalizarNome() {
		return desnormalizarNome;
	}

	public String getTimestampNotificacao() {
		return timestampNotificacao;
	}

	public String getEstadoIBGE() {
		return estadoIBGE;
	}

	public String getEstadoNotificacaoIBGE() {
		return estadoNotificacaoIBGE;
	}

	public String getMunicipioIBGE() {
		return municipioIBGE;
	}

	public String getMunicipioNotificacaoIBGE() {
		return municipioNotificacaoIBGE;
	}

	public String getNotificadorCpf() {
		return notificadorCpf;
	}

	public String getNotificadorEmail() {
		return notificadorEmail;
	}

	public String getNotificadorNome() {
		return notificadorNome;
	}

	public String getNotificadorCNPJ() {
		return notificadorCNPJ;
	}

	public String getCodigoClassificacaoFinal() {
		return codigoClassificacaoFinal;
	}

	public String getCodigoEvolucaoCaso() {
		return codigoEvolucaoCaso;
	}

	public String getCodigoEstadoTeste() {
		return codigoEstadoTeste;
	}

	public String getLabCnes() {
		return labCnes;
	}

	public String getCodigoCondicoes() {
		return codigoCondicoes;
	}

	public String getCodigoResultadoTeste() {
		return codigoResultadoTeste;
	}

	public String getCodigoSintomas() {
		return codigoSintomas;
	}

	public String getEmail() {
		return email;
	}

	public String getComunidadeTradicional() {
		return comunidadeTradicional;
	}

	public String getContemComunidadeTradicional() {
		return contemComunidadeTradicional;
	}

	public String getVersaoFormulario() {
		return versaoFormulario;
	}

	public String getCodigoResultadoTesteSorologicoIgM() {
		return codigoResultadoTesteSorologicoIgM;
	}

	public String getCodigoResultadoTesteSorologicoIgG() {
		return codigoResultadoTesteSorologicoIgG;
	}

	public String getCodigoTipoTesteSorologico() {
		return codigoTipoTesteSorologico;
	}

	public String getCodigoTesteSorologico() {
		return codigoTesteSorologico;
	}

	public String getCodigoTipoTeste() {
		return codigoTipoTeste;
	}

	public String getCodigoProfissionalSeguranca() {
		return codigoProfissionalSeguranca;
	}

	public String getCodigoProfissionalSaude() {
		return codigoProfissionalSaude;
	}

	public String getCodigoTemCpf() {
		return codigoTemCpf;
	}

	public String getCodigoSexo() {
		return codigoSexo;
	}

	public String getCodigoEstrangeiro() {
		return codigoEstrangeiro;
	}

	public String getCodigoCbo() {
		return codigoCbo;
	}

	public String getCodigoPaisOrigem() {
		return codigoPaisOrigem;
	}

	public String getCodigoResultadoTesteSorologicoTotais() {
		return codigoResultadoTesteSorologicoTotais;
	}

	public String getCodigoResultadoTesteSorologicoIgA() {
		return codigoResultadoTesteSorologicoIgA;
	}

	public String getCodigoComunidadeTradicional() {
		return codigoComunidadeTradicional;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	@Override
	public String toString() {
		return "Notificacao [dataNascimento=" + dataNascimento + ", dataNotificacao=" + dataNotificacao
				+ ", dataInicioSintomas=" + dataInicioSintomas + ", dataTeste=" + dataTeste + ", pUsuario=" + pUsuario
				+ ", estrangeiro=" + estrangeiro + ", profissionalSaude=" + profissionalSaude
				+ ", profissionalSeguranca=" + profissionalSeguranca + ", cbo=" + cbo + ", cpf=" + cpf + ", cns=" + cns
				+ ", nomeCompleto=" + nomeCompleto + ", nomeMae=" + nomeMae + ", paisOrigem=" + paisOrigem + ", sexo="
				+ sexo + ", racaCor=" + racaCor + ", etnia=" + etnia + ", cep=" + cep + ", passaporte=" + passaporte
				+ ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", bairro="
				+ bairro + ", estado=" + estado + ", municipio=" + municipio + ", telefoneContato=" + telefoneContato
				+ ", telefone=" + telefone + ", sintomas=" + sintomas + ", outrosSintomas=" + outrosSintomas
				+ ", condicoes=" + condicoes + ", estadoTeste=" + estadoTeste + ", tipoTeste=" + tipoTeste
				+ ", testeSorologico=" + testeSorologico + ", dataTesteSorologico=" + dataTesteSorologico
				+ ", resultadoTeste=" + resultadoTeste + ", tipoTesteSorologico=" + tipoTesteSorologico
				+ ", resultadoTesteSorologicoIgA=" + resultadoTesteSorologicoIgA + ", resultadoTesteSorologicoIgG="
				+ resultadoTesteSorologicoIgG + ", resultadoTesteSorologicoIgM=" + resultadoTesteSorologicoIgM
				+ ", resultadoTesteSorologicoTotais=" + resultadoTesteSorologicoTotais + ", numeroNotificacao="
				+ numeroNotificacao + ", cnes=" + cnes + ", estadoNotificacao=" + estadoNotificacao
				+ ", municipioNotificacao=" + municipioNotificacao + ", origem=" + origem
				+ ", nomeCompletoDesnormalizado=" + nomeCompletoDesnormalizado + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", sourceId=" + sourceId + ", idade=" + idade + ", classificacaoFinal="
				+ classificacaoFinal + ", evolucaoCaso=" + evolucaoCaso + ", dataEncerramento=" + dataEncerramento
				+ ", descricaoRacaCor=" + descricaoRacaCor + ", pUsuarioAlteracao=" + pUsuarioAlteracao + ", rpa=" + rpa
				+ ", idOrigem=" + idOrigem + ", desnormalizarNome=" + desnormalizarNome + ", timestamp=" + timestampNotificacao
				+ ", estadoIBGE=" + estadoIBGE + ", estadoNotificacaoIBGE=" + estadoNotificacaoIBGE + ", municipioIBGE="
				+ municipioIBGE + ", municipioNotificacaoIBGE=" + municipioNotificacaoIBGE + ", notificadorCpf="
				+ notificadorCpf + ", notificadorEmail=" + notificadorEmail + ", notificadorNome=" + notificadorNome
				+ ", notificadorCNPJ=" + notificadorCNPJ + ", codigoClassificacaoFinal=" + codigoClassificacaoFinal
				+ ", codigoEvolucaoCaso=" + codigoEvolucaoCaso + ", codigoEstadoTeste=" + codigoEstadoTeste
				+ ", labCnes=" + labCnes + ", codigoCondicoes=" + codigoCondicoes + ", codigoResultadoTeste="
				+ codigoResultadoTeste + ", codigoSintomas=" + codigoSintomas + ", email=" + email
				+ ", comunidadeTradicional=" + comunidadeTradicional + ", contemComunidadeTradicional="
				+ contemComunidadeTradicional + ", versaoFormulario=" + versaoFormulario
				+ ", codigoResultadoTesteSorologicoIgM=" + codigoResultadoTesteSorologicoIgM
				+ ", codigoResultadoTesteSorologicoIgG=" + codigoResultadoTesteSorologicoIgG
				+ ", codigoTipoTesteSorologico=" + codigoTipoTesteSorologico + ", codigoTesteSorologico="
				+ codigoTesteSorologico + ", codigoTipoTeste=" + codigoTipoTeste + ", codigoProfissionalSeguranca="
				+ codigoProfissionalSeguranca + ", codigoProfissionalSaude=" + codigoProfissionalSaude
				+ ", codigoTemCpf=" + codigoTemCpf + ", codigoSexo=" + codigoSexo + ", codigoEstrangeiro="
				+ codigoEstrangeiro + ", codigoCbo=" + codigoCbo + ", codigoPaisOrigem=" + codigoPaisOrigem
				+ ", codigoResultadoTesteSorologicoTotais=" + codigoResultadoTesteSorologicoTotais
				+ ", codigoResultadoTesteSorologicoIgA=" + codigoResultadoTesteSorologicoIgA
				+ ", codigoComunidadeTradicional=" + codigoComunidadeTradicional + "]";
	}
}
