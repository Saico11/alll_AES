// Clase para probar la implementacion de la cola
public class Test {
    public static void main(String[] args) {
        try {
            // Prueba con cola de enteros
            System.out.println("--- Prueba con enteros ---");
            Queue<Integer> intQueue = new QueueLink<>();
            
            System.out.println("Cola vacia? " + intQueue.isEmpty());  // true
            
            // Agregar elementos
            intQueue.enqueue(10);
            intQueue.enqueue(20);
            intQueue.enqueue(30);
            
            System.out.println("Cola: " + intQueue);  // 10 -> 20 -> 30
            System.out.println("Frente: " + intQueue.front());  // 10
            System.out.println("Final: " + intQueue.back());    // 30
            
            // Eliminar elemento
            System.out.println("Elemento eliminado: " + intQueue.dequeue());  // 10
            System.out.println("Cola despues de dequeue: " + intQueue);  // 20 -> 30
            
            // Prueba con cola de strings
            System.out.println("\n--- Prueba con strings ---");
            Queue<String> strQueue = new QueueLink<>();
            strQueue.enqueue("Hola");
            strQueue.enqueue("Mundo");
            
            System.out.println("Cola: " + strQueue);  // Hola -> Mundo
            System.out.println("Frente: " + strQueue.front());  // Hola
            System.out.println("Final: " + strQueue.back());    // Mundo
            
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

