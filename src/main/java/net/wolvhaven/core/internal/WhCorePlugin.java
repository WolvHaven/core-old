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
