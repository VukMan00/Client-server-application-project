package registracija_prijava;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ListaKorisnika {

	ArrayList<Korisnik> korisnici = new ArrayList<>();
	String[] mesec = {"Januar" , "Februar" , "Mart" , "April","Maj","Jun","Jul","Avgust","Septembar","Oktobar","Novembar","Decembar"};
	
	public void dodajKorisnika(Korisnik korisnik) {
		korisnici.add(korisnik);
	}

	public ArrayList<Korisnik> vratiListu(){
		return korisnici;
	}
	@SuppressWarnings("deprecation")
	public void ispisiListu() {
		for(int i=0;i<korisnici.size();i++) {
			System.out.println("Korisnicko ime: " + korisnici.get(i).getKorisnickoIme() + " Sifra: " + korisnici.get(i).sifra + " Ime: " + korisnici.get(i).getIme() + " Prezime: " + korisnici.get(i).getPrezime() + " JMBG: " + korisnici.get(i).getJMBG() + " Email: " + korisnici.get(i).getEmail());
			if(korisnici.get(i).getPrimio1().equals("Primio")) {
				System.out.println("Da li je primio prvu dozu: " + korisnici.get(i).getPrimio1() + " Vakcina prve doze: " + korisnici.get(i).getVakcina1() + " Datum prve doze: " + mesec[korisnici.get(i).getDatum1().getTime().getMonth()] + "/" + korisnici.get(i).getDatum1().getTime().getDate() + "/" + korisnici.get(i).getDatum1().get(GregorianCalendar.YEAR));
			}
			else {
				System.out.print("Da li je primio prvu dozu: " + korisnici.get(i).getPrimio1());
			}
			if(korisnici.get(i).getPrimio2().equals("Primio")) {
				System.out.println("Da li je primio drugu dozu: " + korisnici.get(i).getPrimio2() + " Vakcina druge doze: " + korisnici.get(i).getVakcina2() + " Datum prve doze: " + mesec[korisnici.get(i).getDatum2().getTime().getMonth()] + "/" + korisnici.get(i).getDatum2().getTime().getDate() + "/" + korisnici.get(i).getDatum2().get(GregorianCalendar.YEAR));
			}
			else {
				System.out.print("Da li je primio drugu dozu: " + korisnici.get(i).getPrimio2());
			}
			if(korisnici.get(i).getPrimio3().equals("Primio")) {
				System.out.println("Da li je primio trecu dozu: " + korisnici.get(i).getPrimio3() + " Vakcina trece doze: " + korisnici.get(i).getVakcina3() + " Datum trece doze: " + " Datum prve doze: " + korisnici.get(i).getDatum3().getTime().getMonth() + "/" + mesec[korisnici.get(i).getDatum3().getTime().getDate()] + "/" + korisnici.get(i).getDatum3().get(GregorianCalendar.YEAR));
			}
			else {
				System.out.println("Da li je primio trecu dozu: " + korisnici.get(i).getPrimio3());
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void upisiUFajl() {
		try {
			PrintWriter out = new PrintWriter("korisnici.txt");
			for(int i=0; i<korisnici.size();i++) {
				out.println("Korisnicko ime: " + korisnici.get(i).getKorisnickoIme() + " Sifra: " + korisnici.get(i).getSifra() + " Ime: " + korisnici.get(i).getIme() + " Prezime: " + korisnici.get(i).getPrezime() + " JMBG: " + korisnici.get(i).getJMBG() + " Email: " + korisnici.get(i).getEmail());
				if(korisnici.get(i).getPrimio1().equals("Primio")) {
					out.println("Da li je primio prvu dozu: " + korisnici.get(i).getPrimio1() + " Vakcina prve doze: " + korisnici.get(i).getVakcina1() + " Datum prve doze: " + mesec[korisnici.get(i).getDatum1().getTime().getMonth()] + "/" + korisnici.get(i).getDatum1().getTime().getDate() + "/" + korisnici.get(i).getDatum1().get(GregorianCalendar.YEAR));
				}
				else {
					out.println("Da li je primio prvu dozu: " + korisnici.get(i).getPrimio1());
				}
				if(korisnici.get(i).getPrimio2().equals("Primio")) {
					out.println("Da li je primio drugu dozu: " + korisnici.get(i).getPrimio2() + " Vakcina druge doze: " + korisnici.get(i).getVakcina2() + " Datum druge doze: " + mesec[korisnici.get(i).getDatum2().getTime().getMonth()] + "/" + korisnici.get(i).getDatum2().getTime().getDate() + "/" + korisnici.get(i).getDatum2().get(GregorianCalendar.YEAR));
				}
				else {
					out.println("Da li je primio drugu dozu: " + korisnici.get(i).getPrimio2());
				}
				if(korisnici.get(i).getPrimio3().equals("Primio")) {
					out.println("Da li je primio trecu dozu: " + korisnici.get(i).getPrimio3() + " Vakcina trece doze: " + korisnici.get(i).getVakcina3() + " Datum trece doze: " + mesec[korisnici.get(i).getDatum3().getTime().getMonth()] + "/" + korisnici.get(i).getDatum3().getTime().getDate() + "/" + korisnici.get(i).getDatum3().get(GregorianCalendar.YEAR));
				}
				else {
					out.println("Da li je primio trecu dozu: " + korisnici.get(i).getPrimio3());
				}
				out.println();
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PrintWriter out1 = new PrintWriter("korisniciSaPrvomDozom.txt");
			for(int i=0;i<korisnici.size();i++) {
				if(korisnici.get(i).getPrimio1().equals("Primio")) {
					out1.println("Korisnicko ime: " + korisnici.get(i).getKorisnickoIme() + " Sifra: " + korisnici.get(i).getSifra() + " Ime: " + korisnici.get(i).getIme() + " Prezime: " + korisnici.get(i).getPrezime() + " JMBG: " + korisnici.get(i).getJMBG() + " Email: " + korisnici.get(i).getEmail());
					out1.println("Da li je primio prvu dozu: " + korisnici.get(i).getPrimio1() + " Vakcina prve doze: " + korisnici.get(i).getVakcina1() + " Datum prve doze: " + mesec[korisnici.get(i).getDatum1().getTime().getMonth()] + "/" + korisnici.get(i).getDatum1().getTime().getDate() + "/" + korisnici.get(i).getDatum1().get(GregorianCalendar.YEAR));
				}
				out1.println();
			}
			out1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PrintWriter out2 = new PrintWriter("korisniciSaDrugomDozom.txt");
			for(int i=0;i<korisnici.size();i++) {
				if(korisnici.get(i).getPrimio1().equals("Primio") && korisnici.get(i).getPrimio2().equals("Primio")) {
					out2.println("Korisnicko ime: " + korisnici.get(i).getKorisnickoIme() + " Sifra: " + korisnici.get(i).getSifra() + " Ime: " + korisnici.get(i).getIme() + " Prezime: " + korisnici.get(i).getPrezime() + " JMBG: " + korisnici.get(i).getJMBG() + " Email: " + korisnici.get(i).getEmail());
					out2.println("Da li je primio prvu dozu: " + korisnici.get(i).getPrimio1() + " Vakcina prve doze: " + korisnici.get(i).getVakcina1() + " Datum prve doze: " + mesec[korisnici.get(i).getDatum1().getTime().getMonth()] + "/" + korisnici.get(i).getDatum1().getTime().getDate() + "/" + korisnici.get(i).getDatum1().get(GregorianCalendar.YEAR));
					out2.println("Da li je primio drugu dozu: " + korisnici.get(i).getPrimio2() + " Vakcina druge doze: " + korisnici.get(i).getVakcina2() + " Datum druge doze: " + mesec[korisnici.get(i).getDatum2().getTime().getMonth()] + "/" + korisnici.get(i).getDatum2().getTime().getDate() + "/" + korisnici.get(i).getDatum2().get(GregorianCalendar.YEAR));
				}
				out2.println();
			}
			out2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PrintWriter out3 = new PrintWriter("korisniciSaTrecomDozom.txt");
			for(int i=0;i<korisnici.size();i++) {
				if(korisnici.get(i).getPrimio1().equals("Primio") && korisnici.get(i).getPrimio2().equals("Primio") && korisnici.get(i).getPrimio3().equals("Primio")) {
					out3.println("Korisnicko ime: " + korisnici.get(i).getKorisnickoIme() + " Sifra: " + korisnici.get(i).getSifra() + " Ime: " + korisnici.get(i).getIme() + " Prezime: " + korisnici.get(i).getPrezime() + " JMBG: " + korisnici.get(i).getJMBG() + " Email: " + korisnici.get(i).getEmail());
					out3.println("Da li je primio prvu dozu: " + korisnici.get(i).getPrimio1() + " Vakcina prve doze: " + korisnici.get(i).getVakcina1() + " Datum prve doze: " + mesec[korisnici.get(i).getDatum1().getTime().getMonth()] + "/" + korisnici.get(i).getDatum1().getTime().getDate() + "/" + korisnici.get(i).getDatum1().get(GregorianCalendar.YEAR));
					out3.println("Da li je primio drugu dozu: " + korisnici.get(i).getPrimio2() + " Vakcina druge doze: " + korisnici.get(i).getVakcina2() + " Datum druge doze: " + mesec[korisnici.get(i).getDatum2().getTime().getMonth()] + "/" + korisnici.get(i).getDatum2().getTime().getDate() + "/" + korisnici.get(i).getDatum2().get(GregorianCalendar.YEAR));
					out3.println("Da li je primio trecu dozu: " + korisnici.get(i).getPrimio3() + " Vakcina trece doze: " + korisnici.get(i).getVakcina3() + " Datum trece doze: " + mesec[korisnici.get(i).getDatum3().getTime().getMonth()] + "/" + korisnici.get(i).getDatum3().getTime().getDate() + "/" + korisnici.get(i).getDatum3().get(GregorianCalendar.YEAR));
				}
				
				out3.println();
			}
			out3.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PrintWriter out4 = new PrintWriter("korisniciSaDrugomDozomPhizer.txt");
			PrintWriter out5 = new PrintWriter("korisniciSaDrugomDozomSputnjik.txt");
			PrintWriter out6 = new PrintWriter("korisniciSaDrugomDozomSinopharm.txt");
			PrintWriter out7 = new PrintWriter("korisniciSaDrugomDozomAstraZeneca.txt");
			for(int i=0;i<korisnici.size();i++) {
				if(korisnici.get(i).getPrimio2().equals("Primio")) {
					if(korisnici.get(i).getVakcina2().equals("Phizer BionTek")) {
						out4.println("Korisnici koji su primili drugu dozu Phizer BionTek");
						out4.println("Korisnicko ime: " + korisnici.get(i).getKorisnickoIme() + " Sifra: " + korisnici.get(i).getSifra() + " Ime: " + korisnici.get(i).getIme() + " Prezime: " + korisnici.get(i).getPrezime() + " JMBG: " + korisnici.get(i).getJMBG() + " Email: " + korisnici.get(i).getEmail());
					}
					
					if(korisnici.get(i).getVakcina2().equals("Sputnjik B")) {
						out5.println("Korisnici koji su primili drugu dozu Sputnjik B");
						out5.println("Korisnicko ime: " + korisnici.get(i).getKorisnickoIme() + " Sifra: " + korisnici.get(i).getSifra() + " Ime: " + korisnici.get(i).getIme() + " Prezime: " + korisnici.get(i).getPrezime() + " JMBG: " + korisnici.get(i).getJMBG() + " Email: " + korisnici.get(i).getEmail());
					}
					
					if(korisnici.get(i).getVakcina2().equals("Sinopharm")) {
						out6.println("Korisnici koji su primili drugu dozu Sinopharm");
						out6.println("Korisnicko ime: " + korisnici.get(i).getKorisnickoIme() + " Sifra: " + korisnici.get(i).getSifra() + " Ime: " + korisnici.get(i).getIme() + " Prezime: " + korisnici.get(i).getPrezime() + " JMBG: " + korisnici.get(i).getJMBG() + " Email: " + korisnici.get(i).getEmail());
					}
					
					if(korisnici.get(i).getVakcina2().equals("AstraZeneca")) {
						out7.println("Korisnici koji su primili drugu dozu AstraZeneca");
						out7.println("Korisnicko ime: " + korisnici.get(i).getKorisnickoIme() + " Sifra: " + korisnici.get(i).getSifra() + " Ime: " + korisnici.get(i).getIme() + " Prezime: " + korisnici.get(i).getPrezime() + " JMBG: " + korisnici.get(i).getJMBG() + " Email: " + korisnici.get(i).getEmail());
					}
				}
			}
			out4.close();
			out5.close();
			out6.close();
			out7.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void generisiCovidPropusnicu(String jmbg) {
		try {
			
			PrintWriter cp = new PrintWriter(jmbg + ".txt");
			
			for(int i=0;i<korisnici.size();i++) {
				if(jmbg.equals(korisnici.get(i).getJMBG())) {
					if(korisnici.get(i).getPrimio1().equals("Primio") && korisnici.get(i).getPrimio2().equals("Primio")) {
						cp.println("------------------" + "COVID PROPUSNICA" + "------------------");
						cp.println("Ime: " + korisnici.get(i).getIme() + " Prezime: " + korisnici.get(i).getPrezime() + " JMBG: " + korisnici.get(i).getJMBG() + " Email: " + korisnici.get(i).getEmail());
						cp.println("Da li je primio prvu dozu: " + korisnici.get(i).getPrimio1() + " Vakcina prve doze: " + korisnici.get(i).getVakcina1() + " Datum prve doze: " +  mesec[korisnici.get(i).getDatum1().getTime().getMonth()] + "/" + korisnici.get(i).getDatum1().getTime().getDate() + "/" + korisnici.get(i).getDatum1().get(GregorianCalendar.YEAR));
						cp.println("Da li je primio drugu dozu: " + korisnici.get(i).getPrimio2() + " Vakcina druge doze: " + korisnici.get(i).getVakcina2() + "Datum druge doze: " +  mesec[korisnici.get(i).getDatum2().getTime().getMonth()] + "/" + korisnici.get(i).getDatum2().getTime().getDate() + "/" + korisnici.get(i).getDatum2().get(GregorianCalendar.YEAR));
						
						if(korisnici.get(i).getPrimio3().equals("Primio")) {
							cp.println("Da li je primio trecu dozu: " + korisnici.get(i).getPrimio3() + " Vakcina trece doze: " + korisnici.get(i).getVakcina3() + " Datum trece doze: " + mesec[korisnici.get(i).getDatum3().getTime().getMonth()] + "/" + korisnici.get(i).getDatum3().getTime().getDate() + "/" + korisnici.get(i).getDatum3().get(GregorianCalendar.YEAR));
						}
					}
				}
			}
			
			cp.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
