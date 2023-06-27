public class Thread implements Runnable{
    public class Contador {
        for(int i = 0; i < 10; i++){
            System.out.println(i);
        }
    }

    public void run() {Contador();
    }
}

public class TestaContador {
    // Nova classe denominada ExecutaThread com apenas o método main()
    public static void main(String[] args) {
        ThreadSimples simples = new ThreadSimples();
        simples.run();
    }
}

//EXERCICIO 01

public class Contador extends Thread {
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }
    }
}

public class TesteContador {
    public static void main(String[] args) {
        Contador contador = new Contador();
        contador.start();
    }
}


//EXERCICIO 02

// Classe Contador agora implementa Runnable
public class Contador implements Runnable {
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }
    }
}

public class TesteContador {
    public static void main(String[] args) {
        Contador contador = new Contador();
        // Para inicar a thread basta chamar pela função run() implementada em Runnable
        contador.run();
    }
}

//EXERCICIO 3

// Classe Contador agora implementa Runnable
public class Contador implements Runnable {
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }
    }
}

public class TesteContador {
    public static void main(String[] args) {
        Contador contador = new Contador();
        // Para inicar a thread basta chamar pela função run() implementada em Runnable
        contador.run();
    }
}
