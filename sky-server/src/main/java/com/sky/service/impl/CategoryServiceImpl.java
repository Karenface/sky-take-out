package com.sky.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 修改分类
     *
     * @param categoryDTO
     */
    @Override
    public void editCategory(CategoryDTO categoryDTO) {

        Category category = Category.builder().id(categoryDTO.getId()).type(categoryDTO.getType()).name(categoryDTO.getName()).sort(categoryDTO.getSort()).updateTime(LocalDateTime.now()).updateUser(BaseContext.getCurrentId()).build();
        categoryMapper.update(category);
    }

    /**
     * 分类分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult categoryPageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {

        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());

        Page<Category> page = categoryMapper.categoryPageQuery(categoryPageQueryDTO);

        List<Category> result = page.getResult();
        long total = page.getTotal();

        return new PageResult(total, result);
    }

    /**
     * 启用、禁用分类
     *
     * @param status
     * @param id
     */
    @Override
    public void enableOrDisableCategory(Integer status, Long id) {

        Category category = Category.builder().id(id).status(status).updateUser(BaseContext.getCurrentId()).updateTime(LocalDateTime.now()).build();

        categoryMapper.update(category);

    }

    /**
     * 新增分类
     *
     * @param categoryDTO
     */
    @Override
    public void addNewCategory(CategoryDTO categoryDTO) {

        Category category = Category.builder()
                .type(categoryDTO.getType())
                .sort(categoryDTO.getSort())
                .name(categoryDTO.getName())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .createUser(BaseContext.getCurrentId())
                .updateUser(BaseContext.getCurrentId())
                .build();

        categoryMapper.addNewCategory(category);

    }

    /**
     * 根据id删除分类
     *
     * @param id
     */
    @Override
    public void deleteCategory(Long id) {

        categoryMapper.deleteCategory(id);

    }
}
