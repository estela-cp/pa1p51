package rank;

public class SiteExtended extends Site{
    private boolean valid;

    public SiteExtended(String nombre){
        super(nombre);
        this.valid=true;
    }

    public void setValid(boolean b){
        this.valid=b;
    }

    public boolean isValid(){
        return this.valid;
    }

    @Override
    public String toString() {
        String mensaje= super.toString();
        if(!this.valid){
            return mensaje+"*";
        }
        return mensaje;
    }
}
