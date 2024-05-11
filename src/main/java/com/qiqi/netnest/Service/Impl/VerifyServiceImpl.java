package com.qiqi.netnest.Service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qiqi.netnest.Enum.ReturnCode;
import com.qiqi.netnest.Exception.BusinessException;
import com.qiqi.netnest.Mapper.PasswdMapper;
import com.qiqi.netnest.Pojo.Passwd;
import com.qiqi.netnest.Service.VerifyService;
import com.qiqi.netnest.Utils.JwtUtil;
import com.qiqi.netnest.Utils.PasswdUtil;
import com.qiqi.netnest.Vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyServiceImpl implements VerifyService {
    private static final Logger logger = LoggerFactory.getLogger(VerifyServiceImpl.class);
    @Autowired
    private PasswdMapper passwdMapper;

    @Override
    public Result<String> resetPass(String pass) {
        if (StrUtil.isEmptyIfStr(pass)) {
            return Result.error(ReturnCode.RC500.getCode(), "密码为空");
        }
        String password = PasswdUtil.generatePass(pass);
        Passwd passwd = new Passwd("admin", password);
        Passwd ps = passwdMapper.selectOne(new QueryWrapper<Passwd>().eq("role", "admin"));
        if (ps == null) {
            try {
                passwdMapper.insert(passwd);
                return Result.ok(ReturnCode.RC200.getCode(), "设置密码成功");
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error(500, e.getMessage());
            }
        }
        try {
            passwdMapper.update(passwd, new UpdateWrapper<Passwd>().eq("role", "admin"));
            return Result.ok(ReturnCode.RC200.getCode(), "重设密码成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, e.getMessage());
        }
    }

    @Override
    public Result<String> verify(String pass) {
        if (StrUtil.isEmptyIfStr(pass)) {
            return Result.error(ReturnCode.RC500.getCode(), "密码为空");
        }
        Passwd ps = passwdMapper.selectOne(new QueryWrapper<Passwd>().eq("role", "admin"));
        if (ps == null) {
            logger.error("未设置admin密码!");
            throw new BusinessException(ReturnCode.RC500);
        }
        boolean b = PasswdUtil.judPass(pass, ps.getPassword());
        if (!b) {
            return Result.error(ReturnCode.RC500.getCode(), "密码错误");
        }
        return Result.success(JwtUtil.createToken());
    }
}
