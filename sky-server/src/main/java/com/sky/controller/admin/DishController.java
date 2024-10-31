package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品相关接口")
public class DishController {

    @Autowired
    private DishService dishService;


    /**
     * 修改菜品
     *
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品")
    public Result<String> editDish(@RequestBody DishDTO dishDTO) {

        log.info("修改菜品：{}", dishDTO);

        dishService.editDish(dishDTO);

        return Result.success();

    }

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> dishPageQuery(DishPageQueryDTO dishPageQueryDTO) {

        log.info("菜品分页查询：{}", dishPageQueryDTO);

        PageResult pageResult = dishService.dishPageQuery(dishPageQueryDTO);

        return Result.success(pageResult);

    }

    /**
     * 根据id查询菜品
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<Dish> getDishById(@PathVariable Long id) {

        log.info("根据id查询菜品：{}", id);

        Dish dish = dishService.getDishById(id);

        return Result.success(dish);

    }

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> getDishByCategoryId(Integer categoryId) {

        log.info("根据分类id查询菜品：{}", categoryId);

        List<Dish> dishList = dishService.getDishByCategoryId(categoryId);

        return Result.success(dishList);

    }

    /**
     * 批量删除菜品
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除菜品")
    public Result<String> deleteDishByIds(Long[] ids) {

        log.info("批量删除菜品：{}", (Object[]) ids);

        dishService.deleteDishByIds(ids);

        return Result.success();

    }

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result<String> addNewDish(@RequestBody DishDTO dishDTO) {

        log.info("新增菜品：{}", dishDTO);

        dishService.addNewDish(dishDTO);

        return Result.success();

    }

    /**
     * 菜品起售、停售
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售、停售")
    public Result<String> editDishStatus(@PathVariable Integer status, Long id) {

        log.info("菜品起售、停售：{},{}", status, id);

        dishService.editDishStatus(status, id);

        return Result.success();

    }
}
