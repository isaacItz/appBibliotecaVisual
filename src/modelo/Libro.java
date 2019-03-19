package modelo;

import java.io.Serializable;

public class Libro implements Serializable, Comparable<Libro> {
	private String isbn;
	private String titulo;
	private String autor;
	private String editoria;
	private String numeroEdicion;
	private String anioEdicion;
	private String idioma;
	private String pais;

	public Libro(String isbn, String titulo, String autor, String editoria, String numeroEdicion, String anioEdicion,
			String idioma, String pais) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.editoria = editoria;
		this.numeroEdicion = numeroEdicion;
		this.anioEdicion = anioEdicion;
		this.idioma = idioma;
		this.pais = pais;
	}

	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Libro(String isbn) {
		super();
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditoria() {
		return editoria;
	}

	public void setEditoria(String editoria) {
		this.editoria = editoria;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getNumeroEdicion() {
		return numeroEdicion;
	}

	public void setNumeroEdicion(String numeroEdicion) {
		this.numeroEdicion = numeroEdicion;
	}

	public String getAnioEdicion() {
		return anioEdicion;
	}

	public void setAnioEdicion(String anioEdicion) {
		this.anioEdicion = anioEdicion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public boolean equals(Object o) {

		Libro libro = (Libro) o;
		return isbn.equals(libro.isbn);
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", editoria=" + editoria
				+ ", numeroEdicion=" + numeroEdicion + ", anioEdicion=" + anioEdicion + ", idioma=" + idioma + ", pais="
				+ pais + "]";
	}

	@Override
	public int compareTo(Libro o) {
		return isbn.compareTo(o.isbn);
	}

}
