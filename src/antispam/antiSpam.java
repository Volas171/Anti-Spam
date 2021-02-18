package antispam;

import arc.*;
import mindustry.game.EventType.*;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.mod.*;

import static mindustry.Vars.*;
import static mindustry.Vars.player;

public class antiSpam extends Plugin{
    //this is the most curses I've ever typed
    public static final String[] copypastas = {"Server jacked by Fire o7. Use ozone mindustry in mod browser. powerful griefing client. https://discord.gg/2tqguRj", "Server jacked by Fire o7", "Use Ozone", "https://discord.gg/2tqguRj", "fire o7", "o7", "server hacked", "retard", "Sieg Heil O7", "nig"};
    
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
            if(event.player.lastText.toLowerCase().contains(pasta)) {
                Call.sendMessage(player.con + "Was Kicked For Spamming");
                event.player.con.kick("Go Away You Spammer", 100000 * 30);

                break;
            }}
            });
        Events.on(PlayerConnect.class, event -> {
            for(String pasta : copypastas){
                if(event.player.name.toLowerCase().contains(pasta)){
                    Call.sendMessage(player.con+"Was Kicked For Spamming");
                    event.player.con.kick("Go Away You Spammer", 100000 * 30);
                    break;
                }
            }
        });
    }
}
