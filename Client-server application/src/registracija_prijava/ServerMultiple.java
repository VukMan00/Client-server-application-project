package registracija_prijava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class ServerMultiple {
	public static LinkedList<MultiClientHandler> listaKlijenta = new LinkedList<MultiClientHandler>();
	public static ListaKorisnika lk = new ListaKorisnika();
	public static Korisnik korisnik = new Korisnik();
	
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(1234);
		System.out.println("ServerSocket: " + serverSocket);
		while(true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				System.out.println("Novi klijent je konektovan");
				BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter dos = new PrintWriter(socket.getOutputStream());
				
				System.out.println("Povezujemo nit sa Klijentom");
				MultiClientHandler t = new MultiClientHandler(socket,dis,dos);
				listaKlijenta.add(t);
				t.start();
				System.out.println("Ukupan broj klijenata: " +  listaKlijenta.size());
				System.out.println();

			} catch (IOException e) {
				socket.close();
				serverSocket.close();
				e.printStackTrace();
			}	
		}
	}
	
	public void dodajKorisnika(Korisnik korisnik) { //funckija dodavanja korisnika u listu Korisnika
		lk.dodajKorisnika(korisnik);
	}

	public boolean proveraCovidPropusnice(String JMBG) {//funkcija koja vraca true ili false u zavisnosti da li je validna Covid propusnica
		ArrayList<Korisnik> lista = lk.vratiListu();
		for(int i=0;i<lista.size();i++) {
			if(JMBG.equals(lista.get(i).getJMBG())) {
				if((lista.get(i).getPrimio1().equals("Primio") && lista.get(i).getPrimio2().equals("Primio"))) {
					return true;			
				}
			}
		}
		return false;
	}
	
	public Korisnik vratiKorisnika(String JMBG) { //funkcija koja vraca Korisnika ako postoji u listi korisnika u zavisnosti od njegovog JMBG-a
		ArrayList<Korisnik> lista = lk.vratiListu();
		for(int i=0;i<lista.size();i++) {
			if(JMBG.equals(lista.get(i).getJMBG())) {
				return lista.get(i);
			}
		}
		return null;
	}
	
	public void generisanjeCovidPropusnice(String jmbg) {//Generisanje Covid propusnice u zavisnosti od njegovog JMBG-a
		if(proveraCovidPropusnice(jmbg)) {
			lk.generisiCovidPropusnicu(jmbg);
		}
	}
	
	public Korisnik vratiPrijava(String korisnickoIme, String sifra) {//Vraca korisnika ako mu se korisnickoIme i sifra poklapa sa korisnickimImenom i sifrom u listi korisnika
		ArrayList<Korisnik> lista = lk.vratiListu();
		for(int i=0;i<lista.size();i++) {
			if(korisnickoIme.equals(lista.get(i).getKorisnickoIme()) && sifra.equals(lista.get(i).getSifra())) {
				return lista.get(i);
			}
		}
		return null;
	}
	
	public void pregledKorisnika() {//funkcija Administratora za upisivanje svih korisnika po tekstualnim fajlovima!
		lk.upisiUFajl();
	}
	
	public Korisnik vratiKorisnikaSaKorisnickimImenom(String korisnickoIme) {
		ArrayList<Korisnik> lista = lk.vratiListu();
		for(int i=0;i<lista.size();i++) {
			if(korisnickoIme.equals(lista.get(i).getKorisnickoIme())) {
				return lista.get(i);
			}
		}
		
		return null;
	}
	
	public int brojKorisnikaSaPrvomDozom() {
		ArrayList<Korisnik> lista = lk.vratiListu();
		int brojac=0;
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getPrimio1().equals("Primio")) {
				brojac++;
			}
		}
		
		return brojac;
	}
	
	public int brojKorisnikaSaDrugomDozom() {
		ArrayList<Korisnik> lista = lk.vratiListu();
		int brojac=0;
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getPrimio2().equals("Primio")) {
				brojac++;
			}
		}
		
		return brojac;
	}
	
	public int brojKorisnikaSaTrecomDozom() {
		ArrayList<Korisnik> lista = lk.vratiListu();
		int brojac=0;
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getPrimio3().equals("Primio")) {
				brojac++;
			}
		}
		
		return brojac;
	}
	
	public int brojKorisnikaSaDrugomDozomPhizer() {
		ArrayList<Korisnik> lista = lk.vratiListu();
		int brojac=0;
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getPrimio2().equals("Primio") && lista.get(i).getVakcina2().equals("Phizer BionTek")) {
				brojac++;
			}
		}
		
		return brojac;
	}
	
	public int brojKorisnikaSaDrugomDozomSputnjik() {
		ArrayList<Korisnik> lista = lk.vratiListu();
		int brojac=0;
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getPrimio2().equals("Primio") && lista.get(i).getVakcina2().equals("Sputnjik B")) {
				brojac++;
			}
		}
		
		return brojac;
	}
	
	public int brojKorisnikaSaDrugomDozomSinopharm() {
		ArrayList<Korisnik> lista = lk.vratiListu();
		int brojac=0;
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getPrimio2().equals("Primio") && lista.get(i).getVakcina2().equals("Sinopharm")) {
				brojac++;
			}
		}
		
		return brojac;
	}
	
	public int brojKorisnikaSaDrugomDozomAstraZeneca() {
		ArrayList<Korisnik> lista = lk.vratiListu();
		int brojac=0;
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getPrimio2().equals("Primio") && lista.get(i).getVakcina2().equals("AstraZeneca")) {
				brojac++;
			}
		}
		
		return brojac;
	}
	
	public int brojRegistrovanihKorisnika() {
		ArrayList<Korisnik> lista = lk.vratiListu();
		int brojac = 0;
		for(int i=0;i<lista.size();i++) {
			brojac++;
		}
		
		return brojac;
	}

}

