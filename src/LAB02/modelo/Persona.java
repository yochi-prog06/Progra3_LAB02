package LAB02.modelo;
import java.util.Objects;

public abstract class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.edad = edad;
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract String getDescripcion();

    @Override
    public String toString(){
        return "Persona{nombre: " + nombre + ", edad: " + edad + "}";
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nombre, persona.nombre) && Objects.equals(edad, persona.edad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, edad);
    }
}
