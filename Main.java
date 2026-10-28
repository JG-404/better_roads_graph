import java.util.Scanner;

import Estruturas.Graph;

public class Main {
     public static void main(String[] args) {
        String cidadeInicial = Teclado.cidadeInicial();
        System.out.println("Iniciando calculo de rotas a partir de " + cidadeInicial + "...");

        String[] cidades = {
            "Seattle",          // 0
            "Portland",         // 1
            "Boise",            // 2
            "Lake Tahoe",       // 3
            "San Francisco",    // 4
            "Los Angeles",      // 5
            "San Diego",        // 6
            "Palm Springs",     // 7
            "Las Vegas",        // 8
            "Grand Canyon",     // 9
            "Phoenix",          // 10
            "Salt Lake City",   // 11
            "Yellowstone",      // 12
            "Denver",           // 13
            "Vail",             // 14
            "Aspen",            // 15
            "Colorado Springs", // 16
            "Minneapolis",      // 17
            "Omaha",            // 18
            "Kansas City",      // 19
            "Dallas",           // 20
            "San Antonio",      // 21
            "Houston",          // 22
            "New Orleans",      // 23
            "Milwaukee",        // 24
            "Chicago",          // 25
            "St. Louis",        // 26
            "Little Rock",      // 27
            "Memphis",          // 28
            "Detroit",          // 29
            "Indianapolis",     // 30
            "Nashville",        // 31
            "Atlanta",          // 32
            "Albany",           // 33
            "Boston",           // 34
            "New York",         // 35
            "Washington",       // 36
            "Richmond",         // 37
            "Orlando",          // 38
            "Fort Lauderdale",  // 39
            "Miami"             // 40
        }; 

        try{
            Graph<String, Integer> usGraph = new Graph<String, Integer>(cidades);

            usGraph.adicioneAresta(0, 1, 17);    // Seattle - Portland
            usGraph.adicioneAresta(0, 2, 50);    // Seattle - Boise
            usGraph.adicioneAresta(1, 2, 42);    // Portland - Boise
            usGraph.adicioneAresta(1, 3, 58);    // Portland - Lake Tahoe
            usGraph.adicioneAresta(1, 4, 69);    // Portland - San Francisco
            usGraph.adicioneAresta(4, 3, 30);    // San Francisco - Lake Tahoe
            usGraph.adicioneAresta(4, 5, 38);    // San Francisco - Los Angeles
            usGraph.adicioneAresta(3, 2, 42);    // Lake Tahoe - Boise
            usGraph.adicioneAresta(3, 11, 52);   // Lake Tahoe - Salt Lake City
            usGraph.adicioneAresta(3, 8, 78);    // Lake Tahoe - Las Vegas
            usGraph.adicioneAresta(5, 6, 12);    // Los Angeles - San Diego
            usGraph.adicioneAresta(5, 7, 16);    // Los Angeles - Palm Springs
            usGraph.adicioneAresta(5, 8, 27);    // Los Angeles - Las Vegas
            usGraph.adicioneAresta(6, 7, 14);    // San Diego - Palm Springs
            usGraph.adicioneAresta(6, 10, 35);   // San Diego - Phoenix
            usGraph.adicioneAresta(7, 8, 29);    // Palm Springs - Las Vegas
            usGraph.adicioneAresta(7, 9, 44);    // Palm Springs - Grand Canyon
            usGraph.adicioneAresta(7, 10, 36);   // Palm Springs - Phoenix
            usGraph.adicioneAresta(8, 11, 42);   // Las Vegas - Salt Lake City
            usGraph.adicioneAresta(8, 9, 47);    // Las Vegas - Grand Canyon
            usGraph.adicioneAresta(9, 11, 66);   // Grand Canyon - Salt Lake City
            usGraph.adicioneAresta(9, 10, 11);   // Grand Canyon - Phoenix
            usGraph.adicioneAresta(2, 12, 67);   // Boise - Yellowstone
            usGraph.adicioneAresta(12, 17, 134); // Yellowstone - Minneapolis
            usGraph.adicioneAresta(12, 13, 93);  // Yellowstone - Denver
            usGraph.adicioneAresta(11, 14, 53);  // Salt Lake City - Vail
            usGraph.adicioneAresta(14, 15, 8);   // Vail - Aspen
            usGraph.adicioneAresta(14, 13, 16);  // Vail - Denver
            usGraph.adicioneAresta(15, 13, 17);  // Aspen - Denver
            usGraph.adicioneAresta(15, 16, 18);  // Aspen - Colorado Springs
            usGraph.adicioneAresta(13, 16, 12);  // Denver - Colorado Springs
            usGraph.adicioneAresta(13, 18, 54);  // Denver - Omaha
            usGraph.adicioneAresta(13, 20, 73);  // Denver - Dallas
            usGraph.adicioneAresta(10, 20, 107); // Phoenix - Dallas
            usGraph.adicioneAresta(10, 21, 99);  // Phoenix - San Antonio
            usGraph.adicioneAresta(21, 20, 28);  // San Antonio - Dallas
            usGraph.adicioneAresta(21, 22, 31);  // San Antonio - Houston
            usGraph.adicioneAresta(22, 20, 25);  // Houston - Dallas
            usGraph.adicioneAresta(22, 23, 53);  // Houston - New Orleans
            usGraph.adicioneAresta(17, 18, 38);  // Minneapolis - Omaha
            usGraph.adicioneAresta(17, 24, 34);  // Minneapolis - Milwaukee
            usGraph.adicioneAresta(17, 25, 41);  // Minneapolis - Chicago
            usGraph.adicioneAresta(18, 19, 19);  // Omaha - Kansas City
            usGraph.adicioneAresta(18, 25, 47);  // Omaha - Chicago
            usGraph.adicioneAresta(19, 26, 25);  // Kansas City - St. Louis
            usGraph.adicioneAresta(19, 20, 55);  // Kansas City - Dallas
            usGraph.adicioneAresta(24, 25, 9);   // Milwaukee - Chicago
            usGraph.adicioneAresta(25, 29, 28);  // Chicago - Detroit
            usGraph.adicioneAresta(25, 30, 18);  // Chicago - Indianapolis
            usGraph.adicioneAresta(25, 26, 30);  // Chicago - St. Louis
            usGraph.adicioneAresta(29, 33, 64);  // Detroit - Albany
            usGraph.adicioneAresta(29, 36, 53);  // Detroit - Washington
            usGraph.adicioneAresta(33, 34, 17);  // Albany - Boston
            usGraph.adicioneAresta(33, 35, 15);  // Albany - New York
            usGraph.adicioneAresta(33, 36, 65);  // Albany - Washington
            usGraph.adicioneAresta(34, 35, 21);  // Boston - New York
            usGraph.adicioneAresta(35, 36, 24);  // New York - Washington
            usGraph.adicioneAresta(36, 30, 70);  // Washington - Indianapolis
            usGraph.adicioneAresta(36, 37, 20);  // Washington - Richmond
            usGraph.adicioneAresta(30, 26, 25);  // Indianapolis - St. Louis
            usGraph.adicioneAresta(30, 31, 29);  // Indianapolis - Nashville
            usGraph.adicioneAresta(26, 27, 29);  // St. Louis - Little Rock
            usGraph.adicioneAresta(26, 28, 40);  // St. Louis - Memphis
            usGraph.adicioneAresta(27, 28, 14);  // Little Rock - Memphis
            usGraph.adicioneAresta(27, 20, 32);  // Little Rock - Dallas
            usGraph.adicioneAresta(28, 31, 21);  // Memphis - Nashville
            usGraph.adicioneAresta(28, 32, 39);  // Memphis - Atlanta
            usGraph.adicioneAresta(31, 37, 71);  // Nashville - Richmond
            usGraph.adicioneAresta(31, 32, 24);  // Nashville - Atlanta
            usGraph.adicioneAresta(37, 32, 56);  // Richmond - Atlanta
            usGraph.adicioneAresta(20, 32, 79);  // Dallas - Atlanta
            usGraph.adicioneAresta(32, 23, 47);  // Atlanta - New Orleans
            usGraph.adicioneAresta(32, 38, 44);  // Atlanta - Orlando
            usGraph.adicioneAresta(23, 38, 64);  // New Orleans - Orlando
            usGraph.adicioneAresta(23, 40, 86);  // New Orleans - Miami
            usGraph.adicioneAresta(38, 39, 18);  // Orlando - Fort Lauderdale
            usGraph.adicioneAresta(38, 40, 23);  // Orlando - Miami
            usGraph.adicioneAresta(39, 40, 3);   // Fort Lauderdale - Miami

            Scanner scanner = new Scanner(System.in);
            boolean digitou = false;
            while(!digitou){
                System.out.println("Escolha um processo para executar:");
                System.out.println("1 - Ir a uma cidade X");
                System.out.println("2 - Inserir uma nova cidade");
                System.out.println("2 - Inserir uma nova estrada");
                System.out.println("4 - Viajar com limite de Km");
                System.out.println("5 - Viajar de X ate Y");

                String escolha = scanner.next();
                switch (escolha) {
                    case "1":
                        while(!digitou){
                            System.out.println("Digite o nome da cidade: ");
                            String city = scanner.next();
                            usGraph.adicioneVertice(city);


                            System.out.println("Agora crie as estradas conectadas a essa cidade(ao minimo 2)");

                            boolean cidadeExistente = false;
                            while(!cidadeExistente){
                                System.out.println("Digite para qual cidade a estrada vai: ");
                                String cidadeDest = scanner.next();
                                for(String c : usGraph.getVertices()){
                                    if(cidadeDest == c){
                                      cidadeExistente = true;
                                    }
                                }
                            }

                            System.out.println("Digite quantos km tem a cidade: ");
                            int tamanho = scanner.nextInt();
                            tamanho = tamanho/10;
                            usGraph.adicioneAresta(usGraph.getVertices().length, 40, 3);


                            digitou = true;  
                        }
                    System.out.println(Searcher.calcularMelhorRotaEntreDoisPontos(usGraph, cidadeInicial, "Orlando"));
                    
                    break;
                case "2":
                    
                    digitou = true;
                    break;
                case "3":
                    
                    digitou = true;
                    break;
                case "4":
                    
                    digitou = true;
                    break;
                case "5":
                    
                    digitou = true;
                    break;
                default:
                    System.out.println("Escolha inválida, tente novamente.");
                }
            }
                

            String escolha = scanner.next();

            Searcher.imprimirTodasRotas1N(usGraph, cidadeInicial, cidades);
            


        }catch(Exception e){
            System.err.println("Erro ao inicializar o grafo: " + e.getMessage());
            e.printStackTrace();
        }         
    }
}
 



