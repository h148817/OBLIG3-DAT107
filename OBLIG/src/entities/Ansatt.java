package entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ansatt", schema = "oblig3_jpa")
@NamedQuery(name = "hentAlleAnsatte", query ="SELECT a FROM Ansatt a")

public class Ansatt {
		@Id     
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id; 
		private String brukernavn;
		private String fornavn;
		private String etternavn;
		private Date ansettelse;
		private String stilling;
		private Integer inntekt;
		//2
		@ManyToOne
		@JoinColumn(name = "avdid", referencedColumnName = "id")
		private Avdeling avdeling;
		
		@OneToMany(mappedBy = "ansatt",fetch = FetchType.EAGER)
		private List<ProsjektDeltakelse> prosjekter;
		
		
		
	public Ansatt() {
	}
	
	public Ansatt(String brukernavn, String fornavn, String etternavn, Date ansettelse, String stilling, int inntekt, Avdeling avdeling) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelse = ansettelse;
		this.stilling = stilling;
		this.inntekt = inntekt;
		this.avdeling = avdeling;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}
	
	public String getBrukernavn() {
		return brukernavn;
	}

	public Avdeling getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	@Override
	public String toString()
	{
		
		
        
		return String.format("ANSATT: [id: %d], [brukernavn: %s], [fornavn: %s], [etternavn%s],"
							+ " [ansettelse: %s], [stilling: %s], [inntekt: %d],"
							+ " [Prosjekt: %s],  [avdeling: %s]",
							id, brukernavn, fornavn, etternavn, ansettelse.toString(),
							stilling, inntekt, prosjekter.toString(), avdeling.getNavn());
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public Date getAnsettelse() {
		return ansettelse;
	}

	public void setAnsettelse(Date ansettelse) {
		this.ansettelse = ansettelse;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public Integer getInntekt() {
		return inntekt;
	}

	public void setInntekt(Integer inntekt) {
		this.inntekt = inntekt;
	}

	public List<ProsjektDeltakelse> getProsjekter() {
		return prosjekter;
	}

	public void setProsjekter(List<ProsjektDeltakelse> prosjekter) {
		this.prosjekter = prosjekter;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
