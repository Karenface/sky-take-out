package com.sky.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    /**
     * 修改菜品
     *
     * @param dishDTO
     */
    @Override
    public void editDish(DishDTO dishDTO) {

        Dish dish = Dish.builder()
                .id(dishDTO.getId())
                .name(dishDTO.getName())
                .categoryId(dishDTO.getCategoryId())
                .price(dishDTO.getPrice())
                .status(dishDTO.getStatus())
                .description(dishDTO.getDescription())
                .image(dishDTO.getImage())
                .build();

        dishMapper.editDish(dish);

    }

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult dishPageQuery(DishPageQueryDTO dishPageQueryDTO) {

        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        Page<Dish> page = dishMapper.dishPageQuery(dishPageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());

    }

    /**
     * 根据id查询菜品
     *
     * @param id
     * @return
     */
    @Override
    public Dish getDishById(Long id) {

        return dishMapper.getDishById(id);

    }

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> getDishByCategoryId(Integer categoryId) {

        return dishMapper.getDishByCategoryId(categoryId);

    }

    /**
     * 批量删除菜品
     *
     * @param ids
     */
    @Override
    public void deleteDishByIds(Long[] ids) {

        dishMapper.deleteDishByIds(ids);

    }

    /**
     * 新增菜品
     *
     * @param dishDTO
     */
    @Override
    public void addNewDish(DishDTO dishDTO) {

        Dish dish = Dish.builder()
                .name(dishDTO.getName())
                .price(dishDTO.getPrice())
                .image(dishDTO.getImage())
                .description(dishDTO.getDescription())
                .categoryId(dishDTO.getCategoryId())
                .build();

        dishMapper.addNewDish(dish);

    }

    /**
     * 菜品起售、停售
     *
     * @param status
     * @param id
     */
    @Override
    public void editDishStatus(Integer status, Long id) {

        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();

        dishMapper.editDish(dish);

    }
}
