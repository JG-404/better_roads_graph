public class Road {
    private float comprimento;
    private float custo;
    public float getComprimento() {
        return comprimento;
    }
    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }
    public float getCusto() {
        return custo;
    }
    public void setCusto(float custo) {
        this.custo = custo;
    }
    public Road(float comp,float custo){
        this.comprimento = comp;
        this.custo = custo;
    }

}
