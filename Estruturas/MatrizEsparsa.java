package Estruturas;
import java.util.ArrayList;

public class MatrizEsparsa<X> {
    private class Linha<Y> implements Comparable<Linha<Y>>{

        private class Coluna<Z> implements Comparable<Coluna<Z>>{
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
        } //FIM DA CLASSE COLUNA

        private int num;
        private ArvoreBinariaDeBusca<Coluna<Y>> arvore;
        public ArrayList<Integer> cols;

        public Linha(int num){
            this.num = num;
            this.arvore = new ArvoreBinariaDeBusca<>();
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
    } //FIM DA CLASSE LINHA

    private ArvoreBinariaDeBusca<Linha<X>> arvore;
    private X valorPadrao;
    private ArrayList<Integer> lins;

    public MatrizEsparsa(X valor){
        this.valorPadrao = valor;
        this.arvore = new ArvoreBinariaDeBusca<>();
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
                if (valor.equals(this.valorPadrao)) return;
                coluna = new Linha<X>(lin).new Coluna<X>(col, valor);
                linha.arvore.guardeUmItem(coluna);
                return;
            }
        }
        catch(Exception e){
            if (valor.equals(this.valorPadrao)) return;
            linha = new Linha<>(lin);
            coluna = new Linha<X>(lin).new Coluna<X>(col, valor);
            linha.arvore.guardeUmItem(coluna);
            this.arvore.guardeUmItem(linha);
        }

        /*
        if (valor.equals(this.valorPadrao)){
            linha.arvore.removaUmItem(coluna);
            this.arvore.
        }
        */

    }

    public X getValor(int lin, int col) throws Exception{
        //TODO
    }
}
