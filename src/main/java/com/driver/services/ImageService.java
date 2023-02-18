package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService
{

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions)
    {
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        // To add images in blogList
        Blog blog = blogRepository2.findById(blogId).get();  // Now we have that blog to use
        List<Image> imageList = new ArrayList<>();
        imageList = blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);

        // To save in repository
        blogRepository2.save(blog);
        imageRepository2.save(image);
        return image;
    }

    public void deleteImage(Integer id)
    {
        //Image image = imageRepository2.findById(id).get();  // Now we have image entity
        // we should Delete this image from blogs but we dont have blogId to do so
        // Hence
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions)
    {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        // here id-->ImageId
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image = imageRepository2.findById(id).get();
        String imageDimension = image.getDimensions();

        String imageArr[] = imageDimension.split("X");
        String screenArr[] = screenDimensions.split("X");

        int length=0;
        int width=0;
        if(imageArr.length==2 && screenArr.length==2)
        {
            length = (Integer.valueOf(screenArr[0]))/(Integer.valueOf(imageArr[0]));
            width = (Integer.valueOf(screenArr[1]))/(Integer.valueOf(imageArr[1]));
        }
        return length*width;
    }
}
