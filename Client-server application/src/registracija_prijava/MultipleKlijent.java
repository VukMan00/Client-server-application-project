package registracija_prijava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MultipleKlijent {

public static ListaKorisnika lk = new ListaKorisnika();
public static Administrator admin = new Administrator();
public static MultipleKlijent mk = new MultipleKlijent();

public static String proveraEmail(String email, Scanner sc) {
	while(true) {
		 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
	             "[a-zA-Z0-9_+&*-]+)*@" +
	             "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
	             "A-Z]{2,7}$";
	               
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null) {
			System.out.println("Pogresno ukucan email");
			email = sc.nextLine();
		}
		if(pat.matcher(email).matches()) {
			break;
		}
		else {
			System.out.println("Pogresno ukucan email");
			email = sc.nextLine();
		}
	}
	return email;
}
public static String proveraJMBG(String jmbg, Scanner sc) {
	char[] brojevi = jmbg.toCharArray(); //USLOV jmbg mora da ima 13 brojeva, ne sme da sadrzi bilo kakav drugi karakter!
	boolean uspesan = false;
	for(int i=0;i<brojevi.length;i++) {
		if(Character.isDigit(brojevi[i])) {
			uspesan = true;
		}
		else {
			uspesan = false;
			break;
		}
	}
	
	if(jmbg.length()!=13 || uspesan==false) {
		while(jmbg.length()!=13 || uspesan==false) {
			System.out.println("Pogresan JMBG");
			System.out.println("Probajte ponovo: ");
			jmbg=sc.nextLine();
			brojevi = jmbg.toCharArray();
			for(int i=0;i<brojevi.length;i++) {
				if(Character.isDigit(brojevi[i])) {
					uspesan = true;
				}
				else {
					uspesan = false;
					break;
				}
			}
		}
	}
	
	return jmbg;
}
public static String odgovorDaNe(String odgovor, Scanner sc) {
	if(!(odgovor.toUpperCase().equals("DA") || odgovor.toUpperCase().equals("NE"))) { //ovo sluzi da ne moze tek tako da kuca safasfasf sai to, nego ili da ili ne!
		while(!(odgovor.toUpperCase().equals("DA") || odgovor.toUpperCase().equals("NE"))) {
			System.out.println("Greska! Pokusajte ponovo.");
			odgovor = sc.nextLine();
		}
	}
	return odgovor.toUpperCase();
}
public static String proveraNazivaVakcine(String vakcina, Scanner sc) {
	if(!(vakcina.equals("Phizer BionTek") || vakcina.equals("Sputnjik B") || vakcina.equals("Sinopharm") || vakcina.equals("AstraZeneca"))) { //Da vakcina ne moze glasiti sfasfsaf nego mora neka od ovih
		while(!(vakcina.equals("Phizer BionTek") || vakcina.equals("Sputnjik B") || vakcina.equals("Sinopharm") || vakcina.equals("AstraZeneca"))) {
			System.out.println("Pogresna vakcina, pokusajte ponovo: ");
			vakcina = sc.nextLine();
		}
	}
	return vakcina;
}
public static String proveraGodine(String date, Scanner sc) {
	date = proveraIspravnostiDatuma(date, sc);
	String[] s = date.split("/",3);
	if(!(s[2].equals("2021"))) {//godina mora biti 2021!
		while(true) {
			System.out.println("Godina mora biti 2021! Pokusajte ponovo!");
			date = sc.nextLine();
			date = proveraIspravnostiDatuma(date, sc);
			s = date.split("/",3);
			if(s[2].equals("2021")) {				
				break;
			}
		}
	}
	return date;
}
public static int[] odredjivanjeNedeljeMeseca(String date) { //vraca nedelju i mesec datuma vakcinacije koji ce nam biti potrebni pri odredjivanju datuma druge doze
	int[] niz = new int[2];
	String[] s = date.split("/",3);
	int mesec = Integer.parseInt(s[0]);
	int dan = Integer.parseInt(s[1]);
	int godina = Integer.parseInt(s[2]);
	GregorianCalendar datum1 = new GregorianCalendar();
	datum1.set(godina,mesec-1,dan);
	int nedelja = datum1.get(GregorianCalendar.WEEK_OF_YEAR);
	niz[0] = nedelja;
	niz[1] = mesec;
	
	return niz;
}
public static String proveraIspravnostiDatuma(String date, Scanner sc) {
	while(true) {
		char[] datum = date.toCharArray();
		if(datum.length != 10) {
			System.out.println("Pogresan datum, ukucajte ponovo!");
			date = sc.nextLine();
		}
		else {
			int brojac = 0;
			for(int i=0;i<datum.length;i++) {
				if(!Character.isDigit(datum[i])) {
					if(datum[i]=='/') {
						if(i==2 || i==5) {
							brojac++;
						}
					}
					else {
						brojac++;
					}
				}
			}
			if(brojac == 2) {
				String[] s = date.split("/",3);
				int mesec = Integer.parseInt(s[0]);
				int dan = Integer.parseInt(s[1]);
				boolean flag = true;
				if(mesec<=12 && dan<=31) {
					if(mesec==2 && dan>29) {
						System.out.println("Pogresan datum, ukucajte ponovo!");
						date = sc.nextLine();
						flag = false;
					}
					if((mesec==1 || mesec==3 || mesec == 5 || mesec == 7 || mesec == 8 || mesec == 10 || mesec == 12) && dan>31) {
						System.out.println("Pogresan datum, ukucajte ponovo!");
						date = sc.nextLine();
						flag = false;
					}
					if((mesec==4 || mesec==6 || mesec==9 || mesec==11) && dan>30) {
						System.out.println("Pogresan datum, ukucajte ponovo!");
						date = sc.nextLine();
						flag = false;
					}
				}
				else {
					System.out.println("Pogresan datum, ukucajte ponovo!");
					date = sc.nextLine();
					flag = false;
				}
				
				if(flag == true) {
					break;
				}
			}
			else {
				System.out.println("Pogresan datum, ukucajte ponovo!");
				date = sc.nextLine();
			}
		}
	}
	
	return date;
}
public static String proveraPol(String pol, Scanner sc) {
	if(!(pol.equals("1") || pol.equals("2"))) { 
		while(!(pol.equals("1") || pol.equals("2"))) {
			System.out.println("Greska! Pokusajte ponovo.");
			pol = sc.nextLine();
		}
	}
	return pol;
}

