package com.wu.blog;

import com.wu.blog.dao.BlogDao;
import com.wu.blog.dao.TypeDao;
import com.wu.blog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApplicationTests {

	@Autowired
    BlogDao blogDao;

	@Autowired
    TypeDao typeDao;

	@Autowired
    BlogService blogService;

	@Test
	void contextLoads() {
	}

	@Test
    void testFindTypeIdByParentId() {
        System.out.println(typeDao.findTypeIdByParentId(10));
    }

    @Test
    void testFindBlogsByType(){
        List<Integer> typeIds =new ArrayList<Integer>();
        typeIds.add(2);
        typeIds.add(3);
        typeIds.add(4);
        System.out.println(blogDao.findBlogsByTypeId(typeIds,0,3));
    }

    @Test
    void testBlogServiceFindBlogsByType(){
        System.out.println(blogService.findBlogsByTypeId(2,0,3));
    }

    @Test
    void testBlogDaoFindBlogsNumByTypeId() {
        List<Integer> typeIds =new ArrayList<Integer>();
        typeIds.add(2);
        typeIds.add(3);
        typeIds.add(4);
        System.out.println(blogDao.findBlogsNumByTypeId(typeIds));
    }
}
