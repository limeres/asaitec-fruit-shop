package com.asaitec.exam.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    public boolean checkFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty()) {
            throw new FileSystemNotFoundException("Failed to load empty file.");
        }
        //TODO: Validate file content format
        return true;
    }

    public List<String> getLines(MultipartFile file) throws Exception{
        BufferedReader br;
        List<String> result = new ArrayList<>();
        String line;
        InputStream is = file.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));
        br.readLine();
        while ((line = br.readLine()) != null) {
            result.add(line);
        }
        System.out.println();
        return result;
    }
}
