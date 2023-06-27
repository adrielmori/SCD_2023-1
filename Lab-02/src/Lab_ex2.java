// public class Deposito {

//   private int items = 0;
//   private final int capacidade = 10;

//   public int retirar() {
//     if (items > 0) {
//       items--;
//       System.out.println("Caixa retirada: Sobram " + items + " caixas");
//       return 1;
//     }
//     return 0;
//   }

//   public int colocar() {
//     if (items < capacidade) {
//       items++;
//       System.out.println(
//         "Caixa armazenada: Passaram a ser " + items + "caixas"
//       );
//       return 1;
//     }
//     return 0;
//   }

//   public static void main(String[] args) {
//     Deposito dep = new Deposito();
//     Produtor p = new Produtor(d, 2);
//     Consumidor c = new Consumidor(d, 1);
//     //inicia o produtor
//     //...
//     //inicia o consumidor
//     //...
//     System.out.println("Execução do main da classe Deposito terminada!");
//   }
// }

public class Produtor implements Runnable {

  private Deposito deposito;
  private int tempoProducao;

  public Produtor(Deposito dep, int tempo) {
    deposito = dep;
    tempoProducao = tempo;
  }

  public void run() {
    while (true) {
      deposito.armazenar();
      try {
        Thread.sleep(tempoProducao * 1000);
      } catch (InterruptedException e) {
        // Tratamento de interrupção da thread
        System.out.println("Produtor interrompido.");
        break;
      }
    }
  }
}

public class Consumidor implements Runnable {

  private Deposito deposito;
  private int tempoRetirada;

  public Consumidor(Deposito dep, int tempo) {
    deposito = dep;
    tempoRetirada = tempo;
  }

  public void run() {
    while (true) {
      int caixasRetiradas = deposito.retirar();
      if (caixasRetiradas == 0) {
        System.out.println(
          "Consumidor bloqueado. Aguardando caixas para retirar..."
        );
      } else {
        System.out.println(
          "Consumidor retirou " + caixasRetiradas + " caixas."
        );
      }
      try {
        Thread.sleep(tempoRetirada * 1000); // Converte o tempo para milissegundos
      } catch (InterruptedException e) {
        // Tratamento de interrupção da thread
        System.out.println("Consumidor interrompido.");
        break;
      }
    }
  }
}

public class Deposito {

  private int items = 0;
  private final int capacidade = 10;

  public synchronized int retirar() {
    while (items == 0) {
      try {
        wait(); // Aguarda até que haja caixas disponíveis para retirar
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    items--;
    System.out.println("Caixa retirada: Sobram " + items + " caixas");
    notifyAll(); // Notifica todas as threads em espera
    return 1;
  }

  public synchronized int armazenar() {
    while (items == capacidade) {
      try {
        wait(); // Aguarda até que haja espaço disponível para armazenar
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    items++;
    System.out.println("Caixa armazenada: Passaram a ser " + items + " caixas");
    notifyAll(); // Notifica todas as threads em espera
    return 1;
  }

  public static void main(String[] args) {
    Deposito dep = new Deposito();
    Produtor p = new Produtor(dep, 2);
    Consumidor c = new Consumidor(dep, 1);
    Thread produtorThread = new Thread(p);
    Thread consumidorThread = new Thread(c);
    produtorThread.start();
    consumidorThread.start();
    System.out.println("Execução do main da classe Deposito terminada!");
  }
}
