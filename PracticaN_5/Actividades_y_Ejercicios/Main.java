// Clase principal para demostrar el funcionamiento
public class Main {
    public static void main(String[] args) {
        // Demostracion del GestorDeTareas
        System.out.println("---- DEMOSTRACION GESTOR DE TAREAS ----");
        
        // Crear gestor con Strings
        GestorDeTareas<String> gestorStrings = new GestorDeTareas<>();
        gestorStrings.agregarTarea("Comprar leche");
        gestorStrings.agregarTarea("Hacer ejercicio");
        gestorStrings.agregarTarea("Estudiar Java");
        
        // Mostrar tareas iniciales
        System.out.println("\nTareas iniciales:");
        gestorStrings.imprimirTareas();
        
        // Operaciones de eliminacin y busqueda
        System.out.println("\nEliminar 'Hacer ejercicio': " + 
            gestorStrings.eliminarTarea("Hacer ejercicio"));
        System.out.println("Contiene 'Estudiar Java': " + 
            gestorStrings.contieneTarea("Estudiar Java"));
        
        // Mostrar estado actual
        System.out.println("\nTareas después de operaciones:");
        gestorStrings.imprimirTareas();
        
        // Demostrar inversion de lista
        System.out.println("\nInvertir tareas:");
        gestorStrings.invertirTareas();
        gestorStrings.imprimirTareas();
        
        // Crear gestor con objetos Tarea
        GestorDeTareas<Tarea> gestorTareas = new GestorDeTareas<>();
        gestorTareas.agregarTarea(new Tarea("Terminar proyecto", 3));
        gestorTareas.agregarTarea(new Tarea("Revisar correos", 1));
        gestorTareas.agregarTarea(new Tarea("Reunión con equipo", 5));
        
        // Mostrar tareas con prioridad
        System.out.println("\nTareas con prioridad:");
        gestorTareas.imprimirTareas();
        
        // Obtener tarea mas prioritaria
        System.out.println("\nTarea mas prioritaria: " + 
            gestorTareas.obtenerTareaMasPrioritaria());
        
        // Demostracion de los ejercicios
        System.out.println("\n---- DEMOSTRACION EJERCICIOS ----");
        
        // Crear lista para ejercicios
        Node<Integer> listaNumeros = null;
        listaNumeros = ListaEjercicios.insertarAlFinal(listaNumeros, 10);
        listaNumeros = ListaEjercicios.insertarAlFinal(listaNumeros, 20);
        listaNumeros = ListaEjercicios.insertarAlFinal(listaNumeros, 30);
        
        // Mostrar lista original
        System.out.println("Lista original:");
        imprimirLista(listaNumeros);
        
        // Ejercicio 1: Busqueda
        System.out.println("Buscar 20: " + 
            ListaEjercicios.buscarElemento(listaNumeros, 20));
        System.out.println("Buscar 40: " + 
            ListaEjercicios.buscarElemento(listaNumeros, 40));
        
        // Ejercicio 4: Contar nodos
        System.out.println("Número de nodos: " + 
            ListaEjercicios.contarNodos(listaNumeros));
        
        // Ejercicio 2: Invertir lista
        Node<Integer> listaInvertida = ListaEjercicios.invertirLista(listaNumeros);
        System.out.println("Lista invertida:");
        imprimirLista(listaInvertida);
        
        // Restaurar lista original
        listaNumeros = ListaEjercicios.invertirLista(listaInvertida);
        
        // Crear segunda lista para ejercicios 5 y 6
        Node<Integer> listaNumeros2 = null;
        listaNumeros2 = ListaEjercicios.insertarAlFinal(listaNumeros2, 40);
        listaNumeros2 = ListaEjercicios.insertarAlFinal(listaNumeros2, 50);
        
        System.out.println("Lista 2:");
        imprimirLista(listaNumeros2);
        
        // Ejercicio 5: Comparar listas
        System.out.println("Listas iguales?: " + 
            ListaEjercicios.sonIguales(listaNumeros, listaNumeros2));
        
        // Ejercicio 6: Concatenar listas
        Node<Integer> listaConcatenada = ListaEjercicios.concatenarListas(listaNumeros, listaNumeros2);
        System.out.println("Listas concatenadas:");
        imprimirLista(listaConcatenada);
    }
    
    // Metodo auxiliar para imprimir una lista
    public static <T> void imprimirLista(Node<T> head) {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
