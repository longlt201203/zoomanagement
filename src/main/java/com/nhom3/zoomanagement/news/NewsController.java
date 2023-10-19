package com.nhom3.zoomanagement.news;

import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.utils.search_filter.SearchRequestDTO;
import com.nhom3.zoomanagement.utils.search_filter.SearchResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("news")
public class NewsController implements INewsController {
    @Autowired
    NewsService newsService;
    
    @Override
    public List<NewsDTO> get() {
        return newsService.get();
    }
    
    @Override
    public NewsDTO get(@PathVariable("id") Integer id) throws BadRequestException {
        return newsService.get(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    public NewsDTO create(@RequestBody @Valid CreateNewsDTO dto) throws BadRequestException {
        return newsService.create(dto);
    }

    @Override
    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    public NewsDTO update(@PathVariable("id") Integer id, UpdateNewsDTO dto) throws BadRequestException {
        return newsService.update(id, dto);
    }

    @Override
    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    public NewsDTO delete(@PathVariable("id") Integer id) throws BadRequestException {
        return newsService.delete(id);
    }

    @PostMapping("search")
    public SearchResponseDTO<NewsDTO> search(@RequestParam(name = "pageNums", defaultValue = "0") int pageNum,
                                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                @RequestBody SearchRequestDTO searchDTO) {
        return newsService.search(pageNum, pageSize, searchDTO);
    }
}
