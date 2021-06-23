package com.sorokdva.dynamicHeader;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.WindowManager;
import java.awt.Window;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;

public class Util {
  public Util() {
  }

  public static boolean hasHeader(VirtualFile file) {
    try {
      System.out.println(file.getName());
      String str = new String(file.getInputStream().readNBytes(20));
      System.out.println(str);
      PrintStream sys = System.out;
      boolean isComment = str.contains("/* ") || str.contains("# *");
      sys.println("has header " + isComment);
      return str.contains("/* ") || str.contains("# *");
    } catch (IOException error) {
      error.printStackTrace();
      return true;
    }
  }

  public static Optional<Project> getActiveProject() {
    Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
    Project[] instances = openProjects;
    int length = openProjects.length;

    for(int i = 0; i < length; ++i) {
      Project project = instances[i];
      Window window = WindowManager.getInstance().suggestParentWindow(project);
      if (window != null && window.isActive()) {
        return Optional.of(project);
      }
    }

    return Optional.empty();
  }
}