public static void pregledSvihKorisnika(BufferedReader dis) throws IOException { //Metoda za Admin-a, pregled broja registrovanih korisnika
	
	System.out.println(dis.readLine());
	int br = dis.read();
	System.out.println("Ukupan broj registrovanih korisnika: " + br);
	int br1 = dis.read();
	System.out.println("Broj korisnika koji su primili prvu dozu: " + br1);
	int br2 = dis.read();
	System.out.println("Broj korisnika koji su primili drugu dozu: " + br2);
	int br3 = dis.read();
	System.out.println("Broj korisnika koji su primili trecu dozu: " + br3);
	int br2Phizer = dis.read();
	System.out.println("Broj korisnika koji su primili drugu dozu Phizer BionTek: " + br2Phizer);
	int br2Sputnjik = dis.read();
	System.out.println("Broj korisnika koji su primili drugu dozu Sputnjik: " + br2Sputnjik);
	int br2Sinopharm = dis.read();
	System.out.println("Broj korisnika koji su primili drugu dozu Sinopharm: " + br2Sinopharm);
	int br2AstraZeneca = dis.read();
	System.out.println("Broj Korisnika koji su primili drugu dozu AstraZeneca: " + br2AstraZeneca);
}

public static String[] nijePrimioPrvuVakcinu(String vakcina, String date1, PrintWriter dos, Scanner sc) { //Razlog zasto vraca niz Stringova jeste zato sto , za odredjivanje druge vakcine, potreban nam je naziv prve vakcine i datum kada je primljena prva doza
	String[] niz = new String[2];
	System.out.println("Izaberite neku od 4 navedene vakcine!:");
	System.out.println("Phizer BionTek");
	System.out.println("Sputnjik B");
	System.out.println("Sinopharm");
	System.out.println("AstraZeneca");
	vakcina = sc.nextLine();
	vakcina = proveraNazivaVakcine(vakcina, sc);

	dos.println(vakcina);
	dos.flush();
	
	System.out.println("Ukucajte datum primanja prve vakcine.");
	System.out.println("Ukucajte u formatu MM(mesec)/dd(dan)/yyyy(godina). PAZNJA! Godina mora biti 2021!");
	date1 = sc.nextLine();
	date1 = proveraGodine(date1, sc);
	
	niz[0] = date1;
	niz[1] = vakcina;
	
	return niz;
}

