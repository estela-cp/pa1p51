package rank;
import java.util.*;



public class Web {
    private Set<Link> links;//conjunto de links
    protected Set<Site> sites;//conjunto de paginas
    private static final double THRESHOLD= 1E-5;
    private static Random alea= new Random(1);

    public Web(){
        //Set se inicializan con HashSet<>()
        this.links = new HashSet<>();
        this.sites = new HashSet<>();
    }

    protected void addSite(Site site){
        sites.add(site);
    }

    protected void addSiteWithName(String name){
        //crea una pagina con nombre name y la añade con addSite
        Site newsite= new Site(name);
        addSite(newsite);
    }

    public void addLink(String dataLink){
        //dataLink tiene la forma A->B
        if(dataLink==null || dataLink.isEmpty())
            throw new IllegalArgumentException();

        String[] cadena= dataLink.split("->");
        if(cadena.length!=2 || cadena[0].isEmpty() || cadena[1].isEmpty())
            throw new IllegalArgumentException();

        addSiteWithName(cadena[0]);
        addSiteWithName(cadena[1]);

        Link AB= new Link(cadena[0],cadena[1]);
        links.add(AB);
    }

    public Site getSite(String name){
        for(Site site: sites){
            if(site.getName().equalsIgnoreCase(name))
                return site;//devuelve la PAGINA de nombre name
        }
        throw new NoSuchElementException("No hay ninguna pagina con ese nombre");
    }

    public Set<String> getNames(){
        //por si no lo sabes, supongo que si, pero por si, en el set solo se pueden meter elementos no repetidos
        //si intentar meter un elemento que ya esta dentro no se añade

        //creo un nuevo conjunto
        Set<String> nombres= new HashSet<>();

        //recorro el set de Sites, tomo su nombre y lo añado a nombres
        for(Site site: sites){
            nombres.add(site.getName());
        }
        return nombres;
    }

    private Set<Site> getSitesLinkedFrom (Site pagina){
        //dada pagina devuelve conjunto con todas las pag enlazadas a ella

        Set<Site> enlazadas= new HashSet<>();

        for(Link link: links){
            if(link.getOrigin().equals(pagina.getName())){
                enlazadas.add(getSite(link.getLinked()));
            }
        }

        return enlazadas;
    }

    protected void distribute(Site site, double prize){
        double halfprize = prize/2;
        double prizePerLinkedSite;

        if(prize >= THRESHOLD){
            site.addRank(halfprize);
            Set<Site> linkedSites = getSitesLinkedFrom(site);
            if(!linkedSites.isEmpty()) {
                prizePerLinkedSite = halfprize / linkedSites.size();
                for (Site linkedSite : linkedSites) {
                    distribute(linkedSite, prizePerLinkedSite);
                }
            }
        }
    }

    /*protected void distribute(Site site,double prize){
        //si prize es menor q THRESHOLD -> nada
        if(prize>=THRESHOLD){
            //mita de prize aumenta rank del site
            site.addRank(prize/2);
            //la otra mitad se reparte a cada "pagina enlazada desde site" -> prize/2*numPagEnlazadas
            Set<Site> enlazadas= getSitesLinkedFrom(site);
            if(!enlazadas.isEmpty()){
                for(Site paginas: enlazadas){
                    //RECURSIVIDAD: tengo q aumentarle el rank a cada site enlazada a la q esta enlazada a site del argumento
                    //llamo a la funcion de nuevo y hace la mitad  la pagina enlazada a site y la otra mitad  las q están enlazaadas a la enlazada
                    distribute(paginas,prize/2*enlazadas.size());
                }
            }
        }
    }*/

    public void click(String name){
        try{
            distribute(getSite(name),1);
        }catch(NoSuchElementException e){}
    }

    public void simulateClick(int numClick){//seleciona numClick veces aleatoriamente una pagina
        if(!sites.isEmpty()){
            //creamos clase alea de tipo random en los atributos
            //next*Int* (1) devuelve los random int [0,1) *Double...*
            // el orden y los números que aparecen siempre son los mismo para Random(1) -> evita errores de prueba

            //creo una lista con las paginas de la web
            List<Site> listaPaginas= new ArrayList<>(this.sites);
            //luego un for q se haga numClick veces y dentro quermeos q haga click a paginas de forma random
            for(int i=0; i<numClick; i++){
                //con alea.nextInt(size) da un numero entre 0 y size-1
                //para posteriormente hacer click en una pagina de la lista de la posición q salió con el alea
                int numRandom= alea.nextInt(listaPaginas.size());
                click(listaPaginas.get(numRandom).getName());
            }
        }
    }

    public SortedSet<Site> getSitesByName(){
        //las SortedSet se crean como:
        SortedSet<Site> paginasOrdenadas= new TreeSet<>();//SortedSet eb orden normal
        paginasOrdenadas.addAll(sites); //añadimos sites al conjunto ordenado


        //si no se dice nada se ordenan segun el compareTo de Site que era:
        //this.name.compareToIgnoreCase(otherSite.name); <- por nombre y no por rank
        //si quisiese el orden al revecs del original: SortedSet<Site> sites = new TreeSet<>(Comparator.reversedOrder())
        return paginasOrdenadas;
    }

    public SortedSet<Site> getSitesByRank(){
        //0. definimos orden en una clase q implementa a Compare con el nuevo orden
        //1. creo una nueva clase del tipo Compare
        Comparator<Site> orden = new RankOrder().reversed(); //creamos un objeto compare de la clase RankOrder invertido

        SortedSet<Site> ordenadasRank= new TreeSet<>(orden);//al poner(orden) definimos q sigue el orden de "orden"
        ordenadasRank.addAll(sites);//añadimos la lista a ordenar
        return ordenadasRank;
    }

    @Override
    public String toString() {
        return "Web("+sites+", "+links+")";
    }
}
