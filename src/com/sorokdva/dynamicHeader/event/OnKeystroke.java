package com.sorokdva.dynamicHeader.event;

import  com.sorokdva.dynamicHeader.action.InsertHeader;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.vfs.VirtualFile;

public class OnKeystroke extends AnAction {
  public OnKeystroke() {
  }

  public void actionPerformed(AnActionEvent actionEvent) {
    VirtualFile file = (VirtualFile)actionEvent.getData(CommonDataKeys.VIRTUAL_FILE);
    Editor editor = (Editor)actionEvent.getData(LangDataKeys.EDITOR);
    InsertHeader.insert(file, editor);
  }
}
