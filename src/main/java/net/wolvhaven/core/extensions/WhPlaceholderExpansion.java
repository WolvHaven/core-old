package net.wolvhaven.core.extensions;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public abstract class WhPlaceholderExpansion extends PlaceholderExpansion {
  @Override
  // "Bundled" expansions should return true
  public final boolean persist() {
    return true;
  }
}