public static String nijePrimioDruguVakcinu(String vakcina, int nedelja, String vakcina2, String date2, PrintWriter dos, Scanner sc) { //Razlog zasto vraca string jeste zato sto, za odredjivanje trece vakcine, potreban nam je datum primanja druge doze, naziv druge vakcine za trecu dozu nije od vaznosti
	
	System.out.println("Izaberite neku od 4 navedene vakcine!:");
	System.out.println("Phizer BionTek");
	System.out.println("Sputnjik B");
	System.out.println("Sinopharm");
	System.out.println("AstraZeneca");
	vakcina2 = sc.nextLine();
	vakcina2 = proveraNazivaVakcine(vakcina2, sc);
	
	if(!(vakcina2.equals(vakcina))) {
		while(!(vakcina2.equals(vakcina))) {
			System.out.println("Vakcina druge doze mora biti istog proizvodjaca kao i prve doze");
			System.out.println("Pokusajte ponovo");
			vakcina2 = sc.nextLine();
		}
	}

	dos.println(vakcina2);
	dos.flush();
	
	System.out.println("Ukucajte datum primanja druge vakcine.");
	System.out.println("Ukucajte u formatu MM(mesec)/dd(dan)/yyyy(godina). PAZNJA! Godina mora biti 2021!");
	date2 = sc.nextLine();
	
	date2 = proveraGodine(date2, sc);
	int[] niz = odredjivanjeNedeljeMeseca(date2);
	int nedelja2 = niz[0];
	
	if(nedelja2<nedelja + 3) {
		while(true) {
			System.out.println("Datum primanja druge doze mora biti najmanje 3 nedelje kasnije od primanja prve doze");
			System.out.println("Pokusajte ponovo");
			date2 = sc.nextLine();
			date2 = proveraGodine(date2, sc);
			niz = odredjivanjeNedeljeMeseca(date2);
			nedelja2 = niz[0];
			if(nedelja2>=nedelja+3) {
				break;
			}
		}
	}
	
	dos.println(date2);
	dos.flush();
	
	return date2;
}

public static void nijePrimioTrecuVakcinu(String vakcina3, String date3, int mesec2, PrintWriter dos, Scanner sc) {
	
	System.out.println("Izaberite neku od 4 navedene vakcine!:");
	System.out.println("Phizer BionTek");
	System.out.println("Sputnjik B");
	System.out.println("Sinopharm");
	System.out.println("AstraZeneca");
	vakcina3 = sc.nextLine();
	vakcina3 = proveraNazivaVakcine(vakcina3, sc);
	
	dos.println(vakcina3);
	dos.flush();
	
	System.out.println("Ukucajte datum primanja tece doze.");
	System.out.println("Ukucajte u formatu MM(mesec)/dd(dan)/yyyy(godina). PAZNJA! Godina mora biti 2021!");
	date3 = sc.nextLine();
	
	date3 = proveraGodine(date3, sc);
	String[] s3 = date3.split("/",3);
	int mesec3 = Integer.parseInt(s3[0])-1;					
	if(mesec3<mesec2+6){
		while(true) {
			System.out.println("Datum primanja trece vakcine mora biti najmanje 6 meseci kasnije od primanja druge vakcine!");
			System.out.println("Pokusajte ponovo");
			date3 = sc.nextLine();
			date3 = proveraGodine(date3, sc);
			s3 = date3.split("/",3);
			mesec3 = Integer.parseInt(s3[0]);
			if(mesec3>=mesec2+6) {
				break;
			}
		}
	}
	
	dos.println(date3);
	dos.flush();
	
}

