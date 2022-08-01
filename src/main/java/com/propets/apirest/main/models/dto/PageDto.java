package com.propets.apirest.main.models.dto;

import com.propets.apirest.main.models.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PageDto {
    private List<Object> content;
    private Pageable pageable;
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

    public List<Object> getContent() {return content;}
    public void setContent(List<Object> content) {this.content = content;}

    public Pageable getPageable() {return pageable;}
    public void setPageable(Pageable pageable) {this.pageable = pageable;}

    public Boolean isLast() {return last;}
    public void setLast(Boolean last) {this.last = last;}

    public Boolean isFirst() {return first;}
    public void setFirst(Boolean first) {this.first = first;}

    public Integer getTotalPages() {return totalPages;}
    public void setTotalPages(Integer totalPages) {this.totalPages = totalPages;}

    public Integer getSize() {return size;}
    public void setSize(Integer size) {this.size = size;}

    public Integer getNumber() {return number;}
    public void setNumber(Integer number) {this.number = number;}

    public Integer getNumberOfElements() {return numberOfElements;}

    public void setNumberOfElements(Integer numberOfElements) {this.numberOfElements = numberOfElements;}

    public Sort getSort() {return sort;}

    public void setSort(Sort sort) {this.sort = sort;}

}
