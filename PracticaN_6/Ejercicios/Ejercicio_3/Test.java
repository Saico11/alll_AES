public class Test {
    public static void main(String[] args) {
        try {
            // Crear una cola de prioridad con 3 niveles (0: baja, 1: media, 2: alta)
            PriorityQueue<String, Integer> pq = new PriorityQueueLinked<>(3);
            
            // Agregar elementos con diferentes prioridades
            pq.enqueue("Tarea baja prioridad", 0);
            pq.enqueue("Tarea media prioridad", 1);
            pq.enqueue("Tarea alta prioridad", 2);
            pq.enqueue("Otra tarea media", 1);
            pq.enqueue("Otra tarea baja", 0);
            
            System.out.println("Cola de prioridad completa:");
            System.out.println(pq);
            
            // Procesar elementos (deberían salir en orden de prioridad)
            System.out.println("\nProcesando elementos:");
            while (!pq.isEmpty()) {
                System.out.println("Procesando: " + pq.dequeue());
            }
            
            // Prueba con diferentes tipos de datos
            PriorityQueue<Double, Integer> pqDouble = new PriorityQueueLinked<>(3);
            pqDouble.enqueue(3.14, 0);
            pqDouble.enqueue(2.71, 2);
            pqDouble.enqueue(1.61, 1);
            
            System.out.println("\nCola de prioridad con doubles:");
            System.out.println(pqDouble);
            
        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
