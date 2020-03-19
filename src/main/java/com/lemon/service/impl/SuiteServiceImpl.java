package com.lemon.service.impl;

import com.lemon.pojo.Suite;
import com.lemon.mapper.SuiteMapper;
import com.lemon.service.SuiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
@Service
public class SuiteServiceImpl extends ServiceImpl<SuiteMapper, Suite> implements SuiteService {

    @Autowired
    SuiteMapper suiteMapper;

    @Override
    public List<Suite> findSuitAndReleadtedCasesBy(Integer projectId){
        return suiteMapper.findSuitAndReleadtedCasesBy(projectId);
    }
}
