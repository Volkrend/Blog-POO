package interfaz;

import control.Controladora;
import java.util.*;
import java.io.BufferedReader; //prueba
import java.io.InputStreamReader; //prueba


public class EjemploBlog {

    static Controladora controladora = new Controladora();
    static int blogActual = 0;
    static int publicacionActual = 0;

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //prueba
    //lee linea por linea
    //Integer.parseInt lo pasa a int
    //InputStreamReader convierte el teclado (System.in) a texto legible


    // menus
    private static int mostrarMenuPrincipal() throws Exception {
        System.out.println("\n Administracion de Blogs \n");
        System.out.println(
            "\n1. Crear blog nuevo." +
            "\n2. Trabajar con blog." +
            "\n3. Borrar blog." +
            "\n4. Salir." +
            "\n ");
        return Integer.parseInt(reader.readLine()); //
    }

    private static void trabajarConBlogs() throws Exception {
        Map<Integer, String> blogs = controladora.obtenerBlogs();
        
        // Mostrar listado y seleccionar blog actual
        System.out.println("\nBlogs disponibles \n");
        for (Map.Entry<Integer, String> entry : blogs.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.print("Seleccione el codigo del blog: ");
        blogActual = Integer.parseInt(reader.readLine());
        
        if (!blogs.containsKey(blogActual)) {
            System.out.println("Error: codigo no valido.");
            return;
        }

        int opcion;
        do {
            //mostrar 
            System.out.println("\nBlog actual: " + controladora.obtenerBlogs().get(blogActual));
            System.out.println("1. Crear publicacion");
            System.out.println("2. Ver publicaciones y comentarios");
            System.out.println("3. Regresar");
            opcion = Integer.parseInt(reader.readLine());
            
            switch (opcion) {
                case 1: crearPublicacion(); 
                break;
                case 2: trabajarConPublicaciones(); 
                break;
                case 3: System.out.println("Menu Principal"); 
                break;
                default: System.out.println("Opcion invalida.");
            }
        } while (opcion != 3);
    }

    
    private static void trabajarConPublicaciones() throws Exception {
        Map<Integer, String> publicaciones = controladora.obtenerPublicaciones(blogActual);
        
        // Mostrar listado y seleccionar publicación actual
        System.out.println("\nPublicaciones del blog:");
        for (Map.Entry<Integer, String> entry : publicaciones.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.print("\nSeleccione el codigo de la publicacion: ");
        int codigoPub = Integer.parseInt(reader.readLine());
        
        if (!publicaciones.containsKey(codigoPub)) {
            System.out.println("Error: codigo de publicacion no valido.");
            return;
        }
        publicacionActual = codigoPub;

        int opcion;
        do {
            //mostrar
            System.out.println("\n" + controladora.obtenerPublicacion(blogActual, publicacionActual));

            System.out.println(" Opciones:");
            System.out.println("1. Crear comentario");
            System.out.println("2. Borrar comentario");
            System.out.println("3. Regresar");

            opcion = Integer.parseInt(reader.readLine());
            
            switch (opcion) {
                case 1:
                    crearComentario();
                    break;
                case 2:
                    borrarComentario();
                    break;
                case 3:
                	System.out.println("Menu de blogs:");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 3);
    }


    //  METODOS A USAR

    private static void crearBlog() throws Exception {
        System.out.print("Nombre del blog: ");
        String nombre = reader.readLine();
        System.out.print("Descripción: ");
        String descripcion = reader.readLine();

        if (nombre.isBlank() || descripcion.isBlank()) { //isBlank mas facil
            System.out.println("Error: el nombre y la descripcion no pueden estar vacios.");
            return;
        }
        controladora.crearBlog(nombre, descripcion);
        System.out.println("Blog creado correctamente.");
        
    }
    
    private static void borrarBlog() throws Exception {
        Map<Integer, String> blogs = controladora.obtenerBlogs();
        if (blogs.isEmpty()) {
            System.out.println("No hay blogs registrados.");
            return;
        }
        System.out.println("Blogs disponibles: " + blogs);
        System.out.print("Código del blog a borrar: ");
        int codigo = Integer.parseInt(reader.readLine());

        try {
            controladora.borrarBlog(codigo);
            if (blogActual == codigo) blogActual = 0;
            System.out.println("Blog eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void crearPublicacion() throws Exception {
        System.out.print("Titulo: ");
        String titulo = reader.readLine();
        System.out.print("Nombre del autor: ");
        String autor = reader.readLine();
        System.out.print("Texto: ");
        String texto = reader.readLine();

        if (titulo.isBlank() || autor.isBlank() || texto.isBlank()) {  //isBlank mas facil
            System.out.println("Error: ningun campo puede estar vacio.");
            return;
        }
        try {
            controladora.agregarPublicacion(blogActual, titulo, texto, autor);
            System.out.println("Publicacion creada correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crearComentario() throws Exception {
        System.out.print("Email: ");
        String email = reader.readLine();
        System.out.print("IP: ");
        String ip = reader.readLine();
        System.out.print("Texto del comentario: ");
        String texto = reader.readLine();

        if (email.isBlank() || ip.isBlank() || texto.isBlank()) { //isBlank mas facil
            System.out.println("Error: ningun campo puede estar vacio.");
            return;
        }
        try {
            controladora.agregarComentario(blogActual, publicacionActual, email, ip, texto);
            System.out.println("Comentario agregado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void borrarComentario() throws Exception {
        System.out.print("Posicion del comentario a borrar: (desde 0)");
        int pos = Integer.parseInt(reader.readLine());
        try {
            controladora.borrarComentario(blogActual, publicacionActual, pos);
            System.out.println("Comentario eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //  datos iniciales

    private static void inicializarDatos() {
        try {
            // Crear blogs iniciales
            controladora.crearBlog("Receta de arroz con leche", "Blog con receta sencilla de arroz con leche");
            controladora.crearBlog("Libros favoritos", "Opiniones sobre libros de fantasia.");

            //Blog 1: Receta de arroz con leche 
            controladora.agregarPublicacion(1, "Ingredientes basicos","Arroz, leche, azucar y canela.", "Pancha"); 
            controladora.agregarPublicacion(1, "Preparacion rapida", "Hervir la leche, añadir arroz y azucar, dejar reposar.", "Pancho");

            // Agregar comentarios 
            controladora.agregarComentario(1, 1, "lobosolitario@gmail.com", "192.168.0.1", "no tengo leche");
            controladora.agregarComentario(1, 1, "randommaster@gmail.com", "192.168.0.2", "no me gusta la canela");
            controladora.agregarComentario(1, 2, "nosequehacer@gmail.com", "192.168.0.3", "Gracias por la receta");
            controladora.agregarComentario(1, 2, "hola@gmail.com", "192.168.0.4", "nunca la hare");

            //Blog 2: Libros favoritos 
            controladora.agregarPublicacion(2, "El Señor de los Anillos","Una saga epica de fantasia con mundos increíbles.", "lolita");
            controladora.agregarPublicacion(2, "Shadow and Bone", "Historias increibles.", "lolito");

            // Agregar comentarios 
            controladora.agregarComentario(2, 3, "arwen@gmail.com", "10.0.0.1", "Mi libro favorito de todos.");
            controladora.agregarComentario(2, 3, "legolas@gmail.com", "10.0.0.2", "Lo he leido varias veces.");
            controladora.agregarComentario(2, 4, "zoya.nazyalensky@gmail.com", "10.0.0.3", "que lastima que cancelaron la serie :(");
            controladora.agregarComentario(2, 4, "inej@gmail.com", "10.0.0.4", "super recomendada");

        } catch (Exception e) {
            System.out.println("Error al inicializar datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        inicializarDatos();

        int opcion;
        do {
            opcion = mostrarMenuPrincipal();
            switch (opcion) {
                case 1: 
                    crearBlog();
                    break;
                case 2: 
                    trabajarConBlogs();
                    break;
                case 3: 
                    borrarBlog();
                    break;
                case 4: 
                    System.out.println("El programa ha terminado.");
                    break;
                default: 
                    System.out.println("Opcion invalida, intente de nuevo.");
            }
        } while (opcion != 4);
    }
}