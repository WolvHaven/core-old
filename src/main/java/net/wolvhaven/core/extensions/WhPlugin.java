package net.wolvhaven.core.extensions;

import cloud.commandframework.CommandManager;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import net.wolvhaven.core.WhCore;
import net.wolvhaven.core.extensions.entities.PlayerService;
import net.wolvhaven.core.extensions.entities.WhCommandSender;
import net.wolvhaven.core.extensions.entities.WhPlayer;
import net.wolvhaven.core.util.LoggerHolder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

public abstract class WhPlugin extends JavaPlugin implements LoggerHolder {
  private CommandManager<WhCommandSender> manager;

  @Override
  public final void onEnable() {
    try {
      this.manager = new PaperCommandManager<>(this,
              CommandExecutionCoordinator.simpleCoordinator(),
              PlayerService::of,
              WhCommandSender::commandSender);
    } catch (Exception e) {
      logger().error("Error while initializing Command Manager", e);
      this.setEnabled(false);
      return;
    }

    enable();
  }

  @Override
  public final void onDisable() {
    disable();
    this.manager = null;
  }

  @Override
  public Logger logger() {
    return WhCore.logger(this.getClass());
  }

  public CommandManager<WhCommandSender> commandManager() {
    return manager;
  }

  public void reload() {
    this.setEnabled(false);
    this.setEnabled(true);
  }

  public abstract void enable();

  public abstract void disable();
}
