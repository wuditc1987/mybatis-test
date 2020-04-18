package com.mybatis.demo.common.base.pool;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2020/4/18
 * @description TODO
 */
public interface ThreadPool<T extends Runnable> {

    /**
     * 执行工作线程
     * @param job
     */
    void execute(T job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 添加工作线程
     * @param num
     */
    void addJobs(int num);

    /**
     * 添加一个工作线程
     * @param job
     */
    void addJob(T job);

    /**
     * 删除工作线程
     * @param num
     */
    void removeJobs(int num);

    /**
     * 获得工作线程数量
     * @return
     */
    int getJobSize();
}
