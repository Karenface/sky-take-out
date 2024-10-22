package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {

    /**
     * 修改分类
     *
     * @param categoryDTO
     */
    void editCategory(CategoryDTO categoryDTO);

    /**
     * 分类分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult categoryPageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 启用、禁用分类
     *
     * @param status
     * @param id
     */
    void enableOrDisableCategory(Integer status, Long id);

    /**
     * 新增分类
     *
     * @param categoryDTO
     */
    void addNewCategory(CategoryDTO categoryDTO);

    /**
     * 根据id删除分类
     *
     * @param id
     */
    void deleteCategory(Long id);
}
