package Estruturas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class GrafoLista<V,A> implements Cloneable{
    private class Aresta implements Cloneable {
        private int destino;
        private A valor;

        public Aresta(int destino, A valor) {
            this.destino = destino;
            this.valor = valor;
        }

        public int getDestino() {
            return destino;
        }

        public void setDestino(int destino) {
            this.destino = destino;
        }

        public A getValor() {
            return valor;
        }

        public void setValor(A valor) {
            this.valor = valor;
        }
    }

    private V[] vertices;
    private ArrayList<ArrayList<Aresta>> arestas;
    
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

    public GrafoLista(V[] vet) throws Exception{
        if(vet == null)throw new Exception("Vertices não pode ser nulo");
        if(vet.length == 0)throw new Exception("Vertices não pode ser vazio");

        this.vertices = vet;
        this.arestas = new ArrayList<>();

        for (int i = 0; i < vet.length; i++) {
            this.arestas.add(new ArrayList<>());
        }
    }

    private Aresta buscarAresta(int origem, int destino) {
        for (Aresta aresta : this.arestas.get(origem)) {
            if (aresta.getDestino() == destino)
                return aresta;
        }

        return null;
    }


    public V[] getVertices() {
        return vertices;
    }

    public void adicioneVertice(V i) throws Exception {
        if (i == null) throw new Exception("Vertice vazio");

        V[] novoVet = java.util.Arrays.copyOf(this.vertices, this.vertices.length + 1);
        
        for (int j = 0; j < this.vertices.length; j++)
            novoVet[j] = this.vertices[j];

        novoVet[novoVet.length - 1] = i;
        this.vertices = novoVet;
        this.arestas.add(new ArrayList<>());
    }

    public void removerVertice(V vertice) throws Exception {
        int indice = -1;

        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i].equals(vertice)) {
                indice = i;
                break;
            }
        }

        if (indice == -1)throw new Exception("Vértice não encontrado!");

        for (int i = 0; i < this.arestas.size(); i++) {
            if (i != indice) {
                ArrayList<Aresta> lista = this.arestas.get(i);
                for (int j = lista.size() - 1; j >= 0; j--) {
                    if (lista.get(j).getDestino() == indice)
                        lista.remove(j);
                }
            }
        }

        this.arestas.remove(indice);

        for (ArrayList<Aresta> lista : this.arestas) {
            for (Aresta aresta : lista) {
                if (aresta.getDestino() > indice)
                    aresta.setDestino(aresta.getDestino() - 1);
            }
        }

        V[] novosVertices = java.util.Arrays.copyOf(this.vertices, this.vertices.length - 1);

        for (int i = 0, j = 0; i < this.vertices.length; i++) {
            if (i != indice)novosVertices[j++] = this.vertices[i];
        }

        this.vertices = novosVertices;
    }

    public void adicioneAresta(int i, int j, A aresta) throws Exception {
        if (i < 0 || j < 0)throw new Exception("Parametro nao pode ser menor do que 0");
        if (i >= this.vertices.length || j >= this.vertices.length)throw new Exception("Vertice não existente");
        if (this.vertices[i] == null || this.vertices[j] == null)throw new Exception("Vertice não existente");
        if (aresta == null) throw new Exception("A aresta não pode ser nula");

        Aresta existente = buscarAresta(i, j);

        if (existente != null) {
            existente.setValor(aresta);
            buscarAresta(j, i).setValor(aresta);
            return;
        }

        this.arestas.get(i).add(new Aresta(j, aresta));
        if (i != j) this.arestas.get(j).add(new Aresta(i, aresta));
    }

    public void removerAresta(int i, int j) throws Exception {
        if (i < 0 || j < 0)throw new Exception("Parametro nao pode ser menor do que 0");
        if (i >= this.vertices.length || j >= this.vertices.length) throw new Exception("Vertice não existente");
        if (this.vertices[i] == null || this.vertices[j] == null)throw new Exception("Vertice não existente");

        Aresta aresta1 = buscarAresta(i, j);
        Aresta aresta2 = buscarAresta(j, i);
        if (aresta1 != null)this.arestas.get(i).remove(aresta1);
        if (aresta2 != null) this.arestas.get(j).remove(aresta2);
    }

    public Object[] obterVizinhos(V i) throws Exception {
        if (i == null)throw new Exception("Vertice nulo");
        int numVet = -1;

        for (int j = 0; j < this.vertices.length; j++) {
            if (this.vertices[j] != null && this.vertices[j].equals(i)) {
                numVet = j;
                break;
            }
        }
        if (numVet < 0)throw new Exception("Vertice não encontrado");

        Object[] vizinhos = new Object[this.arestas.get(numVet).size()];

        for (int j = 0; j < this.arestas.get(numVet).size(); j++) {
            Aresta aresta = this.arestas.get(numVet).get(j);
            vizinhos[j] = new Object[] {this.vertices[aresta.getDestino()],aresta.getValor()};
        }

        return vizinhos;
    }

    @Override
    public String toString() {
        String ret = "Grafo {\n";
        ret += "  Vertices: [";

        boolean primeiro = true;

        for (V vertice : this.vertices) {
            if (vertice == null)
                continue;

            if (!primeiro)
                ret += ", ";

            ret += vertice;
            primeiro = false;
        }

        ret += "]\n";
        ret += "  Arestas: [";

        primeiro = true;

        for (int i = 0; i < this.arestas.size(); i++) {
            for (Aresta aresta : this.arestas.get(i)) {

                int j = aresta.getDestino();

                if (i > j) continue;

                if (!primeiro)ret += ", ";

                ret += "(" + this.vertices[i] + " -- "
                    + this.vertices[j] + ": " + aresta.getValor() + ")";

                primeiro = false;
            }
        }

        ret += "]\n}";
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        GrafoLista<V, A> outro = (GrafoLista<V, A>) obj;

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
            if (this.vertices[i] == null)continue;

            for (Aresta aresta : this.arestas.get(i)) {
                int destino = aresta.getDestino();
                Aresta outraAresta = outro.buscarAresta(
                    mapa[i],
                    mapa[destino]
                );

                if (outraAresta == null)return false;
                if (!aresta.getValor().equals(outraAresta.getValor()))return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 1;
        int hashVertices = 0;
        int hashArestas = 0;

        for (V vertice : this.vertices) {
            if (vertice != null)
                hashVertices += vertice.hashCode();
        }

        for (int i = 0; i < this.arestas.size(); i++) {
            for (Aresta aresta : this.arestas.get(i)) {

                int destino = aresta.getDestino();

                if (i <= destino) {
                    int hashOrigem = this.vertices[i].hashCode();
                    int hashDestino = this.vertices[destino].hashCode();

                    int hashExtremos = hashOrigem ^ hashDestino;

                    int hashAresta =
                        31 * hashExtremos + aresta.getValor().hashCode();

                    hashArestas += hashAresta;
                }
            }
        }
        ret = ret * 3 + hashVertices;
        ret = ret * 3 + hashArestas;

        if (ret < 0) ret = -ret;
        return ret;
    }


    public GrafoLista(GrafoLista<V, A> modelo) throws Exception {
        if (modelo == null)throw new Exception("Modelo vazio");

        this.vertices = java.util.Arrays.copyOf(modelo.vertices, modelo.vertices.length);

        for (int i = 0; i < modelo.vertices.length; i++) {
            if (modelo.vertices[i] == null)continue;
            if (modelo.vertices[i] instanceof Cloneable) this.vertices[i] = meuCloneDeX(modelo.vertices[i]);
            else this.vertices[i] = modelo.vertices[i];
        }

        this.arestas = new ArrayList<>();

        for (int i = 0; i < modelo.arestas.size(); i++) {
            ArrayList<Aresta> novaLista = new ArrayList<>();

            for (Aresta aresta : modelo.arestas.get(i)) {
                A valor = aresta.getValor();
                if (valor instanceof Cloneable)valor = meuCloneDeX(valor);

                novaLista.add(new Aresta(aresta.getDestino(), valor));
            }

            this.arestas.add(novaLista);
        }
    }

    public Object clone(){
        GrafoLista<V, A> ret = null;

        try{
            ret = new GrafoLista<>(this);
        }
        catch(Exception e)
        {}

        return ret;
    }
}
