package com.lemon.service;

import com.lemon.pojo.Suite;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
public interface SuiteService extends IService<Suite> {

    List<Suite> findSuitAndReleadtedCasesBy(Integer projectId);
}
