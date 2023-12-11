package br.com.trabalhotds.post;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_post")
public class PostModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Id id;
    
    private String postText;

    @Column(length = 50)
    private String title;
    private String author;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Id userId;

    
    public void setTitle (String title) throws Exception{
        if (title.length() > 50) {
            throw new Exception("O campo title deve ter até 50 caracteres");            
        }
        this.title = title;
    }

}
