package com.lsd.transaction.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by lsd
 * 2020-08-31 11:36
 */
@Accessors(chain = true)
@Data
public class Stu {

    @TableId
    private String sno;
    private String sname;
    private String sex;
    private Integer age;
    private String department;
    private String bplace;

}
