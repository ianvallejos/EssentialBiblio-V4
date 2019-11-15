package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="Ejemplar")

public class Ejemplar implements Serializable {
	
private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEjemplar;
	
	private int cantidadEjemplar;

	@ManyToOne
	@JoinColumn(name="idLibro", nullable=false)	
	private Libro libro;
	
	public Ejemplar() {
		super();
	}

	public Ejemplar(int idEjemplar,
			int cantidadEjemplar,Libro libro) {
		super();
		this.idEjemplar = idEjemplar;
		this.cantidadEjemplar = cantidadEjemplar;
		this.libro=libro;
	}

	public int getIdEjemplar() {
		return idEjemplar;
	}

	public void setIdEjemplar(int idEjemplar) {
		this.idEjemplar = idEjemplar;
	}

	public int getCantidadEjemplar() {
		return cantidadEjemplar;
	}

	public void setCantidadEjemplar(int cantidadEjemplar) {
		this.cantidadEjemplar = cantidadEjemplar;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
}