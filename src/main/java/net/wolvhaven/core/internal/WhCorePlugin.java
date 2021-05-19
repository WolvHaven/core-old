package net.wolvhaven.core.internal;

import cloud.commandframework.Command;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.wolvhaven.core.WhCore;
import net.wolvhaven.core.extensions.WhPlugin;
import net.wolvhaven.core.extensions.entities.WhCommandSender;
import net.wolvhaven.core.internal.commands.AdventureCommand;

import static net.wolvhaven.core.WhCore.whCore;

/**
 * The implementation of {@link WhPlugin} so Bukkit will load this as a plugin.
 * If you're writing your own plugin with this lib, extend that class.
 */
public final class WhCorePlugin extends WhPlugin {
  @Override
  public void enable() {
    new WhCorePlaceholders().register();
    new AdventureCommand(this);

    this.commandManager().command(this.baseCommand()
            .literal("reload")
            .permission("whcore.reload")
            .handler(c -> {
              this.reload();
              c.getSender().sendMessage(Component.text("Reloaded WH-Core", NamedTextColor.GREEN));
              c.getSender().playSound(WhCore.Sounds.DING);
            })
    );
  }

  @Override
  public void disable() {
  }

  public Command.Builder<WhCommandSender> baseCommand() {
    return this.commandManager().commandBuilder("whcore", "wh");
  }
}
