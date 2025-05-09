public class Test {
    public static void main(String[] args) {
        try {
            // Crear una cola de enteros con capacidad 5
            Queue<Integer> intQueue = new QueueArray<>(5);
            
            // Encolar elementos
            intQueue.enqueue(10);
            intQueue.enqueue(20);
            intQueue.enqueue(30);
            System.out.println("Cola después de encolar 10, 20, 30: " + intQueue);
            
            // Ver frente y final
            System.out.println("Frente: " + intQueue.front());
            System.out.println("Final: " + intQueue.back());
            
            // Desencolar
            System.out.println("Desencolado: " + intQueue.dequeue());
            System.out.println("Cola después de desencolar: " + intQueue);
            
            // Encolar más elementos
            intQueue.enqueue(40);
            intQueue.enqueue(50);
            System.out.println("Cola después de encolar 40, 50: " + intQueue);
            
            // Verificar si está llena
            System.out.println("¿Está llena? " + ((QueueArray<Integer>) intQueue).isFull());
            
            // Intentar encolar en cola llena (descomentar para probar)
            // intQueue.enqueue(60); // Lanzará IllegalStateException
            
            // Cola de Strings
            Queue<String> strQueue = new QueueArray<>(3);
            strQueue.enqueue("Hola");
            strQueue.enqueue("Mundo");
            System.out.println("\nCola de strings: " + strQueue);
            System.out.println("Desencolado: " + strQueue.dequeue());
            
        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
