package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Libro")

public class Libro implements Serializable {
	
private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLibro;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nameDueno", length=60, nullable=false)
	private String nombreLibro;
	
	@NotNull
	@Past(message="No puedes seleccionar un dia que no existe")
	@Temporal(TemporalType.DATE)
	@Column(name = "fechaIngresoLibro")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaIngresoLibro;
	
	private int numEdicionLibro;
	private int numSerieLibro;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "idiomaLibro", length=60, nullable=false)
	private String idiomaLibro;
	private String estadoLibro;
	private int isbnLibro;
	
	public Libro() {
		super();
	}

	public Libro(int idLibro,
			String nombreLibro,Date fechaIngresoLibro,
			int numEdicionLibro,int numSerieLibro,String idiomaLibro,
			String estadoLibro,int isbnLibro) {
		super();
		this.idLibro = idLibro;
		this.nombreLibro = nombreLibro;
		this.fechaIngresoLibro=fechaIngresoLibro;
		this.numEdicionLibro=numEdicionLibro;
		this.numSerieLibro=numSerieLibro;
		this.idiomaLibro=idiomaLibro;
		this.estadoLibro=estadoLibro;
		this.isbnLibro=isbnLibro;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public Date getFechaIngresoLibro() {
		return fechaIngresoLibro;
	}

	public void setFechaIngresoLibro(Date fechaIngresoLibro) {
		this.fechaIngresoLibro = fechaIngresoLibro;
	}

	public int getNumEdicionLibro() {
		return numEdicionLibro;
	}

	public void setNumEdicionLibro(int numEdicionLibro) {
		this.numEdicionLibro = numEdicionLibro;
	}

	public int getNumSerieLibro() {
		return numSerieLibro;
	}

	public void setNumSerieLibro(int numSerieLibro) {
		this.numSerieLibro = numSerieLibro;
	}

	public String getIdiomaLibro() {
		return idiomaLibro;
	}

	public void setIdiomaLibro(String idiomaLibro) {
		this.idiomaLibro = idiomaLibro;
	}

	public String getEstadoLibro() {
		return estadoLibro;
	}

	public void setEstadoLibro(String estadoLibro) {
		this.estadoLibro = estadoLibro;
	}

	public int getIsbnLibro() {
		return isbnLibro;
	}

	public void setIsbnLibro(int isbnLibro) {
		this.isbnLibro = isbnLibro;
	}
	
}