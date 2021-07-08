package io.hala.whistleon.util;

import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.exception.ExceptionCode;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

  private final String logoPath;
  private final String defaultName;

  public FileUtil(@Value("${file.upload.path}") String path,
      @Value("${file.upload.defaultname}") String defaultName) {
    this.logoPath = path;
    this.defaultName = defaultName;
  }

  public String getName(String folder, String fileName) {
    String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS"));
    return folder + "/" + now + "_" + fileName;
  }

  public String getNameByDefault(String folder) {
    return folder + "/" + this.defaultName;
  }

  public String uploadFile(MultipartFile file, String folder) {
    if (file == null || file.isEmpty()) {
      return getNameByDefault(folder);
    }
    String saveFileName = getName(folder, file.getOriginalFilename());
    String path = logoPath + saveFileName;
    try {
      file.transferTo(new File(path));
    } catch (IOException e) {
      throw new CustomException(ExceptionCode.FILE_UPLOAD_FAIL);
    }
    return saveFileName;
  }
}
