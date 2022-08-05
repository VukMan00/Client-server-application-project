package registracija_prijava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.GregorianCalendar;



public class MultiClientHandler extends Thread{

	private Socket socket;
	private BufferedReader dis = null;
	private PrintWriter dos = null;
	String primljeno;
	Korisnik korisnik1 = new Korisnik();
	ServerMultiple s = new ServerMultiple();
	
	public MultiClientHandler(Socket s, BufferedReader dis, PrintWriter dos) {
		this.socket = s;
		this.dis = dis;
		this.dos = dos;
	}
	
	public void registracija() throws IOException {
		Korisnik korisnik = new Korisnik();
		String korisnickoIme = dis.readLine();
		if(s.vratiKorisnikaSaKorisnickimImenom(korisnickoIme)==null) {
			dos.println("Korisnik ne postoji u listi");
			dos.flush();
		}
		else {
			if(s.vratiKorisnikaSaKorisnickimImenom(korisnickoIme)!=null) {
				dos.println("Korisnik postoji u listi");
				dos.flush();
				while(true) {
					korisnickoIme = dis.readLine();
					if(s.vratiKorisnikaSaKorisnickimImenom(korisnickoIme)==null) {
						dos.println("Uspesno");
						dos.flush();
						break;
					}
					else {
						dos.println("Neuspesno");
						dos.flush();
					}
				}
			}
		}
		korisnik.setKorisnickoIme(korisnickoIme);
		korisnik.setSifra(dis.readLine());
		korisnik.setIme(dis.readLine());
		korisnik.setPrezime(dis.readLine());
		
		String JMBG = dis.readLine();
		if(s.vratiKorisnika(JMBG)==null) {
			dos.println("Korisnik ne postoji u listi");
			dos.flush();
		}
		else {
			if(s.vratiKorisnika(JMBG)!=null) {
				dos.println("Korisnik postoji u listi");
				dos.flush();
				while(true) {
					JMBG = dis.readLine();
					if(s.vratiKorisnika(JMBG)==null) {
						dos.println("Uspesno");
						dos.flush();
						break;
					}
					else {
						dos.println("Neuspesno");
						dos.flush();
					}
				}
			}
		}
		korisnik.setJMBG(JMBG);
		korisnik.setEmail(dis.readLine());
		korisnik.setPol(dis.readLine());
		
		korisnik.setVakcina1(dis.readLine());
		korisnik.setPrimio1(dis.readLine());
		String date1 = dis.readLine();
		String[] dt1 = date1.split("/",3);
		GregorianCalendar datum1 = new GregorianCalendar();
		datum1.set(Integer.parseInt(dt1[2]), Integer.parseInt(dt1[0])-1, Integer.parseInt(dt1[1]));
		korisnik.setDatum1(datum1);
		
		korisnik.setVakcina2(dis.readLine());
		korisnik.setPrimio2(dis.readLine());
		String date2= dis.readLine();
		String[] dt2 = date2.split("/",3);
		GregorianCalendar datum2 = new GregorianCalendar();
		datum2.set(Integer.parseInt(dt2[2]), Integer.parseInt(dt2[0])-1, Integer.parseInt(dt2[1]));
		korisnik.setDatum2(datum2);
		
		korisnik.setVakcina3(dis.readLine());
		korisnik.setPrimio3(dis.readLine());
		String date3 = dis.readLine();
		String[] dt3 = date3.split("/",3);
		GregorianCalendar datum3 = new GregorianCalendar();
		datum3.set(Integer.parseInt(dt3[2]), Integer.parseInt(dt3[0])-1, Integer.parseInt(dt3[1]));
		korisnik.setDatum3(datum3);
		
		korisnik1.ispisiKorisnika(korisnik);
		s.dodajKorisnika(korisnik);
	}
	
