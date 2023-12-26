package cc.dreamcode.pin;

import cc.dreamcode.command.bukkit.BukkitCommandProvider;
import cc.dreamcode.menu.bukkit.okaeri.MenuBuilderSerdes;
import cc.dreamcode.notice.minecraft.bukkit.serdes.BukkitNoticeSerdes;
import cc.dreamcode.pin.command.PinCommand;
import cc.dreamcode.pin.command.PinReloadCommand;
import cc.dreamcode.pin.config.MessageConfig;
import cc.dreamcode.pin.config.PluginConfig;
import cc.dreamcode.pin.controller.BukkitPinUserController;
import cc.dreamcode.platform.DreamVersion;
import cc.dreamcode.platform.bukkit.DreamBukkitConfig;
import cc.dreamcode.platform.bukkit.DreamBukkitPlatform;
import cc.dreamcode.platform.bukkit.component.CommandComponentResolver;
import cc.dreamcode.platform.bukkit.component.ConfigurationComponentResolver;
import cc.dreamcode.platform.bukkit.component.ListenerComponentResolver;
import cc.dreamcode.platform.component.ComponentManager;
import cc.dreamcode.platform.persistence.DreamPersistence;
import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BukkitPinPlugin extends DreamBukkitPlatform implements DreamBukkitConfig, DreamPersistence {

    @Getter private static BukkitPinPlugin bukkitPinPlugin;
    @Getter private List<String> adminList;

    @Override
    public void load(@NonNull final ComponentManager componentManager) {
        bukkitPinPlugin = this;
    }

    @Override
    public void enable(@NonNull final ComponentManager componentManager) {

        adminList = new ArrayList<>();
        this.registerInjectable(BukkitCommandProvider.create(this, this.getInjector()));

        componentManager.registerResolver(CommandComponentResolver.class);
        componentManager.registerResolver(ListenerComponentResolver.class);
        componentManager.registerResolver(ConfigurationComponentResolver.class);

        componentManager.registerComponent(MessageConfig.class, messageConfig ->
                this.getInject(BukkitCommandProvider.class).ifPresent(bukkitCommandProvider -> {
                    bukkitCommandProvider.setRequiredPermissionMessage(messageConfig.invalidPermission.getText());

                    componentManager.registerComponent(PluginConfig.class, pluginConfig -> {
                        componentManager.setDebug(pluginConfig.debug);
                        Arrays.asList(
                                BukkitPinUserController.class,
                                PinCommand.class,
                                BukkitPinTask.class,
                                PinReloadCommand.class
                        ).forEach(componentManager::registerComponent);
                     });

                }));
    }

    @Override
    public void disable() {
        bukkitPinPlugin = null;
    }

    @Override
    public @NonNull DreamVersion getDreamVersion() {
        return DreamVersion.create("Dream-Pin", "1.0-InDEV", "Kazaney");
    }

    @Override
    public @NonNull OkaeriSerdesPack getConfigSerdesPack() {
        return registry -> {
            registry.register(new BukkitNoticeSerdes());
            registry.register(new MenuBuilderSerdes());
        };
    }

    @Override
    public @NonNull OkaeriSerdesPack getPersistenceSerdesPack() {
        return registry -> {
            registry.register(new SerdesBukkit());
        };
    }

}
