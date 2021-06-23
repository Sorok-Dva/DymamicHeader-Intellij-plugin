package com.sorokdva.dynamicHeader.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.JBUI;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SettingsGui {
    public JTextField author;
    public JTextField email;
    public JTextArea header;
    public JPanel panel;
    public JButton updateAllHeaders;
    public JButton Reset;

    public SettingsGui() {
        this.setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel();
        (this.panel = panel).setLayout(new GridLayoutManager(5, 2, JBUI.emptyInsets(), -1, -1, false, false));

        JLabel author = new JLabel();
        author.setText("Author");
        panel.add(author, new GridConstraints(0, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null));

        JLabel email = new JLabel();
        email.setText("Email");
        panel.add(email, new GridConstraints(1, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null));

        JLabel header = new JLabel();
        header.setText("Header");
        panel.add(header, new GridConstraints(2, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null));

        panel.add(
          this.author = new JTextField(),
          new GridConstraints(0, 1, 1, 1, 8, 1, 6, 0, (Dimension)null, new Dimension(150, -1), (Dimension)null)
        );
        panel.add(
          this.email = new JTextField(),
          new GridConstraints(1, 1, 1, 1, 8, 1, 6, 0, (Dimension)null, new Dimension(150, -1), (Dimension)null)
        );

        panel.add(
          this.header = new JTextArea(),
          new GridConstraints(2, 1, 1, 1, 0, 3, 6, 6, (Dimension)null, new Dimension(150, 50), (Dimension)null)
        );

        JButton updateBtn = new JButton();
        (this.updateAllHeaders = updateBtn).setText("Update all headers");
        updateBtn.setToolTipText("Update all headers on this project");
        panel.add(updateBtn, new GridConstraints(3, 1, 1, 1, 0, 1, 3, 0, (Dimension)null, (Dimension)null, (Dimension)null));

        JButton resetBtn = new JButton();
        (this.Reset = resetBtn).setText("Reset");
        resetBtn.setToolTipText("Resets all configurations for Dynamic Header");
        panel.add(resetBtn, new GridConstraints(4, 1, 1, 1, 0, 1, 3, 0, (Dimension)null, (Dimension)null, (Dimension)null));
    }
}
