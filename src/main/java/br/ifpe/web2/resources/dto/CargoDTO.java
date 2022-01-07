package br.ifpe.web2.resources.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.ifpe.web2.model.cadastro.Cargo;

public class CargoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1362442450860428359L;
	
	@NotBlank(message = "Nome do cargo não pode ser vazio")
	private String nome;
	@Min(value=1000, message = "Salário não pode ser menor que R$ 1.000,00")
	private Double salario;
	
	public CargoDTO(Cargo cargo) {
		this.nome = cargo.getNome();
		this.salario = cargo.getSalario();
	}
	
	public CargoDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	
}
