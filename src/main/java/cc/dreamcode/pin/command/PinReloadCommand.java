package cc.dreamcode.pin.command;

import cc.dreamcode.command.bukkit.BukkitCommand;
import cc.dreamcode.pin.config.MessageConfig;
import cc.dreamcode.pin.config.PluginConfig;
import eu.okaeri.injector.annotation.Inject;
import lombok.NonNull;
import org.bukkit.command.CommandSender;

import java.util.List;

public class PinReloadCommand extends BukkitCommand {

    private final PluginConfig config;
    private final MessageConfig messageConfig;

    @Inject
    public PinReloadCommand(@NonNull final PluginConfig config, final MessageConfig messageConfig) {
        super("pinreload");
        this.config = config;
        this.messageConfig = messageConfig;
    }

    @Override
    public void content(@NonNull CommandSender sender, @NonNull String[] strings) {

        if(!sender.hasPermission("dream.pin.reload")) {
            messageConfig.invalidPermission.send(sender);
            return;
        }

        config.load(true);
        messageConfig.load(true);
        messageConfig.configReloadedMessage.send(sender);
    }

    @Override
    public List<String> tab(@NonNull CommandSender commandSender, @NonNull String[] strings) {
        return null;
    }
}
