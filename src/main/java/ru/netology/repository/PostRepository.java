package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
    private final List<Post> posts = new CopyOnWriteArrayList<>();
    private final AtomicLong count = new AtomicLong();
    public List<Post> all() {
        return posts;
    }

    public Optional<Post> getById(long id) {
        for (Post post : posts) {
            if (post.getId() == id) return Optional.of(post);
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        count.incrementAndGet();
        if (post.getId() == 0) {
            post.setId(count.get());
            posts.add(post);
        } else if (post.getId() != 0) {
            for (Post post1 : posts) {
                if (post1.getId() == post.getId()) {
                    post1.setContent(post.getContent());
                }
            }
        } else {
            throw new NotFoundException();
        }
        return post;
    }

    public boolean removeById(long id) {
        return posts.removeIf(post -> post.getId() == id);
    }
}
