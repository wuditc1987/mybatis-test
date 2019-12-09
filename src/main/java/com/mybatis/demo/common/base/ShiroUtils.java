package com.mybatis.demo.common.base;

import com.mybatis.demo.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

/**
 * @author wudi
 * @version 1.0
 * @Description TODO
 * @date 2019/9/29 4:55 PM
 */
public class ShiroUtils {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";

    private ShiroUtils(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * 生成随机盐
     * @return String
     */
    public static String randomSalt(){
        return randomSalt(ShiroConstants.RANDOM_SALT_LENGTH);
    }

    public static String randomSalt(int length){
        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        //返回16进制字符
        return generator.nextBytes(length).toHex();
    }

    public static void encryptPassword(SysUser user){
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
//        String newPassword = new SimpleHash(algorithmName,user.getPassword(),ByteSource.Util.bytes(user.get))
    }

    public static SysUser getSysUser(){
        return (SysUser) SecurityUtils.getSubject();
    }
}
