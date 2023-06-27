public class ThreadSimples {

  // Método para exibir mensagens na saída padrão
  static void mensagem(String mensagem) {
    // Obtém o nome da thread atual
    String nomeThread = Thread.currentThread().getName();
    // Imprime o nome da thread e a mensagem
    System.out.println(nomeThread + " " + mensagem);
  }

  // Classe interna que implementa a interface Runnable
  private static class Loop implements Runnable {

    public void run() {
      // Array de mensagens
      String info[] = {
        "Java",
        "é uma boa linguagem.",
        "Com threads,",
        "é melhor ainda.",
      };
      try {
        for (int i = 0; i < info.length; i++) {
          // Pausa a execução da thread por 4 segundos
          Thread.sleep(4000);
          // Chama o método mensagem para exibir a mensagem atual
          mensagem(info[i]);
        }
      } catch (InterruptedException e) {
        // Caso a thread seja interrompida enquanto está dormindo
        mensagem("Nada feito!");
      }
    }
  }

  public static void main(String args[]) throws InterruptedException {
    // Define o tempo de paciência inicial para 1 hora
    long paciencia = 1000 * 60 * 60;
    if (args.length > 0) {
      try {
        // Se um argumento for fornecido, converte-o para um valor inteiro e define como tempo de paciência
        paciencia = Long.parseLong(args[0]) * 1000;
      } catch (NumberFormatException e) {
        // Caso o argumento fornecido não seja um número inteiro válido
        System.err.println("Argumento deve ser um inteiro.");
        System.exit(1);
      }
    }

    // Inicia uma nova thread com a classe Loop como Runnable
    mensagem("Iniciando a thread Loop");
    long inicio = System.currentTimeMillis();
    Thread t = new Thread(new Loop());
    t.start();

    mensagem("Esperando que a thread Loop termine");
    while (t.isAlive()) {
      mensagem("Ainda esperando...");
      t.join(1000);
      // Verifica se o tempo de paciência foi excedido e a thread ainda está em execução
      if (((System.currentTimeMillis() - inicio) > paciencia) && t.isAlive()) {
        mensagem("Cansado de esperar!");
        // Interrompe a thread
        t.interrupt();
        t.join();
      }
    }

    mensagem("Finalmente!");
  }
}
