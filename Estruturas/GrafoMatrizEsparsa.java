package Estruturas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class GrafoMatrizEsparsa<V,A> implements Cloneable{
    private V[] vertices;
    private MatrizEsparsa<A> arestas;

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

    public GrafoMatrizEsparsa(V[] vet) throws Exception{
        if(vet == null)throw new Exception("Vertices não pode ser nulo");
        if(vet.length == 0)throw new Exception("Vertices não pode ser vazio");

        this.vertices = vet;
        this.arestas =  new MatrizEsparsa<A>((A)null);
    }

    private void redimensionar(int size) {
        int tamanhoNovo = this.vertices.length + size;

        V[] novoVet = Arrays.copyOf(this.vertices, tamanhoNovo);
        Arrays.fill(novoVet, null);

        MatrizEsparsa<A> novoAre = new MatrizEsparsa<A>((A) null);

        int[] mapa = new int[this.vertices.length];

        for (int i = 0; i < mapa.length; i++)
            mapa[i] = -1;

        int destino = 0;

        try{

            for (int i = 0; i < this.vertices.length; i++) {
                    if (this.vertices[i] == null)continue;
    
                    novoVet[destino] = this.vertices[i];
                    mapa[i] = destino;
                    destino++;
                }
    
                for (int i = 0; i < this.vertices.length; i++) {
                if (mapa[i] == -1)
                    continue;
    
                for (int j = i; j < this.vertices.length; j++) {
                    if (mapa[j] == -1)
                        continue;
    
                    A aresta = this.arestas.getValor(i, j);
    
                    if (aresta != null) {
                            novoAre.setValor(mapa[i], mapa[j], aresta);
                            novoAre.setValor(mapa[j], mapa[i], aresta);
                    }
                }
            }
    
            this.vertices = novoVet;
            this.arestas = novoAre;
        }catch(Exception e)
        {}
    }

    public V[] getVertices() {
        return vertices;
    }

    public void adicioneVertice(V i) throws Exception{
        if (i == null) throw new Exception("Vertice vazio");

        redimensionar(+1);

        this.vertices[this.vertices.length-1] = i;
    }


    public void removerVertice(V i) throws Exception {
        if (i == null)
            throw new Exception("Vertice vazio");

        int posicao = -1;

        for (int j = 0; j < this.vertices.length; j++) {
            if (this.vertices[j] != null && this.vertices[j].equals(i)) {
                posicao = j;
                break;
            }
        }

        if (posicao == -1)
            throw new Exception("Vertice não encontrado");

        this.vertices[posicao] = null;

        redimensionar(-1);
    }


    public MatrizEsparsa<A> getArestas() {
        return (MatrizEsparsa<A>)this.arestas.clone();
    }

    public void adicioneAresta(int i, int j, A aresta) throws Exception {
        if (i < 0 || j < 0)throw new Exception("parametro nao pode ser menor do que 0");
        if (i >= vertices.length || j >= vertices.length)throw new Exception("Vertice não existente");
        if (this.vertices[i] == null || this.vertices[j] == null)throw new Exception("Vertice não existente");
        if (aresta == null) throw new Exception("A aresta não pode ser nula");
        this.arestas.setValor(i, j, aresta);
        this.arestas.setValor(j, i, aresta);
    }

    public void removerAresta(int i, int j) throws Exception{
        if (i < 0 || j < 0)throw new Exception("parametro nao pode ser menor do que 0");
        if (i >= vertices.length || j >= vertices.length) throw new Exception("Vertice não existente");
        if (this.vertices[i] == null || this.vertices[j] == null)throw new Exception("Vertice não existente");
        this.arestas.setValor(i, j, null);
        this.arestas.setValor(j, i, null);
    }

    public Object[] obterVizinhos(V i) throws Exception{
        if (i == null) throw new Exception("Vertice nulo");
        
        int numVet = -1;

        for (int j = 0; j < this.vertices.length; j++) {
            if (this.vertices[j] != null && this.vertices[j].equals(i)) {
                numVet = j;
                break;
            }
        }

        if (numVet < 0) throw new Exception("Vertice não encontrado");

        Object[] vizinhos = new Object[this.vertices.length];

        for (int j = 0; j < this.vertices.length; j++){
            A aresta = this.arestas.getValor(numVet, j);

            if (aresta == null) continue;

            vizinhos[j] = new Object[] { this.vertices[j], aresta};
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
        try{
            for (int i = 0; i < this.vertices.length; i++) {
                if (this.vertices[i] == null) continue;
    
                for (int j = i; j < this.vertices.length; j++) {
                    if (this.vertices[j] == null) continue;
    
                    A aresta = this.arestas.getValor(i, j);
    
                    if (aresta == null) continue;
    
                    if (!primeiro) ret += ", ";
    
                    ret += "(" + this.vertices[i] + " -- "
                            + this.vertices[j] + ": " + aresta + ")";
    
                    primeiro = false;
                }
            }
        }catch(Exception e)
            {}
        
        ret += "]\n";
        ret += "}";

        return ret;

       
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        GrafoMatrizEsparsa<V, A> outro = (GrafoMatrizEsparsa<V, A>) obj;

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
        try{
            for (int i = 0; i < this.vertices.length; i++) {
                if (this.vertices[i] == null) continue;
    
                for (int j = i; j < this.vertices.length; j++) {
                    if (this.vertices[j] == null) continue;
    
                    A arestaThis = this.arestas.getValor(i, j);
                    A arestaOutro = outro.arestas.getValor(mapa[i], mapa[j]);
    
                    if (arestaThis == null) {
                        if (arestaOutro != null) return false;
                    }
                    else if (!arestaThis.equals(arestaOutro)) {
                        return false;
                    }
                }
            }

        }
        catch(Exception e)
        {}

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
        try{
            for (int i = 0; i < this.vertices.length; i++) {
                if (this.vertices[i] == null) continue;
    
                for (int j = i; j < this.vertices.length; j++) {
                    if (this.vertices[j] == null) continue;
    
                    A aresta = this.arestas.getValor(i, j);
    
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

        }catch(Exception e) 
        {}

        ret = ret * 3 + hashVertices;
        ret = ret * 3 + hashArestas;

        if (ret < 0) ret = -ret;
        return ret;
    }

    public GrafoMatrizEsparsa(GrafoMatrizEsparsa<V, A> modelo) throws Exception{
        if (modelo == null) throw new Exception("Modelo vazio");

        this.vertices = modelo.vertices.clone();
        this.arestas = new MatrizEsparsa<A>((A) null);

        
        for (int i = 0; i < modelo.vertices.length; i++){
            if (modelo.vertices[i] == null)continue;
            if (modelo.vertices[i] instanceof Cloneable){
                this.vertices[i] = this.meuCloneDeX(modelo.vertices[i]);
            }
            else{
                this.vertices[i] = modelo.vertices[i];
            }
        }

        for (int i = 0; i < modelo.vertices.length; i++){
            for (int j = i; j < modelo.vertices.length; j++){
                if (modelo.arestas.getValor(i, j) instanceof Cloneable){
                    this.arestas.setValor(i, j, this.meuCloneDeX(modelo.arestas.getValor(i, j)));
                }
                else{
                    this.arestas.setValor(i, j, modelo.arestas.getValor(i, j));
                }
                this.arestas.setValor(j, i, this.arestas.getValor(i, j));
            }
        }
    }

    public Object clone(){
        GrafoMatrizEsparsa<V, A> ret = null;

        try{
            ret = new GrafoMatrizEsparsa<>(this);
        }
        catch(Exception e)
        {}

        return ret;
    }
}