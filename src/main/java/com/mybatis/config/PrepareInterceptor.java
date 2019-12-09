package com.mybatis.config;

import com.mybatis.util.ReflectUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author wudi
 * @version 1.0
 * @Description 拦截器
 * @date 2019/11/24 6:14 PM
 */
@Intercepts(
        { @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class, Integer.class }),
          @Signature(method = "update",  type = Executor.class, args = { MappedStatement.class, Object.class }),
          @Signature(method = "query",   type = Executor.class, args = { MappedStatement.class, Object.class,
                        RowBounds.class, ResultHandler.class })}
)
public class PrepareInterceptor implements Interceptor {

    private static final String FIELDNAME_DELEGATE = "delegate";
    private static final String FIELDNAME_MAPPEDSTATMENT = "mappedStatement";

    private static final Logger LOGGER = LoggerFactory.getLogger(PrepareInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = getRealTargetFromProxy(invocation.getTarget());
        if (target instanceof StatementHandler) {
            return prepareStatement(invocation, (StatementHandler) target);
        }
        if (target instanceof Executor) {
            return checkExecuteResult(invocation);
        }

        return invocation.proceed();
    }

    private Object prepareStatement(Invocation invocation, StatementHandler handler) throws Throwable {
        Object prepStatHandler = ReflectUtils.getFieldValue(handler, FIELDNAME_DELEGATE);
        if (prepStatHandler == null) {
            return invocation.proceed();
        }

        Object mappedStmtObj = ReflectUtils.getFieldValue(prepStatHandler, FIELDNAME_MAPPEDSTATMENT);
        if (mappedStmtObj == null) {
            return invocation.proceed();
        }
        MappedStatement mappedStmt = (MappedStatement) mappedStmtObj;
        BoundSql boundSql = handler.getBoundSql();
        String newSql = null;
        try {
            newSql = "";
        }catch (Exception e){

        }

        return invocation.proceed();
    }

    private Object checkExecuteResult(Invocation invocation) throws Throwable {

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }


    private Object getRealTargetFromProxy(Object target) throws Throwable {
        if (target instanceof Proxy) {
            Object proxy = ReflectUtils.getFieldValue(target, "h");
            Object proxyTarget = ReflectUtils.getFieldValue(proxy, "target");
            return getRealTargetFromProxy(proxyTarget);
        }
        return target;
    }
}
