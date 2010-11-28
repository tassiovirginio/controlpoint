package br.tvsolutions.ponto.util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RelatorioXLS {
	private String nome;
	private String periodo;
	private Date jornada;
	private String horaEntrada;
	private String horaSaida;
	private String horaExtra;
	private List<PontoXLS> listaPontos = new ArrayList<PontoXLS>();
	private String nomeXLS;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Date getJornada() {
		return jornada;
	}
	public void setJornada(Date jornada) {
		this.jornada = jornada;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	public String getHoraExtra() {
		return horaExtra;
	}
	public void setHoraExtra(String horaExtra) {
		this.horaExtra = horaExtra;
	}
	public List<PontoXLS> getListaPontos() {
		return listaPontos;
	}
	public void setListaPontos(List<PontoXLS> listaPontos) {
		this.listaPontos = listaPontos;
	}
	public String getNomeXLS() {
		return nomeXLS;
	}
	public void setNomeXLS(String nomeXLS) {
		this.nomeXLS = nomeXLS;
	}
	
	
	

}
