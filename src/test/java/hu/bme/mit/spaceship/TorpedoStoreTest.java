package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {
  @Test
  void firingReducesStockAndAllowsFurtherShots() {
    TorpedoStore store = new TorpedoStore(5);
    assertTrue(store.fire(2));
    assertEquals(3, store.getTorpedoCount());
    assertFalse(store.isEmpty());
    assertTrue(store.fire(3));
    assertEquals(0, store.getTorpedoCount());
    assertTrue(store.isEmpty());
  }

  @Test
  void invalidRequestsThrowWithoutChangingStock() {
    TorpedoStore store = new TorpedoStore(5);
    assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
    assertThrows(IllegalArgumentException.class, () -> store.fire(6));
    assertEquals(5, store.getTorpedoCount());
  }

  @Test
  void emptyStoreRejectsFiring() {
    TorpedoStore store = new TorpedoStore(0);
    assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    assertTrue(store.isEmpty());
  }
}
