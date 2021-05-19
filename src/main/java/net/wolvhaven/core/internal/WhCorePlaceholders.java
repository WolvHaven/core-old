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
