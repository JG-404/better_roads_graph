package Estruturas;
import java.lang.reflect.*;

@SuppressWarnings("unchecked")
public class ArvoreBinariaDeBusca<X extends Comparable<X>> implements Cloneable
{
    private class No
    {
        private No esq;
        private X  info;
        private No dir;
        
        @SuppressWarnings("unused")
        public No (No e, X i, No d)
        {
            this.esq  = e;
            this.info = i;
            this.dir  = d;
        }

        public No (X i)
        {
            this.esq  = null;
            this.info = i;
            this.dir  = null;
        }

        public No getEsq ()
        {
            return this.esq;
        }

        public X getInfo ()
        {
            return this.info;
        }

        public No getDir ()
        {
            return this.dir;
        }

        public void setEsq (No e)
        {
            this.esq = e;
        }
        
        public void setInfo (X i)
        {
            this.info = i;
        }

        public void setDir (No d)
        {
            this.dir = d;
        }
    } //fim da classe No

    private No raiz;

    public ArvoreBinariaDeBusca(){
        //construtor vazio
    }
    
    private X meuCloneDeX (X x)
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
    
    public void guardeUmItem (X i) throws Exception
    {
        if (i==null) throw new Exception ("Informacao ausente");
        
        if (this.raiz==null)
        {
            if (i instanceof Cloneable) i=this.meuCloneDeX(i);
            this.raiz = new No (i);
            return;
        }
        
        No atual=this.raiz;
        for(;;) // forever
        {
            int comparacao=i.compareTo(atual.getInfo());
            
            if (comparacao==0) throw new Exception ("Elemento repetido");
            
            if (comparacao<0)
            {
                if (atual.getEsq()==null)
                {
                    if (i instanceof Cloneable) i=this.meuCloneDeX(i);
                    atual.setEsq (new No (i));
                    return;
                }
                else
                    atual=atual.getEsq();
            }
            else // comparacao>0
            {
                if (atual.getDir()==null)
                {
                    if (i instanceof Cloneable) i=this.meuCloneDeX(i);
                    atual.setDir (new No (i));
                    return;
                }
                else
                    atual=atual.getDir();
            }
        }
    }
    
    public boolean temOItem (X i) throws Exception
    {
		if (i==null) throw new Exception ("Informacao ausente");
		
		if (this.raiz==null) return false;
		
		No atual=this.raiz;
		while (atual!=null)
		{
			int comparacao=i.compareTo(atual.getInfo());
			if (comparacao==0) return true;
			if (comparacao<0)
			    atual=atual.getEsq();
			else // comparacao>0
			    atual=atual.getDir();
		}
		return false;
	}

    public X getOItem(X i) throws Exception{
        if (i == null) throw new Exception("Parametro invalido");

        No atual = this.raiz;

        while (atual != null) {
            int comparacao = i.compareTo(atual.getInfo());

            if (comparacao == 0){
                if (atual instanceof Cloneable) return this.meuCloneDeX(atual.getInfo());
                return atual.getInfo();
            } 
            if (comparacao < 0) atual = atual.getEsq();
            else atual = atual.getDir();
        }

        throw new Exception("Item inexistente");
    }
	
	private int getQtdDeNodos (No r)
	{
		if (r==null) return 0;
		
		return getQtdDeNodos(r.getEsq())+1+getQtdDeNodos(r.getDir());
	}
	
    public int getQtdDeNodos ()
    {
		return getQtdDeNodos (this.raiz);
	}
	
