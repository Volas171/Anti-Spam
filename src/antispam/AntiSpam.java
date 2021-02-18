package antispam;

import arc.Events;
import mindustry.game.EventType.*;
import mindustry.gen.Call;
import mindustry.mod.Plugin;

import static mindustry.Vars.netServer;

public class AntiSpam extends Plugin {
    // Yes
    
    public static final String[] copypastas = {
        "Server jacked by Fire o7. Use ozone mindustry in mod browser. powerful griefing client. https://discord.gg/2tqguRj",
        "Server jacked by Fire o7", "Use Ozone", "https://discord.gg/2tqguRj", "fire o7", "o7", "server hacked", "retard",
        "Sieg Heil O7", "nig"
    };
    
    @Override
    public void init(){
        netServer.admins.addChatFilter((player, text) -> {
            for(String pasta : copypastas){
                text = text.replaceAll("(?iu)" + pasta, "");
            }

            return text;
        });

        Events.on(PlayerChatEvent.class, event -> {
            for(String pasta : copypastas){
                if(event.player.lastText.toLowerCase().contains(pasta.toLowerCase())) {
                    Call.sendMessage(event.player.name + " Was Kicked For Spamming");
                    event.player.con.kick("Go Away You Spammer", 100000 * 30);

                    break;
                }
            }
        });

        // This wont happen as name too long but oke
        Events.on(PlayerConnect.class, event -> {
            final String name = event.player.name.toLowerCase();

            for(String pasta : copypastas){
                if(name.contains(pasta.toLowerCase())){
                    Call.sendMessage(event.player.name + " Was Kicked For Spamming");
                    event.player.con.kick("Go Away You Spammer", 100000 * 30);
                    break;
                }
            }
        });
    }
}
