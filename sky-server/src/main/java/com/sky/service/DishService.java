package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;

import java.util.List;

public interface DishService {

    /**
     * 修改菜品
     *
     * @param dishDTO
     */
    void editDish(DishDTO dishDTO);

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    PageResult dishPageQuery(DishPageQueryDTO dishPageQueryDTO);

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
     * @param dishDTO
     */
    void addNewDish(DishDTO dishDTO);

    /**
     * 菜品起售、停售
     *
     * @param status
     * @param id
     */
    void editDishStatus(Integer status, Long id);
}
