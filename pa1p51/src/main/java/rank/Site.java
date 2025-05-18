package rank;

import java.util.Locale;
import java.util.Objects;

public class Site implements Comparable<Site> {
    private String name;
    private double rank;

    public Site(String name){
        this.name=name;
        this.rank=0;
    }

    public String getName() {
        return name;
    }

    public double getRank() {
        return rank;
    }

    public void addRank(double r){
        this.rank+=r;
    }

    public boolean equals(Object o){
        if(this== o){
            return true;
        }

        return (o instanceof Site site)&&(site.name.equalsIgnoreCase(name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toUpperCase());
    }

    @Override
    //para que se ponga con un punto en el decimal se usa el Locale.UK
    //creo que por defecto usa el Locale.FR que es el de la coma decimal
    public String toString() {
        return String.format(Locale.UK, "%s(%.5f)", this.name, this.rank);
        //siempre es asi %.nf donde n es el numero de decimales
        //% solo para lo que sean variables
        //%s indica tipo string...
        //la parte final ponerlo en el orden de apariencia
    }

    //metodo CompareTo de las string por defecto lo hace lexicograficamente
    //devuelve un -1 si es menor, 0 si es igual y 1 si es mayor
    public int compareTo(Site otherSite){
        return this.name.compareToIgnoreCase(otherSite.name);
    }
    //implements Comparable<Site> en el inicio


}
