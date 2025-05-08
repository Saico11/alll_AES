public class Test {
    public static void main(String[] args) {
        // Crear una pila de enteros
        Stack<Integer> intStack = new StackLink<>();
        
        // Probar con enteros
        System.out.println("Probando pila con enteros:");
        try {
            System.out.println("¿Está vacía? " + intStack.isEmpty());
            
            intStack.push(10);
            intStack.push(20);
            intStack.push(30);
            
            System.out.println("Pila actual: " + intStack);
            System.out.println("Top: " + intStack.top());
            System.out.println("Pop: " + intStack.pop());
            System.out.println("Pila después de pop: " + intStack);
            
            intStack.push(40);
            System.out.println("Pila después de push(40): " + intStack);
            
            System.out.println("Pop: " + intStack.pop());
            System.out.println("Pop: " + intStack.pop());
            System.out.println("¿Está vacía? " + intStack.isEmpty());
            System.out.println("Pop: " + intStack.pop());
            System.out.println("¿Está vacía? " + intStack.isEmpty());
            
            // Esto lanzará una excepción
            System.out.println("Top: " + intStack.top());
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Probar con strings
        Stack<String> strStack = new StackLink<>();
        System.out.println("\nProbando pila con strings:");
        try {
            strStack.push("Hola");
            strStack.push("Mundo");
            strStack.push("Java");
            
            System.out.println("Pila actual: " + strStack);
            System.out.println("Pop: " + strStack.pop());
            System.out.println("Top: " + strStack.top());
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
