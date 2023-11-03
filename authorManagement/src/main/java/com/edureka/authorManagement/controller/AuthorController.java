package com.edureka.authorManagement.controller;

import com.edureka.authorManagement.service.AuthorService;
import com.edureka.authorManagement.entity.Author;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping
    @Operation(summary = "Add a new Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Author")})
    private ResponseEntity<String> addNewAuthor(@RequestBody Author author){
        return authorService.addNewAuthor(author);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authors retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Authors Added")})
    private ResponseEntity<?> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping("/{name}")
    @Operation(summary = "Get a Author by name")
    private Author getAuthorByName(@PathVariable String name){
        return authorService.getAuthorByName(name);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Author details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Author details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Author with specified Id found")})
    private ResponseEntity<String> updateAuthor(@RequestBody Author author,@PathVariable Long id){
        return authorService.updateAuthor(author,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Author details updated successfully")})
    private ResponseEntity<String> deleteAuthorById(@PathVariable Long id){
        return authorService.deleteAuthorById(id);
    }


}
