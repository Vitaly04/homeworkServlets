package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final Map<Long, Post> posts = new ConcurrentHashMap<>();
    private final AtomicLong count = new AtomicLong();
    public Map<Long, Post> all() {
        return posts;
    }

    public Optional<Post> getById(long id) {
        if (posts.containsKey(id)) return Optional.of(posts.get(id));
        return Optional.empty();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(count.incrementAndGet());
            posts.put(post.getId(), post);
        } else if (post.getId() != 0) {
            if (posts.containsKey(post.getId())) {
                posts.replace(post.getId(), post);
            } else {
                return null;
            }
        }
        return post;
    }

    public boolean removeById(long id) {
        return posts.remove(id, posts.get(id));
    }
}
