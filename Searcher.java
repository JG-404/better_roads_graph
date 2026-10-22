import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Searcher {

    public static List<Object> calcularMelhorRotaEntreDoisPontos(Graph<String, Integer> grafo, String cidadeInicial, String cidadeDestino) throws Exception {
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




    public static void imprimirTodasRotas1N(Graph<String, Integer> usGraph, String cidadeInicial, String[] cidades) throws Exception {
            Map<String, Object[]> rotas = calcularMelhorRota(usGraph, cidadeInicial, cidades);
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


    public static Map<String, Object[]> calcularMelhorRota(Graph<String, Integer> grafo ,String cidadeInicial, String[] cidades) throws Exception {
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

    public static Map<String, Object[]> rodarMapaInteiro(Graph<String, Integer> grafo, String cidadeInicial, String[] cidades) throws Exception{
        //TODO
        throw new NoSuchAlgorithmException();
    }

    public static String visitarCidadesArray(Graph<String, Integer> grafo, String cidadeInicial, String[] cidadesParaVisitar)throws Exception{
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

        String resp = "";
        Map<String, Integer> distanciasDaCidadeInicial = new HashMap<>();

        for (String c : cidadesParaVisitar) {
            distanciasDaCidadeInicial.put(c, (int) calcularMelhorRotaEntreDoisPontos(grafo, cidadeInicial, c).get(0));
        }
        

        return resp;
    }
}
