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

import net.kyori.adventure.identity.Identity;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

import static net.wolvhaven.core.WhCore.whCore;

public class WhPlayer extends WhCommandSender implements Identity {
  private final Player player;

  protected WhPlayer(final Player player) {
    super(player);
    this.player = player;
  }

  public Player player() {
    return this.player;
  }

  public boolean isVanished() {
    return whCore().vanish().getManager().isVanished(this.player);
  }

  public void setVanished(boolean vanished) {
    if (vanished) {
      whCore().vanish().getManager().vanish(this.player, false, true);
    } else {
      whCore().vanish().getManager().reveal(this.player, false, true);
    }
  }

  public boolean isStaff() {
    return this.player.hasPermission("whcore.staff");
  }

  public com.earth2me.essentials.User essentialsUser() {
    return whCore().essentials().getUser(this.player);
  }

  public net.luckperms.api.model.user.User luckpermsUser() {
    return whCore().luckperms().getUserManager().getUser(this.player.getUniqueId());
  }

  public BigDecimal balance() {
    return essentialsUser().getMoney();
  }

  @Override
  public @NonNull UUID uuid() {
    return this.player.getUniqueId();
  }
}