	public void removaUmItem (X i) throws Exception
    {
		if (i==null) throw new Exception ("Informacao ausente");

        if (this.raiz == null) throw new Exception("Arvore vazia");

        No atual = this.raiz;
        No pai = null;
        boolean filhoEsquerdo = true;

        for(;;) // forever
        {
            int comparacao = i.compareTo(atual.getInfo());
            if (comparacao==0) break;
     
            pai = atual;
            if (comparacao<0)
            {
                atual=atual.getEsq();
                filhoEsquerdo=true;
            }
            else // comparacao>0
            {
                atual=atual.getDir();
                filhoEsquerdo=false;
            }
            
            if (atual==null) throw new Exception ("Remocao de algo inexistente");
        }

        // se a info for encontrada numa folha, deslique a folha da árvore,
        // fazendo o ponteiro que aponta para ela dentro do seu nó pai,
        // tornar-se null
        if (atual.getEsq()==null && atual.getDir()==null)
        {
            if (atual==this.raiz)
                this.raiz=null;
            else if (filhoEsquerdo)
                pai.setEsq(null);
            else
                pai.setDir(null);
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if (atual.getDir()==null && filhoEsquerdo)
        {
            if (atual==this.raiz)
                this.raiz=atual.getEsq();
            else
                pai.setEsq(atual.getEsq());
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho direito de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if (atual.getDir()==null && !filhoEsquerdo)
        {
            if (atual==this.raiz)
                this.raiz=atual.getEsq();
            else
                pai.setDir(atual.getEsq());
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à direita, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na direita de N
        else if (atual.getEsq()==null && filhoEsquerdo)
        {
            if (atual==this.raiz)
                this.raiz=atual.getDir();
            else
                pai.setEsq(atual.getDir());
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à direita, e sendo N filho direita de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na direita de N
        else if (atual.getEsq()==null && !filhoEsquerdo)
        {
            if (atual==this.raiz)
                this.raiz=atual.getDir();
            else
                pai.setDir(atual.getDir());
        }
        // se info for encontrada num nó N, que não é folha e tem 2 filhos,
        // encontre a informação info que existe à extrema esquerda da
        // subarvore direita de N ou à extrema direita da subarvore esquerda
        // de N; remova o nó que contém info e substitua dentro do nó N,
        // a informação que ali se encontra por info
        else
        {
            No substituto = null;
            pai=atual;
            if (getQtdDeNodos(atual.getEsq())>getQtdDeNodos(atual.getDir())) {
                substituto = atual.getEsq();
                filhoEsquerdo = true;
                while (substituto.getDir() != null)
                {
                    pai = substituto;
                    substituto = substituto.getDir();
                    filhoEsquerdo = false;
                }
            }
            else
            {
                substituto = atual.getDir();
                filhoEsquerdo = false;
                while (substituto.getEsq() != null)
                {
                    pai = substituto;
                    substituto = substituto.getEsq();
                    filhoEsquerdo = true;
                }
            }
            if (filhoEsquerdo){
                pai.setEsq(substituto.getDir());
            }
            else{
                pai.setDir(substituto.getEsq());
            }

            atual.setInfo(substituto.getInfo());
        }
	}

	private int getAltura (No r)
	{
		if (r == null) return 0;

        int alturaEsq = getAltura(r.getEsq());
        int alturaDir = getAltura(r.getDir());

        int maior = 1 + (alturaEsq > alturaDir ? alturaEsq : alturaDir);

        return maior;
	}
	
	public int getAltura ()
	{

        return getAltura(this.raiz);
	}
	
	private boolean isBalanceada (No r)
	{
        if (r == null) return true;

        int tamanhoEsq = getAltura(r.getEsq());
        int tamanhoDir = getAltura(r.getDir());

        if (tamanhoEsq - tamanhoDir > 1 || tamanhoEsq - tamanhoDir < -1){
            return false;
        }

        return isBalanceada(r.getEsq()) && isBalanceada(r.getDir());
    }
    
	public boolean isBalanceada ()
	{
        return isBalanceada(this.raiz);
    }
    
	private void balanceieSe (No r)
	{
        if (r==null) return;

		int qtdEsq = this.getQtdDeNodos(r.getEsq()),
		    qtdDir = this.getQtdDeNodos(r.getDir());
		    
		while (Math.abs(qtdEsq-qtdDir)>1)
			if (qtdEsq>qtdDir)
            {
				// giro para a direita, giro no sentido horário
				No pai   = r;
				No atual = r.getEsq();
				while (atual.getDir() != null)
				{
					pai   = atual;
					atual = atual.getDir();
				}
				X infoRaiz = r.getInfo();
				r.setInfo(atual.getInfo());
				pai.setDir(atual.getEsq());
				qtdEsq--;
                try{
                    this.guardeUmItem(infoRaiz);
                }
				catch(Exception e)
                {}
				qtdDir++;
			}
			else // qtdDir>qtdEsq
            {
				// giro para a esquerda, giro no sentido antihorário
				No pai   = r;
				No atual = r.getDir();
				while (atual.getEsq() != null)
				{
					pai   = atual;
					atual = atual.getEsq();
				}
				X infoRaiz = r.getInfo();
				r.setInfo(atual.getInfo());
				pai.setEsq(atual.getDir());
				qtdDir--;
				try{
                    this.guardeUmItem(infoRaiz);
                }
				catch(Exception e)
                {}
				qtdEsq++;
			}
			
		this.balanceieSe(r.getEsq());
		this.balanceieSe(r.getDir());		
	}

	public void balanceieSe ()
	{
		this.balanceieSe(this.raiz);
	}
    /*
    // implemente os 4 métodos abaixo, imagiando
    // que a árvore não é uma árvore binária
    // DE BUSCA.
    
	private boolean isEspelho (No r1, No r2)
	{
		// faça
	}    
    
    public boolean isEspelho (ArvoreBinariaDeBusca<X> arv)
	{
		// faça
	}
	
	private void espelheSe (No r)
	{
	    // faça
	}
	
	public void espelheSe ()
	{
	    // faça
	}
	*/

	@Override
	public boolean equals (Object obj)
	{
		if (obj==this) return true;
		if (obj==null) return false;
		if (obj.getClass()!=this.getClass()) return false;
		ArvoreBinariaDeBusca<X> arv = (ArvoreBinariaDeBusca<X>)obj;
		return equals(this.raiz,arv.raiz);
    }
	
	private boolean equals (No raizArv1, No raizArv2)
	{
	    if (raizArv1==null && raizArv2==null) return true;
	    if (raizArv1==null && raizArv2!=null) return false;
	    if (raizArv1!=null && raizArv2==null) return false;
	    if (!raizArv1.getInfo().equals(raizArv2.getInfo())) return false;
	    /*
	    return equals(raizArv1.getEsq(),raizArv2.getEsq()) &&
	           equals(raizArv1.getDir(),raizArv2.getDir());
	    */
	    if (!equals(raizArv1.getEsq(),raizArv2.getEsq())) return false;
	    if (!equals(raizArv1.getDir(),raizArv2.getDir())) return false;
	    return true;
	}
	
    private String toString(No r){
        if (r == null) return "";

        return "(" + toString(r.getEsq()) + " " + r.getInfo() + " " + toString(r.getDir()) + ")";
    }

    @Override
    public String toString(){
        return toString(this.raiz);
    }

    private int hashCode(No r){
        if (r == null) return 0;

        int ret = 1;

        ret = ret * 3 + r.getInfo().hashCode();
        ret = ret * 3 + hashCode(r.getEsq());
        ret = ret * 3 + hashCode(r.getDir());

        if (ret < 0) ret = -ret;

        return ret;
    }

    @Override
    public int hashCode(){
        return hashCode(this.raiz);
    }

    @Override
    public Object clone(){
        ArvoreBinariaDeBusca<X> ret = null;

        try{
            ret = new ArvoreBinariaDeBusca<>(this);
        }
        catch (Exception e)
        {} // Sei que nunca vai cair no catch pq this nunca é null

        return ret;
    }

    public ArvoreBinariaDeBusca(ArvoreBinariaDeBusca<X> modelo) throws Exception{
        if (modelo == null) throw new Exception("Modelo vazio");

        copiador(modelo.raiz);
    }

    private void copiador(No modelo) throws Exception{
        if (modelo == null) return;

        this.guardeUmItem(modelo.getInfo());
        copiador(modelo.getEsq());
        copiador(modelo.getDir());
    }

	// faça os demais métodos obrigatórios incluindo os "implements" apropriados
} // fim da classe ArvoreBinariaDeBusca