public static void registracija(BufferedReader dis, PrintWriter dos, Scanner sc) throws IOException {
		System.out.println("Unesite korisnicko ime: ");
		String korisnickoIme = sc.nextLine();
		if(korisnickoIme.equals(admin.getKorisnickoIme())) {
			while(korisnickoIme.equals(admin.getKorisnickoIme())) {
				System.out.println("Korisnicko ime je zauzeto.Admin koristi takvo korisnicko ime!");
				System.out.println("Pokusajte ponovo!");
				korisnickoIme = sc.nextLine();
			}
		}
		dos.println(korisnickoIme);
		dos.flush();
		String odgovorNaKorisnickoIme = dis.readLine();//ukoliko korisnik vec postoji u listi sa datim korisnickim imenom!
		if(odgovorNaKorisnickoIme.equals("Korisnik postoji u listi")) {
			while(true) {
				System.out.println(odgovorNaKorisnickoIme);
				System.out.println("Pokusajte ponovo!");
				korisnickoIme=sc.nextLine();
				dos.println(korisnickoIme);
				dos.flush();
				odgovorNaKorisnickoIme=dis.readLine();
				if(odgovorNaKorisnickoIme.equals("Uspesno")) {
					break;
				}
			}
		}
		
		System.out.println("Unesite sifru: ");
		String sifra = sc.nextLine();
		dos.println(sifra);
		dos.flush();
	
		System.out.println("Unesite ime: ");
		String ime = sc.nextLine();
		dos.println(ime);
		dos.flush();
		
		System.out.println("Unesite prezime: ");
		String prezime = sc.nextLine();
		dos.println(prezime);
		dos.flush();
		
		System.out.println("Unestie JMBG: ");
		System.out.println("Paznja! JMBG mora da sadrzi 13 brojeva.");
		String jmbg = sc.nextLine();
		jmbg = proveraJMBG(jmbg, sc); //Provera da li je JMBG sacinjen od 13 brojeva
		dos.println(jmbg);
		dos.flush();
		String odgovorNaJmbg = dis.readLine();    //Provera Da li takav JMBG postoji vec u listi
		if(odgovorNaJmbg.equals("Korisnik postoji u listi")) {
			while(true) {
				System.out.println(odgovorNaJmbg);
				System.out.println("Pokusajte ponovo!");
				jmbg=sc.nextLine();
				jmbg = proveraJMBG(jmbg, sc);
				dos.println(jmbg);
				dos.flush();
				odgovorNaJmbg=dis.readLine();
				if(odgovorNaJmbg.equals("Uspesno")) {
					break;
				}
			}
		}
		
		System.out.println("Unesite e-mail: ");
		String email = sc.nextLine();
		email = proveraEmail(email, sc);
		dos.println(email);
		dos.flush();
		
		System.out.println("Pol: ");
		System.out.println("1)Muski");
		System.out.println("2)Zenski");
		String pol = sc.nextLine();
		pol = proveraPol(pol, sc);
		if(pol.equals("1")) {
			pol = "Muski";
		}
		else {
			pol = "Zenski";
		}
		dos.println(pol);
		dos.flush();
		
		String vakcina = null;
		String vakcina2 = null;
		String vakcina3 = null;
		String primio1 = null;
		String primio2 = null;
		String primio3 = null;
		String date1 = null;
		String date2 = null;
		String date3 = null;
		int[] niz = new int[2];
		
		System.out.println("Da li ste primili prvu dozu? Odgovorite sa DA ili NE");
		String odgovor = sc.nextLine();
		odgovor = odgovorDaNe(odgovor, sc);
		
		if(odgovor.equals("DA")) {  
			System.out.println("Koji je naziv primljene vakcine? Izaberite jednu od od ovih vakcina: ");
			System.out.println("Phizer BionTek");
			System.out.println("Sputnjik B");
			System.out.println("Sinopharm");
			System.out.println("AstraZeneca");
			vakcina = sc.nextLine();
			vakcina = proveraNazivaVakcine(vakcina, sc);
			
			primio1 = "Primio";
			
			System.out.println("Ukucajte datum primanja prve vakcine.");
			System.out.println("Ukucajte u formatu MM(mesec)/dd(dan)/yyyy(godina). PAZNJA! Godina mora biti 2021!");
			date1 = sc.nextLine();
			date1 = proveraGodine(date1, sc);
			
			niz = odredjivanjeNedeljeMeseca(date1);
			int nedelja = niz[0];
			int mesec = niz[1];
	
			
			System.out.println("Da li ste primili drugu dozu? Odgovorite sa DA ili NE");
			odgovor = sc.nextLine();
			odgovor = odgovorDaNe(odgovor, sc);
			
			if(odgovor.equals("DA")) {
				System.out.println("Koji je naziv primljene vakcine? Izaberite jednu od od ovih vakcina: ");
				System.out.println("Phizer BionTek");
				System.out.println("Sputnjik B");
				System.out.println("Sinopharm");
				System.out.println("AstraZeneca");
				vakcina2 = sc.nextLine();
				vakcina2 = proveraNazivaVakcine(vakcina2, sc);
				
				if(!(vakcina2.equals(vakcina))) {//vakcina2 mora biti iste vrste kao vakcina1
					while(!(vakcina2.equals(vakcina))) {
						System.out.println("Vakcina druge doze mora biti istog proizvodjaca kao i prve doze");
						System.out.println("Pokusajte ponovo");
						vakcina2 = sc.nextLine();
					}
				}
				
				primio2 = "Primio";
				
				System.out.println("Ukucajte datum primanja druge vakcine.");
				System.out.println("Ukucajte u formatu MM(mesec)/dd(dan)/yyyy(godina). PAZNJA! Godina mora biti 2021!");
				date2 = sc.nextLine();
				
				date2 = proveraGodine(date2, sc);
				niz = odredjivanjeNedeljeMeseca(date2);
				int nedelja2 = niz[0];
				int mesec2 = niz[1];
				
				if(nedelja2<nedelja + 3) { //uslov da datum primanja druge doze mora biti najmanje 3 nedelje kasnije od primanja prve doze
					while(true) {
						System.out.println("Datum primanja druge doze mora biti najmanje 3 nedelje kasnije od primanja prve doze");
						System.out.println("Pokusajte ponovo");
						date2 = sc.nextLine();
						date2 = proveraGodine(date2, sc);
						niz = odredjivanjeNedeljeMeseca(date2);
						nedelja2 = niz[0];
						if(nedelja2>=nedelja+3) {
							break;
						}
					}
				}
				
				System.out.println("Da li ste primili trecu dozu? Odgovorite sa DA ili NE");
				odgovor = sc.nextLine();
				odgovor = odgovorDaNe(odgovor, sc);
				
				if(odgovor.equals("DA")) {
					if(mesec<=6 && mesec2<=6) { //Ovim uslovom se eliminise mogucnost primanja trece doze u 2021 godini ukoliko je korisnik drugu dozu primio posle juna
						System.out.println("Koji je naziv primljene vakcine? Izaberite jednu od od ovih vakcina: ");
						System.out.println("Phizer BionTek");
						System.out.println("Sputnjik B");
						System.out.println("Sinopharm");
						System.out.println("AstraZeneca");
						vakcina3 = sc.nextLine();
						vakcina3 = proveraNazivaVakcine(vakcina3, sc);
					
						primio3 = "Primio";
					
						System.out.println("Ukucajte datum primanja tece doze.");
						System.out.println("Ukucajte u formatu MM(mesec)/dd(dan)/yyyy(godina). PAZNJA! Godina mora biti 2021!");
						date3 = sc.nextLine();
						date3 = proveraGodine(date3, sc);
						
						String[] s = date3.split("/",3);
					
						int mesec3 = Integer.parseInt(s[0]);					
						if(mesec3<mesec2+6){ //Uslov za primanje trece doze jeste 6 meseci kasnije od primanja druge doze
							while(true) {
								System.out.println("Datum primanja trece vakcine mora biti najmanje 6 meseci kasnije od primanja druge vakcine!");
								System.out.println("Pokusajte ponovo");
								date3 = sc.nextLine();
								s = date3.split("/",3);
								mesec3 = Integer.parseInt(s[0]);
								if(mesec3>=mesec2+6) {
									break;
								}
							}
						}
					}
					else {
						System.out.println("Greska!Trecu dozu niste primili u 2021 i primicete naredne godine");
						primio3 = "Nije primio";
						vakcina3 = "Nije primio 3 dozu";
						date3 = "00/00/0000";
					}	
				}	
				else {
					primio3 = "Nije primio";
					vakcina3 = "Nije primio 3 dozu";
					date3 = "00/00/0000";
				}
			}	
			else {
				vakcina2 = "Nije primio 2 dozu";
				vakcina3 = "/";
				primio2 = "Nije primio";
				primio3 = "Nije primio";
				date2 = "00/00/0000";
				date3 = "00/00/0000";
			}
		}
		else {
			vakcina = "Nije se vakcinisao";
			vakcina2 = "/";
			vakcina3 = "/";
			primio1 = "Nije primio";
			primio2 = "Nije primio";
			primio3 = "Nije primio";
			date1 = "00/00/0000";
			date2 = "00/00/0000";
			date3 = "00/00/0000";
			
		}
		
		dos.println(vakcina);
		dos.flush();
		dos.println(primio1);
		dos.flush();
		dos.println(date1);
		dos.flush();
		dos.println(vakcina2);
		dos.flush();
		dos.println(primio2);
		dos.flush();
		dos.println(date2);
		dos.flush();
		dos.println(vakcina3);
		dos.flush();
		dos.println(primio3);
		dos.flush();
		dos.println(date3);
		dos.flush();
}

