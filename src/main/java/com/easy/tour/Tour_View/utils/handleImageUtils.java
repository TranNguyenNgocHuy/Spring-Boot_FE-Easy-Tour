package com.easy.tour.Tour_View.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.thymeleaf.util.StringUtils.substring;

@Service
public class handleImageUtils {
    public String saveAvatarImage(MultipartFile file,String imgName ,String urlStorage) throws IOException {
        // file != null and Name != null ===> update
        if (!file.isEmpty() && !("").equals(imgName)) {
            Path oldFilePath = Paths.get(urlStorage + imgName);
            if (Files.exists(oldFilePath)) {
                Files.delete(oldFilePath);
            }
        }

        // file != null and Name == null ===> Create new
            // Tạo tên mới cho file
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(urlStorage + fileName);

            // Ensure the directory exists
            Files.createDirectories(filePath.getParent());

            // Save the file to the target location
            Files.copy(file.getInputStream(), filePath);

        // return ulr path
        return filePath.toString();
    }

    public String getImageName(String url) {
        // define the last "/" locate
        int lastIndex = url.lastIndexOf("\\");
        // get name
        if (lastIndex != -1 && lastIndex < url.length() - 1) {
            return url.substring(lastIndex + 1);
        }
        return "";
    }
}
