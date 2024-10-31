package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFillAnnotation;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 修改分类
     *
     * @param category
     */
    @AutoFillAnnotation(value = OperationType.UPDATE)
    void update(Category category);

    /**
     * 分类分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> categoryPageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 新增分类
     *
     * @param category
     */
    @AutoFillAnnotation(value = OperationType.INSERT)
    void addNewCategory(Category category);

    /**
     * 根据id删除分类
     *
     * @param id
     */
    void deleteCategory(Long id);

    /**
     * 根据类型查询分类
     *
     * @param type
     * @return
     */
    List<Category> getCategoryByType(Integer type);
}
