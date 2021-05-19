package net.wolvhaven.core.internal.commands;

import cloud.commandframework.Command;
import cloud.commandframework.arguments.flags.CommandFlag;
import cloud.commandframework.arguments.standard.StringArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.wolvhaven.core.WhCore;
import net.wolvhaven.core.extensions.entities.PlayerService;
import net.wolvhaven.core.extensions.entities.WhCommandSender;
import net.wolvhaven.core.extensions.entities.WhPlayer;
import net.wolvhaven.core.extensions.entities.WhPlayerCollection;
import net.wolvhaven.core.internal.WhCorePlugin;
import net.wolvhaven.core.util.CommandModule;
import org.bukkit.entity.Player;

import static net.wolvhaven.core.WhCore.whCore;

public class AdventureCommand extends CommandModule {
  public AdventureCommand(final WhCorePlugin plugin) {
    super(plugin);
  }

  @Override
  public void load() {
    manager().command(baseCommand()
            .literal("test")
            .argument(StringArgument.greedy("content"))
            .handler(c -> {
              Component parsed = MiniMessage.get().parse(whCore().setPlaceholderAPI(c.get("content")));
              c.getSender().sendMessage(parsed);
            })
    );

    manager().command(baseCommand()
            .literal("broadcast")
            .argument(StringArgument.quoted("content"))
            .permission("whcore.broadcast")
            .flag(CommandFlag.newBuilder("ding"))
            .flag(CommandFlag.newBuilder("perm").withArgument(StringArgument.of("perm")))
            .flag(CommandFlag.newBuilder("staff"))
            .handler(c -> {
              Component content = MiniMessage.get().parse(whCore().setPlaceholderAPI(c.get("content")));

              WhPlayerCollection players;
              if (c.flags().isPresent("staff")) players = PlayerService.staff();
              else if (c.flags().getValue("perm").isPresent()) players = PlayerService.withPermission(c.flags().getValue("perm", "randomPermission"));
              else players = PlayerService.online();

              players.sendMessage(content);
              PlayerService.console().sendMessage(content);

              if (c.flags().isPresent("ding")) players.playSound(WhCore.Sounds.DING);
            })
    );

  }

  private Command.Builder<WhCommandSender> baseCommand() {
    return manager().commandBuilder("adventure", "mm");
  }
}
