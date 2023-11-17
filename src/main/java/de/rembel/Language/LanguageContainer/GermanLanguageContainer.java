package de.rembel.Language.LanguageContainer;

import de.rembel.Main.PositionatorMain;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.logging.Level;

public class GermanLanguageContainer {

    public static HashMap<Integer, String> translation = new HashMap<Integer, String>();

    public GermanLanguageContainer(){
        //General
        translation.put(10, ChatColor.RED+"Schließen");
        translation.put(11, ChatColor.GOLD+"Vorheriege Seite");
        translation.put(12, ChatColor.GOLD+"Zurück");
        translation.put(13, ChatColor.GOLD+"Nächte Seite");
        translation.put(66, ChatColor.GREEN+"Aktiv");
        translation.put(73, ChatColor.RED+"Inaktiv");

        //StartMenu
        translation.put(1, ChatColor.GOLD+"Öffentliche Positions Liste");
        translation.put(2, ChatColor.GREEN+"Jeder kann diese Liste sehen");
        translation.put(3, ChatColor.GOLD+"Private Positions Lists");
        translation.put(4, ChatColor.GREEN+"Nur du kannst diese Liste sehen");
        translation.put(5, ChatColor.GOLD+"Compass verwalten");
        translation.put(6, ChatColor.GOLD+"Einstellungen");
        translation.put(7, ChatColor.RED+"Admin Einstellungen");
        translation.put(8, ChatColor.GOLD+"Positionator");

        //General Public and Private List
        translation.put(14, ChatColor.GOLD+"Filter");
        translation.put(15, ChatColor.GRAY+"Aktive Filter:");
        translation.put(16, ChatColor.GREEN+"-Spieler ");
        translation.put(17, ChatColor.GREEN+"-Dimension ");
        translation.put(18, ChatColor.GRAY+"Keine aktiven Filter!");
        translation.put(19, ChatColor.DARK_GRAY+"Links-Klick: Bearbeite Filter");
        translation.put(20, ChatColor.DARK_GRAY+"Rechts-Klick: Setzte alle Filter zurück");
        translation.put(21, ChatColor.GREEN+"Ersteller: ");
        translation.put(22, ChatColor.GREEN+"Koordinaten: ");
        translation.put(23, ChatColor.GREEN+"Dimension: ");
        translation.put(24, ChatColor.DARK_GRAY+"Links-Klick: Position einstellungen öffnen");
        translation.put(25, ChatColor.DARK_GRAY+"Shift + Links-Klick: Position in Bossbar setzten");
        //Public Position List Menu
        translation.put(9, ChatColor.GOLD+"Öffentliche Liste - Seite ");

        //Private Position List Menu
        translation.put(26, ChatColor.GOLD+"Private Liste - Seite ");
        translation.put(27, ChatColor.RED+"Ersteller: ");
        translation.put(28, ChatColor.RED+"Koordinaten: ");
        translation.put(29, ChatColor.RED+"Dimension: ");

        //Geneal Public and Private Settings Menu
        translation.put(31, ChatColor.GREEN+"Füge ");
        translation.put(33, ChatColor.DARK_GRAY+"Links-Klick: Hinzufügen");
        translation.put(34, ChatColor.DARK_GRAY+"Rechts-Klick: Öffentliche Liste öffnen");
        translation.put(35, " zur Bossbar hinzu");
        translation.put(36, ChatColor.GOLD+"Die Koordinaten werden in der Bossbar ");
        translation.put(37, ChatColor.GOLD+"angezeigt damit du sie immer sehen kannst.");
        translation.put(38, ChatColor.DARK_GRAY+"(Kann im Startmenu wieder entfernt werden)");
        translation.put(39, ChatColor.RED+"Löschen ");
        translation.put(40, ChatColor.RED+"Ersteller: ");
        translation.put(41, ChatColor.GREEN+"Umbennen");
        translation.put(42, ChatColor.GREEN+"Teleportieren");
        translation.put(43, ChatColor.GOLD+"Du wird dann zu dieser Position teleportiert");
        translation.put(49, ChatColor.RED+"Du kannst diese Position nicht bearbeiten da sie ein anderer Erstellt hat.");
        translation.put(50, "Exestiert bereits");
        translation.put(51, ChatColor.GREEN+" erfolgreich umbennant");
        translation.put(52, "Ungültig Name");
        translation.put(53, ChatColor.GOLD+"Position Umbennen");
        translation.put(54, ChatColor.RED+"Position ");
        translation.put(55, " würde erfolgreich gelöscht");
        translation.put(58, ChatColor.GREEN+"Gebe ");
        translation.put(59, ChatColor.GREEN+" ein um den Vorgang abzubrechen.");

        //Private Settings Menu
        translation.put(30, ChatColor.GOLD+"Private Einstellungen - ");
        translation.put(32, " zur öffentliche Liste");
        translation.put(56, ChatColor.RED+"Es gibt bereits eine Position in der Öffentlichen mit diesem Namen. Bennen sie um und versuche es erneut.");
        translation.put(57, ChatColor.GOLD+" wurde erfolgreich zur öffentlichen Liste hinzugefügt");

        //Public Settings Menu
        translation.put(47, ChatColor.GOLD+"Öffentliche Eistellungen - ");
        translation.put(48, " zur privaten Liste");
        translation.put(60, ChatColor.RED+"Es gibt bereits eine Position in der Privaten mit diesem Namen. Bennen sie um und versuche es erneut.");
        translation.put(61, ChatColor.GOLD+" wurde erfolgreich zur privaten Liste hinzugefügt");

        //Bossbar
        translation.put(44, ChatColor.GOLD+"Dimension: ");
        translation.put(45, ChatColor.GOLD+"Koordinaten: ");
        translation.put(46, ChatColor.GOLD+"     Entfernung: ");

        //General Filter Menu
        translation.put(63, ChatColor.GOLD+"Spielername");
        translation.put(64, ChatColor.GOLD+"Dimension");

        //Public Filter Menu
        translation.put(62, ChatColor.GOLD+"Öffentliches Filter Menu");
        translation.put(65, ChatColor.GOLD+"Öffentlicher Spielername filter ");
        translation.put(67, ChatColor.GOLD+"Öffentlicher Dimension filter");

        //Private Filter Menu
        translation.put(68, ChatColor.GOLD+"Privates Filter Menu");
        translation.put(69, ChatColor.GOLD+"Privater Spielernamen filter ");
        translation.put(70, ChatColor.GOLD+"Privater Dimension filter");

        //Settings Menu
        translation.put(71, ChatColor.GOLD+"Einstellungen");
        translation.put(72, ChatColor.GOLD+"Zeige Tode");
        translation.put(74, ChatColor.GRAY+"Wenn aktiv werden deine Todespunke");
        translation.put(75, ChatColor.GRAY+"in deiner Privaten Liste angezeigt");
        translation.put(76, ChatColor.GOLD+"Tod in Bossbar");
        translation.put(77, ChatColor.GRAY+"Wenn aktiv wird wenn du stirbt automatisch");
        translation.put(78, ChatColor.GRAY+"der Todespunkt in die Bossbar getpackt.");
        translation.put(79, ChatColor.GOLD+"Filter");
        translation.put(80, ChatColor.GRAY+"Wenn aktiv kannst du auf Filter zugreifen.");
        translation.put(81, ChatColor.GOLD+"Menu Klick Ton");
        translation.put(82, ChatColor.GRAY+"Wenn aktiv spielt der Klick ein Ton ab");

        //Admin Settings Menu
        translation.put(83, ChatColor.RED+"Admin Einstellungen");
        translation.put(84, ChatColor.GOLD+"Spieler können Positionen von anderen Spielern bearbeiten.");
        translation.put(85, ChatColor.GRAY+"Wenn aktiv können Spieler Position von anderen");
        translation.put(86, ChatColor.GRAY+"Spieler bearbeiten (Löschen, Umbennen)");
        translation.put(87, ChatColor.GOLD+"Update Nachrichten senden");
        translation.put(88, ChatColor.GRAY+"Wenn aktiv wird ihnen eine Nachricht gesenden");
        translation.put(89, ChatColor.GRAY+"sobalt es ein Plugin update gibt.");
        translation.put(90, ChatColor.GOLD+"Teleportieren");
        translation.put(91, ChatColor.GREEN+"Aktiv (nur Op)");
        translation.put(92, ChatColor.GREEN+"Aktiv (jeder)");
        translation.put(93, ChatColor.GRAY+"Wenn aktiv kannst du dich zu Position teleportieren");
        translation.put(94, ChatColor.GOLD+"Auto-Backup");
        translation.put(95, ChatColor.GRAY+"Wenn aktiv macht das Plugin automatisch Backups von");
        translation.put(96, ChatColor.GRAY+"seinen Daten. Können mit /backup abgerufen werden");

        //Confirmation
        translation.put(97, ChatColor.GOLD+"Bestätigung");
        translation.put(98, ChatColor.GREEN+"Akzeptieren");
        translation.put(99, ChatColor.RED+"Abbrechen");
        translation.put(100, ChatColor.RED+"Du bist kein Operator");

        //Add Menu
        //translation.put(101, ChatColor.GOLD+"Hinzufügen von");
        translation.put(102, ChatColor.GOLD+"Zur Öffentlichen Liste hinzufügen");
        translation.put(103, ChatColor.GOLD+"Zur Privaten Liste hinzufügen");
//        translation.put(104, ChatColor.GOLD+"Position ");
//        translation.put(105, ChatColor.GOLD+" wurde erfolgreich hinzugefügt");
        translation.put(106, ChatColor.RED+"Die Position ");
        translation.put(107, " exestiert bereits in der Öffentlichen Liste.");
        translation.put(108, " exestiert bereits in der Privaten Liste.");

        //BackUp Menu
        translation.put(109, ChatColor.GOLD+"BackUps - Seite ");
        translation.put(110, ChatColor.GOLD+"Kurz Info");
        translation.put(111, ChatColor.GREEN+"BackUps: ");
        translation.put(112, ChatColor.GREEN+"Größe von allen BackUps: ");
        translation.put(113, ChatColor.GREEN+"Erstellt: ");
        translation.put(114, ChatColor.GREEN+"Plugin Version: ");
        translation.put(115, ChatColor.GREEN+"Ersteller: ");
        translation.put(116, ChatColor.GREEN+"Grund: ");
        translation.put(117, ChatColor.GREEN+"Größe: ");
        translation.put(118, ChatColor.DARK_GRAY+"Links-Klick: BackUp laden");
        translation.put(119, ChatColor.DARK_GRAY+"Rechts-Klick: BackUp löschen");
        translation.put(120, ChatColor.GOLD+"Backup erstellen");

        //Backup load logic
        translation.put(131, ChatColor.RED+"Grund: Positionator läd ein BackUp \n");
        translation.put(132, "Du kannst in wenigen Sekunden wieder beitreten");

        //Language Menu
        translation.put(121, ChatColor.GOLD+"Sprachen");
        translation.put(122, ChatColor.GREEN+"Bald verfügbar!");
        translation.put(123, ChatColor.GREEN+"vollständig");
        translation.put(124, ChatColor.GOLD+"! Wichtig !");
        translation.put(125, ChatColor.GREEN+" Das Sprachprojekt befindet sich noch in der Beta-Phase und einige Wörter und Phrasen werden möglicherweise nicht korrekt und grammatikalisch richtig übersetzt.");
        translation.put(126, ChatColor.GREEN+" Wir bemühen uns, Fehler so schnell wie möglich zu beheben.");
        translation.put(127, ChatColor.GREEN+" Dabei sind wir auf Ihr Feedback angewiesen.");
        translation.put(128, ChatColor.GOLD+" Sende hier Feedback");
        translation.put(129, ChatColor.GOLD+"Klick mich");
        translation.put(130, ChatColor.GREEN+" Die Hauptsprache des Plugins ist Englisch.");

        int implemented = translation.size()*100/EnglishLanguageContainer.translation.size()-2;
        PositionatorMain.getPlugin().getLogger().log(Level.INFO, "[LanguageManager] loaded German with "+implemented+"% implimated ("+translation.size()+"/"+EnglishLanguageContainer.translation.size()+")");
    }
}
