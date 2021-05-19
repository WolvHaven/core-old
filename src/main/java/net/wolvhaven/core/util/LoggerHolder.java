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
