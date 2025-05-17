package rank;

import java.util.Objects;

public class Link {
    private String origin;//nombre de la pagina del hipervinculo
    private String linked;//nombre de la pagina a la q se enlaza

    public Link(String origin, String linked){
        this.origin=origin;
        this.linked=linked;
    }

    public String getLinked() {
        return linked;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o)
            return true;
        return (o instanceof Link newLink)&&(newLink.linked.equalsIgnoreCase(linked))&&(newLink.origin.equalsIgnoreCase(origin));
    }

    @Override
    public int hashCode() {
        return Objects.hash(linked.toUpperCase(),origin.toUpperCase());
    }

    @Override
    public String toString() {
        return this.origin+"->"+this.linked;
    }


}
