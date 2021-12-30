package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        List<Post> postList = new ArrayList<>();
        for (Map.Entry<Long, Post> posts : repository.all().entrySet()) {
            postList.add(posts.getValue());
        }
        return postList;
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public boolean removeById(long id) {
        if(repository.removeById(id)) return true;
        return false;
    }
}
