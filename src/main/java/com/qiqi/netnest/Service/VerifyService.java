package com.qiqi.netnest.Service;

import com.qiqi.netnest.Vo.Result;

public interface VerifyService {

    Result<String> resetPass(String pass);
    Result<String> verify(String pass);
}
