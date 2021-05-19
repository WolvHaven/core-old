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
