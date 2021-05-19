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

package net.wolvhaven.core;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.io.File;

public class WhConfig<C> {
  private final Class<C> type;
  private final File file;
  private final HoconConfigurationLoader loader;

  private CommentedConfigurationNode configNode;
  private C config;

  public WhConfig(final Class<C> type, final File file) {
    this.type = type;
    this.file = file;

    this.loader = HoconConfigurationLoader.builder()
            .defaultOptions(opts -> opts.shouldCopyDefaults(true))
            .file(file)
            .build();
  }

  public C load() throws ConfigurateException {
    this.configNode = this.loader.load();
    this.config = this.configNode.get(this.type);
    return this.config;
  }

  public C get() {
    if (this.config != null) return this.config;
    throw new IllegalStateException("Attempt to whCore config when it wasn't loaded!");
  }

  public void save() throws ConfigurateException {
    if (this.configNode == null) throw new IllegalStateException("Attempt to save config when it wasn't loaded");
    this.configNode.set(this.config);
    this.loader.save(this.configNode);
  }

  public File file() {
    return this.file;
  }
}
