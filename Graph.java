public class Graph<V,A>{
    private V[] vertices;
    private A[][] arestas;

    public Graph(V[] vet) throws Exception{
        if(vet == null)throw new Exception("Vertices não pode ser nulo");
        if(vet.length == 0)throw new Exception("Vertices não pode ser vazio");

        this.vertices = vet;
        this.arestas = (A[][])new Object[vet.length][vet.length];
    }

    public void adicioneVertice() throws Exception{

    }

    public void removerVertice() throws Exception{

    }

    public void adicioneAresta(int i, int j, A aresta) throws Exception {
        if (i < 0 || j < 0)throw new Exception("parametro nao pode ser menor do que 0");
        if (i > arestas.length || j > arestas[0].length) throw new Exception("Vertice não existente");
        if (aresta == null) throw new Exception("A aresta não pode ser nula");
        this.arestas[i][j] = aresta;
        this.arestas[j][i] = aresta;
    }

    public void removerAresta(int i, int j) throws Exception{
        if (i < 0 || j < 0)throw new Exception("parametro nao pode ser menor do que 0");
        if (i > arestas.length || j > arestas[0].length) throw new Exception("Vertice não existente");
        this.arestas[i][j] = null;
        this.arestas[j][i] = null;
    }

    public void obterVizinhos() throws Exception{
        
    }
}