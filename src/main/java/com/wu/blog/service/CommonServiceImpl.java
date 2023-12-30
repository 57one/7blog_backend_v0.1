package com.wu.blog.service;

import com.wu.blog.dao.CommonDao;
import com.wu.blog.pojo.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonServiceImpl implements CommonService{

    @Autowired
    CommonDao commonDao;

    @Override
    @Transactional
    public Common findCommon() {
        return commonDao.findCommon();
    }

    @Override
    @Transactional
    public Common checkDiaryPASD(String password) {
        return commonDao.checkDiaryPASD(password);
    }

    @Override
    public void updataCommon(Common common) {
        commonDao.updateCommon(common);
    }
}
