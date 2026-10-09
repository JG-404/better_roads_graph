public class Graph<V,A>{
    private V[] vertices;
    private A[][] arestas;

    public Graph(V[] vet) throws Exception{
        if(vet == null)throw new Exception("Vertices não pode ser nulo");
        if(vet.length == 0)throw new Exception("Vertices não pode ser vazio");

        this.vertices = vet;
        this.arestas = (A[][]) new Object[vet.length][vet.length];
    }

    private void redimensionar(int size){
        V[] novoVet = (V[]) new Object[this.vertices.length + size];

        for (int i = 0, j = 0; i < this.vertices.length; i++, j=this.vertices[i]==null?j+0:j+1){
            if (this.vertices[i] == null) continue;
            novoVet[j] = this.vertices[i];
        }

        this.vertices = novoVet;

        A[][] novoAre = (A[][]) new Object[this.vertices.length][this.vertices.length];

        for (int i = 0; i < this.arestas.length; i++){
            for (int j = 0; j < this.arestas[i].length; j++){
                novoAre[i][j] = this.arestas[i][j];
            }
        }

        this.arestas = novoAre;
    }

    public void adicioneVertice(V i) throws Exception{
        if (i == null) throw new Exception("Vertice vazio");

        redimensionar(+1);

        this.vertices[this.vertices.length-1] = i;
    }

    public void removerVertice(V i) throws Exception{
        if (i == null) throw new Exception("Vertice vazio");

        for (int j = 0; j < this.vertices.length; j++){
            if (this.vertices[j].equals(i)) this.vertices[j] = null;
        }

        redimensionar(-1);
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

    public Object[] obterVizinhos(V i) throws Exception{
        if (i == null) throw new Exception("Vertice nulo");
        
        int numVet = -1;

        for (int j = 0; j < this.vertices.length; j++){
            if (this.vertices[j].equals(i)) numVet = j;
        }

        if (numVet < 0) throw new Exception("Vertice não encontrado");

        Object[][] vizinhos = new Object[this.arestas[numVet].length][];

        for (int j = 0; j < this.arestas[numVet].length; j++){
            if (this.arestas[numVet][j] == null) continue;

            vizinhos[j] = new Object[] {this.vertices[j], this.arestas[numVet][j]};
        }

        return vizinhos;
    }
}