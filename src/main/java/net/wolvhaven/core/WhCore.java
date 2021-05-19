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

import com.earth2me.essentials.Essentials;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.wolvhaven.core.extensions.entities.WhCommandSender;
import net.wolvhaven.core.extensions.entities.WhPlayer;
import net.wolvhaven.core.util.LoggerHolder;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.kitteh.vanish.VanishPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhCore implements LoggerHolder {
  private static final WhCore whCore = new WhCore();

  public static WhCore whCore() {
    return whCore;
  }

  public LuckPerms luckperms() {
    return LuckPermsProvider.get();
  }

  public VanishPlugin vanish() {
    return VanishPlugin.getPlugin(VanishPlugin.class);
  }

  public Essentials essentials() {
    return Essentials.getPlugin(Essentials.class);
  }

  @Override
  public Logger logger() {
    return global();
  }

  public Server server() {
    return Bukkit.getServer();
  }

  public WhCommandSender console() {
    return new WhCommandSender(server().getConsoleSender());
  }

  public String setPlaceholderAPI(String input, Player player) {
    return PlaceholderAPI.setPlaceholders(player, input);
  }

  public String setPlaceholderAPI(String input, WhPlayer player) {
    return setPlaceholderAPI(input, player.player());
  }

  public String setPlaceholderAPI(String input) {
    return setPlaceholderAPI(input, (Player) null);
  }

  public static Logger global() {
    return logger(WhCore.class);
  }

  public static Logger logger(Class<?> clazz) {
    return LoggerFactory.getLogger(clazz);
  }

  public static class Sounds {
    public static final Sound DING = Sound.sound(Key.key("minecraft:entity.experience_orb.pickup"),
            Sound.Source.MASTER,
            1F,
            1F);

    public static final Sound ANVIL_CLANG = Sound.sound(Key.key("minecraft:block.anvil.land"),
            Sound.Source.MASTER,
            1F,
            1F);
  }
}
