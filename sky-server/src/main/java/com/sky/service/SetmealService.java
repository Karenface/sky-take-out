package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;

public interface SetmealService {

    /**
     * 修改套餐
     *
     * @param setmealDTO
     */
    void editSetmeal(SetmealDTO setmealDTO);

    /**
     * 分页查询
     *
     * @param setmealDTO
     * @return
     */
    PageResult setmealPageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增套餐
     *
     * @param setmealDTO
     */
    void addNewSetmeal(SetmealDTO setmealDTO);

    /**
     * 套餐起售、停售
     *
     * @param status
     * @param id
     */
    void editSetmealStatus(Integer status, Long id);

    /**
     * 批量删除套餐
     *
     * @param ids
     */
    void deleteSetmealByIds(Long[] ids);

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    Setmeal getSetmealById(Long id);
}
