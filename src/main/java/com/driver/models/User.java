package com.driver.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    private String password;
    private String firstName = "test";     //firstName and lastName can be set as “test” by default. -->Given In Question
    private String lastName = "test";


    // User(Parent) : Blog(Child) mapping ---> OneToMany
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Blog> blogsWrittenByUserList;

    public List<Blog> getBlogsWrittenByUserList()
    {
        return blogsWrittenByUserList;
    }

    public void setBlogsWrittenByUserList(List<Blog> blogsWrittenByUserList)
    {
        this.blogsWrittenByUserList = blogsWrittenByUserList;
    }

    public User()
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

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName=userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

}