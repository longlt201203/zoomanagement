package com.nhom3.zoomanagement.local_files;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalFileDTO {
    private Integer id;
    private String path;

    public static LocalFileDTO fromLocalFile(LocalFile localFile) {
        return new LocalFileDTO(localFile.getId(), localFile.getPath());
    }

    public static List<LocalFileDTO> fromLocalFileList(List<LocalFile> localFileList) {
        List<LocalFileDTO> localFileDTOList = new ArrayList<>();
        for (LocalFile localFile : localFileList) {
            LocalFileDTO localFileDTO = fromLocalFile(localFile);
            localFileDTOList.add(localFileDTO);
        }
        return localFileDTOList;
    }
}
