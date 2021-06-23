package com.sorokdva.dynamicHeader.event;

import com.sorokdva.dynamicHeader.action.UpdateHeaderEditLine;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class OnSave implements BulkFileListener {
  public OnSave() {
  }

  private static void reportNull() {
    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "events", "com.sorokdva.dynamicHeader/event/OnSave", "after"));
  }

  public void after(@NotNull List<? extends VFileEvent> events) {
    if (events == null) {
      reportNull();
    }

    events.forEach((e) -> {
      if (e.isFromSave() || e.isFromRefresh()) {
        UpdateHeaderEditLine.update(e.getFile());
      }
    });
  }
}