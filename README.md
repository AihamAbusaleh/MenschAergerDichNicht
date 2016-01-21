#*Dieses Dokument enthält Infos zu dem SE Projekt WS15*

________________________________________________________________________________
##Inhalt
__________________________________________________________________________________
*1.Allgemeine Informationen*

*2.Spielprinzip*

*3.Spielregeln*

*4.Mitwirkende*
_____________________________________________________________________________________

##1.ALLGEMEINE INFORMATION
_________________________________________________________________________________

Der Name des Projektes ist MenschAergereDichNicht vom gleichnamigen Spiel.

Das Projekt enthält das bekannte Brettspiel Mensch Aergere Dich nicht. Als Zeitfenster wurde hier ein Semester
(Also 6 Monate) vorgesehen. Das Spiel wurde in Eclipse Programmiert.
Ziel des Projektes war es, die Umsetzung einer agilen Software. Hierbei wurde Wert auf eine MVC-Schicht (Model, View, Controller),
Designpattern und selbsterklärenden Code gelegt.


__________________________________________________________________________________

##2.Spielprinzip

__________________________________________________________________________________

Das Spiel lässt sich von Github herunterladen und in Eclipse, oder auch IDEA einbinden.

Das Spiel lässt sich für bis zu vier Spieler spielen. Jeder dieser Spieler hat 4 Spielfiguren auf seinem Startfeld.
Ziel des Spiels ist es, mit allen vier Spielfiguren eine Runde zu laufen und dann in die Zielposition, genannt Haus,
zu bringen. Gespielt wird mit einem handelsüblichen Würfel.

Zu Begin des Spieles hat jeder Spieler seine Spielfiguren auf seinem Startfeld. Wenn der Spieler an der Reihe ist,
muss er eine 6 würfeln, damit eine seiner Spielfiguren auf das Spielfeld kann. Jeder Spieler hat hierfür eine
Startposition die in seiner Farbe gekennzeichnet ist. Es darf nur ein mal gewürfelt werden, wenn der Spieler noch
keine Spielfigur auf dem Spielfeld hat und die Spielfiguren entweder in der Startposition oder in der Zielposition sind.

Gewinner ist der, der zuerst seine vier Spielfiguren von der Startposition in die Zielposition bringt.

Unsere TUI Zeigt das Spielfeld an und die gewürfelten Würfelaugen. Wenn man an der Reihe ist, kannt man über einen Index seine Spielfigur bewegen. Die Indexe für das Spielfeld ist auch auf der TUI zu sehen. Man kann nur seine eigenen Spielfiguren bewegen und auch nur wenn man an der Reihe ist. Würfelt man eine 6 geht automatisch eine Spielfigur auf das Spielfeld. Aber nur wenn sich in dem eigenen Haus noch eine Spielfigur befindet.

Unsere GUI zeigt das Spielfeld in einer grafischen Oberfläche. Hier steht einem ein Würfelbutton zur verfügung. Ist ein Spieler an der Reihe klickt er einfach seine Spielfigur an mit der er fahren möchte. Ist der nächste an der Reihe muss er als erstes Würfeln. Der Spieler kann sich frei entscheiden mit welcher Spielfigur er ziehen möchte.

_________________________________________________________________________

##3.Spielregeln

_______________________________________________________________________

Es gelten folgende Regeln:
-Der jüngste Spieler darf anfangen :) 
-Gespielt wird im Uhrzeigersinn
-Es darf ein mal gewürfelt werden, sofern sich keine eigene Spielfigur auf dem Spielfeld befindet
-Wird eine sechs gewürfelt muss eine Spielfigur von der Startposition auf die Startposition gelegt werden, außer auf der
 Startposition befindet sich schon eine eigene Spielfigur.Hatte man eine sechs gewürfelt dann darf man noch einmal würfeln.
-In die Zielposition muss genau gezogen werden. Spielfiguren in der Zielposition dürfen nicht bewegt werden.
-Geht eine Spielfigur auf das gleiche Feld einer anderen wird die Spielfigur die bereits auf dem Feld steht "geschlagen"
 und somit in ihre Startposition zurück geworfen.
-Befinden sich mehrere Spielfiguren eines Spielers auf dem Spielfeld, darff gewählt werden mit welcher er ziehen will.

______________________________________________________________________________

##4.Miwirkende
___________________________________________________________________________

Das Spiel wurde von Studenten der Angewandten Informatik an der HTWG in Konstanz Programmiert.
Beteiligt an diesem Spiel waren *Patrick Fiur* und *Aiham Abusaleh*. Betreuender Professor war
*Prof. Dr. Marko Boger, der Professor mit dem Hut*. ;) 
