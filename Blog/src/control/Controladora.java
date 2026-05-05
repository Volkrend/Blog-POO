package control;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import logica.Blog;

public class Controladora {
	private Map<Integer, Blog> blogs;
	 
	public Controladora() {
		blogs = new TreeMap<Integer, Blog>();
	}
	public void crearBlog(String nombre, String descripcion) {
		Blog b = new Blog(nombre, descripcion);
		blogs.put(b.getCodigo(), b);
	}
	public void borrarBlog(int codigoBlog) throws Exception {
        if (!blogs.containsKey(codigoBlog))
            throw new Exception("El código de blog no es válido.");
        blogs.remove(codigoBlog);
    }
	public Map<Integer, String> obtenerBlogs() {
		Map<Integer, String> resultado = new TreeMap<Integer, String>();
		for (Blog b : blogs.values()) {
			resultado.put(b.getCodigo(), b.getNombre());
		}
		return resultado;
	}
	public void agregarPublicacion(int codigoBlog, String titulo, String texto, String nombre)
																				throws Exception {
			if (!blogs.containsKey(codigoBlog))
		        throw new Exception("El código de blog no es válido.");
		    Blog b = blogs.get(codigoBlog);
		    b.agregarPublicacion(titulo, texto, nombre);
		}
	public Map<Integer, String> obtenerPublicaciones(int codigoBlog) throws Exception {
        if (!blogs.containsKey(codigoBlog))
            throw new Exception("El código de blog no es válido.");
        Map<Integer, String> p = blogs.get(codigoBlog).obtenerPublicaciones();
        return p;
    }

    public String obtenerPublicacion(int codigoBlog, int codigoPublicacion) throws Exception {
        if (!blogs.containsKey(codigoBlog))
            throw new Exception("El código de blog no es válido.");
        String p = blogs.get(codigoBlog).obtenerRepresentacionP(codigoPublicacion);
        return p;
    }
    public void agregarComentario(int codigoBlog, int codigoPublicacion, String email, String ip, String texto) 
    														throws Exception {
		if (!blogs.containsKey(codigoBlog))
			throw new Exception("El código de blog no es válido.");
		blogs.get(codigoBlog).agregarComentario(codigoPublicacion, email, ip, texto);
	}
    //agregado
    public List<String> obtenerComentarios(int codigoBlog, int codigoPublicacion) throws Exception {
        if (!blogs.containsKey(codigoBlog))
            throw new Exception("El código de blog no es válido.");
        return blogs.get(codigoBlog).obtenerComentarios(codigoPublicacion);
    }

	public void borrarComentario(int codigoBlog, int codigoPublicacion, int posicion) throws Exception {
		if (!blogs.containsKey(codigoBlog))
			throw new Exception("El código de blog no es válido.");
		blogs.get(codigoBlog).borrarComentario(codigoPublicacion, posicion);
	}
}
