package com.lemon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.ApiVO;
import com.lemon.common.CaseEditVO;
import com.lemon.common.CaseListVO;
import com.lemon.pojo.ApiRequestParam;
import com.lemon.pojo.CaseParamValue;
import com.lemon.pojo.Cases;
import com.lemon.mapper.CasesMapper;
import com.lemon.pojo.User;
import com.lemon.service.CaseParamValueService;
import com.lemon.service.CasesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.service.TestRuleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author benqi
 * @since 2020-02-14
 */
@Service
public class CasesServiceImpl extends ServiceImpl<CasesMapper, Cases> implements CasesService {

    @Autowired
    CaseParamValueService caseParamValueService;

    @Autowired
    CasesMapper casesMapper;

    @Autowired
    TestRuleService testRuleService;

    @Override
    public void add(Cases caseVo, ApiVO apiRunVo) {
        // 添加到case
        this.save(caseVo);
        // 批量添加到case_param_value
        List<ApiRequestParam> requestParams = apiRunVo.getRequestParams();

        List<CaseParamValue> caseParamValues = new ArrayList<CaseParamValue>();
        for (ApiRequestParam apiRequestParam : requestParams) {
            CaseParamValue caseParamValue = new CaseParamValue();
            caseParamValue.setCaseId(caseVo.getId());
            caseParamValue.setApiRequestParamId(apiRequestParam.getId());
            caseParamValue.setApiRequestParamValue(apiRequestParam.getValue());
            caseParamValue.setCreateTime(new Date());

            Subject subject = SecurityUtils.getSubject();
            User loginUser =(User) subject.getPrincipal();
            caseParamValue.setCreateUser(loginUser.getId());
            caseParamValues.add(caseParamValue);
        }
        caseParamValueService.saveBatch(caseParamValues);
    }

    @Override
    public List<CaseListVO> showCaseUnderProject(Integer projectId) {

        return casesMapper.showCaseUnderProject(projectId);
    }

    @Override
    public List<CaseListVO> showCaseUnderSuite(String suiteId) {
        return casesMapper.showCaseUnderSuite(suiteId);
    }

    @Override
    public CaseEditVO findCaseEditVo(String caseId) {
        return casesMapper.findCaseEditVo(caseId);
    }

    @Override
    public void updateCase(CaseEditVO caseEditVO) {
        // 更新case表

        updateById(caseEditVO);
        // 更新case_param_value表
        List<ApiRequestParam> requestParams = caseEditVO.getRequestParams();

        List<CaseParamValue> list = new ArrayList<CaseParamValue>();
        for (ApiRequestParam apiRequestParam : requestParams){
            CaseParamValue caseParamValue = new CaseParamValue();
            caseParamValue.setId(apiRequestParam.getValueId());
            caseParamValue.setApiRequestParamId(apiRequestParam.getId());
            caseParamValue.setApiRequestParamValue(apiRequestParam.getValue());
            caseParamValue.setCaseId(caseEditVO.getId());

            list.add(caseParamValue);
        }
        caseParamValueService.updateBatchById(list);

        // 更新test_rule表  先删后插入
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("case_id",caseEditVO.getId());
        testRuleService.remove(queryWrapper);
        System.out.println("case_id 为："+caseEditVO.getId()+queryWrapper);
        testRuleService.saveBatch( caseEditVO.getTestRules());

    }
}
