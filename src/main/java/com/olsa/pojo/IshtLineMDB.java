
package com.olsa.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

public class IshtLineMDB {
	
	@JsonProperty("Id")
    private String id;
	@JsonProperty("Name")
    private String name;
	@JsonProperty("Ritwik")
    private String ritwik;
	@JsonProperty("Swastyayani")
    private Double swastyayani =0.00d;
	@JsonProperty("Istavrity")
    private Double istavrity=0.00;
	@JsonProperty("Acharyavrity")
    private Double acharyavrity=0.00d;
	@JsonProperty("Dakshina")
    private Double dakshina=0.00d;
	@JsonProperty("Sangathani")
    private Double sangathani=0.00d;
	@JsonProperty("Pronami")
    private Double pronami=0.00d;
	@JsonProperty("Surplus")
    private Double surplus=0.00d;
	@JsonProperty("Ritwiki")
    private Double ritwiki=0.00d;
	@JsonProperty("Parivrity")
	private Double parivrity=0.00d;
	@JsonProperty("Total")
    private Double total=0.00d;
	@JsonProperty("Action")
    private String action;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRitwik() {
        return ritwik;
    }

    public void setRitwik(String ritwik) {
        this.ritwik = ritwik;
    }

    public Double getSwastyayani() {
        return swastyayani;
    }

    public void setSwastyayani(Double swastyayani) {
        this.swastyayani = swastyayani;
    }

    public Double getIstavrity() {
        return istavrity;
    }

    public void setIstavrity(Double istavrity) {
        this.istavrity = istavrity;
    }

    public Double getAcharyavrity() {
        return acharyavrity;
    }

    public void setAcharyavrity(Double acharyavrity) {
        this.acharyavrity = acharyavrity;
    }

    public Double getDakshina() {
        return dakshina;
    }

    public void setDakshina(Double dakshina) {
        this.dakshina = dakshina;
    }

    public Double getSangathani() {
        return sangathani;
    }

    public void setSangathani(Double sangathani) {
        this.sangathani = sangathani;
    }

    public Double getPronami() {
        return pronami;
    }

    public void setPronami(Double pronami) {
        this.pronami = pronami;
    }

    public Double getSurplus() {
        return surplus;
    }

    public void setSurplus(Double surplus) {
        this.surplus = surplus;
    }

    public Double getParivrity() {
        return parivrity;
    }

    public void setParivrity(Double parivrity) {
        this.parivrity= parivrity;
    }
    public Double getRitwiki() {
    	return ritwiki;
    }
    
    public void setRitwiki(Double ritwiki) {
    	this.ritwiki = ritwiki;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public Double lineTotal(){
    	return getSwastyayani()+ getIstavrity()+getAcharyavrity()+getDakshina()+getSangathani()+getPronami()+getSurplus()+getParivrity()+getRitwiki();
    }

}
