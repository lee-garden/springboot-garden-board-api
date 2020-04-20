package com.gardeny.gardenboard.springboot.domain.comment;

import com.gardeny.gardenboard.springboot.domain.account.User;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_comment_id", referencedColumnName="id", nullable = true, updatable = false)
    private Comment parentComment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentComment")
    private Set<Comment> children;

    @Column(length = 500, nullable = false)
    private String content;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean isRemoved;

    public Comment(Long id) {
        super();
        this.id = id;
    }

    @Builder
    public Comment(User user, Post post, Comment parentComment, String content) {
        this.user = user;
        this.post = post;
        this.parentComment = parentComment;
        this.content = content;
    }
}
