package com.manhtb.productservice.service.impl;

import com.manhtb.productservice.entity.Book;
import com.manhtb.productservice.dto.request.BookRequest;
import com.manhtb.productservice.dto.response.BookResponse;
import com.manhtb.productservice.repository.IBookRepository;
import com.manhtb.productservice.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {
    @Autowired
    IBookRepository bookRepository;

    @Override
    public BookResponse create(BookRequest bookRequest) {
        Book book = new Book(
                bookRequest.getName(), bookRequest.getAuthor(), bookRequest.getPrice(), bookRequest.isReady(), bookRequest.getBookType());
        book.setCreatedDate(Instant.now());
        Book newBook = bookRepository.save(book);

        return BookResponse.builder()
                .id(newBook.getId())
                .name(newBook.getName())
                .author(newBook.getAuthor())
                .price(newBook.getPrice())
                .isReady(newBook.isReady())
                .bookType(newBook.getBookType())
                .build();
    }

    @Override
    public BookResponse update(BookRequest bookRequest, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("not found book"));
        book.setName(bookRequest.getName());
        book.setAuthor(bookRequest.getAuthor());
        book.setPrice(bookRequest.getPrice());
        book.setReady(bookRequest.isReady());
        book.setBookType(bookRequest.getBookType());
        book.setUpdateDate(Instant.now());

        Book newBook = bookRepository.save(book);

        return BookResponse.builder()
                .id(newBook.getId())
                .name(newBook.getName())
                .author(newBook.getAuthor())
                .price(newBook.getPrice())
                .isReady(newBook.isReady())
                .bookType(newBook.getBookType())
                .build();
    }

    @Override
    public BookResponse get(Long id) {
        Book book =  bookRepository.findById(id).orElseThrow(() -> new RuntimeException("not found book"));

        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .price(book.getPrice())
                .isReady(book.isReady())
                .bookType(book.getBookType())
                .build();
    }

    @Override
    public List<BookResponse> getAll() {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .map(item -> BookResponse.builder()
                            .id(item.getId())
                            .name(item.getName())
                            .author(item.getAuthor())
                            .price(item.getPrice())
                            .isReady(item.isReady())
                            .bookType(item.getBookType())
                            .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
