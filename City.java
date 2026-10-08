public class City {
    private String nome;
    private float area;
    private int populacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public City(String nome,float area,int populacao){
        this.nome = nome;
        this.area = area;
        this.populacao = populacao;
    }
}
