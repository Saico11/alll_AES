public class App {
    public static void main(String[] args) {
        // crea B-Tree de orden 4 para RegistroEstudiante
        BTree<RegistroEstudiante> tree = new BTree<>(4);
        
        // inserta estudiantes dados
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
        tree.insert(new RegistroEstudiante(108, "Juan")); // duplicado, no se inserta
        
        // busca codigo 115
        System.out.println("Buscar 115 -> " + (tree.search(new RegistroEstudiante(115, "")) ? "Encontrado" : "No encontrado"));
        // busca codigo 132
        System.out.println("Buscar 132 -> " + (tree.search(new RegistroEstudiante(132, "")) ? "Encontrado" : "No encontrado"));
        // busca codigo 999
        System.out.println("Buscar 999 -> " + (tree.search(new RegistroEstudiante(999, "")) ? "Encontrado" : "No encontrado"));
        
        // elimina estudiante 101
        tree.remove(new RegistroEstudiante(101, ""));
        System.out.println("Tras eliminar 101:\n" + tree);
        
        // inserta nuevo estudiante (106, "Sara")
        tree.insert(new RegistroEstudiante(106, "Sara"));
        System.out.println("Tras insertar 106:\n" + tree);
        
        // busca 106
        System.out.println("Buscar 106 -> " + (tree.search(new RegistroEstudiante(106, "")) ? "Encontrado" : "No encontrado"));
    }
}

