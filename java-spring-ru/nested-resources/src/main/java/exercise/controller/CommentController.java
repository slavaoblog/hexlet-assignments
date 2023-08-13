package exercise.controller;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getAllCommentsForPost(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getCommentForPost(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post " + postId + " not found"));
    }

    @PostMapping(path = "/{postId}/comments")
    public Comment createCommentForPost(@RequestBody CommentDto dto, @PathVariable("postId") long postId) {
        Comment comment = new Comment();
        comment.setContent(dto.content());
        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post " + postId + " not found"));
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateCommentForPost(
            @RequestBody CommentDto dto,
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post " + postId + " not found"));
        comment.setContent(dto.content());
        return commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post " + postId + " not found"));
        commentRepository.delete(comment);
    }
    // END
}
