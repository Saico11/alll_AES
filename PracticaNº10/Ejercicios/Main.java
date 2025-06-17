// clase que contiene el main para el Ejercicio 04
public class Main {
    public static void main(String[] args) {
        // crea un B-Tree de orden 4 para RegistroEstudiante
        BTree<RegistroEstudiante> tree = new BTree<>(4);

        // inserta los estudiantes indicados
        tree.insert(new RegistroEstudiante(103, "Ana"));
        tree.insert(new RegistroEstudiante(110, "Luis"));
        tree.insert(new RegistroEstudiante(101, "Carlos"));
        tree.insert(new RegistroEstudiante(120, "Lucia"));
        tree.insert(new RegistroEstudiante(115, "David"));
        tree.insert(new RegistroEstudiante(125, "Jorge"));
        tree.insert(new RegistroEstudiante(140, "Camila"));
        tree.insert(new RegistroEstudiante(108, "Rosa"));
        tree.insert(new RegistroEstudiante(132, "Ernesto"));
        tree.insert(new RegistroEstudiante(128, "Denis"));
        tree.insert(new RegistroEstudiante(145, "Enrique"));
        tree.insert(new RegistroEstudiante(122, "Karina"));
        tree.insert(new RegistroEstudiante(108, "Juan"));

        // busca por codigo y muestra el nombre o "No encontrado"
        System.out.println(tree.buscarNombre(115)); // debe imprimir: David
        System.out.println(tree.buscarNombre(132)); // debe imprimir: Ernesto
        System.out.println(tree.buscarNombre(999)); // debe imprimir: No encontrado

        // elimina al estudiante con codigo 101
        tree.remove(new RegistroEstudiante(101, ""));

        // inserta nuevo estudiante (106, "Sara")
        tree.insert(new RegistroEstudiante(106, "Sara"));

        // busca al nuevo estudiante y muestra el nombre
        System.out.println(tree.buscarNombre(106)); // debe imprimir: Sara
    }
}

