import java.util.Scanner;

public class Teclado {
    public static String cidadeInicial(){
        Scanner scanner = new Scanner(System.in);
        boolean digitou = false;
        String cidadeInicial = "";
        while(!digitou){
            System.out.println("Escolha uma cidade para começar seu turismo:");
            System.out.println("1 - Miami");
            System.out.println("2 - Los Angeles");
            System.out.println("3 - Chicago");
            System.out.println("4 - New York");
            System.out.println("5 - Dallas");
            System.out.println("6 - Atlanta");

            String escolha = scanner.next();
            switch (escolha) {
                case "1":
                    cidadeInicial = "Miami";
                    digitou = true;
                    break;
                case "2":
                    cidadeInicial = "Los Angeles";
                    digitou = true;
                    break;
                case "3":
                    cidadeInicial = "Chicago";
                    digitou = true;
                    break;
                case "4":
                    cidadeInicial = "New York";
                    digitou = true;
                    break;
                case "5":
                    cidadeInicial = "Dallas";
                    digitou = true;
                    break;
                case "6":
                    cidadeInicial = "Atlanta";
                    digitou = true;
                    break;
                default:
                    System.out.println("Escolha inválida, tente novamente.");

            }
        }

        return cidadeInicial;
    }
}
