package com.lsd.transaction.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsd.transaction.dao.TestDao;
import com.lsd.transaction.entity.Stu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lsd
 * 2020-08-31 11:41
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestDao, Stu> implements TestService {

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional
    @Override
    public void insert1(Stu student) {
        this.save(student);
//        throw new RuntimeException("insert1抛异常...");
    }

    @Transactional
    @Override
    public void insert2(Stu student) {
        this.save(student);
        throw new RuntimeException("insert2抛异常...");
    }

    /**
     * 事务失效--自调用
     */
    @Override
    public void insert12(Stu student1, Stu student2) {
        // 不管是哪个事务方法抛出异常都会回滚失败
        this.insert1(student1);
        this.insert2(student2);
    }

    /**
     * 事务失效--被调用方法的事务传播行为设置为PROPAGATION_REQUIRES_NEW,导致产生两个独立的事务,外围方法抛出异常只回滚和外围方法同一事务的方法。
     */
    @Transactional
    @Override
    public void insert3(Stu student) {
        this.save(student);
        //排除自调用的影响，因为insert4方法新开了一个独立的事务，所以下边即使insert3方法抛出异常也不会使insert4方法的事务回滚
        applicationContext.getBean(TestService.class).insert4(new Stu().setSname("独立事务"));
        // insert5是非独立事务，所以与insert3抛出异常也能正常回滚
//        applicationContext.getBean(TestService.class).insert5(new Stu().setSname("继承事务"));
        throw new RuntimeException("insert3抛异常...");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert4(Stu student) {
        this.save(student);
    }

    @Transactional
    @Override
    public void insert5(Stu student) {
        this.save(student);
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void insert6(Stu student) {
        this.save(student);
        throw new RuntimeException("insert6抛异常...");
    }


    /**
     * 使用 nested 传播级别 + 手动捕获异常 实现小事务抛出异常独立回滚而大事务仍然可以提交。
     * <p>
     * 传播级别为nested时，实际上只存在一个事务，只是在调用a方法时设置了一个保存点，
     * 当a方法回滚时，实际上是回滚到保存点上，并且当外部事务提交时，内部事务才会提交，外部事务如果回滚，内部事务会跟着回滚。
     */
    @Transactional
    @Override
    public void insert7(Stu student) {
        this.save(student);
        //排除自调用的影响，因为insert6方法开了一个nested嵌套事务，insert6方法抛异常独立回滚insert7的事务仍然可以提交
        try {
            applicationContext.getBean(TestService.class).insert6(new Stu().setSname("nested嵌套事务"));
        } catch (Exception e) {
            System.out.println("手动捕获掉nested嵌套事务抛出的异常，从而不要影响insert7事务的提交...");
        }
    }

}