public static void prijava(BufferedReader dis,PrintWriter dos,Scanner sc) throws IOException {
	while(true) {
		System.out.println("Ukucajte korisnicko ime: ");
		String korisnickoIme = sc.nextLine();
		dos.println(korisnickoIme);
		dos.flush();
		System.out.println("Ukucajte sifru: ");
		String sifra = sc.nextLine();
		dos.println(sifra);
		dos.flush();	
		boolean administrator = false;
		if(admin.getKorisnickoIme().equals(korisnickoIme) && admin.getSifra().equals(sifra)){ //Administrator!
			dos.println("Administrator");
			dos.flush();
			System.out.println("Administrator: " + admin.getIme() + " " + admin.getPrezime());
			while(true) {
				System.out.println(dis.readLine());
				System.out.println(dis.readLine());
				System.out.println(dis.readLine());
				System.out.println(dis.readLine());
				String odgAdmin = sc.nextLine();
				dos.println(odgAdmin);
				dos.flush();
				
				//--Funkcije administratora---
				
				switch(odgAdmin) {
				case "1":
					proveraIgenerisanjeCovidPropusnice(dis, dos, sc);
					break;
				case "2":
					pregledSvihKorisnika(dis);
					break;
				case "0":
					administrator = true;
					System.out.println();
					break;
				}
				
				if(administrator == true) {
					break;
				}	
			}
		}else {
			dos.println("Nije administrator");
			dos.flush();
		}
		
		if(administrator==true) { //prekida petlju ako je bio administrator , ne ulazi za analizu ostalih korisnika, vraca na pocetni meni
			break;
		}
		
		//---Ukoliko nije administrator---
		
		
		String p = dis.readLine();
		if(p.equals("Prijava je uspesna")) {
			System.out.println(p);
			dos.println("Korisnik se prijavio");
			dos.flush();
			String vakcina=dis.readLine();
			String vakcina2=dis.readLine();
			String vakcina3=dis.readLine();
			
			String date1 = null;
			String date2 = null;
			String date3 = null;
			
			int mesec=dis.read();
			int mesec2=dis.read();
			int dan = dis.read();

			GregorianCalendar d1 = new GregorianCalendar();
			d1.set(2021,mesec,dan);
			int nedelja=d1.get(GregorianCalendar.WEEK_OF_YEAR);
			
			String vkc = dis.readLine();
			String odgovor;
			int[] niz = new int[2];
			
			
			//--Prijavljeni korisnik se uopste nije vakcinisao--
			
			
			if(vkc.equals("Korisnik se nije vakcinisao")) {
				System.out.println("Da li zelite da promenite svoj odgovor u vezi primanja prve doze?");
				System.out.println("Ukucajte DA ukoliko zelite, NE u suprotnom!");
				odgovor = sc.nextLine();
				odgovor = odgovorDaNe(odgovor, sc);
				dos.println(odgovor);
				dos.flush();
				
				if(odgovor.equals("DA")) {
					String[] s = nijePrimioPrvuVakcinu(vakcina, date1, dos, sc);
					date1 = s[0];
					vakcina = s[1];
					
					niz = odredjivanjeNedeljeMeseca(date1);
					nedelja = niz[0];
					mesec = niz[1];
					
					dos.println(date1);
					dos.flush();
					
					//----Prijavljeni korisnik je promenio odgovor za 1 dozu i sad se postavlja pitanje za 2 dozu-----
					
					
					System.out.println("Da li zelite da promenite svoj odgovor u vezi primanja druge doze?");
					System.out.println("Ukucajte DA ukoliko zelite, NE u suprotnom!");
					odgovor = sc.nextLine();
					odgovor = odgovorDaNe(odgovor, sc);
					dos.println(odgovor);
					dos.flush();
					
					if(odgovor.equals("DA")) {
						date2 = nijePrimioDruguVakcinu(vakcina, nedelja, vakcina2, date2, dos, sc);
						niz = odredjivanjeNedeljeMeseca(date1);
						mesec2 = niz[1];
						
						//-----Prijavljeni korisnik je promenio odgovore za 1 i 2 dozu i postavlja se pitanje za 3 dozu-----
						
						
						System.out.println("Da li zelite da promenite svoj odgovor u vezi primanja trece doze?");
						System.out.println("Ukucajte DA ukoliko zelite, NE u suprotnom!");
						odgovor = sc.nextLine();
						odgovor = odgovorDaNe(odgovor, sc);
						
						if(mesec+1>6 || mesec2+1>6) {
							System.out.println("Greska!Trecu dozu niste primili u 2021 i primicete naredne godine");
							odgovor="NE";
						}
						
						dos.println(odgovor);
						dos.flush();
						if(odgovor.equals("DA")) {	
							nijePrimioTrecuVakcinu(vakcina3, date3, mesec2, dos, sc);
							break;
						}
						else {
							break;
						}
					}
					else {
						break;
					}
				}
				else {
					break;
				}
			
			}
			
			//--------Prijavljeni korisnik je primio 1 dozu ali nije primio drugu dozu------
			
			if(vkc.equals("Korisnik nije primio drugu dozu")) {
				System.out.println("Da li zelite da promenite svoj odgovor u vezi primanja druge doze?");
				System.out.println("Ukucajte DA ukoliko zelite, NE u suprotnom!");
				odgovor = sc.nextLine();
				odgovor = odgovorDaNe(odgovor, sc);
				
				dos.println(odgovor);
				dos.flush();
				
				if(odgovor.equals("DA")) {
					date2 = nijePrimioDruguVakcinu(vakcina, nedelja, vakcina2, date2, dos, sc);
					niz = odredjivanjeNedeljeMeseca(date2);
					mesec2 = niz[1];
					
					//-----Prijavljeni korisnik koji je primio 1 dozu promenio je odgovor za 2 dozu i postavlja se pitanje za 3 dozu--------
					
					System.out.println("Da li zelite da promenite svoj odgovor u vezi primanja trece doze?");
					System.out.println("Ukucajte DA ukoliko zelite, NE u suprotnom!");
					odgovor = sc.nextLine();
					odgovor = odgovorDaNe(odgovor, sc);
					if(mesec+1>6 || mesec2+1>6) {
						System.out.println("Greska!Trecu dozu niste primili u 2021 i primicete naredne godine");
						odgovor="NE";
					}
					
					dos.println(odgovor);
					dos.flush();
					
					if(odgovor.equals("DA")) {
						nijePrimioTrecuVakcinu(vakcina3, date3, mesec2, dos, sc);
						break;
					}
					else {
						break;
					}
					
				}
				else {
					break;
				}
			}
			
			
			//-----Prijavljeni korisnik je primio 1 i 2 dozu ali nije primio 3 dozu!--------
			
			
			
			if(vkc.equals("Korisnik nije primio trecu dozu")) {
				System.out.println("Da li zelite da promenite svoj odgovor u vezi primanja trece doze?");
				System.out.println("Ukucajte DA ukoliko zelite, NE u suprotnom!");
				odgovor = sc.nextLine();
				odgovor = odgovorDaNe(odgovor, sc);
				
				if(mesec+1>6 || mesec2+1>6) {
					System.out.println("Greska!Trecu dozu niste primili u 2021 i primicete naredne godine");
					odgovor="NE";
				}
				
				dos.println(odgovor);
				dos.flush();
				
				if(odgovor.equals("DA")) {
					nijePrimioTrecuVakcinu(vakcina3, date3, mesec2, dos, sc);
					break;
				}
				else {
					break;
				}
			}
			
			//-----Prijavljeni korisnik je primio sve tri doze------
			
			
			if(vkc.equals("Prijavljeni korisnik je primio sve tri doze")) {
				System.out.println(vkc);
				break;
			}
			
		}
		else {
			System.out.println(p);
			System.out.println("Pokusajte ponovo!");
			System.out.println("Ukucajte QUIT ako zelite da izadjete iz prijave, u suprotnom samo pritiskom na taster da NASTAVITE!");
			String quit = sc.nextLine();
			if(quit.toUpperCase().equals("QUIT")) {
				dos.println(quit);
				dos.flush();
				break;
			}
			else {
				dos.println(quit);
				dos.flush();
			}
		}
		
	}
}

