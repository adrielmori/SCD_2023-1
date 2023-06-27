public class ThreadSimples implements Runnable {

  public static void main(String args[]) throws InterruptedException {
      String info[] = {
          "Java",
          "é uma boa linguagem.",
          "Com threads",
          "é melhor ainda.",
      };

      ThreadSimples simples = new ThreadSimples();
      Thread thread = new Thread(simples);
      thread.start();

      for (int i = 0; i < info.length; i++) {
          Thread.sleep(10000);
          System.out.println(info[i]);
      }
  }

  public void run() {
      String nomeThreadPrincipal = Thread.currentThread().getName();
      System.out.println(nomeThreadPrincipal);
  }
}

public class ThreadSimples implements Runnable {

  public static void main(String args[]) throws InterruptedException {
      String info[] = {
          "Java",
          "é uma boa linguagem.",
          "Com threads",
          "é melhor ainda.",
      };

      ThreadSimples simples = new ThreadSimples();

      simples.run(); // Chama o método run() diretamente na instância existente

      for (int i = 0; i < info.length; i++) {
          Thread.sleep(10000);
          System.out.println(info[i]);
      }
  }

  public void run() {
      String nomeThreadPrincipal = Thread.currentThread().getName();
      System.out.println(nomeThreadPrincipal);
  }
}
