package com.sorokdva.dynamicHeader.settings;

import com.sorokdva.dynamicHeader.HeaderState;
import com.sorokdva.dynamicHeader.action.UpdateAllHeaders;
import com.sorokdva.dynamicHeader.gui.SettingsGui;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;

import javax.swing.JComponent;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Nls.Capitalization;

public class HeaderSettings implements SearchableConfigurable {
  SettingsGui settings;
  String originAuthor = "";
  String originEmail = "";
  String originHeader = "";

  public HeaderSettings() {
  }

  @NotNull
  public String getId() {
    return "preferences.DynamicHeader";
  }

  @Nls(
    capitalization = Capitalization.Title
  )
  public String getDisplayName() {
    return "Dynamic Header";
  }

  @Nullable
  public JComponent createComponent() {
    this.settings = new SettingsGui();
    this.settings.email.setText(HeaderState.getEmail());
    this.settings.author.setText(HeaderState.getAuthor());
    this.settings.header.setText(HeaderState.getHeader());
    this.settings.updateAllHeaders.addActionListener((e) -> {
      try {
        this.apply();
      } catch (ConfigurationException var3) {
        var3.printStackTrace();
        return;
      }

      UpdateAllHeaders.update();
    });
    this.settings.Reset.addActionListener((e) -> {
      try {
        this.settings.email.setText("");
        this.settings.author.setText("");
        this.settings.header.setText("/** ***************************************************************************\n *                                                                            */\n/*                                                        :::      ::::::::   */\n/*   {file}                                             :+:      :+:    :+:   */\n/*                                                    +:+ +:+         +:+     */\n/*   By: {author_and_email}                         +#+  +:+       +#+        */\n/*                                                +#+#+#+#+#+   +#+           */\n/*   Created: {created_date_and_author}                #+#    #+#             */\n/*   Updated: {updated_date_and_author}               ###   ########.fr       */\n/*                                                                            */\n/* ************************************************************************** */\n");
        this.apply();
      } catch (ConfigurationException var3) {
        var3.printStackTrace();
        return;
      }

      UpdateAllHeaders.update();
    });
    this.setOriginValues();
    return this.settings.panel;
  }

  private void setOriginValues() {
    this.originAuthor = this.settings.author.getText();
    this.originEmail = this.settings.email.getText();
    this.originHeader = this.settings.header.getText();
  }

  public boolean isModified() {
    return this.settings != null &&
      (!this.settings.email.getText().equals(this.originEmail)
        || !this.settings.author.getText().equals(this.originAuthor)
        || !this.settings.header.getText().equals(this.originHeader));
  }

  public void apply() throws ConfigurationException {
    HeaderState.setHeader(this.settings.header.getText());
    HeaderState.setEmail(this.settings.email.getText());
    HeaderState.setAuthor(this.settings.author.getText());
    this.setOriginValues();
  }
}
