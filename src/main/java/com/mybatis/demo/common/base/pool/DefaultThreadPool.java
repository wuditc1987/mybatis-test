package com.mybatis.demo.common.base.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2020/4/18
 * @description TODO
 */
public class DefaultThreadPool<T extends Runnable> implements ThreadPool<T> {

    private static final int MAX_WORKER_NUMBERS = 10;

    private static final int DEFAULT_WORKER_NUMBERS = 5;

    private static final int MIN_WORKER_NUMBERS = 1;

    private final LinkedList<T> jobs = new LinkedList<>();

    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    private int workerNum = DEFAULT_WORKER_NUMBERS;

    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool(){
        init(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num){
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : (Math.max(num, MIN_WORKER_NUMBERS));
        init(workerNum);
    }

    @Override
    public void execute(T job) {
        if(null != job){
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notifyAll();
            }
        }
    }

    @Override
    public void shutdown() {
        for(Worker worker : workers){
            worker.shutdown();
        }
    }

    @Override
    public void addJobs(int num) {
        synchronized (jobs){
            if(num + this.workerNum > MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            if(num > 0){
                init(num);
                this.workerNum += num;
            }
        }
    }

    @Override
    public void addJob(T job){
        synchronized (jobs){
            if(this.workerNum < MAX_WORKER_NUMBERS){
                init(1);
                this.workerNum += 1;
            }
        }
    }

    @Override
    public void removeJobs(int num) {
        synchronized (jobs){
            if(num >= this.workerNum){
                throw new IllegalArgumentException("beyond worker num");
            }
            //按照给定的数目停止线程
            int count = 0;
            while (count < num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count ++;
                }
            }
            this.workerNum --;
        }
    }

    @Override
    public int getJobSize() {
        return workerNum;
    }

    /**
     * 初始化线程
     * @param num
     */
    private void init(int num){
        for (int i = 0; i < num; i++){
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-Worker-"+threadNum.incrementAndGet());
            thread.start();
        }
    }

    /**
     * 工作者
     */
    class Worker implements Runnable{

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running){
                T job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        }catch (InterruptedException e){
                            //感知到外部对workerThread的中断操作，直接返回
                            Thread.currentThread().interrupt();
                        }
                    }
                    //取出一个job
                    job = jobs.removeFirst();
                }
                if(job != null){
                    try {
                        job.run();
                    }catch (Exception e){
                        //ignore
                    }
                }
            }
        }
        //通过修改volatile变量来刷新内存中的值，通知其他线程此job已经停止了。
        public void shutdown(){
            running = false;
        }
    }
}
