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
