package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Usuario")

public class Usuario implements Serializable{
	private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nameUsuario", length=60, nullable=false)
	private String nameUsuario;
	
	
	@Column(name = "apellidoUsuario", length=60, nullable=false)
	private String apellidoUsuario;
	
	
	@Column(name = "dni", length=60, nullable=false)
	private int dni;
	

	@Column(name = "correo", length=60, nullable=false)
	private String correo;
	

	
	public Usuario() {
		super();
	}

	public Usuario(int idUsuario,
			String nameUsuario,String apellidoUsuario,int dni,String correo) {
		super();
		this.idUsuario = idUsuario;
		this.nameUsuario= nameUsuario;
		this.apellidoUsuario= apellidoUsuario;
		this.dni= dni;
		this.correo= correo;
		
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNameUsuario() {
		return nameUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nameUsuario = nameUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}



	
	
}
	
