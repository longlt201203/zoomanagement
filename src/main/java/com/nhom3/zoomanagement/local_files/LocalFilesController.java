package com.nhom3.zoomanagement.local_files;

import com.nhom3.zoomanagement.errors.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("local-files")
public class LocalFilesController implements ILocalFilesController {
    @Autowired
    LocalFilesService localFilesService;

    @Override
    public List<LocalFileDTO> get() {
        return localFilesService.get();
    }

    @Override
    public LocalFileDTO get(@PathVariable("id") Integer id) throws BadRequestException {
        return localFilesService.get(id);
    }

    @Override
    public LocalFileDTO create(@RequestBody @Valid CreateLocalFileDTO dto) throws BadRequestException {
        return localFilesService.create(dto);
    }

    @Override
    public LocalFileDTO update(@PathVariable("id") Integer id, @RequestBody @Valid UpdateLocalFileDTO dto) throws BadRequestException {
        return localFilesService.update(id, dto);
    }

    @Override
    public LocalFileDTO delete(@PathVariable("id") Integer id) throws BadRequestException {
        return localFilesService.delete(id);
    }
}