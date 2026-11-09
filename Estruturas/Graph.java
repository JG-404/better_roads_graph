package Estruturas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Graph<V,A> implements Cloneable{
    private V[] vertices;
    private A[][] arestas;

    private<X> X meuCloneDeX (X x)
    {
        X ret=null;

        try
        {
            Class<?> classe         = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo           = classe.getMethod("clone",tipoDosParms);
            Object[] parms          = null;
            ret                     = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException erro)
        {}
        catch(IllegalAccessException erro)
        {}
        catch(InvocationTargetException erro)
        {}

        return ret;
    }

    public Graph(V[] vet) throws Exception{
        if(vet == null)throw new Exception("Vertices não pode ser nulo");
        if(vet.length == 0)throw new Exception("Vertices não pode ser vazio");

        this.vertices = vet;
        this.arestas = (A[][]) new Object[vet.length][vet.length];
    }

    private void redimensionar(int size) {
        int tamanhoAntigo = this.vertices.length;
        int tamanhoNovo = tamanhoAntigo + size;

        V[] novoVet = (V[]) new Object[tamanhoNovo];

        int destino = 0;

        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i] == null)
                continue;

            novoVet[destino] = this.vertices[i];
            destino++;
        }

        A[][] novoAre = (A[][]) new Object[tamanhoNovo][tamanhoNovo];

        int indice = 0;

        for (int i = 0; i < this.arestas.length; i++) {
            if (this.vertices[i] == null) continue;

            int indiceInterno = 0;

            for (int j = 0; j < this.arestas[i].length; j++) {
                if (this.vertices[j] == null) continue;
                novoAre[indice][indiceInterno] = this.arestas[i][j];
                indiceInterno++;
            }

            indice++;
        }

        this.vertices = novoVet;
        this.arestas = novoAre;
    }

    public V[] getVertices() {
        return vertices;
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

    public A[][] getArestas() {
        return arestas;
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

    @Override
    public String toString() {
        String ret = "Grafo {\n";
        ret += "  Vertices: [";

        boolean primeiro = true;

        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i] == null) continue;

            if (!primeiro) ret += ", ";

            ret += this.vertices[i];

            primeiro = false;
        }

        ret += "]\n";
        ret += "  Arestas: [";

        primeiro = true;

        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i] == null) continue;

            for (int j = i; j < this.vertices.length; j++) {
                if (this.vertices[j] == null) continue;

                A aresta = this.arestas[i][j];

                if (aresta == null) continue;

                if (!primeiro) ret += ", ";

                ret += "(" + this.vertices[i] + " -- "
                        + this.vertices[j] + ": " + aresta + ")";

                primeiro = false;
            }
        }

        ret += "]\n";
        ret += "}";

        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Graph<V, A> outro = (Graph<V, A>) obj;

        int qtdThis = 0;
        int qtdOutro = 0;

        for (V v : this.vertices)
            if (v != null) qtdThis++;

        for (V v : outro.vertices)
            if (v != null) qtdOutro++;

        if (qtdThis != qtdOutro) return false;

        int[] mapa = new int[this.vertices.length];
        boolean[] usados = new boolean[outro.vertices.length];

        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i] == null) continue;

            int encontrado = -1;

            for (int j = 0; j < outro.vertices.length; j++) {
                if (outro.vertices[j] == null || usados[j]) continue;

                if (this.vertices[i].equals(outro.vertices[j])) {
                    encontrado = j;
                    break;
                }
            }

            if (encontrado == -1) return false;

            mapa[i] = encontrado;
            usados[encontrado] = true;
        }

        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i] == null) continue;

            for (int j = i; j < this.vertices.length; j++) {
                if (this.vertices[j] == null) continue;

                A arestaThis = this.arestas[i][j];
                A arestaOutro = outro.arestas[mapa[i]][mapa[j]];

                if (arestaThis == null) {
                    if (arestaOutro != null) return false;
                }
                else if (!arestaThis.equals(arestaOutro)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 1;

        int hashVertices = 0;
        int hashArestas = 0;

        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i] == null) continue;

            hashVertices += this.vertices[i].hashCode();
        }

        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i] == null) continue;

            for (int j = i; j < this.vertices.length; j++) {
                if (this.vertices[j] == null) continue;

                A aresta = this.arestas[i][j];

                if (aresta == null) continue;

                int hashExtremos =
                    this.vertices[i].hashCode() +
                    this.vertices[j].hashCode();

                int hashAresta = 1;

                hashAresta += 3 + hashExtremos;
                hashAresta += 3 + aresta.hashCode();

                hashArestas += hashAresta;
            }
        }

        ret = ret * 3 + hashVertices;
        ret = ret * 3 + hashArestas;

        if (ret < 0) ret = -ret;
        return ret;
    }

    public Graph(Graph<V, A> modelo) throws Exception{
        if (modelo == null) throw new Exception("Modelo vazio");

        this.vertices = (V[]) new Object[modelo.vertices.length];
        this.arestas = (A[][]) new Object[modelo.arestas.length][modelo.arestas.length];

        
        for (int i = 0; i < modelo.vertices.length; i++){
            if (modelo.vertices[i] instanceof Cloneable){
                this.vertices[i] = this.meuCloneDeX(modelo.vertices[i]);
            }
            else{
                this.vertices[i] = modelo.vertices[i];
            }
        }

        for (int i = 0; i < modelo.arestas.length; i++){
            for (int j = i; j < modelo.arestas[i].length; j++){
                if (modelo.arestas[i][j] instanceof Cloneable){
                    this.arestas[i][j] = this.meuCloneDeX(modelo.arestas[i][j]);
                }
                else{
                    this.arestas[i][j] = modelo.arestas[i][j];
                }
                this.arestas[j][i] = this.arestas[i][j];
            }
        }
    }

    public Object clone(){
        Graph<V, A> ret = null;

        try{
            ret = new Graph<>(this);
        }
        catch(Exception e)
        {}

        return ret;
    }
}