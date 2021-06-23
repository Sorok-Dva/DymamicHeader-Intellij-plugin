package com.sorokdva.dynamicHeader.action;

import com.sorokdva.dynamicHeader.Header;
import com.sorokdva.dynamicHeader.Util;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.vfs.VirtualFile;

public class InsertHeader {
  public InsertHeader() {
  }

  public static void insert(VirtualFile file, Editor editor) {
    if (file != null) {
      if (!file.isDirectory()) {
        if (editor != null) {
          String filename = file.getName();
          boolean isMakefile = filename.equalsIgnoreCase("Makefile");
          String header = Header.buildHeader(file, isMakefile);
          if (!Util.hasHeader(file)) {
            Runnable[] runnable = new Runnable[1];
            Util.getActiveProject().ifPresent((project) -> {
              runnable[0] = () -> {
                editor.getDocument().insertString(0, header);
              };
              WriteCommandAction.runWriteCommandAction(project, runnable[0]);
            });
          }

        }
      }
    }
  }
}
