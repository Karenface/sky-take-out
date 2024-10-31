package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFillAnnotation;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 修改菜品
     *
     * @param dish
     */
    @AutoFillAnnotation(value = OperationType.UPDATE)
    void editDish(Dish dish);

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    Page<Dish> dishPageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品
     *
     * @param id
     * @return
     */
    Dish getDishById(Long id);

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    List<Dish> getDishByCategoryId(Integer categoryId);

    /**
     * 批量删除菜品
     *
     * @param ids
     */
    void deleteDishByIds(Long[] ids);

    /**
     * 新增菜品
     *
     * @param dish
     */
    @AutoFillAnnotation(value = OperationType.INSERT)
    void addNewDish(Dish dish);
}
