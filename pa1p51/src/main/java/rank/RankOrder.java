package rank;

import java.util.Comparator;

public class RankOrder implements Comparator<Site> {//dentro del <> tienes que poner la clase que vas a usar para comparar
    public int compare(Site site1, Site site2){
        int resultado = Double.compare(site1.getRank(), site2.getRank());//orden natural es de menor a mayor (lo queremos al reves pero se hace fuera)
        //int resultado devuelve -1 si es menor, 0 si es igual, 1 si es mayor -> entorno al PRIMER argumento
        //Double.comp... DOUBLE pq rank es double

        if(resultado==0){
            resultado=site2.compareTo(site1);//lo ponemos al reves para q al hacer reversed no nos modifique esto
        }
        return resultado;
    }
}
