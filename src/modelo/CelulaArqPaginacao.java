package modelo;

/**
 *
 * @author IgorCamargo
 */
public class CelulaArqPaginacao {
    private Processo processo;

    public CelulaArqPaginacao(){
    }
    
    public CelulaArqPaginacao(Processo processo) {
        this.processo = processo;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }
}
