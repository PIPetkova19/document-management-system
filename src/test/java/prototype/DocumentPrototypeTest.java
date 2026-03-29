package prototype;

import org.example.documentmanagementsystem.model.Document;
import org.example.documentmanagementsystem.model.DocumentType;
import org.example.documentmanagementsystem.model.PDFDocument;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DocumentPrototypeTest {
    @Test
    void shouldCloneDocument() {
        PDFDocument original = new PDFDocument("Title",
                "Author", LocalDate.now(), DocumentType.PDF);

        Document clone = original.clone();

        assertNotSame(original, clone);
    }

    @Test
    void shouldCloneFields(){
        PDFDocument original = new PDFDocument("Title","Author",
                LocalDate.now(),DocumentType.PDF);

        Document clone = original.clone();

        assertEquals(original.getTitle(),clone.getTitle());
    }
}
