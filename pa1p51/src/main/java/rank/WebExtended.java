package rank;

public class WebExtended extends Web {
    public WebExtended(){super();}

    protected void addSiteWithName(String name){
        SiteExtended site= new SiteExtended(name);
            super.addSite(site);
    }

    protected void distribute(Site site, double prize){
        SiteExtended siteEx = (SiteExtended)site;//convierte site en extended
        if(siteEx.isValid())
            super.distribute(site,prize);
    }

    protected void switchSiteWithName(String name){
        Site site= super.getSite(name);
        SiteExtended siteEx= (SiteExtended) site;
        if(siteEx.isValid()) {
            siteEx.setValid(false);
        }else{
            siteEx.setValid(true);
        }
    }
}
