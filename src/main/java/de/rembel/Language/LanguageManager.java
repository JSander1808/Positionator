package de.rembel.Language;

import de.rembel.Config.NormalConfig;
import de.rembel.Language.LanguageContainer.EnglishLanguageContainer;
import de.rembel.Language.LanguageContainer.FrenchLanguageContainer;
import de.rembel.Language.LanguageContainer.GermanLanguageContainer;
import de.rembel.Language.LanguageContainer.NorwegianLanguageContainer;
import org.bukkit.entity.Player;

public class LanguageManager {

    private Player player;
    private String language;

    public LanguageManager(Player player){
        this.player=player;
        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        this.language=config.get("language");
    }

    public static void LanguageInit(){
        new EnglishLanguageContainer();
        new GermanLanguageContainer();
        new FrenchLanguageContainer();
        new NorwegianLanguageContainer();
    }

    public String transalte(int textId){
        String text = null;
        if(language.equals(Languages.ENGLISH)){
            text = EnglishLanguageContainer.translation.get(textId);
            return text;
        }
        if(language.equals(Languages.GERMAN)){
            if(GermanLanguageContainer.translation.containsKey(textId)){
                text = GermanLanguageContainer.translation.get(textId);
            }else{
                text = transalteDefaultEnglish(textId);
            }
        }
        if(language.equals(Languages.FRENCH)){
            if(FrenchLanguageContainer.translation.containsKey(textId)){
                text = FrenchLanguageContainer.translation.get(textId);
            }else{
                text = transalteDefaultEnglish(textId);
            }
        }
        if(language.equals(Languages.NORWEGIAN)){
            if(NorwegianLanguageContainer.translation.containsKey(textId)){
                text = NorwegianLanguageContainer.translation.get(textId);
            }else{
                text = transalteDefaultEnglish(textId);
            }
        }
        return text;
    }
    public String transalteDefaultEnglish(int textId){
        return EnglishLanguageContainer.translation.get(textId);
    }

    public Player getPlayer() {
        return player;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
