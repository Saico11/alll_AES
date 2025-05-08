// En el paquete por defecto del proyecto
public class Test {
    public static void main(String[] args) {
        try {
            // Crear una cola de prioridad para Strings con prioridad Integer
            PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>();
            
            // Encolar elementos con diferentes prioridades
            pq.enqueue("Tarea urgente", 3);
            pq.enqueue("Tarea normal", 2);
            pq.enqueue("Tarea baja prioridad", 1);
            pq.enqueue("Tarea muy urgente", 5);
            
            // Mostrar la cola
            System.out.println("Cola de prioridad: " + pq);
            
            // Probar front y back
            System.out.println("Elemento con mayor prioridad: " + pq.front());
            System.out.println("Elemento con menor prioridad: " + pq.back());
            
            // Desencolar elementos
            System.out.println("\nDesencolando elementos:");
            while (!pq.isEmpty()) {
                System.out.println("Elemento desencolado: " + pq.dequeue());
            }
            
            // Probar con cola vacia
            System.out.println("Intentando desencolar de cola vacia...");
            pq.dequeue(); // Esto lanzara una excepcion
            
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
