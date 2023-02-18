package com.driver.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blog")
public class Blog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;    // blog written by userId

    private String title;
    private String content;

    @CreationTimestamp
    private Date pubDate;

    // User(Parent) : Blog(Child) mapping ---> OneToMany
    @ManyToOne
    @JoinColumn
    private User user;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }


    // Blog(Parent) : Image(Child) mapping ---> OneToMany
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    List<Image> imageList;

    public List<Image> getImageList()
    {
        return imageList;
    }

    public void setImageList(List<Image> imageList)
    {
        this.imageList = imageList;
    }

    public Blog()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Date getPubDate()
    {
        return pubDate;
    }

    public void setPubDate(Date pubDate)
    {
        this.pubDate = pubDate;
    }
}