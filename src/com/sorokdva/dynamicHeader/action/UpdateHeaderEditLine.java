package com.sorokdva.dynamicHeader.action;

import com.sorokdva.dynamicHeader.Header;
import com.sorokdva.dynamicHeader.Util;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;

public class UpdateHeaderEditLine {
  public UpdateHeaderEditLine() {
  }

  public static void update(VirtualFile file) {
    if (file != null) {
      if (!file.isDirectory()) {
        if (Util.hasHeader(file)) {
          System.out.println("update line event ");
          Document doc = FileDocumentManager.getInstance().getDocument(file);
          if (doc != null) {
            boolean isMakefile = file.getName().equalsIgnoreCase("Makefile");
            System.out.println(Header.buildUpdateLineHeader(isMakefile, file));
            System.out.println(Util.getActiveProject());
            boolean isMakefile2 = false;
            int[] offset = new int[1];
            Runnable[] updateHeader = new Runnable[1];
            Header.buildUpdateLineHeader(isMakefile, file).ifPresent((line) -> {
              Util.getActiveProject().ifPresent((project) -> {
                offset[0] = Header.getOffsetUpdatedLine(false);
                if (offset[0] != -1) {
                  updateHeader[0] = () -> {
                    doc.replaceString(offset[0], offset[0] + line.length(), line);
                  };
                  if (doc.getModificationStamp() > 0L) {
                    WriteCommandAction.runWriteCommandAction(project, updateHeader[0]);
                  }
                }

              });
            });
          }
        }
      }
    }
  }
}
