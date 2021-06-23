package com.sorokdva.dynamicHeader;

import com.intellij.ide.util.PropertiesComponent;

public class HeaderState {
  public static final String DEFAULT_HEADER = "/*****************************************************************************\n/*                                                                            */\n/*                                                        :::      ::::::::   */\n/*   {file}                                             :+:      :+:    :+:   */\n/*                                                    +:+ +:+         +:+     */\n/*   By: {author_and_email}                         +#+  +:+       +#+        */\n/*                                                +#+#+#+#+#+   +#+           */\n/*   Created: {created_date_and_author}                #+#    #+#             */\n/*   Updated: {updated_date_and_author}               ###   ########.fr       */\n/*                                                                            */\n/* ************************************************************************** */\n";

  public HeaderState() { }

  public static String getAuthor() {
    if (!PropertiesComponent.getInstance().isValueSet("dynamicHeader_author")) {
      setAuthor("");
    }

    return PropertiesComponent.getInstance().getValue("dynamicHeader_author");
  }

  public static void setAuthor(String author) {
    PropertiesComponent.getInstance().setValue("dynamicHeader_author", author);
  }

  public static String getEmail() {
    if (!PropertiesComponent.getInstance().isValueSet("dynamicHeader_email")) {
      setEmail("");
    }

    return PropertiesComponent.getInstance().getValue("dynamicHeader_email");
  }

  public static void setEmail(String email) {
    PropertiesComponent.getInstance().setValue("dynamicHeader_email", email);
  }

  public static String getHeader() {
    if (!PropertiesComponent.getInstance().isValueSet("dynamicHeader_header")) {
      setHeader("/** ***************************************************************************\n *                                                                            */\n/*                                                        :::      ::::::::   */\n/*   {file}                                             :+:      :+:    :+:   */\n/*                                                    +:+ +:+         +:+     */\n/*   By: {author_and_email}                         +#+  +:+       +#+        */\n/*                                                +#+#+#+#+#+   +#+           */\n/*   Created: {created_date_and_author}                #+#    #+#             */\n/*   Updated: {updated_date_and_author}               ###   ########.fr       */\n/*                                                                            */\n/* ************************************************************************** */\n");
    }

    return PropertiesComponent.getInstance().getValue("dynamicHeader_header");
  }

  public static void setHeader(String header) {
    PropertiesComponent.getInstance().setValue("dynamicHeader_header", header);
  }
}