package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 修改分类
     *
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改分类")
    public Result<String> editCategory(@RequestBody CategoryDTO categoryDTO) {

        log.info("修改分类：{}", categoryDTO);

        categoryService.editCategory(categoryDTO);

        return Result.success();

    }

    /**
     * 分类分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public Result<PageResult> categoryPageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {

        log.info("分页查询分类：{}", categoryPageQueryDTO);

        PageResult pageResult = categoryService.categoryPageQuery(categoryPageQueryDTO);

        return Result.success(pageResult);

    }

    /**
     * 启用、禁用分类
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用、禁用分类")
    public Result<String> enableOrDisableCategory(@PathVariable Integer status, Long id) {

        log.info("启用、禁用分类：{},{}", status, id);

        categoryService.enableOrDisableCategory(status, id);

        return Result.success();
    }

    /**
     * 新增分类
     *
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增分类")
    public Result<String> addNewCategory(@RequestBody CategoryDTO categoryDTO) {

        log.info("新增分类：{}", categoryDTO);

        categoryService.addNewCategory(categoryDTO);

        return Result.success();

    }

    /**
     * 根据id删除分类
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("根据id删除分类")
    public Result<String> deleteCategory(Long id) {

        log.info("根据id删除分类：{}", id);

        categoryService.deleteCategory(id);

        return Result.success();

    }

    /**
     * 根据类型查询分类
     *
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> list(Integer type) {

        log.info("根据类型查询分类：{}", type);

        List<Category> list = categoryService.getCategoryByType(type);

        return Result.success(list);

    }
}
