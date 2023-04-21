package com.manhtb.productservice.service;

import com.manhtb.productservice.model.request.BookRequest;
import com.manhtb.productservice.model.response.BookResponse;

import java.util.List;

public interface IBookService {
    BookResponse create(BookRequest productRequest);
    BookResponse update(BookRequest productRequest, Long id);
    BookResponse get(Long id);
    List<BookResponse> getAll();
    void delete(Long id);
}
