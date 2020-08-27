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
		
		/* Einführung */
		pages.add("§lRegeln für den Minecraft-Server§r"+sep+sep+ 
				"Bitte lies dir die Regeln aufmerksam durch! Wenn du auf unserem Server spielst, gehen wir " + 
				"davon aus, dass du die Regeln gelesen, verstanden und akzeptiert hast. Wenn du eine Frage " + 
				"hast, oder etwas unklar ist,");
		pages.add("zögere nicht, uns anzusprechen! Wir haben versucht, die Regeln so " + 
				"einfach wie möglich zu formulieren.");
		
		
		/* Inhaltsverzeichniss */
		pages.add("§lInhaltsverzeichnis§r"+sep+sep+sep+
				"§lS.7 Kommunikation§r"+sep+sep+
				"§lS.9§r §oBeleidigungen§r"+sep+
				"§lS.11§r §oVeröffentlichung§r"+sep+
				"§lS.13§r §oLügen§r"+sep+
				"§lS.14§r Spam"+sep+
				"§lS.16§r §oGrammatik");
		pages.add(sep+sep+sep+"§lS.17 Abbauregeln§r"+sep+sep+
				"§lS.20§r §oGriefing§r"+sep+
				"§lS.22§r §oFarming§r"+sep+
				"§lS.23§r §oKreativität§r"+sep+
				"§lS.25§r §oArtig!§r"+sep+
				"§lS.26§r §oBaustil§r");
		pages.add(sep+sep+sep+"§lS.17 Gegenstände§r"+sep+sep+
				"§lS.29§r §oHandel§r"+sep+
				"§lS.30§r §oUps!§r"+sep+
				"§lS.31§r §oDrops§r"+sep+
				"§lS.33§r §oTod§r"+sep+
				"§lS.35§r §oTruhen§r");
		pages.add(sep+sep+sep+"§lS.37 Kampf§r"+sep+sep+
				"§lS.38§r §oMonster§r"+sep+
				"§lS.40§r §oSpieler§r");
		
		
		
		/* Chatverhalten */
		pages.add("§l§n§cKommunikation/Chatverhalten§r"+sep+sep+"Auf unserem Minecraft-Server gibt es verschiedene Wege zu kommunizieren. Der erste ist der Chat. Standardmäßig öffnet man diesen mit der Taste §o„t“§r. "
				+ "Alle Spieler können Nachrichten senden und empfangen.");
		pages.add("Des Weiteren kann man Nachrichten persönlich an Spieler senden mit " + 
				"§omsg, /tell, /r, /whisper, /w§r. Außerdem kann man sich mittels Schilder, Banner oder sonstiger " + 
				"Bauwerke austauschen. Die folgenden Regeln gelten für alle diese Wege.");
		
		//Chatverhalten 1
		pages.add("§l1.§r"+sep+"§lBeleidige niemanden!§r"+sep+"Dazu zählen natürlich Schimpfwörter und Kraftausdrücke, aber auch" + 
				"Witze und Sticheleien auf Kosten anderer. Was du vielleicht als §o„lustig“§r empfindest, findet dein" + 
				"Opfer vielleicht gar nicht so lustig.");
		pages.add("Anstößige Nachrichten oder Nachrichten, die die Rechte\n" + 
				"anderer verletzen, sind auch verboten. Sei also rücksichtsvoll!");
		
		//Chatverhalten 2
		pages.add("§l2."+sep+"Veröffentliche keine persönlichen Informationen!§r"+sep+"Das können zum Beispiel der Name, der"+
				"Wohnort oder die Adresse, Alter, usw. sein. Wenn eine Person diese Informationen teilen"+
				"möchte, soll sie es selbst tun. Wir hoffen, dass du die Privatsphäre deiner");
		pages.add("Mitspieler schützt!");
		
		//Chatverhalten 3
		pages.add("§l3."+sep+"Du sollst nicht lügen!§r"+sep+"Wenn du absichtlich Unwahres verbreitest, um andren Mitspielern zu " + 
				"schaden und um dir oder einer anderen Person einen Vorteil zu verschaffen, akzeptieren wir dies nicht.");
		
		//Chatverhalten 4
		pages.add("§l4."+sep+"Kein Spam und keine Werbung!§r"+sep+"Wir freuen uns, wenn du deinen Mitspielern etwas " + 
				"mitzuteilen hast. Aber bitte spamme sie bzw. den Minecraft-Chat nicht voll. Werbung darf " + 
				"generell nicht gemacht werden. Es gibt allerdings eine Ausnahme.");
		pages.add("Sollte für ein Produkt, eine " + 
				"Dienstleistung oder ein Angebot innerhalb des Serverone-Netzwerks geworben werden, ist " + 
				"dies in Maßen erlaubt.");
		
		//Chatverhalten 5
		pages.add("§l5."+sep+"Die Rechtschreibung gilt auch für dich!§r"+sep+"nWir achten auf unserem Server auf die Einhaltung " + 
				"der deutschen Rechtschreibung und Grammatik. Natürlich wirst du nicht für eine falsche " + 
				"Schreibweise gebannt.");
		pages.add("Das Server-Team behält sich jedoch das Recht vor, dir für Sprachfehler " + 
				"einen Serverone-Taler abzuziehen.");
		
		/* Bauen und Abbauen */
		pages.add("§l§n§cBauen und Abbauen§r"+sep+sep+"In Minecraft setzt man Blöcke und baut Blöcke ab. Doch damit das auch im Mehrspielermodus " + 
				"Spaß macht braucht es Regeln. Auf Serverone haben wir verschiedene Welten und somit " + 
				"verschiedene Bauregeln.");
		pages.add("Es gibt die Bauwelt. In ihr spawnst du und hast dein Haus oder deine " + 
				"Häuser. Dann gibt es noch die Farmwelt, den Nether und das Ende. Außerdem gibt es noch die " + 
				"„Special“-Welt.");
		
		//Bauen 1
		pages.add("§l1."+sep+"Griefing is not enabled on this server.§r"+sep+"In der Bauwelt darfst du nur deine Grundstücke oder " + 
				"Grundstücke, bei denen der Besitzer dir erlaubt hat, bearbeiten. Zum Schutz haben wir ein " + 
				"Plugin, das das Abbauen auf fremden");
		pages.add("Grundstücken verhindern soll. Solltest du daher ein " + 
				"Grundstück haben wollen oder jemand anderes auf deinem Grundstück mitbauen lassen " + 
				"wollen, solltest du die Berechtigungen durch die Administration umstellen lassen.");
		
		//Bauen 2
		pages.add("§l2."+sep+"Farmen ist erlaubt!§r"+sep+"In den Farmwelten, also Farmwelt, Nether und dem Ende, darfst du alles, " + 
				"bis auf den Spawn abbauen oder verändern. Das gilt auch für Bauwerke von anderen " + 
				"Spielern. Wir hoffen aber, dass du die Arbeit deiner Mitspieler zu schätzt und nichts zerstörst.");
		
		//Bauen 3
		pages.add("§l3."+sep+"Kreativität ohne Grenzen!§r"+sep+"In der „Special“-Welt wird im Kreativmodus gebaut. Auch dort " + 
				"benötigst du zum Bauen eine von den Administratoren erstellte Region. Die Bauwerke von " + 
				"anderen Spielern darfst du nicht verändern.");
		pages.add("Die einzige Ausnahme stellt die Erlaubnis dieser dar.");
		
		//Bauen 4
		pages.add("§l4."+sep+"Sei artig!§r"+sep+"Wir wollen keine anstößigen Gebäude in unserer Welt. Für Geschriebens siehe auch " + 
				"den Abschnitt „Kommunikation“.");
		
		//Bauen 5
		pages.add("§l5."+sep+"Bau realistisch und schön!§r"+sep+"Obwohl jeder seine eigene Definition von diesen zwei Begriffen " + 
				"hat, gibt es dennoch ein paar Sachen, die ganz klar eines der beiden Wörter nicht erfüllen. " + 
				"Unsere Welt soll schön aussehen!");
		
		
		/* Gegenstände */
		pages.add("§l§n§cGegenstände§r"+sep+sep+ 
				"Dieser Abschnitt beschreibt Gegenstände (engl. Items), die beim Abbauen von Blöcken, " + 
				"Herrausgeben von Werfern oder Spendern oder beim Fallenlassen der Gegenstände durch");
		pages.add("Spieler oder andere Wesen in der Minecraft-Welt.");
		
		//Gegenstände 1
		pages.add("§l1."+sep+"Handel dich reich!§r"+sep+"Wenn du mit einem anderen Spieler handelst, sind diese Gegenstände für " + 
				"den anderen Spieler bestimmt. Nicht für jemand anderen. Deswegen darfst du diese " + 
				"Gegenstände nicht aufsammeln. Sollte es dir versehentlich passiert sein, gib die Gegenstände " + 
				"bitte sofort zurück!");
		
		//Gegenstände 2
		pages.add("§l2."+sep+"Ups!§r"+sep+"Wenn dir versehentlich ein Gegenstand aus dem Inventar fällt, gehört er immer noch dir\r\n" + 
				"und niemand anderes darf ihn aufheben.");
		
		//Gegenstände 3
		pages.add("§l3."+sep+"„Drops und Mob-Loot“§r"+sep+"Durch das Besiegen von Monstern und Tieren oder das Abbauen von " + 
				"Blöcken entstehen manchmal Gegenstände. Diese sollten von der Person, die den Block bzw, " + 
				"das Wesen getötet hat, eingesammelt werden.");
		pages.add("Das ist ein Gebot der Gerechtigkeit. Diese " + 
				"Regel gilt auch für Gegenstände, die von Werfern und Spendern erzeugt wurden.");
		
		//Gegenstände 4
		pages.add("§l4."+sep+"Herzen weg - Gegenstände weg!§r"+sep+"Wenn du stirbst hast du natürlich unser Beileid, aber leider " + 
				"kein Recht auf deine verlorenen Gegenstände. Diese sind dann herrenlos und jeder kann sie " + 
				"sich aneignen. Wir hoffen aber, dass du die Gegenstände");
		pages.add("schleunigst dem Besitzer zurückgibst.");
		
		//Gegenstände 5
		pages.add("§l5."+sep+"Truhen und Kisten sind nicht immer sicher!§r"+sep+"Alle Gegenstände, die du in der Bau- oder " + 
				"„Special“-Welt aufbewahrst, dürfen nur von dir oder Grundstücksmiteigentümern genommen " + 
				"werden. Gegenstände in der Farmwelt, dem Nether und dem Ende");
		pages.add("sind nicht durch das Regelwerk geschützt.");
		
		
		/* Kampf */
		pages.add("§l§c§nKampf§r"+sep+sep+
				"Kämpfen ist nicht immer schön, aber leider manchmal notwendig. Im folgenden Abschnitt geht " + 
				"es sowohl um den Kampf mit Monstern als auch mit Spielern.");
		
		//Kampf 1
		pages.add("§l1."+sep+"Nimm dich vor den Monstern in Acht!§r"+sep+"Du darfst jederzeit gegen Monster kämpfen. Du darfst " + 
				"auch Tiere töten. Die einzige Ausnahme stellen Haustiere von anderen Spielern dar. Damit " + 
				"man normale Tiere von Haustieren unterscheiden kann, sollten diese einen mit");
		pages.add("einem Namensschild erstellten Namen haben.");
		
		//Kampf 2
		pages.add("§l2."+sep+"Die Spieler sind böse!§r"+sep+"In der Bauwelt geht’s ums Bauen. Deswegen darfst du dort eigentlich " + 
				"keine Spieler angreifen. Standardmäßig kannst du jedoch auf deinem Grundstück andere " + 
				"Spieler verletzen, um dir eine Hoheit über dein Grundstück");
		pages.add("zu geben. Du darfst das aber nicht " + 
				"als Falle verwenden (In der Bauwelt behält man seine Gegenstände nach dem Tod)! In allen " + 
				"anderen Welten darfst du andere Spieler töten. Bitte beachte, dass es manchmal " + 
				"Einschränkungen vom Plugin gibt.");
		
		/* Schluss */
		pages.add(sep+"§lVielen Dank, dass du dir unser Regelwerk für den Minecraft-Server durchgelesen hast. Wenn du " + 
				"etwas nicht verstanden hast oder etwas unklar ist, schreib uns gerne an!");
		pages.add(sep+"§lUm das Regelwerk aktuell zu halten, aktualisieren wir es in unregelmäßigen Abständen. Wir kündigen dies aber an.");
		
		meta.setTitle("Die allereinzige Wahrheit");
		meta.setAuthor("Robotix aus Robania");
		meta.setPages(pages);
		item.setItemMeta(meta);
		return item;
	}
}
