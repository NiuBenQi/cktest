package com.lemon.service;

import com.lemon.common.ApiVO;
import com.lemon.common.CaseEditVO;
import com.lemon.common.CaseListVO;
import com.lemon.pojo.Cases;
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
public interface CasesService extends IService<Cases> {
    public void add(Cases caseVo, ApiVO apiRunVo);

    List<CaseListVO> showCaseUnderProject(Integer projectId);

    List<CaseListVO> showCaseUnderSuite(String suiteId);

    CaseEditVO findCaseEditVo(String caseId);

    void updateCase(CaseEditVO caseEditVO);
}
