package com.propets.apirest.main.models.dto;

import com.propets.apirest.main.models.entity.Pet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageDto extends ResponseDto {

    private transient List<Object> content;
    private transient Pageable pageable;
    private Boolean last;
    private Boolean first;
    private Integer totalPages;
    private Integer size;
    private Integer number;
    private Integer numberOfElements;
    private Sort sort;

    public PageDto(Page<Pet> page){
        this.setPageable(page.getPageable());
        this.setLast(page.isLast());
        this.setFirst(page.isFirst());
        this.setTotalPages(page.getTotalPages());
        this.setSize(page.getSize());
        this.setNumber(page.getNumber());
        this.setNumberOfElements(page.getNumberOfElements());
    }
}