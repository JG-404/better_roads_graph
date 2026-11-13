import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Estruturas.Graph;

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

        StringBuilder apresentacao = new StringBuilder();

        apresentacao.append("\n");
        apresentacao.append("========================================\n");
        apresentacao.append("             MELHOR ROTA                \n");
        apresentacao.append("========================================\n\n");

        apresentacao.append("  Origem: ").append(cidadeInicial).append("\n");
        apresentacao.append("  Destino: ").append(cidadeDestino).append("\n\n");

        apresentacao.append("  Trajeto:\n");

        for (int i = 0; i < caminhoAteDestino.size(); i++) {
            apresentacao.append("  ")
                        .append(i + 1)
                        .append(". ")
                        .append(caminhoAteDestino.get(i));

            if (i < caminhoAteDestino.size() - 1) {
                apresentacao.append("\n       |\n       v\n");
            }
        }

        apresentacao.append("\n\n----------------------------------------\n");
        apresentacao.append("  Distancia total: ")
                    .append(distancias[indexDestino])
                    .append(" km\n");
        apresentacao.append("  Cidades percorridas: ")
                    .append(caminhoAteDestino.size())
                    .append("\n");
        apresentacao.append("  Estradas utilizadas: ")
                    .append(caminhoAteDestino.size() - 1)
                    .append("\n");
        apresentacao.append("========================================\n");

        resultado.add(apresentacao.toString());

        

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

    public static List<Object> visitarCidadesArray(
            Graph<String, Integer> grafo,
            String cidadeInicial,
            String[] cidadesParaVisitar) throws Exception {

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

        for (String cidade : cidadesParaVisitar) {
            boolean encontrada = false;

            for (String vertice : grafo.getVertices()) {
                if (vertice.equals(cidade)) {
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                throw new Exception("Cidade não encontrada: " + cidade);
            }
        }

        StringBuilder resp = new StringBuilder();

        List<String> cidadesPendentes = new ArrayList<>(
                Arrays.asList(cidadesParaVisitar)
        );

        // Evita visitar novamente a cidade inicial
        cidadesPendentes.removeIf(c -> c.equals(cidadeInicial));

        String cidadeAtual = cidadeInicial;
        int distanciaTotal = 0;
        int contador = 1;

        resp.append("\n========================================\n");
        resp.append("          ROTEIRO DE VIAGEM              \n");
        resp.append("========================================\n\n");

        resp.append("Origem: ").append(cidadeInicial).append("\n\n");
        resp.append("TRAJETO:\n");
        resp.append("  ").append(contador).append(". ").append(cidadeAtual);

        while (!cidadesPendentes.isEmpty()) {

            String cidadeMaisProxima = null;
            int menorDistancia = Integer.MAX_VALUE;

            // Procura a cidade pendente mais próxima da cidade atual
            for (String cidade : cidadesPendentes) {

                List<Object> resultado = calcularMelhorRotaEntreDoisPontos(
                        grafo, cidadeAtual, cidade
                );

                int distancia = (int) resultado.get(0);

                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    cidadeMaisProxima = cidade;
                }
            }

            if (cidadeMaisProxima == null) {
                throw new Exception("Não foi possível encontrar uma próxima cidade.");
            }

            distanciaTotal += menorDistancia;

            contador++;

            resp.append("\n       |\n       v\n");
            resp.append("  ").append(contador).append(". ")
                .append(cidadeMaisProxima);

            cidadesPendentes.remove(cidadeMaisProxima);

            cidadeAtual = cidadeMaisProxima;
        }

        resp.append("\n\n----------------------------------------\n");
        resp.append("Distância total: ").append(distanciaTotal).append(" km\n");
        resp.append("Cidades visitadas: ").append(contador).append("\n");
        resp.append("Estradas entre visitas: ").append(contador - 1).append("\n");
        resp.append("========================================\n");

        List<Object> resultado = new ArrayList<>();
        resultado.add(distanciaTotal); 
        resultado.add(resp.toString());

        return resultado;
    }



    public static Map<String, Object[]> rodarMapaInteiro(Graph<String, Integer> grafo, String cidadeInicial, String[] cidades) throws Exception{
        //TODO
        throw new NoSuchAlgorithmException();
    }

    public static List<Object> viajarComLimiteDeDistancia(Graph<String, Integer> grafo, String cidadeInicial, int limiteDistancia) throws Exception {
        int indexInicial = -1;
        limiteDistancia = limiteDistancia / 10;

        for (int i = 0; i < grafo.getVertices().length; i++) {
            if (grafo.getVertices()[i].equals(cidadeInicial)) {
                indexInicial = i;
                break;
            }
        }

        if (indexInicial == -1) {
            throw new Exception("Cidade inicial não encontrada no grafo.");
        }

        
        List<String> cidades = new ArrayList<>();
        boolean[] visitados = new boolean[grafo.getVertices().length];
        int distanciaTotal = 0;
        String cidadeAtualNome = (String) grafo.getVertices()[indexInicial];

        cidades.add(cidadeAtualNome);
        visitados[indexInicial] = true;

        while(distanciaTotal < limiteDistancia){
            String cidadeMaisProxima = null;
            int indexMaisProximo = -1;
            int menorDistancia = Integer.MAX_VALUE;

            for (Object c : grafo.obterVizinhos(cidadeAtualNome)) {
                Object[] dadosVizinho = (Object[]) c;
                if (dadosVizinho == null) continue;

                String cidadeVizinha = (String) dadosVizinho[0];
                Integer pesoAresta = (Integer) dadosVizinho[1];
                if (pesoAresta == null) continue;

                int indexVizinho = -1;
                for (int i = 0; i < grafo.getVertices().length; i++) {
                    if (cidadeVizinha.equals(grafo.getVertices()[i])) {
                        indexVizinho = i;
                        break;
                    }
                }

                if (indexVizinho != -1 && !visitados[indexVizinho]) {
                    if (distanciaTotal + pesoAresta <= limiteDistancia/2 && pesoAresta < menorDistancia) {
                        menorDistancia = pesoAresta;
                        cidadeMaisProxima = cidadeVizinha;
                        indexMaisProximo = indexVizinho;
                    }
                }
            }

            if(cidadeMaisProxima == null){
                break; 
            }

            visitados[indexMaisProximo] = true;
            cidades.add(cidadeMaisProxima);
            cidadeAtualNome = cidadeMaisProxima;
            distanciaTotal += menorDistancia;

        }

        distanciaTotal *= 10;  
        limiteDistancia *= 10; 

        StringBuilder apresentacao = new StringBuilder();

        apresentacao.append("\n");
        apresentacao.append("========================================\n");
        apresentacao.append("             MELHOR ROTA                \n");
        apresentacao.append("========================================\n\n");

        apresentacao.append("  Origem: ").append(cidadeInicial).append("\n");
        apresentacao.append("  Limite de Distância: ").append(limiteDistancia).append(" km\n");

        apresentacao.append("  Trajeto(caminho de ida e volta):\n");

        for (int i = 0; i < cidades.size(); i++) {
            apresentacao.append("  ")
                        .append(i + 1)
                        .append(". ")
                        .append(cidades.get(i));

            if (i < cidades.size() - 1) {
                
                apresentacao.append("\n       - ^\n       | |\n       v -\n");
            }
        }

        apresentacao.append("\n\n----------------------------------------\n");
        apresentacao.append("  Distancia de mão unica: ")
                    .append(distanciaTotal)
                    .append(" km\n");
        apresentacao.append("  Distancia Total: ")
                    .append(distanciaTotal*2)
                    .append(" km\n");
        apresentacao.append("  Cidades percorridas: ")
                    .append(cidades.size())
                    .append("\n");
        apresentacao.append("  Estradas utilizadas: ")
                    .append((cidades.size() - 1)*2)
                    .append("\n");
        apresentacao.append("========================================\n");

        List<Object> res = new ArrayList<>();
        res.add(distanciaTotal);
        res.add(cidades);
        res.add(apresentacao.toString());
        return res;
    }
}
