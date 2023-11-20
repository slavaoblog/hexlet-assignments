package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable long id) {
        return toPostDTO(id);
    }

    @GetMapping
    public List<PostDTO> index() {
        var posts = postRepository.findAll();
        var postsDTO = posts.stream()
                .map(post -> toPostDTO(post.getId()))
                .toList();
        return postsDTO;
    }

    private CommentDTO toCommentDTO(Comment comment) {
        var dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;
    }

    private List<CommentDTO> toListCommentDTO(long id) {
        var comments = commentRepository.findByPostId(id);
        var commentsDTO = comments.stream()
                .map(this::toCommentDTO)
                .toList();
        return commentsDTO;
    }

    private PostDTO toPostDTO(long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        var postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setComments(toListCommentDTO(id));

        return postDTO;
    }
        }
// END
