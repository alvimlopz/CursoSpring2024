package com.example.demo.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.example.demo.domain.Cliente;
import com.example.demo.services.validation.ClienteUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@ClienteUpdate
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Prenchimento obrigatorio")
	@Length(min = 5, max= 120, message = "Otamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Prenchimento obrigatorio")
	@Email(message = "Email invalido")
	private String email;
	
	public ClienteDTO () {}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
