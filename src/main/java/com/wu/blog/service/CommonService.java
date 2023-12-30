package com.wu.blog.service;

import com.wu.blog.pojo.Common;

public interface CommonService {
    Common findCommon();

    Common checkDiaryPASD(String password);

    void updataCommon(Common common);
}
