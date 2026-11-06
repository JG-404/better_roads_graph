import java.util.Scanner;
import Estruturas.Graph;

public class Main {

    public static int buscarCidade(Graph<String, Integer> grafo, String nome) {
        String[] vertices = grafo.getVertices();
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equalsIgnoreCase(nome.trim())) return i;
        }
        return -1;
    }

    private static int lerDistancia(Scanner scanner) {
        while (true) {
            System.out.print("Distância da estrada em km: ");
            if (scanner.hasNextInt()) {
                int distancia = scanner.nextInt();
                scanner.nextLine();
                if (distancia > 0) return distancia;
            } else {
                scanner.nextLine();
            }
            System.out.println("Digite uma distância inteira maior que zero.");
        }
    }

    public static void main(String[] args) {
        String cidadeInicial = Teclado.cidadeInicial();
        System.out.println("Iniciando cálculo de rotas a partir de " + cidadeInicial + "...");

        String[] cidades = {
            "Seattle", "Portland", "Boise", "Lake Tahoe", "San Francisco",
            "Los Angeles", "San Diego", "Palm Springs", "Las Vegas",
            "Grand Canyon", "Phoenix", "Salt Lake City", "Yellowstone",
            "Denver", "Vail", "Aspen", "Colorado Springs", "Minneapolis",
            "Omaha", "Kansas City", "Dallas", "San Antonio", "Houston",
            "New Orleans", "Milwaukee", "Chicago", "St. Louis", "Little Rock",
            "Memphis", "Detroit", "Indianapolis", "Nashville", "Atlanta",
            "Albany", "Boston", "New York", "Washington", "Richmond",
            "Orlando", "Fort Lauderdale", "Miami"
        };

        try {
            Graph<String, Integer> usGraph = new Graph<>(cidades);

            // Estradas originais
            usGraph.adicioneAresta(0, 1, 17);
            usGraph.adicioneAresta(0, 2, 50);
            usGraph.adicioneAresta(1, 2, 42);
            usGraph.adicioneAresta(1, 3, 58);
            usGraph.adicioneAresta(1, 4, 69);
            usGraph.adicioneAresta(4, 3, 30);
            usGraph.adicioneAresta(4, 5, 38);
            usGraph.adicioneAresta(3, 2, 42);
            usGraph.adicioneAresta(3, 11, 52);
            usGraph.adicioneAresta(3, 8, 78);
            usGraph.adicioneAresta(5, 6, 12);
            usGraph.adicioneAresta(5, 7, 16);
            usGraph.adicioneAresta(5, 8, 27);
            usGraph.adicioneAresta(6, 7, 14);
            usGraph.adicioneAresta(6, 10, 35);
            usGraph.adicioneAresta(7, 8, 29);
            usGraph.adicioneAresta(7, 9, 44);
            usGraph.adicioneAresta(7, 10, 36);
            usGraph.adicioneAresta(8, 11, 42);
            usGraph.adicioneAresta(8, 9, 47);
            usGraph.adicioneAresta(9, 11, 66);
            usGraph.adicioneAresta(9, 10, 11);
            usGraph.adicioneAresta(2, 12, 67);
            usGraph.adicioneAresta(12, 17, 134);
            usGraph.adicioneAresta(12, 13, 93);
            usGraph.adicioneAresta(11, 14, 53);
            usGraph.adicioneAresta(14, 15, 8);
            usGraph.adicioneAresta(14, 13, 16);
            usGraph.adicioneAresta(15, 13, 17);
            usGraph.adicioneAresta(15, 16, 18);
            usGraph.adicioneAresta(13, 16, 12);
            usGraph.adicioneAresta(13, 18, 54);
            usGraph.adicioneAresta(13, 20, 73);
            usGraph.adicioneAresta(10, 20, 107);
            usGraph.adicioneAresta(10, 21, 99);
            usGraph.adicioneAresta(21, 20, 28);
            usGraph.adicioneAresta(21, 22, 31);
            usGraph.adicioneAresta(22, 20, 25);
            usGraph.adicioneAresta(22, 23, 53);
            usGraph.adicioneAresta(17, 18, 38);
            usGraph.adicioneAresta(17, 24, 34);
            usGraph.adicioneAresta(17, 25, 41);
            usGraph.adicioneAresta(18, 19, 19);
            usGraph.adicioneAresta(18, 25, 47);
            usGraph.adicioneAresta(19, 26, 25);
            usGraph.adicioneAresta(19, 20, 55);
            usGraph.adicioneAresta(24, 25, 9);
            usGraph.adicioneAresta(25, 29, 28);
            usGraph.adicioneAresta(25, 30, 18);
            usGraph.adicioneAresta(25, 26, 30);
            usGraph.adicioneAresta(29, 33, 64);
            usGraph.adicioneAresta(29, 36, 53);
            usGraph.adicioneAresta(33, 34, 17);
            usGraph.adicioneAresta(33, 35, 15);
            usGraph.adicioneAresta(33, 36, 65);
            usGraph.adicioneAresta(34, 35, 21);
            usGraph.adicioneAresta(35, 36, 24);
            usGraph.adicioneAresta(36, 30, 70);
            usGraph.adicioneAresta(36, 37, 20);
            usGraph.adicioneAresta(30, 26, 25);
            usGraph.adicioneAresta(30, 31, 29);
            usGraph.adicioneAresta(26, 27, 29);
            usGraph.adicioneAresta(26, 28, 40);
            usGraph.adicioneAresta(27, 28, 14);
            usGraph.adicioneAresta(27, 20, 32);
            usGraph.adicioneAresta(28, 31, 21);
            usGraph.adicioneAresta(28, 32, 39);
            usGraph.adicioneAresta(31, 37, 71);
            usGraph.adicioneAresta(31, 32, 24);
            usGraph.adicioneAresta(37, 32, 56);
            usGraph.adicioneAresta(20, 32, 79);
            usGraph.adicioneAresta(32, 23, 47);
            usGraph.adicioneAresta(32, 38, 44);
            usGraph.adicioneAresta(23, 38, 64);
            usGraph.adicioneAresta(23, 40, 86);
            usGraph.adicioneAresta(38, 39, 18);
            usGraph.adicioneAresta(38, 40, 23);
            usGraph.adicioneAresta(39, 40, 3);

            Scanner scanner = new Scanner(System.in);
            boolean executou = true;

            while (executou) {
                System.out.println("\nEscolha um processo para executar:");
                System.out.println("1 - Inserir uma nova cidade");
                System.out.println("2 - Inserir uma nova estrada");
                System.out.println("3 - Ir da cidade inicial até uma cidade X");
                System.out.println("4 - Viajar com limite de Km");
                System.out.println("5 - Viajar de X até Y");
                System.out.println("6 - Sair do programa");
                System.out.print("Opção: ");

                String escolha = scanner.nextLine().trim();

                switch (escolha) {
                    case "1": {
                        System.out.print("Nome da nova cidade: ");
                        String novaCidade = scanner.nextLine().trim();

                        if (novaCidade.isEmpty() || buscarCidade(usGraph, novaCidade) != -1) {
                            System.out.println("Nome vazio ou cidade já cadastrada.");
                            break;
                        }

                        usGraph.adicioneVertice(novaCidade);
                        int indiceNovaCidade = usGraph.getVertices().length - 1;
                        int estradas = 0;

                        while (estradas < 2) {
                            System.out.print("Digite uma cidade já cadastrada para conectar: ");
                            String destino = scanner.nextLine().trim();
                            int indiceDestino = buscarCidade(usGraph, destino);

                            if (indiceDestino == -1 || indiceDestino == indiceNovaCidade) {
                                System.out.println("Cidade inválida. Tente novamente.");
                                continue;
                            }

                            int distancia = lerDistancia(scanner);
                            usGraph.adicioneAresta(indiceNovaCidade, indiceDestino, distancia);
                            estradas++;
                            System.out.println("Estrada cadastrada.");
                        }

                        System.out.println("Cidade e estradas adicionadas com sucesso!");
                        
                        break;
                    }

                    case "2": {
                        System.out.print("Cidade de origem: ");
                        String origem = scanner.nextLine().trim();
                        int indiceOrigem = buscarCidade(usGraph, origem);

                        if (indiceOrigem == -1) {
                            System.out.println("Cidade de origem não encontrada.");
                            break;
                        }

                        System.out.print("Cidade de destino: ");
                        String destino = scanner.nextLine().trim();
                        int indiceDestino = buscarCidade(usGraph, destino);

                        if (indiceDestino == -1 || indiceDestino == indiceOrigem) {
                            System.out.println("Cidade de destino inválida.");
                            break;
                        }

                        int distancia = lerDistancia(scanner);
                        usGraph.adicioneAresta(indiceOrigem, indiceDestino, distancia);
                        System.out.println("Estrada adicionada com sucesso!");
                        
                        break;
                    }

                    case "3": {
                        if (buscarCidade(usGraph, cidadeInicial) == -1) {
                            System.out.println("A cidade inicial não está cadastrada no grafo.");
                            break;
                        }

                        System.out.print("Cidade de destino: ");
                        String destino = scanner.nextLine().trim();

                        if (buscarCidade(usGraph, destino) == -1) {
                            System.out.println("Cidade não encontrada.");
                            break;
                        }

                        System.out.println(Searcher.calcularMelhorRotaEntreDoisPontos(
                            usGraph, cidadeInicial, destino
                        ).get(2));
                        
                        break;
                    }

                    case "4": {
                        int distancia = 0;
                        while (true) {
                            System.out.print("Distância em km a ser percorrida: ");
                            if (scanner.hasNextInt()) {
                                distancia = scanner.nextInt();
                                scanner.nextLine();
                                if (distancia > 0) {break;} else{
                                    System.out.println("Digite uma distância inteira maior que zero.");
                                }
                            } else {
                                scanner.nextLine();
                            }
                        }
                        /*
                        System.out.println(Searcher.calcularMelhorRotaComLimite(
                            usGraph, cidadeInicial, distancia
                        ));
                        break;
                        */
                    }

                    case "5": {
                        System.out.print("Cidade de partida: ");
                        String origem = scanner.nextLine().trim();

                        if (buscarCidade(usGraph, origem) == -1) {
                            System.out.println("Cidade de origem não encontrada.");
                            break;
                        }

                        System.out.print("Cidade de destino: ");
                        String destino = scanner.nextLine().trim();

                        if (buscarCidade(usGraph, destino) == -1) {
                            System.out.println("Cidade de destino não encontrada.");
                            break;
                        }

                        System.out.println(Searcher.calcularMelhorRotaEntreDoisPontos(
                            usGraph, origem, destino
                        ).get(2));
                        
                        break;
                    }

                    case "6":
                        System.out.println("Encerrando o programa...");
                        executou = false;
                        break;

                    default:
                        System.out.println("Opção inválida. Escolha de 1 a 5.");
                    
                    }
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Se deseja continuar, escolha outra opção. Caso contrário, digite 6 para sair."); 
            }

            scanner.close();

        } catch (Exception e) {
            System.err.println("Erro ao executar o programa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
