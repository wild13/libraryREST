package com.wcs.library.restlibrary.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.library.restlibrary.entity.Book;
import com.wcs.library.restlibrary.repository.BookRepository;

@RestController
public class BookController {

    @Autowired
    BookRepository myBookRepository;

    @GetMapping( "/books" )
    public List<Book> index() {
        return myBookRepository.findAll();
    }

    @GetMapping( "/books/{id}" )
    public Book showOne( @PathVariable int id ) {
        return myBookRepository.findById( id ).get();
    }

    @PostMapping( "/books" )
    public Book create( @RequestBody Book book ) {
        return myBookRepository.save( book );
    }

    @PostMapping( "/books/search" )
    public List<Book> search( @RequestBody Map<String, String> body ) {
        String searchTerm = body.get( "text" );
        return myBookRepository.findByTitleContainingOrAuthorContainingOrDescriptionContaining( searchTerm, searchTerm,
                searchTerm );
    }

    @PutMapping( "/books/{id}" )
    public Book update( @RequestBody Book book, @PathVariable int id ) {
        Book bookToUpdate = myBookRepository.findById( id ).get();
        bookToUpdate.setTitle( book.getTitle() );
        bookToUpdate.setAuthor( book.getAuthor() );
        bookToUpdate.setDescription( book.getDescription() );
        return myBookRepository.save( bookToUpdate );
    }

    @DeleteMapping( "/books/{id}" )
    public boolean delete( @PathVariable int id ) {
        myBookRepository.deleteById( id );
        return true;
    }
}
