package com.lsd.transaction.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsd.transaction.entity.Stu;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by lsd
 * 2020-08-31 11:32
 */
@Mapper
public interface TestDao extends BaseMapper<Stu> {


}
