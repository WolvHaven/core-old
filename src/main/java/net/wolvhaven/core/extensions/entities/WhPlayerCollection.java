package net.wolvhaven.core.extensions.entities;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Set;

public record WhPlayerCollection(Set<WhPlayer> players) implements ForwardingAudience {

  @Override
  public @NonNull
  Iterable<? extends Audience> audiences() {
    return players;
  }

  public int size() {
    return this.players.size();
  }
}