	public void prijava() throws IOException {
		while(true) {
			String korisnickoIme = dis.readLine();
			String sifra = dis.readLine();
			boolean administrator = false;
			
			//-------------------------ADMINISTRATOR-------------------------------
			
			
			String adminVrati = dis.readLine();
			if(adminVrati.equals("Administrator")) {
				while(true) {
					dos.println("Izaberite: ");
					dos.flush();
					dos.println("1)Provera Covid propusnice");
					dos.flush();
					dos.println("2)Pregled svih korisnika");
					dos.flush();
					dos.println("0)Exit");
					dos.flush();
					String odgovorAdmin = dis.readLine();
					
					//------Funkcionalnosti administratora--------------------
					
					
					if(odgovorAdmin.equals("1")) {
						String jmbg = dis.readLine();
						if(s.proveraCovidPropusnice(jmbg)) {
							dos.println("Korisnik poseduje validnu Covid propusnicu");
							dos.flush();
						}
						else {
							dos.println("Korisnik ne poseduje validnu Covid propusnicu");
							dos.flush();
						}
					}
					
					if(odgovorAdmin.equals("2")) {
						dos.println("Korisnike i njihov status vakcinacije mozete videti u tekstualnim fajlovima!");
						dos.flush();
						s.pregledKorisnika();
						dos.write(s.brojRegistrovanihKorisnika());
						dos.flush();
						dos.write(s.brojKorisnikaSaPrvomDozom());
						dos.flush();
						dos.write(s.brojKorisnikaSaDrugomDozom());
						dos.flush();
						dos.write(s.brojKorisnikaSaTrecomDozom());
						dos.flush();
						dos.write(s.brojKorisnikaSaDrugomDozomPhizer());
						dos.flush();
						dos.write(s.brojKorisnikaSaDrugomDozomSputnjik());
						dos.flush();
						dos.write(s.brojKorisnikaSaDrugomDozomSinopharm());
						dos.flush();
						dos.write(s.brojKorisnikaSaDrugomDozomAstraZeneca());
						dos.flush();
					}
					
					if(odgovorAdmin.equals("0")) {
						administrator = true;
						break;
					}
				}
			}
			
			if(administrator==true) {
				break;
			}
			
			
			//----------------------OSTALI KORISNCI---------------------
			
			
			Korisnik k = s.vratiPrijava(korisnickoIme, sifra);
			if(k!=null) {
				dos.println("Prijava je uspesna");
				dos.flush();
				System.out.println(dis.readLine());
			
				System.out.println("Prijavljeni korisnik: ");
				korisnik1.ispisiKorisnika(k);
				dos.println(k.getVakcina1());
				dos.flush();
				dos.println(k.getVakcina2());
				dos.flush();
				dos.println(k.getVakcina3());
				dos.flush();
				GregorianCalendar datum1 = k.getDatum1();
				dos.write(datum1.get(GregorianCalendar.MONTH));
				dos.flush();
				GregorianCalendar datum2 = k.getDatum2();
				dos.write(datum2.get(GregorianCalendar.MONTH));
				dos.flush();
				dos.write(datum1.get(GregorianCalendar.DATE));
				dos.flush();
				
				
				//--------------Prijavljeni korisnik nije vakcinisan---------------------
				
				if(k.getPrimio1().equals("Nije primio")) {
					dos.println("Korisnik se nije vakcinisao");
					dos.flush();
					String odg = dis.readLine();
					if(odg.equals("DA")) {
						String vakcina = dis.readLine();
						String date1= dis.readLine();
						String[] dt1 = date1.split("/",3);
						GregorianCalendar d1 = new GregorianCalendar();
						d1.set(Integer.parseInt(dt1[2]), Integer.parseInt(dt1[0])-1, Integer.parseInt(dt1[1]));
						k.setPrimio1("Primio");
						k.setVakcina1(vakcina);
						k.setDatum1(d1);

					//----------Prijavljeni korisnik je ppromenio odgovor za 1 dozu i postavlja se pitanje za 2-----------------
						
						String odg2 = dis.readLine();
						if(odg2.equals("DA")) {
							String vakcina2 = dis.readLine();
							String date2= dis.readLine();
							String[] dt2 = date2.split("/",3);
							GregorianCalendar d2 = new GregorianCalendar();
							d2.set(Integer.parseInt(dt2[2]), Integer.parseInt(dt2[0])-1, Integer.parseInt(dt2[1]));
							k.setPrimio2("Primio");
							k.setVakcina2(vakcina2);
							k.setDatum2(d2);
							
						//---------------Prijavljeni korisnik je promenio odgovor za 1 i 2 dozu i postavlja se pitanje za 3 dozu-------------------	
							
							String odg3 = dis.readLine();
							if(odg3.equals("DA")) {
								String vakcina3 = dis.readLine();
								String date3= dis.readLine();
								String[] dt3 = date3.split("/",3);
								GregorianCalendar d3 = new GregorianCalendar();
								d3.set(Integer.parseInt(dt3[2]), Integer.parseInt(dt3[0])-1, Integer.parseInt(dt3[1]));
								k.setPrimio3("Primio");
								k.setVakcina3(vakcina3);
								k.setDatum3(d3);
								korisnik1.ispisiKorisnika(k);
								break;
							}
							else {
								korisnik1.ispisiKorisnika(k);
								break;
							}
							
						}
						else {
							korisnik1.ispisiKorisnika(k);
							break;
						}
						
					}
					else {
						korisnik1.ispisiKorisnika(k);
						break;
					}
					
				}
				
				//---------------------------Prijavljeni korisnik je primio 1 dozu ali nije primio 2 dozu-------------------------
				
				if(k.getPrimio2().equals("Nije primio")) {
					dos.println("Korisnik nije primio drugu dozu");
					dos.flush();
					String odg = dis.readLine();
					if(odg.equals("DA")) {
						String vakcina2 = dis.readLine();
						String date2= dis.readLine();
						String[] dt2 = date2.split("/",3);
						GregorianCalendar d2 = new GregorianCalendar();
						d2.set(Integer.parseInt(dt2[2]), Integer.parseInt(dt2[0])-1, Integer.parseInt(dt2[1]));
						k.setPrimio2("Primio");
						k.setVakcina2(vakcina2);
						k.setDatum2(d2);
						
						//------------------Prijavljeni korisnik koji je primio 1 dozu i promenio odgovor na drugu , postavlja mu se pitanje za 3 dozu------------------
						
						String odg3 = dis.readLine();
						if(odg3.equals("DA")) {
							String vakcina3 = dis.readLine();
							String date3= dis.readLine();
							String[] dt3 = date3.split("/",3);
							GregorianCalendar d3 = new GregorianCalendar();
							d3.set(Integer.parseInt(dt3[2]), Integer.parseInt(dt3[0])-1, Integer.parseInt(dt3[1]));
							k.setPrimio3("Primio");
							k.setVakcina3(vakcina3);
							k.setDatum3(d3);
							korisnik1.ispisiKorisnika(k);
							break;
							
						}
						else {
							k.ispisiKorisnika(k);
							break;
						}
					}
					else {
						korisnik1.ispisiKorisnika(k);
						break;
					}
				}
			
				
				//-------------------Prijavljeni korisnik je primio prvu i drugu dozu, postavlja se pitanje za 3 dozu-----------------------
				
				
				
				
				if(k.getPrimio3().equals("Nije primio")) {
					dos.println("Korisnik nije primio trecu dozu");
					dos.flush();
					String odg = dis.readLine();
					if(odg.equals("DA")) {
						String vakcina3 = dis.readLine();
						String date3= dis.readLine();
						String[] dt3 = date3.split("/",3);
						GregorianCalendar d3 = new GregorianCalendar();
						d3.set(Integer.parseInt(dt3[2]), Integer.parseInt(dt3[0])-1, Integer.parseInt(dt3[1]));
						k.setPrimio3("Primio");
						k.setVakcina3(vakcina3);
						k.setDatum3(d3);
						korisnik1.ispisiKorisnika(k);
						break;
					
					}
					else {
						korisnik1.ispisiKorisnika(k);
						break;
					}
				}
				
				//-----------------------------------Prijavljeni korisnik je primio sve tri doze------------------------------------
				
				
				dos.println("Prijavljeni korisnik je primio sve tri doze");
				dos.flush();
				korisnik1.ispisiKorisnika(k);
				break;
			}
			
			else {
				System.out.println("Prijava nije uspesna");
				dos.println("Prijava nije uspesna");
				dos.flush();
				String quit = dis.readLine();
				if(quit.toUpperCase().equals("QUIT")){
					break;
				}
			}
		}
			
	}
	
