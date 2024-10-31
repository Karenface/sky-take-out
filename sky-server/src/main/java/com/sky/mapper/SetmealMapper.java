package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealMapper {

    /**
     * 修改套餐
     *
     * @param setmealDTO
     */
    void editSetmeal(SetmealDTO setmealDTO);

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    Page<Setmeal> setmealPageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增套餐
     *
     * @param setmeal
     */
    void addNewSetmeal(Setmeal setmeal);

    /**
     * 套餐起售、停售
     *
     * @param setmeal
     */
    void editSetmealStatus(Setmeal setmeal);

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
