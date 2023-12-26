package cc.dreamcode.pin.config;

import cc.dreamcode.notice.minecraft.MinecraftNoticeType;
import cc.dreamcode.notice.minecraft.bukkit.BukkitNotice;
import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.Headers;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;

@Configuration(
        child = "message.yml"
)
@Headers({
        @Header("## Dream-Pin (Message-Config) ##"),
        @Header("Dostepne type: (DO_NOT_SEND, CHAT, ACTION_BAR, SUBTITLE, TITLE, TITLE_SUBTITLE)")
})
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class MessageConfig extends OkaeriConfig {

    public BukkitNotice usage = new BukkitNotice(MinecraftNoticeType.CHAT, "&7Poprawne uzycie: &c{usage}");
    public BukkitNotice invalidPermission = new BukkitNotice(MinecraftNoticeType.CHAT, "&cNie posiadasz uprawnien!");
    public BukkitNotice pinAnnoucementMessage = new BukkitNotice(MinecraftNoticeType.CHAT, "&cMusisz przepisac podac specjalny kod administratora pod /pin. Nie mozesz sie pomylic bo zostaniesz wyrzucony!");
    public BukkitNotice consoleRequestMessage = new BukkitNotice(MinecraftNoticeType.CHAT, "&cMusisz byc graczem, zeby uzyc tej komendy!");
    public BukkitNotice invalidCodeKickMessage = new BukkitNotice(MinecraftNoticeType.CHAT, "&cPodales niepoprawny kod Administratora!");
    public BukkitNotice codeValidMessage = new BukkitNotice(MinecraftNoticeType.CHAT, "&aZostales pomyslnie zweryfikowany!");
    public BukkitNotice alreadyVerifiedMessage = new BukkitNotice(MinecraftNoticeType.CHAT, "&cJestes juz zweryfkowany, po co robisz to znowu :)?");
    public BukkitNotice interactionBlockedMessage = new BukkitNotice(MinecraftNoticeType.CHAT, "&cNie zostales zweryfikowany. Interakcja zablokowana");
    public BukkitNotice codeWriteTimeout = new BukkitNotice(MinecraftNoticeType.CHAT, "&cMinal czas na zweryfikowanie!");
    public BukkitNotice configReloadedMessage = new BukkitNotice(MinecraftNoticeType.CHAT, "&aPrzeladowano pomyslnie konfiguracje!");
}
