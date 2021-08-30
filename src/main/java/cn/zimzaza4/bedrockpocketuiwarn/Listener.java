package cn.zimzaza4.bedrockpocketuiwarn;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.geysermc.cumulus.ModalForm;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.geysermc.floodgate.util.UiProfile;

import java.util.UUID;

public class Listener implements org.bukkit.event.Listener {


     @EventHandler (priority = EventPriority.LOWEST)

    public void Join(PlayerJoinEvent event) {
         UUID uuid = event.getPlayer().getUniqueId();
         FloodgateApi fapi = FloodgateApi.getInstance();
         if (fapi.isFloodgatePlayer(uuid)) {

             FloodgatePlayer fp = fapi.getPlayer(uuid);

             if (fp.getUiProfile() == UiProfile.POCKET) {

                 FileConfiguration config = BedrockPocketUIWarn.inst.getConfig();

                 if (BedrockPocketUIWarn.inst.ConfigWarnType == BedrockPocketUIWarn.WarnType.KICK)

                     event.getPlayer().kickPlayer("kick.kick-message");

                 else if (BedrockPocketUIWarn.inst.ConfigWarnType == BedrockPocketUIWarn.WarnType.MESSAGE)

                     new BukkitRunnable(){
                         @Override
                         public void run() {
                             event.getPlayer().sendMessage(config.getString("message.message"));
                         }
                     }.runTaskLater(BedrockPocketUIWarn.inst, config.getInt("message.message-delay", 60));

                 else
                     new BukkitRunnable() {
                         @Override
                         public void run() {
                             fp.sendForm(ModalForm.builder().title(config.getString("form.title"))
                                     .content(config.getString("form.content"))
                                     .button1(config.getString("form.button1"))
                                     .button2(config.getString("form.button2")));
                         }
                     }.runTaskLater(BedrockPocketUIWarn.inst, config.getInt("form.form-delay", 60));
             }
         }
     }
}
