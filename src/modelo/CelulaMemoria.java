package modelo;
/**
 *
 * @author IgorCamargo
 */
public class CelulaMemoria {
    private Processo processo;

    public CelulaMemoria(){
    }
    
    public CelulaMemoria(Processo processo) {
        this.processo = processo;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }
}
