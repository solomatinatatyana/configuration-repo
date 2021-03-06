package ru.otus.homework.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Comment")
public class CommentTest {
    @DisplayName("Комментарий корректно создаётся конструктором")
    @Test()
    public void shouldHaveCorrectCommentConstructor(){
        Comment comment = new Comment("testComment",5);
        assertAll("comment",
                ()-> assertEquals("testComment", comment.getCommentText()),
                ()->assertEquals(5, comment.getRating()));
    }
}
