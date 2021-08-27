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
         if (FloodgateApi.getInstance().isFloodgatePlayer(uuid)) {
             FloodgatePlayer fp = FloodgateApi.getInstance().getPlayer(uuid);
             if (fp.getUiProfile() == UiProfile.POCKET) {
                 FileConfiguration config = BedrockPocketUIWarn.inst.getConfig();
                 if (config.getBoolean("kick.kick-player", false)) {
                     event.getPlayer().kickPlayer("kick.kick-message");
                 } else {
                     new BukkitRunnable() {
                         @Override
                         public void run() {
                             fp.sendForm(ModalForm.builder().title(config.getString("form.title"))
                                     .content(config.getString("form.content"))
                                     .button1(config.getString("form.button1"))
                                     .button2(config.getString("form.button2")));
                         }
                     }.runTaskLater(BedrockPocketUIWarn.inst, 60);

                 }
             }
         }
     }
}
