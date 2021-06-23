package com.sorokdva.dynamicHeader.action;

import com.sorokdva.dynamicHeader.Header;
import com.sorokdva.dynamicHeader.Util;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import java.util.Optional;
import java.util.stream.Stream;

public class UpdateAllHeaders {
  public UpdateAllHeaders() {
  }

  public static void update() {
    Optional<Project> activeProject = Util.getActiveProject();
    String[] header = new String[1];
    Document[] doc = new Document[1];
    Runnable[] updateHeader = new Runnable[1];
    activeProject.ifPresent((project) -> {
      Stream.of(FilenameIndex.getAllFilenames(project)).forEach((filename) -> {
        FilenameIndex.getVirtualFilesByName(project, filename, GlobalSearchScope.projectScope(project)).forEach((file) -> {
          if (!file.isDirectory() && Util.hasHeader(file)) {
            header[0] = Header.buildHeader(file, file.getName().equalsIgnoreCase("Makefile"));
            doc[0] = FileDocumentManager.getInstance().getDocument(file);
            if (doc[0] != null) {
              updateHeader[0] = () -> {
                doc[0].replaceString(0, header[0].length(), header[0]);
              };
              WriteCommandAction.runWriteCommandAction(project, updateHeader[0]);
            }
          }

        });
      });
    });
  }
}
