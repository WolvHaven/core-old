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

package net.wolvhaven.core.internal;

import net.wolvhaven.core.WhCore;
import net.wolvhaven.core.extensions.WhPlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kitteh.vanish.VanishManager;
import org.kitteh.vanish.VanishPerms;

import static net.wolvhaven.core.WhCore.whCore;

public final class WhCorePlaceholders extends WhPlaceholderExpansion {
  @Override
  public @NotNull String getIdentifier() {
    return "wh";
  }

  @Override
  public @NotNull String getAuthor() {
    return "Underscore11";
  }

  @Override
  public @NotNull String getVersion() {
    return "-";
  }

  @Override
  public String onPlaceholderRequest(final Player player, @NotNull final String params) {
    if (player == null) return "";
    if (params.equals("online")) {
      if (VanishPerms.canSeeAll(player) && vanishManager().numVanished() != 0) {
        return String.format("&f%s&b+%s&f/%s",
                Bukkit.getOnlinePlayers().size() - vanishManager().numVanished(),
                vanishManager().numVanished(),
                Bukkit.getMaxPlayers()
        );
      } else {
        return String.format("&f%s/%s",
                Bukkit.getOnlinePlayers().size() - vanishManager().numVanished(),
                Bukkit.getMaxPlayers()
        );
      }
    }

    if (params.equals("isvanished")) {
      if (vanishManager().isVanished(player)) {
        return "&b[V]";
      } else {
        return "";
      }
    }

    return "";
  }

  private VanishManager vanishManager() {
    return whCore().vanish().getManager();
  }
}
