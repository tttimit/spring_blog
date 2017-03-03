package com.timit.bean;

import com.timit.exception.PostNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timit on 2017/3/2.
 */
public class PostList {

    private static List<Post> posts = new ArrayList();

    static{
        posts.add(new Post("title1", "content1"));
        posts.add(new Post("title2", "content2"));
    }

    public static List<Post> getPosts() {
        return posts;
    }

    public static int deletePost(Long id){
        return 0;
    }

    public static void main(String[] args) {

    }

    public static Post getPostById(long id) {
        return posts.stream()
                .filter(p->p.getId().equals(id))
                .findFirst()
                .orElseThrow(PostNotFoundException::new);
    }

    public static Post add(Post post) {
        posts.add(post);
        return post;
    }
}
