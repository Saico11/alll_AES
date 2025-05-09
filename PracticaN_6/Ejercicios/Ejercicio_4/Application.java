public class Application {
    
     //Verifica si una cadena de corchetes esta correctamente balanceada.
     // s La cadena de corchetes a verificar
     //true si la cadena esta balanceada, false en caso contrario
    
    public static boolean symbolBalancing(String s) {
        StackLink<Character> stack = new StackLink<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } 
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                try {
                    char top = stack.pop();  // Ahora manejamos la excepción
                    
                    if ((c == ')' && top != '(') || 
                        (c == ']' && top != '[') || 
                        (c == '}' && top != '{')) {
                        return false;
                    }
                } catch (ExceptionIsEmpty e) {
                    // Esto no debería ocurrir porque ya verificamos isEmpty()
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(symbolBalancing("()()()[()]()"));  
        System.out.println(symbolBalancing("((()))[]"));      
        System.out.println(symbolBalancing("([])[]("));     
        System.out.println(symbolBalancing("([{)]}"));        
        System.out.println(symbolBalancing("["));             
        System.out.println(symbolBalancing("[][][]{{{}}}"));  
    }
}
