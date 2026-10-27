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
        //TODO
    }

    public X getValor(int lin, int col) throws Exception{
        //TODO
    }
}
