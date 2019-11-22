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
@Table(name="LibroAutor")

public class LibroAutor implements Serializable {
	
private static final long serialVersionUID =1L;

     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idLibroAutor;
	
	
	@ManyToOne
	@JoinColumn(name="idLibro", nullable=false)
	
	private Libro libro;
	
	@ManyToOne
	@JoinColumn(name="idAutor", nullable=false)

	private Autor autor;
	

	public LibroAutor() {
		super();
	}

	public LibroAutor(int idLibroAutor,Libro libro,
			Autor autor) {
		super();
		this.idLibroAutor=idLibroAutor;
		this.autor=autor;
		this.libro=libro;
	}

	public int getIdLibroAutor() {
		return idLibroAutor;
	}

	public void setIdLibroAutor(int idLibroAutor) {
		this.idLibroAutor = idLibroAutor;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
}