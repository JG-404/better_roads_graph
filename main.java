import java.util.*;


public class main {
     public static void main(String[] args) {
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

            imprimirTodasRotas1N(usGraph, cidadeInicial, cidades);
            System.out.println(calcularMelhorRotaEntreDoisPontos(usGraph, cidadeInicial, "Orlando"));

        }catch(Exception e){
            System.err.println("Erro ao inicializar o grafo: " + e.getMessage());
            e.printStackTrace();
        }
         
    }

    private static List<Object> calcularMelhorRotaEntreDoisPontos(Graph<String, Integer> grafo, String cidadeInicial, String cidadeDestino) throws Exception {
        int indexInicial = -1;
        int indexDestino = -1;
        int numVertices = grafo.getVertices().length;

        // 1. Localiza os índices de início e fim no grafo
        for (int i = 0; i < numVertices; i++) {
            if (grafo.getVertices()[i].equals(cidadeInicial)) {
                indexInicial = i;
            }
            if (grafo.getVertices()[i].equals(cidadeDestino)) {
                indexDestino = i;
            }
        }

        if (indexInicial == -1) {
            throw new Exception("Cidade inicial não encontrada no grafo.");
        }
        if (indexDestino == -1) {
            throw new Exception("Cidade de destino não encontrada no grafo.");
        }

        int[] distancias = new int[numVertices];
        int[] predecessores = new int[numVertices];
        boolean[] visitados = new boolean[numVertices];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(predecessores, -1); 
        distancias[indexInicial] = 0;

        while (true) {
            int indexAtual = -1;
            int distanciaAtual = Integer.MAX_VALUE;

            for (int i = 0; i < numVertices; i++) {
                if (!visitados[i] && distancias[i] < distanciaAtual) {
                    distanciaAtual = distancias[i];
                    indexAtual = i;
                }
            }

            
            if (indexAtual == -1 || indexAtual == indexDestino) {
                break;
            }

            visitados[indexAtual] = true;
            String cidadeAtualNome = (String) grafo.getVertices()[indexAtual];

            for (Object c : grafo.obterVizinhos(cidadeAtualNome)) {
                Object[] dadosVizinho = (Object[]) c; 
                if (dadosVizinho == null) continue; 

                String cidadeVizinha = (String) dadosVizinho[0]; 
                Integer pesoAresta = (Integer) dadosVizinho[1];
                int indexVizinho = -1;

                for (int i = 0; i < numVertices; i++) {
                    if (grafo.getVertices()[i].equals(cidadeVizinha)) {
                        indexVizinho = i;
                        break;
                    }
                }

                if (indexVizinho == -1) {
                    throw new Exception("Cidade vizinha não encontrada no grafo.");
                }

                if (pesoAresta != null && distancias[indexAtual] != Integer.MAX_VALUE && distancias[indexAtual] + pesoAresta < distancias[indexVizinho]) {
                    distancias[indexVizinho] = distancias[indexAtual] + pesoAresta;
                    predecessores[indexVizinho] = indexAtual;
                }
            }
        }

        if (distancias[indexDestino] == Integer.MAX_VALUE) {
            throw new Exception("Não existe um trajeto possível entre as cidades informadas.");
        } 
        List<String> caminhoAteDestino = new ArrayList<>();
        int atual = indexDestino;
        while (atual != -1) {
            caminhoAteDestino.add((String) grafo.getVertices()[atual]);
            atual = predecessores[atual];
        }
        Collections.reverse(caminhoAteDestino); 

        
        List<Object> resultado = new ArrayList<>();
        resultado.add(distancias[indexDestino]); 
        resultado.add(caminhoAteDestino);
        

        return resultado;      
    }




    private static void imprimirTodasRotas1N(Graph<String, Integer> usGraph, String cidadeInicial, String[] cidades) throws Exception {
            Map<String, Object[]> rotas = main.calcularMelhorRota(usGraph, cidadeInicial, cidades);
            System.out.println("Melhores rotas a partir de " + cidadeInicial + ":");
            System.out.println("-------------------------------------------------------");
            List<Map.Entry<String, Object[]>> listaOrdenada = new ArrayList<>(rotas.entrySet());
            listaOrdenada.sort((entry1, entry2) -> Integer.compare((Integer) entry1.getValue()[0], (Integer) entry2.getValue()[0]));

            for (Map.Entry<String, Object[]> entry : listaOrdenada) {
                String cidadeDestino = entry.getKey();
                int distanciaTotal = (int) entry.getValue()[0];

                if (cidadeDestino.equals(cidadeInicial)) {
                    System.out.println("Cidade: " + cidadeDestino + ", Distância Total Percorrida: 0 (Origem)");
                } else {
                    System.out.println(
                        cidadeInicial + " ------ " + cidadeDestino + 
                        ", Distância Total Percorrida: " + distanciaTotal
                    );
                }
            }
    }


    private static Map<String, Object[]> calcularMelhorRota(Graph<String, Integer> grafo ,String cidadeInicial, String[] cidades) throws Exception {
        int indexInicial = -1;

        for (int i = 0; i < grafo.getVertices().length; i++) {
            if (grafo.getVertices()[i].equals(cidadeInicial)) {
                indexInicial = i;
                break;
            }
        }

        if (indexInicial == -1) {
            throw new Exception("Cidade inicial não encontrada no grafo.");
        }

        int numVertices = grafo.getVertices().length;
        int[] distancias = new int[numVertices];
        int[] predecessores = new int[numVertices];
        boolean[] visitados = new boolean[numVertices];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(predecessores, -1); 
        distancias[indexInicial] = 0;

        while (true){
            int indexAtual = -1;
            int distanciaAtual = Integer.MAX_VALUE;

            for(int i = 0; i < numVertices; i++){
                if(!visitados[i] && distancias[i] < distanciaAtual){
                    distanciaAtual = distancias[i];
                    indexAtual = i;
                }
            }

            if(indexAtual == -1)break;

            visitados[indexAtual] = true;

            String cidadeAtualNome = (String) grafo.getVertices()[indexAtual];

            for (Object c : grafo.obterVizinhos(cidadeAtualNome)) {
                Object[] dadosVizinho = (Object[]) c; 
                if (dadosVizinho == null) continue; 


                String cidadeVizinha = (String) dadosVizinho[0]; 
                Integer pesoAresta = (Integer) dadosVizinho[1];
                int indexVizinho = -1;

                for (int i = 0; i < numVertices; i++) {
                    if (grafo.getVertices()[i].equals(cidadeVizinha)) {
                        indexVizinho = i;
                        break;
                    }
                }

                if (indexVizinho == -1) {
                    throw new Exception("Cidade vizinha não encontrada no grafo.");
                }

                if (pesoAresta != null && distancias[indexAtual] != Integer.MAX_VALUE && distancias[indexAtual] + pesoAresta < distancias[indexVizinho]) {
                    distancias[indexVizinho] = distancias[indexAtual] + pesoAresta;
                    predecessores[indexVizinho] = indexAtual;
                }

            }
            
        }

        Map<String, Object[]> rotas = new LinkedHashMap<>();

        for (int i = 0; i < numVertices; i++) {
            if (grafo.getVertices()[i] != null) {
                String nomeCidade = (String) grafo.getVertices()[i];
                // Se preferir não retornar cidades inacessíveis (distância infinito), adicione um if aqui
                if (distancias[i] != Integer.MAX_VALUE) {
                    rotas.put(nomeCidade, new Object[]{distancias[i], predecessores[i]});
                }
            }
        }
        return rotas;      
    }
}

        



