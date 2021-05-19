/*
 * WolvHaven Core - Utility and Libraries for the WolvHaven server.
 * Copyright (C) 2021 Underscore11
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
