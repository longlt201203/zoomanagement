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
    @GetMapping("/")
    public List<LocalFileDTO> get() {
        return localFilesService.get();
    }

    @Override
    @PostMapping("/")
    @GetMapping("/{id}")
    public LocalFileDTO get(@PathVariable("id") Integer id) throws BadRequestException {
        return localFilesService.get(id);
    }

    @Override
    @PostMapping("/")
    public LocalFileDTO create(@RequestBody @Valid CreateLocalFileDTO dto) throws BadRequestException {
        return localFilesService.create(dto);
    }

    @Override
    @PutMapping("/{id}")
    public LocalFileDTO update(@PathVariable("id") Integer id, @RequestBody @Valid UpdateLocalFileDTO dto) throws BadRequestException {
        return localFilesService.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public LocalFileDTO delete(@PathVariable("id") Integer id) throws BadRequestException {
        return localFilesService.delete(id);
    }
}
