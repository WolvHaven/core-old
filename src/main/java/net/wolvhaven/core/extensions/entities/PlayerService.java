package net.wolvhaven.core.extensions.entities;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static net.wolvhaven.core.WhCore.whCore;

// TODO byName() byUuid()
public class PlayerService {
  public static WhPlayer one(Player player) {
    return new WhPlayer(player);
  }

  public static WhCommandSender console() {
    return whCore().console();
  }

  public static WhCommandSender of(CommandSender commandSender) {
    if (commandSender instanceof Player player) {
      return new WhPlayer(player);
    }
    return new WhCommandSender(commandSender);
  }

  public static WhPlayerCollection online() {
    return filtered(p -> true);
  }

  public static WhPlayerCollection staff() {
    return filtered(WhPlayer::isStaff);
  }

  public static WhPlayerCollection withPermission(final String permission) {
    return filtered(p -> p.hasPermission(permission));
  }

  private static Collection<? extends Player> onlineBukkitPlayers() {
    return whCore().server().getOnlinePlayers();
  }

  private static WhPlayerCollection filtered(Predicate<WhPlayer> filter) {
    final Set<WhPlayer> whPlayers = new HashSet<>();
    for (final Player bukkitPlayer : onlineBukkitPlayers()) {
      final WhPlayer whPlayer = one(bukkitPlayer);
      if (filter.test(whPlayer)) whPlayers.add(whPlayer);
    }
    return new WhPlayerCollection(whPlayers);
  }
}
