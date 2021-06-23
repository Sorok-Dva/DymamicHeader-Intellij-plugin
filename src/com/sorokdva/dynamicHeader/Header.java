package com.sorokdva.dynamicHeader;

import com.intellij.openapi.vfs.VirtualFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

public class Header {
  public Header() {
  }

  public static String buildHeader(VirtualFile file, boolean isMakefile) {
    String filename = file.getName();
    String header = HeaderState.getHeader();
    header = maySetHeaderForMakefile(header, isMakefile);
    header = replaceValue(header, "{file}", filename);
    header = replaceValue(header, "{author_and_email}", String.format("%s <%s>", HeaderState.getAuthor(), HeaderState.getEmail()));
    header = replaceDateAndAuthor(header, false, file);
    header = replaceDateAndAuthor(header, true, file);
    return header;
  }

  public static Optional<String> buildUpdateLineHeader(boolean isMakefile, VirtualFile file) {
    return Stream.of(HeaderState.getHeader().split("\n")).filter((e) -> {
      return e.contains("{updated_date_and_author}");
    }).findFirst().map((e) -> {
      e = maySetHeaderForMakefile(e, isMakefile);
      System.out.println(e);
      System.out.println(replaceDateAndAuthor(e, true, file));
      System.out.println("----");
      return replaceDateAndAuthor(e, true, file);
    });
  }

  private static String maySetHeaderForMakefile(String line, boolean isMakefile) {
    if (isMakefile) {
      line = line.replaceAll("/\\*", "#");
      line = line.replaceAll("\\*/", "#");
    }

    return line;
  }

  private static String replaceValue(String header, String template, String value) {
    if (value.length() < template.length()) {
      value = value + " ".repeat(template.length() - value.length());
    } else if (value.length() > template.length()) {
      template = template + " ".repeat(value.length() - template.length());
    }

    return header.replace(template, value);
  }

  private static String replaceDateAndAuthor(String text, boolean isUpdate, VirtualFile file) {
    long fileDate = file.getTimeStamp();
    String template = isUpdate ? "{updated_date_and_author}" : "{created_date_and_author}";
    String date = isUpdate
      ? (new SimpleDateFormat("yyyy/MM/dd h:mm a")).format(new Date())
      : (new SimpleDateFormat("yyyy/MM/dd h:mm a")).format(new Date(fileDate));

    return replaceValue(text, template, String.format("%s by %s", date, HeaderState.getAuthor()));
  }

  public static int getOffsetUpdatedLine(boolean isMakefile) {
    String header = maySetHeaderForMakefile(HeaderState.getHeader(), isMakefile);
    int offset = 0;
    String[] split = header.split("\n");
    String[] count = split;
    int length = split.length;

    for(int i = 0; i < length; ++i) {
      String s = count[i];
      if (s.contains("{updated_date_and_author}")) {
        return offset;
      }

      offset = offset + s.length() + 1;
    }

    return -1;
  }
}
