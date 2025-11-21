import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        ConversorMonedas conversor = new ConversorMonedas();

        int opcion = 0;

        while (opcion != 7) {
            System.out.println("*****************************");
            System.out.println("  CONVERSOR DE MONEDAS");
            System.out.println("*****************************");
            System.out.println("1) USD  -> ARS");
            System.out.println("2) ARS  -> USD");
            System.out.println("3) USD  -> BRL");
            System.out.println("4) BRL  -> USD");
            System.out.println("5) EUR  -> ARS");
            System.out.println("6) ARS  -> EUR");
            System.out.println("7) Salir");
            System.out.print("Elige una opción: ");

            opcion = teclado.nextInt();

            if (opcion == 7) {
                System.out.println("Saliendo del programa...");
                break;
            }

            System.out.print("Ingresa el monto a convertir: ");
            double monto = teclado.nextDouble();

            String origen = "";
            String destino = "";

            switch (opcion) {
                case 1 -> { origen = "USD"; destino = "ARS"; }
                case 2 -> { origen = "ARS"; destino = "USD"; }
                case 3 -> { origen = "USD"; destino = "BRL"; }
                case 4 -> { origen = "BRL"; destino = "USD"; }
                case 5 -> { origen = "EUR"; destino = "ARS"; }
                case 6 -> { origen = "ARS"; destino = "EUR"; }
                default -> {
                    System.out.println("Opción inválida.");
                    continue;
                }
            }

            try {
                double resultado = conversor.convertir(origen, destino, monto);
                System.out.printf("%.2f %s equivalen a %.2f %s%n",
                        monto, origen, resultado, destino);
            } catch (RuntimeException e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }

            System.out.println();
        }

        teclado.close();
    }
}
