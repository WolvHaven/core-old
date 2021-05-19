package net.wolvhaven.core.extensions.entities;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

public class WhCommandSender implements ForwardingAudience.Single {
  private final CommandSender commandSender;

  public WhCommandSender(final CommandSender commandSender) {
    this.commandSender = commandSender;
  }

  public CommandSender commandSender() {
    return this.commandSender;
  }

  @Override
  public @NonNull Audience audience() {
    return this.commandSender;
  }

  public boolean hasPermission(String permission) {
    return this.commandSender.hasPermission(permission);
  }
}
