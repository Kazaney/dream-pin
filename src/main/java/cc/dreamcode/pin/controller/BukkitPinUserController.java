package cc.dreamcode.pin.controller;

import cc.dreamcode.pin.BukkitPinPlugin;
import cc.dreamcode.pin.config.MessageConfig;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

@RequiredArgsConstructor(onConstructor_= @Inject)
public class BukkitPinUserController implements Listener {

    private final MessageConfig messageConfig;
    private final BukkitPinPlugin plugin;

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();

        if(this.plugin.getAdminList().contains(player.getName()))
            return;

        if(player.hasPermission("dream.pin.usage")) {
            this.messageConfig.pinAnnoucementMessage.send(player);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(final PlayerInteractEvent e) {

        final Player player = e.getPlayer();
        if(this.plugin.getAdminList().contains(player.getName())) return;
        if(!player.hasPermission("dream.pin.usage")) return;

        e.setCancelled(true);
        this.messageConfig.interactionBlockedMessage.send(player);
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onPlayerChat(final AsyncPlayerChatEvent e) {

        final Player player = e.getPlayer();
        if(this.plugin.getAdminList().contains(player.getName())) return;
        if(!player.hasPermission("dream.pin.usage")) return;

        e.setCancelled(true);
        this.messageConfig.interactionBlockedMessage.send(player);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent e) {

        final Player player = e.getPlayer();
        final String message = e.getMessage();

        if(this.plugin.getAdminList().contains(player.getName())) return;
        if(!player.hasPermission("dream.pin.usage")) return;
        if(message.substring(0).contains("/pin")) return;

        e.setCancelled(true);
        this.messageConfig.interactionBlockedMessage.send(player);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(final PlayerMoveEvent e) {

        final Player player = e.getPlayer();
        if(this.plugin.getAdminList().contains(player.getName())) return;
        if(!player.hasPermission("dream.pin.usage")) return;

        // ignore mouse move
        if(e.getFrom().getBlockX() == e.getTo().getBlockX() &&
                e.getFrom().getBlockY() == e.getTo().getBlockY()
                && e.getFrom().getBlockZ() == e.getTo().getBlockZ())
            return;

        e.setCancelled(true);

        // fix move bug
        player.teleport(player);
    }


}
