package cc.dreamcode.pin.config;

import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;

@Configuration(
        child = "config.yml"
)
@Header("## Dream-Pin (Main-Config) ##")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfig extends OkaeriConfig {
    @Comment("Debug pokazuje dodatkowe informacje do konsoli. Lepiej wylaczyc. :P")
    public boolean debug = true;

    @Comment({"Podaj specjalny kod admina ktory ma byc uzywany przy /pin", "Jesli gracz nie poda prawidlowego kodu zostanie wyrzucony"})
    public String adminCode = "1234";

    @Comment("Podaj czas, w ktorym administrator musi wpisac komende")
    public long time = 20L;
}
