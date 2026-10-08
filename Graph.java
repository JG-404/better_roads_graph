import java.io.EOFException;

public class Graph<V,A>{
    private V[] verticies;
    private A[][] arestas;

    public Graph(V[] vet) throws Exception{
        if(vet == null)throw new Exception("Vertices não pode ser nulo");
        if(vet.length == 0)throw new Exception("Vertices não pode ser vazio");

        this.verticies = vet;
        this.arestas = (A[][] )new Object[vet.length][vet.length];
    }

    public void adicioneVertice() throws Exception{

    }
    public void removerVertice() throws Exception{

    }
    public void adicioneAresta(int i, int j, A distancia) throws Exception {
        if(i < 0 || j < 0)throw new Exception("parametro nao pode ser menor do que 0");
            this.arestas[i][j] = distancia;
            this.arestas[j][i] = distancia; 
    }
    public void removerAresta() throws Exception{

    }
    public void obterVizinhos() throws Exception{
        
    }
}