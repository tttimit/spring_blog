package com.timit.bean;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by timit on 2017/3/2.
 */
public class Post {
    /**
     * 文章计数器
     */
    private static long count = 1;
    /**
     * 文章的唯一标识
     */
    private final Long id = generateId();
    /**
     * 文章的标题
     */
    @Size(min=2, max=30)
    private String title;
    /**
     * 文章的内容
     */
    @Size(min=1)
    private String content;
    /**
     * 文章的创建时间
     */
    private Date created;
    /**
     * 文章的修改时间，每次修改标题或者内容都会自动更新
     */
    private Date updated;

    public Post(){
//        title = "请输入标题";
//        content = "请输入内容";
        created = new Date();
        updated = created;
    }

    public Post(String title, String content) {
        this();
        this.title = title;
        this.content = content;
    }

    /**
     *
     * @return 每当构造一个post对象时，就根据计数器生成一个唯一且不可变的id
     */
    private static long generateId(){
        long currentId = count;
        count++;
        return currentId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
   }

    public String getContent() {
        return content;
    }

    public Date getCreated() {
        return created;
    }

    public void setTitle(String title) {
        this.title = title;
        updated = new Date();
    }

    public void setContent(String content) {
        this.content = content;
        updated = new Date();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    public static void main(String[] args) {

    }
}
