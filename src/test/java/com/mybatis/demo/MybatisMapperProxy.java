package com.mybatis.demo;

import com.mybatis.demo.entity.SysUser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wudi
 * @version: 1.0.0
 * @date: 2019/12/27
 * @description Mybatis Mapper 代理类
 */
public class MybatisMapperProxy implements InvocationHandler {

	public <T> T newInstance(Class<T> clz) {
		return (T) Proxy.newProxyInstance(clz.getClassLoader(), new Class[] { clz }, this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		if (Object.class.equals(method.getDeclaringClass())) {
			try {
				// 诸如hashCode()、toString()、equals()等方法，将target指向当前对象this
				return method.invoke(this, args);
			} catch (Throwable t) {
			}
		}
		// 投鞭断流
//		return new SysUser((Integer) args[0], "zhangsan", 18);
		return new SysUser(17,"zero");
	}
}
