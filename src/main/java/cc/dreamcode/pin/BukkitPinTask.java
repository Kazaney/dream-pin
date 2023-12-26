package cc.dreamcode.pin;

import cc.dreamcode.pin.config.MessageConfig;
import cc.dreamcode.pin.config.PluginConfig;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BukkitPinTask implements Runnable {

    private final BukkitPinPlugin plugin;
    private final PluginConfig config;
    private final MessageConfig messageConfig;

    @Override
    public void run() {
        Bukkit.getOnlinePlayers()
                .stream()
                .filter(player -> player.hasPermission("dream.pin.usage") && !this.plugin.getAdminList().contains(player.getName()))
                .forEach(player -> {
            this.plugin.getServer().getScheduler().runTaskTimer(this.plugin,
                    () -> player.kickPlayer(messageConfig.codeWriteTimeout.getText()), config.time, config.time);
        });
    }

}
