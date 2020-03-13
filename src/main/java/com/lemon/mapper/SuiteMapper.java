package com.lemon.mapper;

import com.lemon.pojo.Suite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
public interface SuiteMapper extends BaseMapper<Suite> {

//    @Select("SELECT * from suite where project_id=#{projectId}")
//    @Results({
//            @Result(column = "id",property = "id"),
//            @Result(column = "id",property = "suite",many = @Many(select = "com.lemon.mapper.SuiteMapper.findAll"))
//    })
//    public List<Suite> findSuitAndReleadtedCasesBy(Integer projectId){
//
//    }
}
