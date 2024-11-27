package com.example.movie.dto;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadResultDto {
    // uuid, fileName, folderPath
    private String uuid;
    private String fileName; // 원본 파일명
    private String folderPath; // 년/월/일

    public String getThumbImageURL() {
        String fullPath = "";
        try {
            fullPath = URLEncoder.encode(folderPath + File.separator + "s_" + uuid + "_" + fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fullPath;
    }

    public String getImageURL() {
        String fullPath = "";
        try {
            fullPath = URLEncoder.encode(folderPath + File.separator + uuid + "_" + fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fullPath;
    }
}
