package com.manhtb.productservice.controller;

import com.manhtb.productservice.model.request.BookRequest;
import com.manhtb.productservice.model.response.BookResponse;
import com.manhtb.productservice.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> get(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.get(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.create(bookRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@RequestBody BookRequest bookRequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(bookRequest, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
