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

import org.slf4j.Logger;

public interface LoggerHolder {
  Logger logger();

  default void trace(String msg) {
    logger().trace(msg);
  }

  default void trace(String format, Object... arguments) {
    logger().trace(format, arguments);
  }

  default void trace(String msg, Throwable t) {
    logger().trace(msg, t);
  }

  default void debug(String msg) {
    logger().debug(msg);
  }

  default void debug(String format, Object... arguments) {
    logger().debug(format, arguments);
  }

  default void debug(String msg, Throwable t) {
    logger().debug(msg, t);
  }

  default void info(String msg) {
    logger().info(msg);
  }

  default void info(String format, Object... arguments) {
    logger().info(format, arguments);
  }

  default void info(String msg, Throwable t) {
    logger().info(msg, t);
  }

  default void warn(String msg) {
    logger().warn(msg);
  }

  default void warn(String format, Object... arguments) {
    logger().warn(format, arguments);
  }

  default void warn(String msg, Throwable t) {
    logger().warn(msg, t);
  }

  default void error(String msg) {
    logger().error(msg);
  }

  default void error(String format, Object... arguments) {
    logger().error(format, arguments);
  }

  default void error(String msg, Throwable t) {
    logger().error(msg, t);
  }
}
