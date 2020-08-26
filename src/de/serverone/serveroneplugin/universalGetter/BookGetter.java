package de.serverone.serveroneplugin.universalGetter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookGetter {
	List<String> pages = new ArrayList<>();
	
	
	public static ItemStack getServerRules() {
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();
		
		List<String> pages = new ArrayList<>();
		
		final String sep = "\n";
		
		/* Einf�hrung */
		pages.add("�lRegeln f�r den Minecraft-Server�r"+sep+sep+ 
				"Bitte lies dir die Regeln aufmerksam durch! Wenn du auf unserem Server spielst, gehen wir " + 
				"davon aus, dass du die Regeln gelesen, verstanden und akzeptiert hast. Wenn du eine Frage " + 
				"hast, oder etwas unklar ist,");
		pages.add("z�gere nicht, uns anzusprechen! Wir haben versucht, die Regeln so " + 
				"einfach wie m�glich zu formulieren.");
		
		
		/* Inhaltsverzeichniss */
		pages.add("�lInhaltsverzeichnis�r"+sep+sep+sep+
				"�lS.7 Kommunikation�r"+sep+sep+
				"�lS.9�r �oBeleidigungen�r"+sep+
				"�lS.11�r �oVer�ffentlichung�r"+sep+
				"�lS.13�r �oL�gen�r"+sep+
				"�lS.14�r Spam"+sep+
				"�lS.16�r �oGrammatik");
		pages.add(sep+sep+sep+"�lS.17 Abbauregeln�r"+sep+sep+
				"�lS.20�r �oGriefing�r"+sep+
				"�lS.22�r �oFarming�r"+sep+
				"�lS.23�r �oKreativit�t�r"+sep+
				"�lS.25�r �oArtig!�r"+sep+
				"�lS.26�r �oBaustil�r");
		pages.add(sep+sep+sep+"�lS.17 Gegenst�nde�r"+sep+sep+
				"�lS.29�r �oHandel�r"+sep+
				"�lS.30�r �oUps!�r"+sep+
				"�lS.31�r �oDrops�r"+sep+
				"�lS.33�r �oTod�r"+sep+
				"�lS.35�r �oTruhen�r");
		pages.add(sep+sep+sep+"�lS.37 Kampf�r"+sep+sep+
				"�lS.38�r �oMonster�r"+sep+
				"�lS.40�r �oSpieler�r");
		
		
		
		/* Chatverhalten */
		pages.add("�l�n�cKommunikation/Chatverhalten�r"+sep+sep+"Auf unserem Minecraft-Server gibt es verschiedene Wege zu kommunizieren. Der erste ist der Chat. Standardm��ig �ffnet man diesen mit der Taste �o�t��r. "
				+ "Alle Spieler k�nnen Nachrichten senden und empfangen.");
		pages.add("Des Weiteren kann man Nachrichten pers�nlich an Spieler senden mit " + 
				"�omsg, /tell, /r, /whisper, /w�r. Au�erdem kann man sich mittels Schilder, Banner oder sonstiger " + 
				"Bauwerke austauschen. Die folgenden Regeln gelten f�r alle diese Wege.");
		
		//Chatverhalten 1
		pages.add("�l1.�r"+sep+"�lBeleidige niemanden!�r"+sep+"Dazu z�hlen nat�rlich Schimpfw�rter und Kraftausdr�cke, aber auch" + 
				"Witze und Sticheleien auf Kosten anderer. Was du vielleicht als �o�lustig��r empfindest, findet dein" + 
				"Opfer vielleicht gar nicht so lustig.");
		pages.add("Anst��ige Nachrichten oder Nachrichten, die die Rechte\n" + 
				"anderer verletzen, sind auch verboten. Sei also r�cksichtsvoll!");
		
		//Chatverhalten 2
		pages.add("�l2."+sep+"Ver�ffentliche keine pers�nlichen Informationen!�r"+sep+"Das k�nnen zum Beispiel der Name, der"+
				"Wohnort oder die Adresse, Alter, usw. sein. Wenn eine Person diese Informationen teilen"+
				"m�chte, soll sie es selbst tun. Wir hoffen, dass du die Privatsph�re deiner");
		pages.add("Mitspieler sch�tzt!");
		
		//Chatverhalten 3
		pages.add("�l3."+sep+"Du sollst nicht l�gen!�r"+sep+"Wenn du absichtlich Unwahres verbreitest, um andren Mitspielern zu " + 
				"schaden und um dir oder einer anderen Person einen Vorteil zu verschaffen, akzeptieren wir dies nicht.");
		
		//Chatverhalten 4
		pages.add("�l4."+sep+"Kein Spam und keine Werbung!�r"+sep+"Wir freuen uns, wenn du deinen Mitspielern etwas " + 
				"mitzuteilen hast. Aber bitte spamme sie bzw. den Minecraft-Chat nicht voll. Werbung darf " + 
				"generell nicht gemacht werden. Es gibt allerdings eine Ausnahme.");
		pages.add("Sollte f�r ein Produkt, eine " + 
				"Dienstleistung oder ein Angebot innerhalb des Serverone-Netzwerks geworben werden, ist " + 
				"dies in Ma�en erlaubt.");
		
		//Chatverhalten 5
		pages.add("�l5."+sep+"Die Rechtschreibung gilt auch f�r dich!�r"+sep+"nWir achten auf unserem Server auf die Einhaltung " + 
				"der deutschen Rechtschreibung und Grammatik. Nat�rlich wirst du nicht f�r eine falsche " + 
				"Schreibweise gebannt.");
		pages.add("Das Server-Team beh�lt sich jedoch das Recht vor, dir f�r Sprachfehler " + 
				"einen Serverone-Taler abzuziehen.");
		
		/* Bauen und Abbauen */
		pages.add("�l�n�cBauen und Abbauen�r"+sep+sep+"In Minecraft setzt man Bl�cke und baut Bl�cke ab. Doch damit das auch im Mehrspielermodus " + 
				"Spa� macht braucht es Regeln. Auf Serverone haben wir verschiedene Welten und somit " + 
				"verschiedene Bauregeln.");
		pages.add("Es gibt die Bauwelt. In ihr spawnst du und hast dein Haus oder deine " + 
				"H�user. Dann gibt es noch die Farmwelt, den Nether und das Ende. Au�erdem gibt es noch die " + 
				"�Special�-Welt.");
		
		//Bauen 1
		pages.add("�l1."+sep+"Griefing is not enabled on this server.�r"+sep+"In der Bauwelt darfst du nur deine Grundst�cke oder " + 
				"Grundst�cke, bei denen der Besitzer dir erlaubt hat, bearbeiten. Zum Schutz haben wir ein " + 
				"Plugin, das das Abbauen auf fremden");
		pages.add("Grundst�cken verhindern soll. Solltest du daher ein " + 
				"Grundst�ck haben wollen oder jemand anderes auf deinem Grundst�ck mitbauen lassen " + 
				"wollen, solltest du die Berechtigungen durch die Administration umstellen lassen.");
		
		//Bauen 2
		pages.add("�l2."+sep+"Farmen ist erlaubt!�r"+sep+"In den Farmwelten, also Farmwelt, Nether und dem Ende, darfst du alles, " + 
				"bis auf den Spawn abbauen oder ver�ndern. Das gilt auch f�r Bauwerke von anderen " + 
				"Spielern. Wir hoffen aber, dass du die Arbeit deiner Mitspieler zu sch�tzt und nichts zerst�rst.");
		
		//Bauen 3
		pages.add("�l3."+sep+"Kreativit�t ohne Grenzen!�r"+sep+"In der �Special�-Welt wird im Kreativmodus gebaut. Auch dort " + 
				"ben�tigst du zum Bauen eine von den Administratoren erstellte Region. Die Bauwerke von " + 
				"anderen Spielern darfst du nicht ver�ndern.");
		pages.add("Die einzige Ausnahme stellt die Erlaubnis dieser dar.");
		
		//Bauen 4
		pages.add("�l4."+sep+"Sei artig!�r"+sep+"Wir wollen keine anst��igen Geb�ude in unserer Welt. F�r Geschriebens siehe auch " + 
				"den Abschnitt �Kommunikation�.");
		
		//Bauen 5
		pages.add("�l5."+sep+"Bau realistisch und sch�n!�r"+sep+"Obwohl jeder seine eigene Definition von diesen zwei Begriffen " + 
				"hat, gibt es dennoch ein paar Sachen, die ganz klar eines der beiden W�rter nicht erf�llen. " + 
				"Unsere Welt soll sch�n aussehen!");
		
		
		/* Gegenst�nde */
		pages.add("�l�n�cGegenst�nde�r"+sep+sep+ 
				"Dieser Abschnitt beschreibt Gegenst�nde (engl. Items), die beim Abbauen von Bl�cken, " + 
				"Herrausgeben von Werfern oder Spendern oder beim Fallenlassen der Gegenst�nde durch");
		pages.add("Spieler oder andere Wesen in der Minecraft-Welt.");
		
		//Gegenst�nde 1
		pages.add("�l1."+sep+"Handel dich reich!�r"+sep+"Wenn du mit einem anderen Spieler handelst, sind diese Gegenst�nde f�r " + 
				"den anderen Spieler bestimmt. Nicht f�r jemand anderen. Deswegen darfst du diese " + 
				"Gegenst�nde nicht aufsammeln. Sollte es dir versehentlich passiert sein, gib die Gegenst�nde " + 
				"bitte sofort zur�ck!");
		
		//Gegenst�nde 2
		pages.add("�l2."+sep+"Ups!�r"+sep+"Wenn dir versehentlich ein Gegenstand aus dem Inventar f�llt, geh�rt er immer noch dir\r\n" + 
				"und niemand anderes darf ihn aufheben.");
		
		//Gegenst�nde 3
		pages.add("�l3."+sep+"�Drops und Mob-Loot��r"+sep+"Durch das Besiegen von Monstern und Tieren oder das Abbauen von " + 
				"Bl�cken entstehen manchmal Gegenst�nde. Diese sollten von der Person, die den Block bzw, " + 
				"das Wesen get�tet hat, eingesammelt werden.");
		pages.add("Das ist ein Gebot der Gerechtigkeit. Diese " + 
				"Regel gilt auch f�r Gegenst�nde, die von Werfern und Spendern erzeugt wurden.");
		
		//Gegenst�nde 4
		pages.add("�l4."+sep+"Herzen weg - Gegenst�nde weg!�r"+sep+"Wenn du stirbst hast du nat�rlich unser Beileid, aber leider " + 
				"kein Recht auf deine verlorenen Gegenst�nde. Diese sind dann herrenlos und jeder kann sie " + 
				"sich aneignen. Wir hoffen aber, dass du die Gegenst�nde");
		pages.add("schleunigst dem Besitzer zur�ckgibst.");
		
		//Gegenst�nde 5
		pages.add("�l5."+sep+"Truhen und Kisten sind nicht immer sicher!�r"+sep+"Alle Gegenst�nde, die du in der Bau- oder " + 
				"�Special�-Welt aufbewahrst, d�rfen nur von dir oder Grundst�cksmiteigent�mern genommen " + 
				"werden. Gegenst�nde in der Farmwelt, dem Nether und dem Ende");
		pages.add("sind nicht durch das Regelwerk gesch�tzt.");
		
		
		/* Kampf */
		pages.add("�l�c�nKampf�r"+sep+sep+
				"K�mpfen ist nicht immer sch�n, aber leider manchmal notwendig. Im folgenden Abschnitt geht " + 
				"es sowohl um den Kampf mit Monstern als auch mit Spielern.");
		
		//Kampf 1
		pages.add("�l1."+sep+"Nimm dich vor den Monstern in Acht!�r"+sep+"Du darfst jederzeit gegen Monster k�mpfen. Du darfst " + 
				"auch Tiere t�ten. Die einzige Ausnahme stellen Haustiere von anderen Spielern dar. Damit " + 
				"man normale Tiere von Haustieren unterscheiden kann, sollten diese einen mit");
		pages.add("einem Namensschild erstellten Namen haben.");
		
		//Kampf 2
		pages.add("�l2."+sep+"Die Spieler sind b�se!�r"+sep+"In der Bauwelt geht�s ums Bauen. Deswegen darfst du dort eigentlich " + 
				"keine Spieler angreifen. Standardm��ig kannst du jedoch auf deinem Grundst�ck andere " + 
				"Spieler verletzen, um dir eine Hoheit �ber dein Grundst�ck");
		pages.add("zu geben. Du darfst das aber nicht " + 
				"als Falle verwenden (In der Bauwelt beh�lt man seine Gegenst�nde nach dem Tod)! In allen " + 
				"anderen Welten darfst du andere Spieler t�ten. Bitte beachte, dass es manchmal " + 
				"Einschr�nkungen vom Plugin gibt.");
		
		/* Schluss */
		pages.add(sep+"�lVielen Dank, dass du dir unser Regelwerk f�r den Minecraft-Server durchgelesen hast. Wenn du " + 
				"etwas nicht verstanden hast oder etwas unklar ist, schreib uns gerne an!");
		pages.add(sep+"�lUm das Regelwerk aktuell zu halten, aktualisieren wir es in unregelm��igen Abst�nden. Wir k�ndigen dies aber an.");
		
		meta.setTitle("Die allereinzige Wahrheit");
		meta.setAuthor("Robotix aus Robania");
		meta.setPages(pages);
		item.setItemMeta(meta);
		return item;
	}
}
