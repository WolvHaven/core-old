package net.wolvhaven.core.util;

import cloud.commandframework.Command;
import cloud.commandframework.CommandManager;
import net.wolvhaven.core.extensions.WhPlugin;
import net.wolvhaven.core.extensions.entities.WhCommandSender;

import java.util.function.Function;

public abstract class CommandModule {
  private final WhPlugin whPlugin;

  public CommandModule(final WhPlugin whPlugin) {
    this.whPlugin = whPlugin;
    this.load();
  }

  public abstract void load();

  public CommandManager<WhCommandSender> manager() {
    return this.whPlugin.commandManager();
  }

  @FunctionalInterface
  public interface CommandFunction extends Function<Command.Builder<WhCommandSender>, Command.Builder<WhCommandSender>> {}
}
