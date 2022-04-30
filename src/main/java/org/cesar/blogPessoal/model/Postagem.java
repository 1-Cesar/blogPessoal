package org.cesar.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @date 29/04/2022
 * @author cesar
 *
 */

//entidade do JPA Hibernate / table: essa entidade se tornará uma tabela no meu banco de dados
@Entity 
@Table(name = "postagem")
public class Postagem {
	
	//encapsulamento e definição de variáveis - Annotation: parametros colocados em cima das classes ou propriedades que definem um determinado comportamento
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY ) //chave primária
	private Long id;
	
	@NotNull
	@Size (min = 2, max = 100)
	private String titulo;
	
	@NotNull
	@Size (min = 5, max = 500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis()); //passando algum dado por estar classe, será captado data e hora.
	
	//relacionamento entre as tabelas tema e postagem
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
		
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}	
	
}
