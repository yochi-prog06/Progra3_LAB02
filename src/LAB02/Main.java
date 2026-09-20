package LAB02;

import LAB02.modelo.Estudiante;
import LAB02.modelo.Profesor;
import LAB02.servicio.Gestor;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){

        Estudiante e01 = new Estudiante("Ana Rodriguez",20,"B20262001","Ingeniería en Sistemas");
        e01.agregarNota(85.5);
        e01.agregarNota(92.0);
        e01.agregarNota(78.0);
        Estudiante e02 = new Estudiante("Joshua Cascante", 20, "B20262002", "Ingeniería en Sistemas");
        e02.agregarNota(65.6);
        e02.agregarNota(83.1);
        e02.agregarNota(81);
        Estudiante e03 = new Estudiante("Maria Cespedes", 23, "B20262003", "Ingeniería en Sistemas");
        e03.agregarNota(88.6);
        e03.agregarNota(98.2);
        e03.agregarNota(97);
        Estudiante e04 = new Estudiante("Christian Mendoza", 19, "B20262004", "Ingeniería en Sistemas");
        e04.agregarNota(82.6);
        e04.agregarNota(95.7);
        e04.agregarNota(82);
        Estudiante e05 = new Estudiante("Elena Jimenez", 25, "B20261001", "Administración");
        e05.agregarNota(97.6);
        e05.agregarNota(85);
        e05.agregarNota(83);
        Estudiante e06 = new Estudiante("Keyla Mendez", 26, "B20261002", "Administración");
        e06.agregarNota(99);
        e06.agregarNota(90);
        e06.agregarNota(100);

        Profesor prof1 = new Profesor("Dra. Elena Rojas", 45, "P-001", "Ingeniería en Sistemas");
        Profesor prof2 = new Profesor("Dra. Mario Castro", 52, "P-002", "Administración");
        Profesor prof3 = new Profesor("Dra. Juan Perez", 40,"P-003","Ingeniería en Sistemas");

        Gestor<Estudiante> gestorEstudiantes = new Gestor<>();
        gestorEstudiantes.agregarTodos(e01,e02,e03,e04,e05,e06);

        System.out.println("=== Lista de estudiantes ===");
        gestorEstudiantes.imprimirTodos();

        System.out.println("\n=== Mejores promedios ===");
        List<Estudiante> destacados = gestorEstudiantes.filtrar(e->e.getPromedio() > 90);
        destacados.forEach(System.out::println);

        System.out.println("\n=== Lista de estudiantes ordenados por nota descendente ===");
        List<Estudiante> ordenados = gestorEstudiantes.getTodos();
        ordenados.sort(Comparator.naturalOrder());
        ordenados.forEach(e->System.out.println(e.getNombre() + " -> " + e.getPromedio()));

        System.out.println("\n");
        System.out.println("=== Buscar a \"Luis\" ===");
        Optional<Estudiante> encontrado1 = gestorEstudiantes.buscarPrimero(e->e.getNombre().contains("Luis"));
        encontrado1.ifPresentOrElse(
                e->System.out.println("Estudiante encontrado: " + e.getDescripcion()),
                () -> System.out.println("No se encontro ningún estudiante con ese nombre.")
        );

        System.out.println("\n");
        System.out.println("=== Buscar a \"Joshua\" ===");
        Optional<Estudiante> encontrado2 = gestorEstudiantes.buscarPrimero(e->e.getNombre().contains("Joshua"));
        encontrado2.ifPresentOrElse(
                e->System.out.println("Estudiante encontrado: " + e.getDescripcion()),
                () -> System.out.println("No se encontro ningún estudiante con ese nombre.")
        );

        System.out.println("\n");
        var conteoPorCarrera = gestorEstudiantes.getTodos().stream()
                .collect(Collectors.groupingBy(Estudiante::getCarrera, Collectors.counting()
                ));

        System.out.println("=== Contar por carrera ===");
        conteoPorCarrera.forEach((carrera, cantidad) ->
                System.out.println(carrera + " -> " + cantidad + " estudiante(s)"));

        System.out.println("\n");
        double promedioGeneral = gestorEstudiantes.getTodos().stream()
                .mapToDouble(Estudiante::getPromedio)
                .average()
                .orElse(0.0);
        System.out.println("El promedio general de todos los estudiantes es: " + promedioGeneral);

        System.out.println("\n=== Lista de profesores ===");
        Gestor<Profesor> gestorProfesores = new Gestor<>();
        gestorProfesores.agregarTodos(prof1, prof2, prof3);
        gestorProfesores.imprimirTodos();

    }
}
