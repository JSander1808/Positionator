package de.rembel.Menus;

import de.rembel.CBossbar.CBossbar;
import de.rembel.CBossbar.CPosition;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.Language.LanguageManager;
import de.rembel.Main.PositionatorMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class CompassManagerMenu {

    public CompassManagerMenu(Player player) {
        LanguageManager language = new LanguageManager(player);
        Inventory inv = Bukkit.createInventory(null, 6 * 9, language.transalte(142));
        player.openInventory(inv);
        CBossbar compass = CBossbar.getByPlayer(player);
        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");

        for (int i = 0; i < 9 * 6; i++) {
            inv.setItem(i, placeholder());
        }

        for (int i = 9; i < 15; i++) {
            inv.setItem(i, null);
        }
        for (int i = 16; i < 18; i++) {
            inv.setItem(i, null);
        }

        for (int i = 18; i < 24; i++) {
            inv.setItem(i, null);
        }
        for (int i = 25; i < 27; i++) {
            inv.setItem(i, null);
        }

        for (int i = 27; i < 33; i++) {
            inv.setItem(i, null);
        }
        for (int i = 34; i < 36; i++) {
            inv.setItem(i, null);
        }

        ItemStack positions = new ItemStack(Material.TRAPPED_CHEST);
        ItemMeta positionsMeta = positions.getItemMeta();
        positionsMeta.setDisplayName(language.transalte(143));
        ArrayList positionsLore = new ArrayList();
        positionsLore.add(language.transalte(144));
        positionsMeta.setLore(positionsLore);
        positions.setItemMeta(positionsMeta);

        ItemStack players = new ItemStack(Material.ARMOR_STAND);
        ItemMeta playersMeta = players.getItemMeta();
        playersMeta.setDisplayName(language.transalte(150));
        ArrayList playersLore = new ArrayList();
        playersLore.add(language.transalte(151));
        playersLore.add(language.transalte(171));
        playersMeta.setLore(playersLore);
        players.setItemMeta(playersMeta);

        int usedPositions = 0;
        ArrayList<CPosition> StoredPositions = getPositionsWithoutEntitys(compass);
        for (int i = 9; i < 33; i++) {
            if (StoredPositions.size() <= usedPositions) break;
            if (i == 15) i += 3;
            if (i == 24) i += 3;
            CPosition position = StoredPositions.get(usedPositions);

            ItemStack positionItem = new ItemStack(Material.CHEST);
            ItemMeta positionItemMeta = positionItem.getItemMeta();
            positionItemMeta.setDisplayName(ChatColor.GOLD+position.getDescription());

            ArrayList entityPositionItemLore = new ArrayList();
            entityPositionItemLore.add("");
            entityPositionItemLore.add("   "+language.transalte(176));
            if(position.getColor().equals(ChatColor.RED)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.RED+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.RED+"■");
            }
            if(position.getColor().equals(ChatColor.BLUE)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.BLUE+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.BLUE+"■");
            }
            if(position.getColor().equals(ChatColor.YELLOW)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.YELLOW+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.YELLOW+"■");
            }
            if(position.getColor().equals(ChatColor.DARK_PURPLE)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.DARK_PURPLE+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.DARK_PURPLE+"■");
            }
            if(position.getColor().equals(ChatColor.LIGHT_PURPLE)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.LIGHT_PURPLE+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.LIGHT_PURPLE+"■");
            }
            if(position.getColor().equals(ChatColor.AQUA)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.AQUA+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.AQUA+"■");
            }
            if(position.getColor().equals(ChatColor.GOLD)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GOLD+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.GOLD+"■");
            }

            entityPositionItemLore.add("");
            entityPositionItemLore.add("   "+language.transalte(177));
            if(position.getSymbol().equals("⌖")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"⌖");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"⌖");
            }
            if(position.getSymbol().equals("\uD83D\uDC80")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"\uD83D\uDC80");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"\uD83D\uDC80");
            }
            if(position.getSymbol().equals("\uD83C\uDFE0")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"\uD83C\uDFE0");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"\uD83C\uDFE0");
            }
            if(position.getSymbol().equals("⚠")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"⚠");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"⚠");
            }
            if(position.getSymbol().equals("\uD83D\uDCC5")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"\uD83D\uDCC5");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"\uD83D\uDCC5");
            }
            if(position.getSymbol().equals("\uD83C\uDFAE")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"\uD83C\uDFAE");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"\uD83C\uDFAE");
            }
            entityPositionItemLore.add("");
            entityPositionItemLore.add(language.transalte(161));
            entityPositionItemLore.add(language.transalte(162));
            entityPositionItemLore.add(language.transalte(163));


            entityPositionItemLore.add("");
            positionItemMeta.setLore(entityPositionItemLore);
            positionItem.setItemMeta(positionItemMeta);
            inv.setItem(i, positionItem);
            usedPositions++;
        }

        int usedEntityPositions = 0;
        ArrayList<CPosition> StoredEntityPositions = getOnlyEntityPositions(compass);
        for(int i = 16;i<36;i++){
            if(StoredEntityPositions.size() <= usedEntityPositions) break;
            if(i==18) i += 7;
            if(i==27) i += 7;
            CPosition position = StoredEntityPositions.get(usedEntityPositions);

            ItemStack entityPosition = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta entityPositionMeta = (SkullMeta) entityPosition.getItemMeta();
            entityPositionMeta.setOwner(ChatColor.stripColor(position.getDescription()));
            entityPositionMeta.setDisplayName(position.getDescription());
            ArrayList entityPositionItemLore = new ArrayList();
            entityPositionItemLore.add("");
            entityPositionItemLore.add("   "+language.transalte(176));
            if(position.getColor().equals(ChatColor.RED)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.RED+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.RED+"■");
            }
            if(position.getColor().equals(ChatColor.BLUE)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.BLUE+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.BLUE+"■");
            }
            if(position.getColor().equals(ChatColor.YELLOW)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.YELLOW+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.YELLOW+"■");
            }
            if(position.getColor().equals(ChatColor.DARK_PURPLE)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.DARK_PURPLE+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.DARK_PURPLE+"■");
            }
            if(position.getColor().equals(ChatColor.LIGHT_PURPLE)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.LIGHT_PURPLE+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.LIGHT_PURPLE+"■");
            }
            if(position.getColor().equals(ChatColor.AQUA)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.AQUA+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.AQUA+"■");
            }
            if(position.getColor().equals(ChatColor.GOLD)){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GOLD+"■");
            }else{
                entityPositionItemLore.add("   "+ChatColor.GOLD+"■");
            }

            entityPositionItemLore.add("");
            entityPositionItemLore.add("   "+language.transalte(177));
            if(position.getSymbol().equals("⌖")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"⌖");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"⌖");
            }
            if(position.getSymbol().equals("\uD83D\uDC80")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"\uD83D\uDC80");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"\uD83D\uDC80");
            }
            if(position.getSymbol().equals("\uD83C\uDFE0")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"\uD83C\uDFE0");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"\uD83C\uDFE0");
            }
            if(position.getSymbol().equals("⚠")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"⚠");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"⚠");
            }
            if(position.getSymbol().equals("\uD83D\uDCC5")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"\uD83D\uDCC5");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"\uD83D\uDCC5");
            }
            if(position.getSymbol().equals("\uD83C\uDFAE")){
                entityPositionItemLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"\uD83C\uDFAE");
            }else{
                entityPositionItemLore.add(ChatColor.GRAY+"   "+"\uD83C\uDFAE");
            }
            entityPositionItemLore.add("");
            entityPositionItemLore.add(language.transalte(161));
            entityPositionItemLore.add(language.transalte(162));
            entityPositionItemLore.add(language.transalte(163));


            entityPositionItemLore.add("");
            entityPositionMeta.setLore(entityPositionItemLore);
            entityPosition.setItemMeta(entityPositionMeta);
            inv.setItem(i, entityPosition);
            usedEntityPositions++;
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(language.transalte(10));
        close.setItemMeta(closemeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(language.transalte(12));
        back.setItemMeta(backmeta);

        ItemStack placeholder = new ItemStack(Material.NAME_TAG);
        ItemMeta placeholderMeta = placeholder.getItemMeta();

        ArrayList placeholderLore = new ArrayList();

        placeholderLore.add("");
        placeholderLore.add("   "+language.transalte(153));
        if(config.get("compassPlaceholder").equals("*")){
            placeholderLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"*");
        }else{
            placeholderLore.add(ChatColor.GRAY+"   "+"*");
        }
        if(config.get("compassPlaceholder").equals("|")){
            placeholderLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"|");
        }else{
            placeholderLore.add(ChatColor.GRAY+"   "+"|");
        }
        if(config.get("compassPlaceholder").equals("-")){
            placeholderLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"-");
        }else{
            placeholderLore.add(ChatColor.GRAY+"   "+"-");
        }
        if(config.get("compassPlaceholder").equals("•")){
            placeholderLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"•");
        }else{
            placeholderLore.add(ChatColor.GRAY+"   "+"•");
        }

        placeholderLore.add("");
        placeholderMeta.setLore(placeholderLore);
        placeholderMeta.setDisplayName(language.transalte(152));
        placeholder.setItemMeta(placeholderMeta);

        ItemStack color = null;
        if(config.get("compassBossbarColor").equals("white")) color = new ItemStack(Material.WHITE_WOOL);
        if(config.get("compassBossbarColor").equals("red")) color = new ItemStack(Material.RED_WOOL);
        if(config.get("compassBossbarColor").equals("green")) color = new ItemStack(Material.GREEN_WOOL);
        if(config.get("compassBossbarColor").equals("blue")) color = new ItemStack(Material.BLUE_WOOL);
        if(config.get("compassBossbarColor").equals("purple")) color = new ItemStack(Material.PURPLE_WOOL);
        if(config.get("compassBossbarColor").equals("yellow")) color = new ItemStack(Material.YELLOW_WOOL);
        if(config.get("compassBossbarColor").equals("pink")) color = new ItemStack(Material.PINK_WOOL);
        ItemMeta colorMeta = color.getItemMeta();
        colorMeta.setDisplayName(language.transalte(154));

        ArrayList colorLore = new ArrayList();
        colorLore.add("");
        colorLore.add("   "+language.transalte(176));
        if(config.get("compassBossbarColor").equals("white")){
            colorLore.add(ChatColor.GREEN+"➜ "+ChatColor.WHITE+"■");
        }else{
            colorLore.add("   "+ChatColor.WHITE+"■");
        }
        if(config.get("compassBossbarColor").equals("red")){
            colorLore.add(ChatColor.GREEN+"➜ "+ChatColor.RED+"■");
        }else{
            colorLore.add("   "+ChatColor.RED+"■");
        }
        if(config.get("compassBossbarColor").equals("green")){
            colorLore.add(ChatColor.GREEN+"➜ "+ChatColor.GREEN+"■");
        }else{
            colorLore.add("   "+ChatColor.GREEN+"■");
        }
        if(config.get("compassBossbarColor").equals("blue")){
            colorLore.add(ChatColor.GREEN+"➜ "+ChatColor.BLUE+"■");
        }else{
            colorLore.add("   "+ChatColor.BLUE+"■");
        }
        if(config.get("compassBossbarColor").equals("purple")){
            colorLore.add(ChatColor.GREEN+"➜ "+ChatColor.DARK_PURPLE+"■");
        }else{
            colorLore.add("   "+ChatColor.DARK_PURPLE+"■");
        }
        if(config.get("compassBossbarColor").equals("yellow")){
            colorLore.add(ChatColor.GREEN+"➜ "+ChatColor.YELLOW+"■");
        }else{
            colorLore.add("   "+ChatColor.YELLOW+"■");
        }
        if(config.get("compassBossbarColor").equals("pink")){
            colorLore.add(ChatColor.GREEN+"➜ "+ChatColor.LIGHT_PURPLE+"■");
        }else{
            colorLore.add("   "+ChatColor.LIGHT_PURPLE+"■");
        }

        colorLore.add("");

        colorMeta.setLore(colorLore);
        color.setItemMeta(colorMeta);

        ItemStack enableCompass = new ItemStack(Material.COMPASS);
        ItemMeta enableCompassMeta = enableCompass.getItemMeta();

        ArrayList enableCompassLore = new ArrayList();
        enableCompassLore.add("");
        enableCompassLore.add("   "+language.transalte(174));
        if(config.getBoolean("compassAlwaysActive")){
            enableCompassLore.add(ChatColor.GREEN+"➜ "+language.transalte(66));
        }else{
            enableCompassLore.add(ChatColor.GRAY+"   "+language.transalte(66));
        }
        if(!config.getBoolean("compassAlwaysActive")){
            enableCompassLore.add(ChatColor.GREEN+"➜ "+ChatColor.RED+language.transalte(73));
        }else{
            enableCompassLore.add(ChatColor.GRAY+"   "+language.transalte(73));
        }
        enableCompassLore.add("");

        enableCompassMeta.setLore(enableCompassLore);
        enableCompassMeta.setDisplayName(language.transalte(156));
        enableCompass.setItemMeta(enableCompassMeta);

        inv.setItem(0, positions);
        inv.setItem(7, players);
        inv.setItem(46, placeholder);
        inv.setItem(48, color);
        inv.setItem(50, enableCompass);
        inv.setItem(53, close);
        inv.setItem(52,back);
    }

    public ArrayList<CPosition> getPositionsWithoutEntitys(CBossbar compass){
        ArrayList list = new ArrayList();
        if(compass != null){
            for(int i = 0;i<compass.getPositions().size();i++){
                if(compass.getPositions().get(i).getEntity()==null){
                    list.add(compass.getPositions().get(i));
                }
            }
        }
        return list;
    }

    public ArrayList<CPosition> getOnlyEntityPositions(CBossbar compass){
        ArrayList list = new ArrayList();
        if(compass!=null){
            for(int i = 0;i<compass.getPositions().size();i++){
                if(compass.getPositions().get(i).getEntity()!=null){
                    list.add(compass.getPositions().get(i));
                }
            }
        }

        return list;
    }

    public ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }

    public ChatColor getChatColor(String color) {
        for(ChatColor c : ChatColor.class.getEnumConstants()) {
            if(c.name().equalsIgnoreCase(color)) {
                return c;
            }
        }
        return null;
    }
}
