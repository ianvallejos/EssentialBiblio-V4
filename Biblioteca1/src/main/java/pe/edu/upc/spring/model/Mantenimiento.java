package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Mantenimiento")

public class Mantenimiento implements Serializable {
	
private static final long serialVersionUID =1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMantenimiento;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nombreAutor", length=60, nullable=false)
	private String obserMante;
	
	@NotNull
	@Past(message="No puedes seleccionar un dia que no existe")
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaMante")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaMante;
	
	@ManyToOne
	@JoinColumn(name="idLibro", nullable=false)
	private Libro libro;
	
	public Mantenimiento() {
		super();
	}

	public Mantenimiento(int idMantenimiento,
			String obserMante,
			Date fechaMante,
			Libro libro) {
		super();
		this.idMantenimiento = idMantenimiento;
		this.obserMante = obserMante;
		this.fechaMante = fechaMante;
		this.libro=libro;
	}

	public int getIdMantenimiento() {
		return idMantenimiento;
	}

	public void setIdMantenimiento(int idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}

	public String getObserMante() {
		return obserMante;
	}

	public void setObserMante(String obserMante) {
		this.obserMante = obserMante;
	}

	public Date getFechaMante() {
		return fechaMante;
	}

	public void setFechaMante(Date fechaMante) {
		this.fechaMante = fechaMante;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
}