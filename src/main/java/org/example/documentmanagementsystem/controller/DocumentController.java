package org.example.documentmanagementsystem.controller;

import org.example.documentmanagementsystem.model.DocumentType;
import org.example.documentmanagementsystem.model.Document;
import org.example.documentmanagementsystem.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping
    public String listDocuments(Model model) {
        model.addAttribute("documents", documentService.getAllDocuments());
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("types", DocumentType.values());
        return "create";
    }

    @PostMapping("/create")
    public String createDocument(@RequestParam String title,
                                 @RequestParam String author,
                                 @RequestParam DocumentType type) {
        documentService.createDocument(type, title, author);
        return "redirect:/documents";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Document doc = documentService.getDocument(id);
        model.addAttribute("document", doc);
        model.addAttribute("types", DocumentType.values());
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateDocument(@PathVariable Long id,
                                 @RequestParam String title,
                                 @RequestParam String author,
                                 @RequestParam DocumentType type) {
        documentService.updateDocument(id, type, title, author);
        return "redirect:/documents";
    }

    @GetMapping("/delete/{id}")
    public String deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return "redirect:/documents";
    }

    @GetMapping("/clone/{id}")
    public String cloneDocument(@PathVariable Long id) {
        documentService.cloneDocument(id);
        return "redirect:/documents";
    }

    @GetMapping("/metadata/{id}")
    public String addMetadata(@PathVariable Long id) {
        documentService.addMetadata(id);
        return "redirect:/documents";
    }
}