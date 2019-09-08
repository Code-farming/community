package com.lhb.community.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean showPrevious;
    private boolean showFirst;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalcount, Integer page, Integer size) {

        this.page = page;
        if (totalcount % size == 0) {
            totalPage = totalcount / size;
        } else {
            totalPage = totalcount / size + 1;
        }

        this.totalPage=totalPage;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        if (pages.contains(1)) {
            showFirst = false;
        } else {
            showFirst = true;
        }

        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}

