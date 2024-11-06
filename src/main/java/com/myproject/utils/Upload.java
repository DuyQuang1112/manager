package com.myproject.utils;

import com.myproject.commons.exception.ApiMessageError;
import com.myproject.commons.exception.ErrorMessages;
import com.myproject.commons.exception.OctResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Upload {

    public static final String UPLOAD_DIRECTORY = "C:\\Users\\dqd11\\IdeaProjects\\L3_project\\src\\main\\resources\\template\\image";

    public static String uploadFile(MultipartFile file) {
        try{
            if (file.isEmpty()) {
                throw new IOException("File is empty");
            }

            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIRECTORY, fileName);
            Files.createDirectories(filePath.getParent());
            file.transferTo(new File(filePath.toString()));
            return filePath.toString();
        } catch(Exception e){
            throw new OctResourceNotFoundException(ErrorMessages.BAD_REQUEST, new ApiMessageError("Can not upload image"));
        }

    }

}
