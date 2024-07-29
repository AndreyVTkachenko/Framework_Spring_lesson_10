package ru.gb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NoteDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Note#Note()}
     *   <li>{@link Note#setContent(String)}
     *   <li>{@link Note#setId(Long)}
     *   <li>{@link Note#setTitle(String)}
     *   <li>{@link Note#getContent()}
     *   <li>{@link Note#getId()}
     *   <li>{@link Note#getTitle()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Note actualNote = new Note();
        actualNote.setContent("Not all who wander are lost");
        actualNote.setId(1L);
        actualNote.setTitle("Dr");
        String actualContent = actualNote.getContent();
        Long actualId = actualNote.getId();

        // Assert that nothing has changed
        assertEquals("Dr", actualNote.getTitle());
        assertEquals("Not all who wander are lost", actualContent);
        assertEquals(1L, actualId.longValue());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Note#Note(Long, String, String)}
     *   <li>{@link Note#setContent(String)}
     *   <li>{@link Note#setId(Long)}
     *   <li>{@link Note#setTitle(String)}
     *   <li>{@link Note#getContent()}
     *   <li>{@link Note#getId()}
     *   <li>{@link Note#getTitle()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        Note actualNote = new Note(1L, "Dr", "Not all who wander are lost");
        actualNote.setContent("Not all who wander are lost");
        actualNote.setId(1L);
        actualNote.setTitle("Dr");
        String actualContent = actualNote.getContent();
        Long actualId = actualNote.getId();

        // Assert that nothing has changed
        assertEquals("Dr", actualNote.getTitle());
        assertEquals("Not all who wander are lost", actualContent);
        assertEquals(1L, actualId.longValue());
    }
}
