package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/setmeal")
@RestController
@Slf4j
@Api(tags = "套餐相关接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 修改套餐
     *
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public Result<String> editSetmeal(@RequestBody SetmealDTO setmealDTO) {

        log.info("修改套餐：{}", setmealDTO);

        setmealService.editSetmeal(setmealDTO);

        return Result.success();

    }

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    @RequestMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> setmealPageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {

        log.info("分页查询：{}", setmealPageQueryDTO);

        PageResult pageResult = setmealService.setmealPageQuery(setmealPageQueryDTO);

        return Result.success(pageResult);

    }

    /**
     * 新增套餐
     *
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result<String> addNewSetmeal(@RequestBody SetmealDTO setmealDTO) {

        log.info("新增套餐：{}", setmealDTO);

        setmealService.addNewSetmeal(setmealDTO);

        return Result.success();

    }

    /**
     * 套餐起售、停售
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售、停售")
    public Result<String> editSetmealStatus(@PathVariable Integer status, Long id) {

        log.info("套餐起售、停售：{}", status);

        setmealService.editSetmealStatus(status, id);

        return Result.success();

    }

    /**
     * 批量删除套餐
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public Result<String> deleteSetmealByIds(Long[] ids) {

        log.info("批量删除套餐：{}", (Object[]) ids);

        setmealService.deleteSetmealByIds(ids);

        return Result.success();

    }

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<Setmeal> getSetmealById(@PathVariable Long id) {

        log.info("根据id查询套餐：{}", id);

        Setmeal setmeal = setmealService.getSetmealById(id);

        return Result.success(setmeal);

    }


}
