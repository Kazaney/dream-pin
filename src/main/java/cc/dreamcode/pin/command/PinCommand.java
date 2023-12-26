package cc.dreamcode.pin.command;

import cc.dreamcode.command.bukkit.BukkitCommand;
import cc.dreamcode.pin.BukkitPinPlugin;
import cc.dreamcode.pin.config.MessageConfig;
import cc.dreamcode.pin.config.PluginConfig;
import cc.dreamcode.utilities.builder.MapBuilder;
import cc.dreamcode.utilities.bukkit.ChatUtil;
import eu.okaeri.injector.annotation.Inject;
import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PinCommand extends BukkitCommand {

    private final BukkitPinPlugin plugin;
    private final PluginConfig config;
    private final MessageConfig messageConfig;

    @Inject
    public PinCommand(@NonNull final BukkitPinPlugin plugin, final MessageConfig messageConfig, final PluginConfig config) {
        super("pin");
        this.plugin = plugin;
        this.config = config;
        this.messageConfig = messageConfig;
    }

    @Override
    public void content(@NonNull CommandSender sender, @NonNull String[] args) {

        if(!(sender instanceof final Player player)) {
            this.messageConfig.consoleRequestMessage.send(sender);
            return;
        }

        if(!player.hasPermission("dream.pin.usage")) {
            this.messageConfig.invalidPermission.send(player);
            return;
        }

       if(args.length != 1) {
           this.messageConfig.usage.send(player, new MapBuilder<String, Object>().put("usage", "/pin <kod>").build());
           return;
       }

       if(!args[0].equals(this.config.adminCode)) {
           player.kickPlayer(ChatUtil.fixColor(this.messageConfig.invalidCodeKickMessage.getText()));
           return;
       }

       if(this.plugin.getAdminList().contains(player.getName())) {
           this.messageConfig.alreadyVerifiedMessage.send(player);
           return;
       }

       // success!
       this.plugin.getAdminList().add(player.getName());
       this.messageConfig.codeValidMessage.send(player);


    }

    @Override
    public List<String> tab(@NonNull CommandSender commandSender, @NonNull String[] strings) {
        return null;
    }
}
