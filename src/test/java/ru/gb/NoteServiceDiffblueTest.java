package ru.gb;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NoteService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NoteServiceDiffblueTest {
    @MockBean
    private NoteRepository noteRepository;

    @Autowired
    private NoteService noteService;

    /**
     * Method under test: {@link NoteService#getAllNotes()}
     */
    @Test
    void testGetAllNotes() {
        // Arrange
        ArrayList<Note> noteList = new ArrayList<>();
        when(noteRepository.findAll()).thenReturn(noteList);

        // Act
        List<Note> actualAllNotes = noteService.getAllNotes();

        // Assert
        verify(noteRepository).findAll();
        assertTrue(actualAllNotes.isEmpty());
        assertSame(noteList, actualAllNotes);
    }

    /**
     * Method under test: {@link NoteService#getNoteById(Long)}
     */
    @Test
    void testGetNoteById() {
        // Arrange
        Note note = new Note();
        note.setContent("Not all who wander are lost");
        note.setId(1L);
        note.setTitle("Dr");
        Optional<Note> ofResult = Optional.of(note);
        when(noteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Note actualNoteById = noteService.getNoteById(1L);

        // Assert
        verify(noteRepository).findById(eq(1L));
        assertSame(note, actualNoteById);
    }

    /**
     * Method under test: {@link NoteService#saveOrUpdate(Note)}
     */
    @Test
    void testSaveOrUpdate() {
        // Arrange
        Note note = new Note();
        note.setContent("Not all who wander are lost");
        note.setId(1L);
        note.setTitle("Dr");
        when(noteRepository.save(Mockito.<Note>any())).thenReturn(note);

        Note note2 = new Note();
        note2.setContent("Not all who wander are lost");
        note2.setId(1L);
        note2.setTitle("Dr");

        // Act
        Note actualSaveOrUpdateResult = noteService.saveOrUpdate(note2);

        // Assert
        verify(noteRepository).save(isA(Note.class));
        assertSame(note, actualSaveOrUpdateResult);
    }

    /**
     * Method under test: {@link NoteService#deleteNote(Long)}
     */
    @Test
    void testDeleteNote() {
        // Arrange
        doNothing().when(noteRepository).deleteById(Mockito.<Long>any());

        // Act
        noteService.deleteNote(1L);

        // Assert that nothing has changed
        verify(noteRepository).deleteById(eq(1L));
    }
}
