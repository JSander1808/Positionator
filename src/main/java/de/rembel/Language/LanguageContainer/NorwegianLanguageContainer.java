package de.rembel.Language.LanguageContainer;

import de.rembel.Main.PositionatorMain;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.logging.Level;

public class NorwegianLanguageContainer {

    public static HashMap<Integer, String> translation = new HashMap<Integer, String>();

    public NorwegianLanguageContainer(){
        //General
        translation.put(10, ChatColor.RED+"Lukk");
        translation.put(11, ChatColor.GOLD+"Forrige side");
        translation.put(12, ChatColor.GOLD+"Tilbake");
        translation.put(13, ChatColor.GOLD+"Neste side");
        translation.put(66, ChatColor.GREEN+"Aktiv");
        translation.put(73, ChatColor.RED+"Inaktiv");

        //StartMenu
        translation.put(1, ChatColor.GOLD+"Offentlig stillingsliste");
        translation.put(2, ChatColor.GREEN+"Alle kan se denne listen");
        translation.put(3, ChatColor.GOLD+"Private stillingsliste");
        translation.put(4, ChatColor.GREEN+"Bare du kan se denne listen");
        translation.put(5, ChatColor.RED+"Fjern posisjon fra Bossbar");
        translation.put(6, ChatColor.GOLD+"Innstillinger");
        translation.put(7, ChatColor.RED+"Administratorinnstillinger");
        translation.put(8, ChatColor.GOLD+"Positionator");

        //General Public and Private List
        translation.put(14, ChatColor.GOLD+"Filter");
        translation.put(15, ChatColor.GRAY+"Aktive filtre:");
        translation.put(16, ChatColor.GREEN+"-Spiller ");
        translation.put(17, ChatColor.GREEN+"-Dimensjon ");
        translation.put(18, ChatColor.GRAY+"Ingen aktive filtre!");
        translation.put(19, ChatColor.DARK_GRAY+"Venstre-klikk: Rediger filtre");
        translation.put(20, ChatColor.DARK_GRAY+"Høyreklikk: Tilbakestill alle aktive filtre");
        translation.put(21, ChatColor.GREEN+"Skaperen: ");
        translation.put(22, ChatColor.GREEN+"Koordinater: ");
        translation.put(23, ChatColor.GREEN+"Dimensjon: ");
        translation.put(24, ChatColor.DARK_GRAY+"Venstre-klikk: Åpne posisjonsinnstillinger");
        translation.put(25, ChatColor.DARK_GRAY+"Shift + venstre-klikk: Angi posisjon i Bossbar");

        //Public Position List Menu
        translation.put(9, ChatColor.GOLD+"Offentlig liste - Side ");

        //Private Position List Menu
        translation.put(26, ChatColor.GOLD+"Privat liste - Side ");
        translation.put(27, ChatColor.RED+"Skaperen: ");
        translation.put(28, ChatColor.RED+"Coordinates: ");
        translation.put(29, ChatColor.RED+"Dimensjon: ");

        //Geneal Public and Private Settings Menu
        translation.put(31, ChatColor.GREEN+"Legg til ");
        translation.put(33, ChatColor.DARK_GRAY+"Venstre-klikk: Legg til");
        translation.put(34, ChatColor.DARK_GRAY+"Høyreklikk: Åpne offentlig liste");
        translation.put(35, " til Bossbar");
        translation.put(36, ChatColor.GOLD+" Koordinatene vises i Bossbar,");
        translation.put(37, ChatColor.GOLD+"slik at du alltid kan se dem");
        translation.put(38, ChatColor.DARK_GRAY+"(Kan slettes igjen i startmenyen)");
        translation.put(39, ChatColor.RED+"Slett ");
        translation.put(40, ChatColor.RED+"Opprettet: ");
        translation.put(41, ChatColor.GREEN+"Gi nytt navn");
        translation.put(42, ChatColor.GREEN+"Teleporter");
        translation.put(43, ChatColor.GOLD+"Du vil bli teleportert til dette punktet");
        translation.put(49, ChatColor.RED+"Du kan ikke redigere denne posisjonen fordi noen andre har opprettet den.");
        translation.put(50, "Eksisterer allerede");
        translation.put(51, ChatColor.GREEN+" omdøpt");
        translation.put(52, "Ugyldig navn");
        translation.put(53, ChatColor.GOLD+"Gi nytt navn til posisjon");
        translation.put(54, ChatColor.RED+"Posisjon ");
        translation.put(55, " ble fjernet");
        translation.put(58, ChatColor.GREEN+"Type ");
        translation.put(59, ChatColor.GREEN+" for å avbryte denne handlingen");

        //Private Settings Menu
        translation.put(30, ChatColor.GOLD+"Private innstillinger - ");
        translation.put(32, " til offentlig liste");
        translation.put(56, ChatColor.RED+"Det er allerede en stilling med dette navnet i den private listen. Gi dem nytt navn og prøv igjen!");
        translation.put(57, ChatColor.GOLD+" har blitt lagt til i din private liste");

        //Public Settings Menu
        translation.put(47, ChatColor.GOLD+"Offentlige innstillinger - ");
        translation.put(48, " til privat liste");
        translation.put(60, ChatColor.RED+"Det er allerede en stilling med dette navnet i den offentlige listen. Gi dem nytt navn og prøv igjen!");
        translation.put(61, ChatColor.GOLD+" har blitt lagt til offentlig liste");

        //Bossbar
        translation.put(44, ChatColor.GOLD+"Dimensjon: ");
        translation.put(45, ChatColor.GOLD+"Koordinater: ");
        translation.put(46, ChatColor.GOLD+"     Avstand: ");

        //General Filter Menu
        translation.put(63, ChatColor.GOLD+"Spillernavn");
        translation.put(64, ChatColor.GOLD+"Dimensjon");

        //Public Filter Menu
        translation.put(62, ChatColor.GOLD+"Offentlig filtermeny");
        //translation.put(65, ChatColor.GOLD+"Offentlig spillernavnfilter ");
        translation.put(67, ChatColor.GOLD+"Offentlig dimensjonsfilter");

        //Private Filter Menu
        translation.put(68, ChatColor.GOLD+"Privat filtermeny");
        //translation.put(69, ChatColor.GOLD+"Privat spillernavnfilter ");
        translation.put(70, ChatColor.GOLD+"Privat dimensjonsfilter");

        //Settings Menu
        translation.put(71, ChatColor.GOLD+"Innstillinger");
        translation.put(72, ChatColor.GOLD+"Vis dødens");
        translation.put(74, ChatColor.GRAY+"Når aktiv, er dødspunktene");
        translation.put(75, ChatColor.GRAY+"vises i den private listen.");
        translation.put(76, ChatColor.GOLD+"Død i Bossbar");
        translation.put(77, ChatColor.GRAY+"Når de er aktive, vil dødspunktet automatisk");
        translation.put(78, ChatColor.GRAY+"vises i bosslinjen når de dør.");
        translation.put(79, ChatColor.GOLD+"Filter");
        translation.put(80, ChatColor.GRAY+"Når du er aktiv kan du få tilgang til filtrene.");
        translation.put(81, ChatColor.GOLD+"Meny Klikk på Lyd");
        translation.put(82, ChatColor.GRAY+"Når den er aktiv, spilles en lyd av med hvert klikk.");

        //Admin Settings Menu
        translation.put(83, ChatColor.RED+"Administratorinnstillinger");
        translation.put(84, ChatColor.GOLD+"Tillat spiller å redigere posisjon fra andre spillere");
        translation.put(85, ChatColor.GRAY+"Når de er aktive, kan spillere i den offentlige");
        translation.put(86, ChatColor.GRAY+"listen redigere posisjoner fra andre spillere.");
        translation.put(87, ChatColor.GOLD+"Send oppdateringsmeldinger");
        translation.put(88, ChatColor.GRAY+"Hvis aktiv vil du få tilsendt en melding");
        translation.put(89, ChatColor.GRAY+"når det er en ny oppdatering.");
        translation.put(90, ChatColor.GOLD+"Teleporter");
        translation.put(91, ChatColor.GREEN+"Aktiv (kun OP)");
        translation.put(92, ChatColor.GREEN+"Aktiv (alle)");
        translation.put(93, ChatColor.GRAY+"Når den er aktiv, kan du teleportere til et sted");
        translation.put(94, ChatColor.GOLD+"Automatisk sikkerhetskopiering");
        translation.put(95, ChatColor.GRAY+"Når den er aktiv, sikkerhetskopierer serveren automatisk");
        translation.put(96, ChatColor.GRAY+"plugin-dataene fra Positionator-pluginen.");

        //Confirmation
        translation.put(97, ChatColor.GOLD+"Bekreftelse");
        translation.put(98, ChatColor.GREEN+"Bekrefte");
        translation.put(99, ChatColor.RED+"Avbryt");
        translation.put(100, ChatColor.RED+"Du er ikke en operatør");

        //Add Menu
        translation.put(101, ChatColor.GOLD+"Legg til ");
        translation.put(102, ChatColor.GOLD+"Legg til offentlig liste");
        translation.put(103, ChatColor.GOLD+"Legg til privat liste");
        translation.put(104, ChatColor.GOLD+"Posisjon ");
        translation.put(105, ChatColor.GOLD+" har blitt lagt til");
        translation.put(106, ChatColor.RED+"The Position ");
        translation.put(107, " finnes allerede i den offentlige listen. Vennligst slett først den eksisterende stillingen.");
        translation.put(108, " finnes allerede i den private listen. Vennligst slett først den eksisterende stillingen.");

        //BackUp Menu
        translation.put(109, ChatColor.GOLD+"Sikkerhetskopier - Side ");
        translation.put(110, ChatColor.GOLD+"Kort info");
        translation.put(111, ChatColor.GREEN+"Sikkerhetskopier: ");
        translation.put(112, ChatColor.GREEN+"Størrelse på alle sikkerhetskopier: ");
        translation.put(113, ChatColor.GREEN+"Opprettet: ");
        translation.put(114, ChatColor.GREEN+"Plugin-versjon: ");
        translation.put(115, ChatColor.GREEN+"Skaperen: ");
        translation.put(116, ChatColor.GREEN+"Grunnen til: ");
        translation.put(117, ChatColor.GREEN+"Størrelse: ");
        translation.put(118, ChatColor.DARK_GRAY+"Venstre-klikk: Last inn sikkerhetskopi");
        translation.put(119, ChatColor.DARK_GRAY+"Høyreklikk: Slett sikkerhetskopi");
        translation.put(120, ChatColor.GOLD+"Lag sikkerhetskopi");

        //Backup load logic
        translation.put(131, ChatColor.RED+"Reason: Positionator load BackUp \n");
        translation.put(132, "Du kan bli med igjen om noen sekunder");

        //Language Menu
        translation.put(121, ChatColor.GOLD+"Språk");
        translation.put(122, ChatColor.GREEN+"Kommer snart!");
        translation.put(123, ChatColor.GREEN+"implimert");
        translation.put(124, ChatColor.GOLD+"! Viktig !");
        translation.put(125, ChatColor.GREEN+" Språkprosjektet er fortsatt i betafasen og noen ord og uttrykk er kanskje ikke oversatt riktig og grammatisk korrekt.");
        translation.put(126, ChatColor.GREEN+" Vi streber etter å fikse feil så raskt som mulig.");
        translation.put(127, ChatColor.GREEN+" Vi er avhengige av din tilbakemelding.");
        translation.put(128, ChatColor.GOLD+" Send tilbakemelding her");
        translation.put(129, ChatColor.GOLD+"Klikk på meg");
        translation.put(130, ChatColor.GREEN+" Hovedspråket for plugin er engelsk.");

        int implemented = translation.size()*100/EnglishLanguageContainer.translation.size()-2;
        PositionatorMain.getPlugin().getLogger().log(Level.INFO, "[LanguageManager] loaded Norwegian with "+implemented+"% implimated ("+translation.size()+"/"+EnglishLanguageContainer.translation.size()+")");
    }
}
