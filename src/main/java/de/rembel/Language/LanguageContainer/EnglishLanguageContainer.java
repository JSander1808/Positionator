package de.rembel.Language.LanguageContainer;

import de.rembel.Main.PositionatorMain;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.logging.Level;

public class EnglishLanguageContainer {

    public static HashMap<Integer, String> translation = new HashMap<Integer, String>();

    public EnglishLanguageContainer(){
        //General
        translation.put(10, ChatColor.RED+"Close");
        translation.put(11, ChatColor.GOLD+"Previous Page");
        translation.put(12, ChatColor.GOLD+"Back");
        translation.put(13, ChatColor.GOLD+"Next Page");
        translation.put(66, "Active");
        translation.put(73, "Inactive");
        translation.put(146, ChatColor.GREEN+"Status");

        //StartMenu
        translation.put(1, ChatColor.GOLD+"Public Position List");
        translation.put(2, ChatColor.GREEN+"Anyone can see this list");
        translation.put(3, ChatColor.GOLD+"Private Position List");
        translation.put(4, ChatColor.GREEN+"Only you can see this list");
        translation.put(5, ChatColor.GOLD+"Compass Manager");
        translation.put(6, ChatColor.GOLD+"Settings");
        translation.put(7, ChatColor.RED+"Admin Settings");
        translation.put(8, ChatColor.GOLD+"Positionator");

        //General Public and Private List
        translation.put(14, ChatColor.GOLD+"Filter");
        translation.put(15, ChatColor.GRAY+"Active filters:");
        translation.put(16, ChatColor.GREEN+"-Player ");
        translation.put(17, ChatColor.GREEN+"-Dimension ");
        translation.put(18, ChatColor.GRAY+"No active filters!");
        translation.put(19, ChatColor.DARK_GRAY+"Left-Click: Edit filters");
        translation.put(20, ChatColor.DARK_GRAY+"Right-Click: Reset all active filters");
        translation.put(21, ChatColor.GREEN+"Creator: ");
        translation.put(22, ChatColor.GREEN+"Coordinates: ");
        translation.put(23, ChatColor.GREEN+"Dimension: ");
        translation.put(24, ChatColor.DARK_GRAY+"Left-Click: Open Position Settings");
        translation.put(25, ChatColor.DARK_GRAY+"Shift + Left-Click: Set Position in Bossbar");
        translation.put(137, ChatColor.DARK_GRAY+"Right-Click: Change Symbol");

        //Public Position List Menu
        translation.put(9, ChatColor.GOLD+"Public List - Page ");

        //Private Position List Menu
        translation.put(26, ChatColor.GOLD+"Private List - Page ");
        translation.put(27, ChatColor.RED+"Creator: ");
        translation.put(28, ChatColor.RED+"Coordinates: ");
        translation.put(29, ChatColor.RED+"Dimension: ");

        //Geneal Public and Private Settings Menu
        translation.put(31, ChatColor.GREEN+"Add ");
        translation.put(33, ChatColor.DARK_GRAY+"Left-Click: Add");
        translation.put(34, ChatColor.DARK_GRAY+"Right-Click: Open public list");
        translation.put(35, " to Bossbar");
        translation.put(36, ChatColor.GOLD+"The coordinates are displayed in the Bossbar ");
        translation.put(37, ChatColor.GOLD+"so you can always see them");
        translation.put(38, ChatColor.DARK_GRAY+"(Can be deleted again in the Compass Manager)");
        translation.put(39, ChatColor.RED+"Delete ");
        translation.put(40, ChatColor.RED+"Created: ");
        translation.put(41, ChatColor.GREEN+"Rename");
        translation.put(42, ChatColor.GREEN+"Teleport");
        translation.put(43, ChatColor.GOLD+"You will be teleported to this point");
        translation.put(49, ChatColor.RED+"You cannot edit this position because someone else created it.");
        translation.put(50, "Allready exists");
        translation.put(51, ChatColor.GREEN+" successfully renamed");
        translation.put(52, "Invalid Name");
        translation.put(53, ChatColor.GOLD+"Rename Position");
        translation.put(54, ChatColor.RED+"Position ");
        translation.put(55, " was successfully removed");
        translation.put(58, ChatColor.GREEN+"Type ");
        translation.put(59, ChatColor.GREEN+" to cancel this action");
        translation.put(138, ChatColor.GREEN+"The position ");
        translation.put(139, ChatColor.GREEN+" has been marked with ");
        translation.put(140, ChatColor.GREEN+" in your compass.");
        translation.put(141, ChatColor.RED+"This position is already on your compass");

        //Private Settings Menu
        translation.put(30, ChatColor.GOLD+"Private Settings - ");
        translation.put(32, " to public list");
        translation.put(56, ChatColor.RED+"There is already a position with this name in the Private List. Rename them and try again!");
        translation.put(57, ChatColor.GOLD+" has been successfully added to your private list");

        //Public Settings Menu
        translation.put(47, ChatColor.GOLD+"Public Settings - ");
        translation.put(48, " to private list");
        translation.put(60, ChatColor.RED+"There is already a position with this name in the Public List. Rename them and try again!");
        translation.put(61, ChatColor.GOLD+" has been successfully added to public list");

        //Bossbar
        translation.put(44, ChatColor.GOLD+"Dimension: ");
        translation.put(45, ChatColor.GOLD+"Coordinates: ");
        translation.put(46, ChatColor.GOLD+"     Distance: ");

        //General Filter Menu
        translation.put(63, ChatColor.GOLD+"Playername");
        translation.put(64, ChatColor.GOLD+"Dimension");

        //Public Filter Menu
        translation.put(62, ChatColor.GOLD+"Public Filter Menu");
        translation.put(65, ChatColor.GOLD+"Public Playername Filter ");
        translation.put(67, ChatColor.GOLD+"Public Dimension Filter");

        //Private Filter Menu
        translation.put(68, ChatColor.GOLD+"Private Filter Menu");
        translation.put(69, ChatColor.GOLD+"Private Playername Filter ");
        translation.put(70, ChatColor.GOLD+"Private Dimension Filter");

        //Settings Menu
        translation.put(71, ChatColor.GOLD+"Settings");
        translation.put(72, ChatColor.GOLD+"Show Death's");
        translation.put(74, ChatColor.GRAY+"When active, the deathpoints are");
        translation.put(75, ChatColor.GRAY+"displayed in the private list.");
        translation.put(76, ChatColor.GOLD+"Death in Bossbar");
        translation.put(77, ChatColor.GRAY+"When active, the death point will automatically");
        translation.put(78, ChatColor.GRAY+"be displayed in the boss bar when they die.");
        translation.put(79, ChatColor.GOLD+"Filter");
        translation.put(80, ChatColor.GRAY+"When active you can access the filters.");
        translation.put(81, ChatColor.GOLD+"Menu Click Sound");
        translation.put(82, ChatColor.GRAY+"When active, a sound is played with every click.");
        translation.put(133, "Pitch: ");
        translation.put(167, ChatColor.GOLD+"Position Broadcast");
        translation.put(168, ChatColor.GRAY+"Broadcast when someone adds a position");
        translation.put(169, ChatColor.DARK_GRAY+"Left-Click: Enable/Disable");
        translation.put(170, ChatColor.DARK_GRAY+"Right-Click: Change Pitch");
        translation.put(174,ChatColor.GOLD+""+ChatColor.UNDERLINE+"Enable/Disable");
        translation.put(175, ChatColor.GOLD+""+ChatColor.UNDERLINE+"Pitch");
        translation.put(178, ChatColor.RED+"Reset Compass");
        translation.put(179, ChatColor.GRAY+"Resets the compass completely  ");
        translation.put(180, ChatColor.GRAY+"(resolves all positions, resets all settings");
        translation.put(181, ChatColor.GRAY+"of the boss bar and the display)");

        //Admin Settings Menu
        translation.put(83, ChatColor.RED+"Admin Settings");
        translation.put(84, ChatColor.GOLD+"Allow Player to edit position from other player");
        translation.put(85, ChatColor.GRAY+"When active, players in the public list");
        translation.put(86, ChatColor.GRAY+"can edit positions from other players.");
        translation.put(87, ChatColor.GOLD+"Send update messages");
        translation.put(88, ChatColor.GRAY+"If active you will be sent a");
        translation.put(89, ChatColor.GRAY+"message when there is a new update.");
        translation.put(90, ChatColor.GOLD+"Teleport");
        translation.put(91, "Active (OP only)");
        translation.put(92, "Active (everyone)");
        translation.put(93, ChatColor.GRAY+"When active you can teleport to a location");
        translation.put(94, ChatColor.GOLD+"Auto BackUp");
        translation.put(95, ChatColor.GRAY+"When active, the server automatically backs up");
        translation.put(96, ChatColor.GRAY+"the plugin data from the Positionator plugin.");

        //Confirmation
        translation.put(97, ChatColor.GOLD+"Confirmation");
        translation.put(98, ChatColor.GREEN+"Confirm");
        translation.put(99, ChatColor.RED+"Cancel");
        translation.put(100, ChatColor.RED+"You are not a Operator");

        //Add Menu
        translation.put(101, ChatColor.GOLD+"Add ");
        translation.put(102, ChatColor.GOLD+"Add to public list");
        translation.put(103, ChatColor.GOLD+"Add to private list");
        translation.put(104, ChatColor.GREEN+" has added position ");
        translation.put(105, ChatColor.GREEN+" to the global list");
        translation.put(106, ChatColor.RED+"The Position ");
        translation.put(107, " allready exist in the Public List. Please delete first the existed Position.");
        translation.put(108, " allready exist in the Private List. Please delete first the existed Position.");
        translation.put(172, ChatColor.GREEN+"You added Position ");
        translation.put(173, ChatColor.GREEN+" to the private list.");

        //BackUp Menu
        translation.put(109, ChatColor.GOLD+"BackUps - Page ");
        translation.put(110, ChatColor.GOLD+"Short Info");
        translation.put(111, ChatColor.GREEN+"BackUps: ");
        translation.put(112, ChatColor.GREEN+"Size of all BackUps: ");
        translation.put(113, ChatColor.GREEN+"Created: ");
        translation.put(114, ChatColor.GREEN+"Plugin Version: ");
        translation.put(115, ChatColor.GREEN+"Creator: ");
        translation.put(116, ChatColor.GREEN+"Reason: ");
        translation.put(117, ChatColor.GREEN+"Size: ");
        translation.put(118, ChatColor.DARK_GRAY+"Left-Click: Load BackUp");
        translation.put(119, ChatColor.DARK_GRAY+"Right-Click: Delete BackUp");
        translation.put(120, ChatColor.GOLD+"Create Backup");

        //Backup load logic
        translation.put(131, ChatColor.RED+"Reason: Positionator load BackUp \n");
        translation.put(132, "You can join again in a few seconds");

        //Language Menu
        translation.put(121, ChatColor.GOLD+"Languages");
        translation.put(122, ChatColor.GREEN+"Coming soon!");
        translation.put(123, ChatColor.GREEN+"implimated");
        translation.put(124, ChatColor.GOLD+"! Important !");
        translation.put(125, ChatColor.GREEN+" The language project is still in beta phase and some words and phrases may not be translated correctly and grammatically correct.");
        translation.put(126, ChatColor.GREEN+" We strive to fix errors as quickly as possible.");
        translation.put(127, ChatColor.GREEN+" We depend on your feedback.");
        translation.put(128, ChatColor.GOLD+" Send Feedback here");
        translation.put(129, ChatColor.GOLD+"Click me");
        translation.put(130, ChatColor.GREEN+" The main language of the plugin is English.");

        //Private Symbol change Menu
        translation.put(134, ChatColor.GOLD+"Private Symbol - ");

        //Public Symbol change Menu
        translation.put(136, ChatColor.GOLD+"Public Symbol - ");

        //Symbol change Menu
        translation.put(135, ChatColor.GREEN+"Set this Symbol");

        //Compass Manager
        translation.put(142, ChatColor.GOLD+"Compass Manager");
        translation.put(143, ChatColor.GOLD+"Active Positions");
        translation.put(144, ChatColor.GRAY+"Here you can view all active positions");
        translation.put(150, ChatColor.GOLD+"Active Player");
        translation.put(151, ChatColor.GRAY+"Here you can see all the\n players you are currently rewarding \nin the Compass.");
        translation.put(171, ChatColor.DARK_GRAY+"Left-Click: Add Player to Compass");
        translation.put(176, ChatColor.GOLD+""+ChatColor.UNDERLINE+"Color");
        translation.put(177, ChatColor.GOLD+""+ChatColor.UNDERLINE+"Symbol");


        //Compass Customizer Menu
        translation.put(152,ChatColor.GOLD+"Set Compass Placeholder");
        translation.put(153,ChatColor.GOLD+""+ChatColor.UNDERLINE+"Placeholder");
        translation.put(154,ChatColor.GOLD+"Set Compass Color");
        translation.put(155,ChatColor.GREEN+"Current Color: ");
        translation.put(156, ChatColor.GOLD+"Always show compass");
        translation.put(157, ChatColor.DARK_GRAY+"Click to change");

        //Compass Position Manager Menu
        translation.put(158, ChatColor.GOLD+"Compass Positions - Page ");
        translation.put(159, ChatColor.GREEN+"Current Symbol: ");
        translation.put(160, ChatColor.GREEN+"Current Color: ");
        translation.put(161, ChatColor.DARK_GRAY+"Left-Click: Change Color");
        translation.put(162, ChatColor.DARK_GRAY+"Right-Click: Change Symbol");
        translation.put(163, ChatColor.DARK_GRAY+"Shift + Left-Click: Delete");
        translation.put(164, ChatColor.GOLD+"Select Online Player ");
        translation.put(165, ChatColor.GOLD+"Player Selector");
        translation.put(166, ChatColor.GREEN+"Here you can add Player to the Compass");

        int implemented = translation.size()*100/EnglishLanguageContainer.translation.size();
        PositionatorMain.getPlugin().getLogger().log(Level.INFO, "[LanguageManager] loaded English with "+implemented+"% implimated");
    }
}
