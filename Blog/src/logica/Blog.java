package logica;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class Blog {
    private static int consecutivo = 1;
    private int codigo;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private Map<Integer, Publicacion> publicaciones;

    public Blog(String nombre, String descripcion) {
        codigo = consecutivo;
        consecutivo++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        fechaCreacion = LocalDateTime.now();
        publicaciones = new TreeMap<Integer, Publicacion>();
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
    private void esCodigoValido(int codigoPublicacion) throws Exception {
    	if (!publicaciones.containsKey(codigoPublicacion)) {
			throw new Exception("Codigo de publicacion no encontrado.");
		}
    }
	public void agregarPublicacion(String titulo, String texto, String nombre) {
		Publicacion p = new Publicacion(titulo, texto, nombre);
		publicaciones.put(p.getCodigo(), p); 
	}
	public String obtenerRepresentacionP(int codigoP) throws Exception {
		esCodigoValido(codigoP);
		Publicacion p = publicaciones.get(codigoP);
		return p.toString();	
	}
	public Map<Integer, String> obtenerPublicaciones() {
		Map<Integer, String> titulos = new TreeMap<Integer, String>();
		for (Publicacion p : publicaciones.values()) {
				titulos.put(p.getCodigo(), p.getTitulo());
		}
		return titulos;
	}
	
	public void agregarComentario(int codigoP, String email, String ip, String texto) throws Exception{
		esCodigoValido(codigoP);
		Publicacion p = publicaciones.get(codigoP);
		p.agregarComentario(email,  ip,  texto);
	}
	
	public void borrarComentario(int codigoP,int  posicion) throws Exception {
		esCodigoValido(codigoP);
		Publicacion p = publicaciones.get(codigoP);
		p.borrarComentario(posicion);
	}
}
