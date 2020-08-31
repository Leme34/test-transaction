package com.lsd.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsd.transaction.entity.Stu;

/**
 * Created by lsd
 * 2020-08-31 11:33
 */
public interface TestService extends IService<Stu> {

    void insert1(Stu student);

    void insert2(Stu student);

    void insert12(Stu student1, Stu student2);

    void insert3(Stu student);

    void insert4(Stu student);

    void insert5(Stu student);

    void insert6(Stu student);

    void insert7(Stu student);

}
