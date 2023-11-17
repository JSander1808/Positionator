package de.rembel.Language.LanguageContainer;

import de.rembel.Main.PositionatorMain;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.logging.Level;

public class FrenchLanguageContainer {


    public static HashMap<Integer, String> translation = new HashMap<Integer, String>();

    public FrenchLanguageContainer(){
        //General
        translation.put(10, ChatColor.RED+"Fermer");
        translation.put(11, ChatColor.GOLD+"Page précédente");
        translation.put(12, ChatColor.GOLD+"Retour");
        translation.put(13, ChatColor.GOLD+"page des nuits");
        translation.put(66, ChatColor.GREEN+"Actif");
        translation.put(73, ChatColor.RED+"Inactif");

        //StartMenu
        translation.put(1, ChatColor.GOLD+"Liste de positions publiques");
        translation.put(2, ChatColor.GREEN+"Tout le monde peut voir cette liste");
        translation.put(3, ChatColor.GOLD+"Listes de positions privées");
        translation.put(4, ChatColor.GREEN+"Vous seul pouvez voir cette liste");
        translation.put(5, ChatColor.RED+"Supprimer la position de la barre de boss");
        translation.put(6, ChatColor.GOLD+"Paramètres");
        translation.put(7, ChatColor.RED+"Paramètres d'administration");
        translation.put(8, ChatColor.GOLD+"Positionator");

        //General Public and Private List
        translation.put(14, ChatColor.GOLD+"Filter");
        translation.put(15, ChatColor.GRAY+"Filtres actifs:");
        translation.put(16, ChatColor.GREEN+"-Joueur ");
        translation.put(17, ChatColor.GREEN+"-Dimension ");
        translation.put(18, ChatColor.GRAY+"Aucun filtre actif!");
        translation.put(19, ChatColor.DARK_GRAY+"Clic gauche : Modifier le filtre");
        translation.put(20, ChatColor.DARK_GRAY+"Clic droit : Réinitialiser tous les filtres");
        translation.put(21, ChatColor.GREEN+"Créateur: ");
        translation.put(22, ChatColor.GREEN+"Coordonnées: ");
        translation.put(23, ChatColor.GREEN+"Dimension: ");
        translation.put(24, ChatColor.DARK_GRAY+"Clic gauche: Ouvrir les paramètres de position");
        translation.put(25, ChatColor.DARK_GRAY+"Maj + clic gauche: définir la position dans la barre de boss");

        //Public Position List Menu
        translation.put(9, ChatColor.GOLD+"Liste publique - Page ");

        //Private Position List Menu
        translation.put(26, ChatColor.GOLD+"Liste privée - page ");
        translation.put(27, ChatColor.RED+"Créateur: ");
        translation.put(28, ChatColor.RED+"Coordonnées: ");
        translation.put(29, ChatColor.RED+"Dimension: ");

        //Geneal Public and Private Settings Menu
        translation.put(31, ChatColor.GREEN+"Écart ");
        translation.put(33, ChatColor.DARK_GRAY+"Clic gauche : Ajouter");
        translation.put(34, ChatColor.DARK_GRAY+"Clic droit: Ouvrir la liste publique");
        translation.put(35, " ajouté à la barre des boss");
        translation.put(36, ChatColor.GOLD+"Les coordonnées sont affichées dans la barre de ");
        translation.put(37, ChatColor.GOLD+"boss afin que vous puissiez toujours les voir.");
        translation.put(38, ChatColor.DARK_GRAY+"(Peut être supprimé à nouveau dans le menu de démarrage)");
        translation.put(39, ChatColor.RED+"Éteindre ");
        translation.put(40, ChatColor.RED+"Créateur: ");
        translation.put(41, ChatColor.GREEN+"Renommer");
        translation.put(42, ChatColor.GREEN+"se téléporter");
        translation.put(43, ChatColor.GOLD+"Vous serez alors téléporté à cet endroit");
        translation.put(49, ChatColor.RED+"Vous ne pouvez pas modifier ce poste car quelqu'un d'autre l'a créé.");
        translation.put(50, "Existe déjà");
        translation.put(51, ChatColor.GREEN+" renommé avec succès");
        translation.put(52, "Nom incorrect");
        translation.put(53, ChatColor.GOLD+"Renommer le poste");
        translation.put(54, ChatColor.RED+"Le ");
        translation.put(55, " de position a été supprimé avec succès.");
        translation.put(58, ChatColor.GREEN+"Entrez ");
        translation.put(59, ChatColor.GREEN+" pour abandonner le processus.");

        //Private Settings Menu
        translation.put(30, ChatColor.GOLD+"Paramètres privés - ");
        translation.put(32, " à la liste publique");
        translation.put(56, ChatColor.RED+"Il y a déjà un poste dans le public avec ce nom. Renommez-les et réessayez.");
        translation.put(57, ChatColor.GOLD+" a été ajouté avec succès à la liste publique");

        //Public Settings Menu
        translation.put(47, ChatColor.GOLD+"Paramètres publics - ");
        translation.put(48, "à la liste privée ");
        translation.put(56, ChatColor.RED+"Il y a déjà un poste dans Private de ce nom. Renommez-les et réessayez.");
        translation.put(57, ChatColor.GOLD+" a été ajouté avec succès à la liste privée");

        //Bossbar
        translation.put(44, ChatColor.GOLD+"Dimension: ");
        translation.put(45, ChatColor.GOLD+"Coordonnées: ");
        translation.put(46, ChatColor.GOLD+"     Distance: ");

        //General Filter Menu
        translation.put(63, ChatColor.GOLD+"nom de joueur");
        translation.put(64, ChatColor.GOLD+"Dimension");

        //Public Filter Menu
        translation.put(62, ChatColor.GOLD+"Menu de filtrage public");
        //translation.put(65, ChatColor.GOLD+"Filtre de nom de joueur public ");
        translation.put(67, ChatColor.GOLD+"Filtre de dimension publique");

        //Private Filter Menu
        translation.put(68, ChatColor.GOLD+"Menu filtre privé");
        //translation.put(69, ChatColor.GOLD+"Filtre de nom de joueur privé ");
        translation.put(70, ChatColor.GOLD+"Filtre de dimension privée");

        //Settings Menu
        translation.put(71, ChatColor.GOLD+"Paramètres");
        translation.put(72, ChatColor.GOLD+"montrer les décès");
        translation.put(74, ChatColor.GRAY+"Lorsqu'il est actif, vos points de mort ");
        translation.put(75, ChatColor.GRAY+"sont affichés dans votre liste privée");
        translation.put(76, ChatColor.GOLD+"Mort dans le bar du boss");
        translation.put(77, ChatColor.GRAY+"Lorsqu'il est actif, lorsque vous mourez, le point ");
        translation.put(78, ChatColor.GRAY+"de mort est automatiquement saisi dans la barre de boss.");
        translation.put(79, ChatColor.GOLD+"Filter");
        translation.put(80, ChatColor.GRAY+"Lorsqu'il est actif, vous pouvez accéder aux filtres.");
        translation.put(81, ChatColor.GOLD+"Son de clic de menu");
        translation.put(82, ChatColor.GRAY+"Lorsqu'il est actif, le clic joue une tonalité");

        //Admin Settings Menu
        translation.put(83, ChatColor.RED+"Paramètres d'administration");
        translation.put(84, ChatColor.GOLD+"Les joueurs peuvent modifier les positions des autres joueurs.");
        translation.put(85, ChatColor.GRAY+"Quand les joueurs actifs peuvent modifier la ");
        translation.put(86, ChatColor.GRAY+"position des autres joueurs (supprimer, renommer)");
        translation.put(87, ChatColor.GOLD+"Envoyer des messages de mise à jour");
        translation.put(88, ChatColor.GRAY+"S'il est actif, un message vous sera envoyé ");
        translation.put(89, ChatColor.GRAY+"dès qu'il y aura une mise à jour du plugin.");
        translation.put(90, ChatColor.GOLD+"se téléporter");
        translation.put(91, ChatColor.GREEN+"Actif (Op uniquement)");
        translation.put(92, ChatColor.GREEN+"Actif (tout le monde)");
        translation.put(93, ChatColor.GRAY+"Lorsqu'il est actif, vous pouvez vous téléporter à l'emplacement");
        translation.put(94, ChatColor.GOLD+"Sauvegarde automatique");
        translation.put(95, ChatColor.GRAY+"Lorsqu'il est actif, le plugin effectue automatiquement des ");
        translation.put(96, ChatColor.GRAY+"sauvegardes de ses données. Peut être récupéré avec /backup");

        //Confirmation
        translation.put(97, ChatColor.GOLD+"Confirmation");
        translation.put(98, ChatColor.GREEN+"Accepter");
        translation.put(99, ChatColor.RED+"Interrompre");
        translation.put(100, ChatColor.RED+"Vous n'êtes pas un opérateur");

        //Add Menu
        //translation.put(101, ChatColor.GOLD+"Ajouter à partir de");
        translation.put(102, ChatColor.GOLD+"Ajouter à la liste publique");
        translation.put(103, ChatColor.GOLD+"Ajouter à la liste privée");
//        translation.put(104, ChatColor.GOLD+"Le ");
//        translation.put(105, ChatColor.GOLD+" de position a été ajouté avec succès");
        translation.put(106, ChatColor.RED+"Le ");
        translation.put(107, " de position existe déjà dans la liste publique.");
        translation.put(108, " de position existe déjà dans la liste privée.");

        //BackUp Menu
        translation.put(109, ChatColor.GOLD+"Sauvegardes - page ");
        translation.put(110, ChatColor.GOLD+"Infos courtes");
        translation.put(111, ChatColor.GREEN+"Sauvegardes: ");
        translation.put(112, ChatColor.GREEN+"taille de toutes les sauvegardes: ");
        translation.put(113, ChatColor.GREEN+"Créé: ");
        translation.put(114, ChatColor.GREEN+"version du plug-in: ");
        translation.put(115, ChatColor.GREEN+"Créateur: ");
        translation.put(116, ChatColor.GREEN+"Sol: ");
        translation.put(117, ChatColor.GREEN+"Taille: ");
        translation.put(118, ChatColor.DARK_GRAY+"Clic gauche: charger la sauvegarde");
        translation.put(119, ChatColor.DARK_GRAY+"Clic droit: supprimer la sauvegarde");
        translation.put(120, ChatColor.GOLD+"créer une sauvegarde");

        //Backup load logic
        translation.put(131, ChatColor.RED+"Raison : Sauvegarde du chargement du Positionator \n");
        translation.put(132, "Vous pouvez vous réinscrire dans quelques secondes");

        //Language Menu
        translation.put(121, ChatColor.GOLD+"Langues");
        translation.put(122, ChatColor.GREEN+"Bientôt disponible!");
        translation.put(123, ChatColor.GREEN+"complet");
        translation.put(124, ChatColor.GOLD+"! Important !");
        translation.put(125, ChatColor.GREEN+" Le projet de langue est encore en phase bêta et certains mots et expressions peuvent ne pas être traduits correctement et grammaticalement corrects.");
        translation.put(126, ChatColor.GREEN+" Nous nous efforçons de corriger les erreurs le plus rapidement possible.");
        translation.put(127, ChatColor.GREEN+" Nous dépendons de vos commentaires.");
        translation.put(128, ChatColor.GOLD+" Envoyez vos commentaires ici");
        translation.put(129, ChatColor.GOLD+"Cliquez sur moi");
        translation.put(130, ChatColor.GREEN+" La langue principale du plugin est l'anglais.");

        int implemented = translation.size()*100/EnglishLanguageContainer.translation.size()-2;
        PositionatorMain.getPlugin().getLogger().log(Level.INFO, "[LanguageManager] loaded French with "+implemented+"% implimated ("+translation.size()+"/"+EnglishLanguageContainer.translation.size()+")");
    }
}
