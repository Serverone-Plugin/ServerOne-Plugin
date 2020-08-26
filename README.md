# ServerOne-Plugin

## Features

### ServerOneController
Das Herzstück des ServerOnePlugins ist der `ServerOneController`. Dieser tritt in Form eines Kompasses mit custom name auf. Beim erstmaligen Beitreten des Spielers auf den Server wird ihm dieser mit einem Starterset aus Steinwerkzeugen und ein wenig Essen dem Inventar hinzugefügt. Sollte das aus einem irgendeinem Grund nicht funktionieren (z. B. durch späteres Hinzufügen des Plugins kann man diesen auf mit dem Befehl `/sop getcontroller` bekommen oder durch das Craftingrezept `[" G ","GKG"," G "]` wobei *G = gold_nugget* und *K = compass* entspricht herstellt werden. Durch das Ausführen eines Rechtsklicks auf den Kompass öffnet sich ein Inventar mit folgenden Items, bei denen man durch klicken in weitere Inventare kommt.
* `Skills` (enchanting_table) -- (wip)
* `Warps` (cartography_table) -- öffnet das Warp-Inventar mit folgenden Items:
  * `0-4 Enderperlen (Warppunt 1-4)` -- durch Klicken einer dieser Perlen wird man nach drei Sekunden zu dem davor definierten Punkt teleportiert (die Anzahl der Perlen hängt von dem Warppunktlimit des Spielers ab)
  * `set Warppoints` (writable_book) -- öffnet ein Inventar mit 0-4 Auswahlmöglichkeiten. Durch dasd Auswählen einer der Papiere wird der spielerspezifische Warppunkt gesetzt 
  * `tpas` -- wip
* `Regelwerk` (book) -- öffnet beim Klicken ein Buch mit dem Regelwerk
* `Premium` (nether_star) -- öffnen das Premiuminventar mit folgenden Items: (benötigte Berechtigung: `serveroneplugin.premium`)
  * `Workbench` (crafting_table) -- öffnet beim Klicken eine Workbench
  * `EnderChest (ender_chest)` -- öfnet beim Klicken die eigene Enderchest
  * `Bank (gold_nugget)` -- öffnet beim Klicken das Bank-Inventar
### ShopSystem
--> Erklärung ausstehend <--
`wip`

### weitere Features
* `anit-spawner-breaker` -- Verhindert das versehentliche Abbauen eines Spawners
* `Todesnachrichten` -- Beim Tod eines Spielers werden zu der Standartnachricht noch die Koordinaten und die Welt des Todes hinzugefügt. 
* `Beitritts- und Verlassensnachrichten` -- Beim Beitreten oder Verlassen eines Spielers wird anstatt der Standartnachricht eine zufällige Nachchricht aus der in der `config.yml`-datei gespeicherten Liste gewählt. (Listenadresse: *messages.join*/*messages.leave*)
* `Antivoidfall` -- Teleportiert den Spieler zurück zum globalen Spawnpunkt, wenn er durch das Void Schaden erleidet und in sich der Welt mit dem in der `config.yml`-datei unter dem Pfad `World.buid` gespeicherten Namen befindet
* `Sitze` -- Durch das Ausführen eines Rechtsklicks auf eine Stufe oder eine Treppe setzt sich der Spieler auf diese
* `Effektkristalle` -- --> Erklärung ausstehend <--
* `Fahrstühle` -- --> Erklärung ausstehend <--
## Befehle
* `/sop` -- ist der Hauptbefehl des ServerOnePlugins, mit dessen Hilfe man auf die unten aufgezählten Features zugreifen kann (benötigte Berechtigung: `serveroneplugin.command.sop`)
  * `/sop spawn [bankier, bernd, wahrsagerin]` -- spawnt den ausgewählten NPC an der Position des ausführenden Spielers
  * `/sop utilinv` -- öffnet dem ausführenden Spieler ein Inventar mit folgenden Items:
    * Holzfälleraxt
    * Multiblock-Spitzhacke
    * Schulker-Schwert
    * Shopkern
    * Shop-Liste
    * Kreditkarte
  * `/sop getmoney [anzahl]` -- fügt dem ausführendem Spieler den angegebenen Betrag an Geld dem Inventar hinzu
  * `/sop getcontroller` -- fügt dem ausführendem Spieler einen *ServerOneController* dem Inventar hinzu
  * `/sop spawnshop [shopname]` -- spawnt den ausgewählten (vorher in der shops.yml-datei zu definierenden) Shop-NPC an der Position des ausführenden Spielers
  * `/sop setwarplimit [spieler] [0-4]` -- setzt dem angegebenen Spieler die Anzahl der verfügbaren Warppunkte auf den eingegebenen Betrag
  * `/sop setmainwarp [spawn, freebuild]` -- setzt den angegebenen globalen Warppunkt an der Position des ausführenden Spielers
* `/vote` -- gibt dem ausführenden Spieler einen Link aus der Konfigurationsdatei *config.yml* an der Position "settings.votelink" aus
* `/toggleop` -- wechselt den ausführenden Spieler aus dem Operator-Modus in den Nicht-Operator-Modus (benötigte Berechtigung: `serveroneplugin.command.toggleop`)
* `/sprachfehler [Spieler] [Grund](alternativ)` -- zieht dem angegebenen Spieler einen ServerOne-Taler ab (benötigte Berechtigungs: `serverone.commands.sprachfehler`)
* `/money [Spieler] ([add/set/get] [anzahl])benötigt die Berechtigung serveroneplugin.command.money` -- die Ausführung dieses Befehls ohne Argumente gibt den Kontostand des ausführenden Spielers aus. Bei einem angegebenen Argument wird der Kontostand des angegebenen Spielers angegeben. Bei Vier angegebenen Argumenten wird der Kontostand der angegebenen Person mit den Operatoren add *(hinzufügen)*, set *(setzen)* und get *(subtrahieren*) um den angegebenen Betrag modifiziert.
* `/skill` -- w.i.p.

## Benötigte Plugins
* [ServerOneSource](https://github.com/Serverone-Plugin/ServerOneSource) *(zwingend)*
* WorldEdit *(alternativ)*
* WorldGuard *(alternativ)*
