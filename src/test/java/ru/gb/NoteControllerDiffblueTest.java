package ru.gb;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {NoteController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NoteControllerDiffblueTest {
    @Autowired
    private NoteController noteController;

    @MockBean
    private NoteService noteService;

    /**
     * Method under test: {@link NoteController#getAllNotes()}
     */
    @Test
    void testGetAllNotes() throws Exception {
        // Arrange
        when(noteService.getAllNotes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/notes");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(noteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link NoteController#createNote(Note)}
     */
    @Test
    void testCreateNote() throws Exception {
        // Arrange
        Note note = new Note();
        note.setContent("Not all who wander are lost");
        note.setId(1L);
        note.setTitle("Dr");
        when(noteService.saveOrUpdate(Mockito.<Note>any())).thenReturn(note);

        Note note2 = new Note();
        note2.setContent("Not all who wander are lost");
        note2.setId(1L);
        note2.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(note2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(noteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\"}"));
    }

    /**
     * Method under test: {@link NoteController#deleteNote(Long)}
     */
    @Test
    void testDeleteNote() throws Exception {
        // Arrange
        doNothing().when(noteService).deleteNote(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/notes/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(noteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link NoteController#deleteNote(Long)}
     */
    @Test
    void testDeleteNote2() throws Exception {
        // Arrange
        doNothing().when(noteService).deleteNote(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/notes/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(noteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link NoteController#getNote(Long)}
     */
    @Test
    void testGetNote() throws Exception {
        // Arrange
        Note note = new Note();
        note.setContent("Not all who wander are lost");
        note.setId(1L);
        note.setTitle("Dr");
        when(noteService.getNoteById(Mockito.<Long>any())).thenReturn(note);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/notes/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(noteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\"}"));
    }

    /**
     * Method under test: {@link NoteController#updateNote(Long, Note)}
     */
    @Test
    void testUpdateNote() throws Exception {
        // Arrange
        Note note = new Note();
        note.setContent("Not all who wander are lost");
        note.setId(1L);
        note.setTitle("Dr");

        Note note2 = new Note();
        note2.setContent("Not all who wander are lost");
        note2.setId(1L);
        note2.setTitle("Dr");
        when(noteService.saveOrUpdate(Mockito.<Note>any())).thenReturn(note2);
        when(noteService.getNoteById(Mockito.<Long>any())).thenReturn(note);

        Note note3 = new Note();
        note3.setContent("Not all who wander are lost");
        note3.setId(1L);
        note3.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(note3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/notes/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(noteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\"}"));
    }
}
