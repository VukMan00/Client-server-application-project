package registracija_prijava;
import java.util.GregorianCalendar;

public class Korisnik{
	
	String korisnickoIme;
	String sifra;
	String ime;
	String prezime;
	String JMBG;
	String pol;
	String email;
	String vakcina1;
	String vakcina2;
	String vakcina3;
	String primio1;
	String primio2;
	String primio3;
	GregorianCalendar datum1;
	GregorianCalendar datum2;
	GregorianCalendar datum3;
	String[] mesec = {"Januar" , "Februar" , "Mart" , "April","Maj","Jun","Jul","Avgust","Septembar","Oktobar","Novembar","Decembar"};
	
	public GregorianCalendar getDatum1() {
		return datum1;
	}

	public void setDatum1(GregorianCalendar datum1) {
		this.datum1 = datum1;
	}

	public GregorianCalendar getDatum2() {
		return datum2;
	}

	public void setDatum2(GregorianCalendar datum2) {
		this.datum2 = datum2;
	}

	public GregorianCalendar getDatum3() {
		return datum3;
	}

	public void setDatum3(GregorianCalendar datum3) {
		this.datum3 = datum3;
	}


	public String getVakcina1() {
		return vakcina1;
	}

	public void setVakcina1(String vakcina1) {
		this.vakcina1 = vakcina1;
	}

	public String getVakcina2() {
		return vakcina2;
	}

	public void setVakcina2(String vakcina2) {
		this.vakcina2 = vakcina2;
	}

	public String getVakcina3() {
		return vakcina3;
	}

	public void setVakcina3(String vakcina3) {
		this.vakcina3 = vakcina3;
	}

	public String getPrimio1() {
		return primio1;
	}

	public void setPrimio1(String primio1) {
		this.primio1 = primio1;
	}

	public String getPrimio2() {
		return primio2;
	}

	public void setPrimio2(String primio2) {
		this.primio2 = primio2;
	}

	public String getPrimio3() {
		return primio3;
	}

	public void setPrimio3(String primio3) {
		this.primio3 = primio3;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getJMBG() {
		return JMBG;
	}
	public void setJMBG(String JMBG) {
		this.JMBG = JMBG;
	}
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void ispisiKorisnika(Korisnik korisnik) {
		System.out.println("Korisnicko ime: " + korisnik.getKorisnickoIme() + " Sifra: " + korisnik.sifra + " Ime: " + korisnik.getIme() + " Prezime: " + korisnik.getPrezime() + " JMBG: " + korisnik.getJMBG() + " Email: " + korisnik.getEmail());
		if(korisnik.getPrimio1().equals("Primio")) {
			System.out.println("Da li je primio prvu dozu: " + korisnik.getPrimio1() + " Vakcina prve doze: " + korisnik.getVakcina1() + " Datum prve doze: " + mesec[korisnik.getDatum1().getTime().getMonth()] + "/" + korisnik.getDatum1().getTime().getDate() + "/" + korisnik.getDatum1().get(GregorianCalendar.YEAR));
		}
		else {
			System.out.println("Da li je primio prvu dozu: " + korisnik.getPrimio1());
		}
		if(korisnik.getPrimio2().equals("Primio")) {
			System.out.println("Da li je primio drugu dozu: " + korisnik.getPrimio2() + " Vakcina druge doze: " + korisnik.getVakcina2() + " Datum druge doze: " + mesec[korisnik.getDatum2().getTime().getMonth()] + "/" + korisnik.getDatum2().getTime().getDate() + "/" + korisnik.getDatum2().get(GregorianCalendar.YEAR));
		}
		else {
			System.out.println("Da li je primio drugu dozu: " + korisnik.getPrimio2());
		}
		if(korisnik.getPrimio3().equals("Primio")) {
			System.out.println("Da li je primio trecu dozu: " + korisnik.getPrimio3() + " Vakcina trece doze: " + korisnik.getVakcina3() + " Datum trece doze: " + mesec[korisnik.getDatum3().getTime().getMonth()] +  "/" + korisnik.getDatum3().getTime().getDate() + "/" + korisnik.getDatum3().get(GregorianCalendar.YEAR));
		}
		else {
			System.out.println("Da li je primio trecu dozu: " + korisnik.getPrimio3());
		}
		
	}
}
