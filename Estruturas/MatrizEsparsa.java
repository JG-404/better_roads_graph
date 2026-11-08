package Estruturas;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MatrizEsparsa<X> implements Cloneable{

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

    private class Linha<Y> implements Comparable<Linha<Y>>, Cloneable{

        private class Coluna<Z> implements Comparable<Coluna<Z>>, Cloneable{
            private int num;
            private Z valor;

            public Coluna(int num, Z valor){
                this.num = num;
                this.valor = valor;
            }

            public int getNum() {
                return num;
            }

            public Z getValor() {
                return valor;
            }

            public void setValor(Z valor){
                this.valor = valor;
            }

            @Override
            public int compareTo(Coluna<Z> outro){
                if (this.num > outro.num) return 1;
                if (this.num < outro.num) return -1;

                return 0;
            }

            public Coluna(Coluna<Z> modelo) throws Exception{
                if (modelo == null) throw new Exception("Modelo vazio");

                this.num = modelo.num;
                if (modelo.valor instanceof Cloneable){
                    this.valor = meuCloneDeX(modelo.valor);
                }
                else this.valor = modelo.valor;
            }

            @Override
            public Object clone(){
                Coluna<Z> ret = null;
                
                try{
                    ret = new Coluna<>(this);
                }
                catch(Exception e)
                {}

                return ret;
            }
        } //FIM DA CLASSE COLUNA

        private int num;
        private ArvoreBinariaDeBusca<Coluna<Y>> arvore;
        public ArrayList<Integer> cols;

        public Linha(int num){
            this.num = num;
            this.arvore = new ArvoreBinariaDeBusca<>();
            this.cols = new ArrayList<>();
        }

        public int getNum(){
            return this.num;
        }

        @Override 
        public int compareTo(Linha<Y> outro){
            if (this.num > outro.num) return 1;
            if (this.num < outro.num) return -1;

            return 0;
        }

        public Linha(Linha<Y> modelo) throws Exception{
            if (modelo == null) throw new Exception("Modelo vazio");

            this.num = modelo.num;
            this.arvore = (ArvoreBinariaDeBusca<Coluna<Y>>)modelo.arvore.clone();
            this.cols = (ArrayList<Integer>)modelo.cols.clone();
        }

        @Override
        public Object clone(){
            Linha<Y> ret = null;

            try{
                ret = new Linha<>(this);
            }
            catch(Exception e)
            {}

            return ret;
        }
    } //FIM DA CLASSE LINHA

    private ArvoreBinariaDeBusca<Linha<X>> arvore;
    private X valorPadrao;
    private ArrayList<Integer> lins;

    public MatrizEsparsa(X valorPadrao){
        this.valorPadrao = valorPadrao;
        this.arvore = new ArvoreBinariaDeBusca<>();
        this.lins = new ArrayList<>();
    }

    public void setValor(int lin, int col, X valor) throws Exception{
        if (lin < 0 || col < 0) throw new Exception("Indicies invalidos");

        Linha<X> linhaBuscar = new Linha<>(lin);
        Linha<X> linha = null;
        Linha<X>.Coluna<X> colunaBuscar = new Linha<X>(lin).new Coluna<X>(col, null);
        Linha<X>.Coluna<X> coluna = null;


        try{
            linha = this.arvore.getOItem(linhaBuscar);
            
            try{
                coluna = linha.arvore.getOItem(colunaBuscar);
            }
            catch(Exception e){
                if ((valor == null && this.valorPadrao == null) || (valor != null && valor.equals(this.valorPadrao))) return;
                coluna = linha.new Coluna<X>(col, valor);
                linha.cols.add(coluna.getNum());
                linha.arvore.guardeUmItem(coluna);
                return;
            }
        }
        catch(Exception e){
            if ((valor == null && this.valorPadrao == null) || (valor != null && valor.equals(this.valorPadrao))) return;
            linha = new Linha<>(lin);
            coluna = linha.new Coluna<X>(col, valor);
            this.lins.add(linha.getNum());
            linha.cols.add(coluna.getNum());
            linha.arvore.guardeUmItem(coluna);
            this.arvore.guardeUmItem(linha);
            return;
        }

        
        if ((valor == null && this.valorPadrao == null) || (valor != null && valor.equals(this.valorPadrao))){
            linha.arvore.removaUmItem(coluna);
            linha.cols.remove(Integer.valueOf(coluna.getNum()));
            if (linha.arvore.getQtdDeNodos() == 0){
                this.arvore.removaUmItem(linha);
                this.lins.remove(Integer.valueOf(linha.getNum()));
            }
        }
        else if ((valor == null && coluna.getValor() == null) || (valor != null && valor.equals(coluna.getValor()))) {
            return;
        }
        else {
            coluna.setValor(valor);
        }
        

    }

    public X getValor(int lin, int col) throws Exception{
        if (lin < 0 || col < 0) throw new Exception("Indices invalidos");
        
        Linha<X> linhaBuscar = new Linha<>(lin);
        Linha<X> linha = null;
        Linha<X>.Coluna<X> colunaBuscar = new Linha<X>(lin).new Coluna<X>(col, null);
        Linha<X>.Coluna<X> coluna = null;

        try{
            linha = this.arvore.getOItem(linhaBuscar);
            coluna = linha.arvore.getOItem(colunaBuscar);
            return coluna.getValor();
        }
        catch (Exception e){
            return this.valorPadrao;
        }
    }
    
    @Override
    public String toString(){
        String resultado = "L C V" + "\n";

        for (int i = 0; i < this.lins.size(); i++){
            Linha<X> linha = null;

            try{
                linha = this.arvore.getOItem(new Linha<>(this.lins.get(i)));
            }
            catch(Exception e){
                continue;
            }

            for (int j = 0; j < linha.cols.size(); j++){
                int col = linha.cols.get(j);

                try{
                    resultado += linha.getNum() + " " + col + " " + this.getValor(linha.getNum(), col) + "\n";
                }
                catch(Exception e)
                {}
            }
        }

        

        return resultado;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        MatrizEsparsa<X> outro = (MatrizEsparsa<X>) obj;
        if (this.valorPadrao == null || outro.valorPadrao == null){
            if (this.valorPadrao != outro.valorPadrao) return false;
        }
        else{
            if (!this.valorPadrao.equals(outro.valorPadrao)) return false;
        }
        if (this.lins.size() != outro.lins.size()) return false;
        for (int i = 0; i < this.lins.size(); i++){
            Linha<X> linha = null;
            Linha<X> outraLinha = null;
            int indice = this.lins.get(i);
            if (!outro.lins.contains(indice)) return false;
            try{
                linha = this.arvore.getOItem(new Linha<>(indice));
                outraLinha = outro.arvore.getOItem(new Linha<>(indice));
            }
            catch(Exception e){
                if (linha != null && outraLinha == null) return false;
                try{
                    outraLinha = outro.arvore.getOItem(new Linha<>(indice));
                    return false;
                }
                catch (Exception f){
                    continue;
                }
            }
            if (linha.cols.size() != outraLinha.cols.size()) return false;
            for (int j = 0; j < linha.cols.size(); j++){
                Linha<X>.Coluna<X> coluna = null;
                Linha<X>.Coluna<X> outraColuna = null;
                if (!linha.cols.contains(outraLinha.cols.get(j))) return false;
                int indiceCol = outraLinha.cols.get(j);
                try{
                    coluna = linha.arvore.getOItem(new Linha<X>(indice).new Coluna<X>(indiceCol, null));
                    outraColuna = outraLinha.arvore.getOItem(new Linha<X>(indice).new Coluna<X>(indiceCol, null));

                    if (coluna.getValor() == null || outraColuna.getValor() == null){
                        if (coluna.getValor() != outraColuna.getValor()) return false;
                    }
                    else if (!coluna.getValor().equals(outraColuna.getValor())) return false;
                }
                catch (Exception e){
                    return false;
                }
                
            }
        }
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 1;

        if (valorPadrao != null) ret = ret * 3 + valorPadrao.hashCode();
        
        for (int i = 0; i < this.lins.size(); i++){
            Linha<X> linha = null;
            int indice = this.lins.get(i);
            try{
                linha = this.arvore.getOItem(new Linha<>(indice));
            }
            catch(Exception e)
            {}
            for (int j = 0; j < linha.cols.size(); j++){
                Linha<X>.Coluna<X> coluna = null;
                int indiceCol = linha.cols.get(j);
                try{
                    coluna = linha.arvore.getOItem(new Linha<X>(indice).new Coluna<X>(indiceCol, null));
                }
                catch(Exception e)
                {}
                int hashItem = 1;
                hashItem = hashItem * 3 + ((Integer)indice).hashCode();
                hashItem = hashItem * 3 + ((Integer)indiceCol).hashCode();
                hashItem = hashItem * 3 + (coluna.getValor() == null ? 0 : coluna.getValor().hashCode());

                ret += hashItem;
            }
        }

        if (ret < 0) ret = -ret;
        return ret;
    }

    public MatrizEsparsa(MatrizEsparsa<X> modelo) throws Exception{
        if (modelo == null) throw new Exception("Modelo vazio");
    
        this.arvore = (ArvoreBinariaDeBusca<Linha<X>>)modelo.arvore.clone();
        this.lins = (ArrayList<Integer>)modelo.lins.clone();

        if (modelo.valorPadrao == null) this.valorPadrao = null;
        else if (modelo.valorPadrao instanceof Cloneable) this.valorPadrao = meuCloneDeX(modelo.valorPadrao);
        else this.valorPadrao = modelo.valorPadrao;
    }

    @Override
    public Object clone(){
        MatrizEsparsa<X> ret = null;

        try{
            ret = new MatrizEsparsa<>(this);
        }
        catch(Exception e)
        {}

        return ret;
    }
}
