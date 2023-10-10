package com.nhom3.zoomanagement.local_files;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalFilesService implements ILocalFilesService {
    @Autowired
    LocalFilesRepository localFilesRepository;
    
    @Override
    public List<LocalFileDTO> get() {
        List<LocalFile> localFiles = localFilesRepository.findAll();
        List<LocalFileDTO> localFileDTOs = LocalFileDTO.fromLocalFileList(localFiles);
        return localFileDTOs;
    }

    @Override
    public LocalFileDTO get(Integer id) throws BadRequestException {
        LocalFile localFile = localFilesRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("File not found")));
        LocalFileDTO localFileDTO = LocalFileDTO.fromLocalFile(localFile);
        return localFileDTO;
    }

    @Override
    public LocalFileDTO create(CreateLocalFileDTO dto) throws BadRequestException {
        LocalFile localFile = dto.toLocalFile();
        LocalFileDTO localFileDTO = LocalFileDTO.fromLocalFile(localFilesRepository.save(localFile));
        return localFileDTO;
    }

    @Override
    public LocalFileDTO update(Integer id, UpdateLocalFileDTO dto) throws BadRequestException {
        LocalFile localFile = localFilesRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("File not found")));
        LocalFile updateLocalFile = dto.toLocalFile();
        LocalFileDTO localFileDTO = LocalFileDTO.fromLocalFile(localFilesRepository.save(updateLocalFile));
        return localFileDTO;
    }

    @Override
    public LocalFileDTO delete(Integer id) throws BadRequestException {
        if (localFilesRepository.existsById(id)) {
            localFilesRepository.deleteById(id);
        } else {
            throw new BadRequestException(new ErrorReport("Account not found"));
        }
        return null;
    }
}
