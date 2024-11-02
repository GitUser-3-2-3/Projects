package com.project.bookbackend.feedback;

import com.project.bookbackend.book.Book;
import com.project.bookbackend.common.PageResponse;
import com.project.bookbackend.exception.OperationNotPermittedException;
import com.project.bookbackend.repo.BookRepository;
import com.project.bookbackend.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;

    public Integer saveFeedback(FeedbackRequest req, Authentication connectedUser) {
        getUserAndVerify(req.bookId(), connectedUser);

        Feedback feedback = feedbackMapper.toFeedback(req);
        return feedbackRepository.save(feedback).getId();
    }

    private void getUserAndVerify(Integer bookId, Authentication connectedUser) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new EntityNotFoundException("No book found with id::" + bookId));

        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("Book is archived or is not shareable.");
        }
        User user = (User) connectedUser.getPrincipal();

        if (Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("Dude...You can't review your own book!");
        }
    }

    public PageResponse<FeedbackResponse> getAllFeedbacksByBook(int page, int size, Integer bookId, Authentication connectedUser) {

    }
}








