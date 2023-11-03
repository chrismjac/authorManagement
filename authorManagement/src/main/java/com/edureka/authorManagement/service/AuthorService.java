package com.edureka.authorManagement.service;

import com.edureka.authorManagement.entity.Author;
import com.edureka.authorManagement.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public ResponseEntity<String> addNewAuthor(Author author) {
        try {
            authorRepository.save(author);
            return new ResponseEntity<>("Author Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new author" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> getAllAuthors() {
        List<Author> allBooks = authorRepository.findAll();
        if (!allBooks.isEmpty()) {
            return ResponseEntity.ok(allBooks);
        }
        return new ResponseEntity<>("No Authors Added", HttpStatus.NO_CONTENT);
    }
    public Author getAuthorByName(String name) {
        Optional<Author> authorDetails = authorRepository.findByName(name);
        return authorDetails.orElse(null);
    }

    public ResponseEntity<String> updateAuthor(Author author, Long id) {
        Optional<Author> authorDetails = authorRepository.findById(id);
        if (authorDetails.isPresent()) {
            try {
                authorRepository.save(authorDetails.get());
                return new ResponseEntity<>("Author Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating author" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No book with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
        return new ResponseEntity<>("Author Details deleted successfully", HttpStatus.OK);
    }
}