	public void proveraCovidPropusnice() throws IOException {
		String jmbg = dis.readLine();
		if(s.proveraCovidPropusnice(jmbg)){
			dos.println("Covid propusnica je validna");
			dos.flush();
		}
		else {
			dos.println("Covid propusnica nije validna");
			dos.flush();
		}
	}
	
	public void generisanjeCovidPropusnice() throws IOException {
		String jmbg = dis.readLine();
		if(s.proveraCovidPropusnice(jmbg)){
			s.generisanjeCovidPropusnice(jmbg);
			dos.println("Covid propusnica je kreirana");
			dos.flush();
		}
		else {
			dos.println("Covid propusnica nije kreirana, korisnik ne ispunjava uslove!");
			dos.flush();
		}
	}
	
	public void run() {
		boolean flag = true;
		while(flag) {
			try {
				dos.println("Izaberite: ");
				dos.flush();
				dos.println();
				dos.flush();
				dos.println("1)Registracija");
				dos.flush();
				dos.println("2)Prijava"); 
				dos.flush();
				dos.println("3)Provera Covid propusnice");
				dos.flush();
				dos.println("4)Generisite Covid propusnicu");
				dos.flush();
				dos.println("0)Exit");
				dos.flush();
				
				
				primljeno = dis.readLine();
				
				switch(primljeno) {
				case "1":
					registracija();
					dos.println("Uspesna registracija");
					dos.flush();
					break;
				case "2":
					prijava();
					break;
				case "3":
					proveraCovidPropusnice();
					break;
				case "4":
					generisanjeCovidPropusnice();
					break;
				case "0":
					System.out.println("Klijent " + this.socket + " salje Exit...");
					System.out.println("Zatvaram konekciju");
					this.socket.close();
					System.out.println("Konekcija je zatvorena");
					System.out.println();
					ServerMultiple.listaKlijenta.remove();
					flag = false;
					break;
				}
				
			}catch(IOException e) {
				try {
					this.dos.close();
					this.dis.close();
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				System.out.println("Iznenadno gasenje konekcije");
				ServerMultiple.listaKlijenta.remove();
				break;
			}
		}
		
		try {
			this.socket.close();
			this.dis.close();
			this.dos.close();
			
		}catch(IOException e) {
			System.out.println("Clossing error" + e);
		}
	}
}
