package com.sky.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 修改套餐
     *
     * @param setmealDTO
     */
    @Override
    public void editSetmeal(SetmealDTO setmealDTO) {

        Setmeal.builder()
                .id(setmealDTO.getId())
                .categoryId(setmealDTO.getCategoryId())
                .name(setmealDTO.getName())
                .price(setmealDTO.getPrice())
                .status(setmealDTO.getStatus())
                .description(setmealDTO.getDescription())
                .image(setmealDTO.getImage())
                .build();

        setmealMapper.editSetmeal(setmealDTO);

    }

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult setmealPageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {

        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());

        Page<Setmeal> page = setmealMapper.setmealPageQuery(setmealPageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());

    }

    /**
     * 新增套餐
     *
     * @param setmealDTO
     */
    @Override
    public void addNewSetmeal(SetmealDTO setmealDTO) {

        Setmeal setmeal = Setmeal.builder()
                .categoryId(setmealDTO.getCategoryId())
                .name(setmealDTO.getName())
                .price(setmealDTO.getPrice())
                .status(setmealDTO.getStatus())
                .description(setmealDTO.getDescription())
                .image(setmealDTO.getImage())
                .build();

        setmealMapper.addNewSetmeal(setmeal);
    }

    /**
     * 修改套餐状态
     *
     * @param status
     * @param id
     */
    @Override
    public void editSetmealStatus(Integer status, Long id) {

        Setmeal setmeal = Setmeal.builder()
                .id(id)
                .status(status)
                .build();

        setmealMapper.editSetmealStatus(setmeal);
    }

    /**
     * 批量删除套餐
     *
     * @param ids
     */
    @Override
    public void deleteSetmealByIds(Long[] ids) {

        setmealMapper.deleteSetmealByIds(ids);

    }

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal getSetmealById(Long id) {

        return setmealMapper.getSetmealById(id);

    }
}
