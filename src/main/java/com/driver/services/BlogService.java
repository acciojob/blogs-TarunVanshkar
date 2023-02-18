package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    @Autowired
    ImageRepository imageRepository;     // I created this

    public Blog createAndReturnBlog(Integer userId, String title, String content)
    {
        //create a blog at the current time
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);

        // To setUser of this blog
        User user = userRepository1.findById(userId).get();
        blog.setUser(user);

        List<Blog> blogsListByUser=user.getBlogsWrittenByUserList();
        // Now append this blog into current user's list
        blogsListByUser.add(blog);

        // updating the blog list in user
        user.setBlogsWrittenByUserList(blogsListByUser);

        blogRepository1.save(blog);

        // Since I have updated the blogList parameter of user model
        // Hence update it in database
        userRepository1.save(user);

        return blog;
    }

    public void deleteBlog(int blogId)
    {
        //delete blog and corresponding images
        // First of fetch that blog
        Blog blog = blogRepository1.findById(blogId).get();

        // To delete corresponding image
        List<Image> imageList = new ArrayList<>();
        imageList = blog.getImagesInBlogsList();
        for(Image image : imageList)
        {
            imageRepository.delete(image);
            //imageRepository.deleteById(image.getId());
        }

        // To delete blog from corresponding user
        User user=blog.getUser();
        List<Blog> blogList = new ArrayList<>();
        blogList = user.getBlogsWrittenByUserList();
        blogList.remove(blog);
        user.setBlogsWrittenByUserList(blogList); // Update user list
        userRepository1.save(user);  // Hence update it in database

        // Now delete from blogRepository
        blogRepository1.delete(blog);
    }
}
