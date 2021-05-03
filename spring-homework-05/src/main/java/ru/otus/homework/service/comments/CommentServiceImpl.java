package ru.otus.homework.service.comments;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.exceptions.CommentException;
import ru.otus.homework.repository.comment.CommentRepositoryJpa;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepositoryJpa commentRepositoryJpa;

    public CommentServiceImpl(CommentRepositoryJpa commentRepositoryJpa) {
        this.commentRepositoryJpa = commentRepositoryJpa;
    }

    @Transactional
    @Override
    public void insertComment(long id, String commentText, long bookId) {
        commentRepositoryJpa.save(new Comment(id, commentText, new Book(bookId)));
    }

    @Override
    public Comment getCommentById(long id) {
        return commentRepositoryJpa.findById(id).orElseThrow(()->new CommentException("Comment with id [" + id + "] not found"));

    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepositoryJpa.findAll();
    }

    @Transactional
    @Override
    public void deleteCommentById(long id) {
        commentRepositoryJpa.deleteById(id);
    }
}