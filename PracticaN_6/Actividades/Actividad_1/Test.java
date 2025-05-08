// Clase para probar la implementacion del Stack
public class Test {
    public static void main(String[] args) {
        try {
            // Crear un stack de enteros con capacidad 3
            Stack<Integer> stack = new StackArray<>(3);
            
            // Verificar si esta vacio
            System.out.println("Esta vacio? " + stack.isEmpty()); // true
            
            // Agregar elementos
            stack.push(10);
            stack.push(20);
            stack.push(30);
            
            // Mostrar contenido
            System.out.println("Contenido: " + stack); // [30, 20, 10]
            
            // Ver el tope
            System.out.println("Tope: " + stack.top()); // 30
            
            // Verificar si esta lleno
            System.out.println("Esta lleno? " + ((StackArray<Integer>)stack).isFull()); // true
            
            // Eliminar un elemento
            System.out.println("Elemento eliminado: " + stack.pop()); // 30
            System.out.println("Contenido despues de pop: " + stack); // [20, 10]
            
            // Probar con strings
            Stack<String> stringStack = new StackArray<>(2);
            stringStack.push("Hola");
            stringStack.push("Mundo");
            
            System.out.println("\nStack de strings: " + stringStack); // [Mundo, Hola]
            
        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
