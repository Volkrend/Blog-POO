package logica;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

class Publicacion {
    private static int consecutivo = 1;
    private int codigo;
    private String titulo;
    private String texto;
    private String autor;
    private LocalDateTime fechaPublicacion;
    private List<Comentario> comentarios;

    public Publicacion(String titulo, String texto, String autor) {
        codigo = consecutivo;
        consecutivo++;
        this.titulo = titulo;
        this.texto = texto;
        this.autor = autor;
        fechaPublicacion = LocalDateTime.now();
        comentarios = new ArrayList<Comentario>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    @Override
    public String toString() {
        String resultado = titulo + "\n";
        resultado += "Creado por: " + autor + " - ";
        resultado += fechaPublicacion.toString() + "\n";
        resultado += texto + "\n";
        resultado += "Comentarios: \n";
        if (comentarios.isEmpty()) {
            resultado += "No hay comentarios.";
        } else {
            for (Comentario c : comentarios) {
                resultado += c.toString() + "\n";
            }
        }
        return resultado;
    }

    public void agregarComentario(String email, String ip, String texto) {
        comentarios.add(new Comentario(email, ip, texto));
    }
    //agregado
    public List<String> obtenerComentarios() {
        List<String> resultado = new ArrayList<>();
        for (Comentario c : comentarios) {
            resultado.add(c.toString());
        }
        return resultado;
    }

    public void borrarComentario(int pos) throws Exception {
        if (pos < 0 || pos >= comentarios.size()) {
            throw new Exception("Comentario no válido");
        }
        comentarios.remove(pos);
    }
    
}