public static void proveraIgenerisanjeCovidPropusnice(BufferedReader dis, PrintWriter dos, Scanner sc) throws IOException {
	System.out.println("Ukucajte JMBG: ");
	String jmbg = sc.nextLine();
	dos.println(jmbg);
	dos.flush();
	System.out.println(dis.readLine());
}

public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Socket socket = new Socket("localhost",1234);
			
			BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter dos = new PrintWriter(socket.getOutputStream());
			
			String poruka;
			boolean flag = true;
			while(flag) {
				System.out.println(dis.readLine());
				System.out.println(dis.readLine());
				System.out.println(dis.readLine());
				System.out.println(dis.readLine());
				System.out.println(dis.readLine());
				System.out.println(dis.readLine());
				System.out.println(dis.readLine());
				
				poruka = sc.nextLine();
				dos.println(poruka);
				dos.flush();
				
				switch(poruka) {
				case "1":
					registracija(dis, dos, sc);
					System.out.println(dis.readLine());
					break;
				case "2":
					prijava(dis, dos, sc);
					break;
				case "3":
					proveraIgenerisanjeCovidPropusnice(dis, dos, sc);
					break;
				case "4":
					proveraIgenerisanjeCovidPropusnice(dis, dos, sc);
					break;
				case "0":
					sc.close();
					socket.close();
					System.out.println("Konekcija je izgubljena");
					flag = false;
					break;
				}
			}
			sc.close();
			dis.close();
			dos.close();
		} catch (IOException e) {
			System.out.println("Klijent Error" + e);
		}
	}
}