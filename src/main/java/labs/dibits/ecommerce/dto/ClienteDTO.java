package labs.dibits.ecommerce.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import labs.dibits.ecommerce.domain.Cliente;
import labs.dibits.ecommerce.services.validation.ClienteUpdate;


@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotBlank(message = "Obrigatório")
	@Length(min = 5, max = 120, message = "Tamanho entre 5 e 120 caracteres")
	private String nome;
	
	@NotBlank(message = "Obrigatório")
	@Email(message = "Email Inválido")
	private String email;

	public ClienteDTO() {

	}

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
