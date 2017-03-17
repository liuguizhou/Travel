package com.travel.liuyun.greendao;

import com.travel.liuyun.TravelApplication;
import com.travel.liuyun.bean.Student;

import java.util.List;

/**
 * Created by liuguizhou on 2017/3/17.
 */

public class GreenDaoManager {

    /**
     * 添加数据
     *
     * @param student
     */
    public static void insertStudent(Student student) {
        TravelApplication.getDaoInstant().getStudentDao().insert(student);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteStudent(long id) {
        TravelApplication.getDaoInstant().getStudentDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param student
     */
    public static void updateStudent(Student student) {
        TravelApplication.getDaoInstant().getStudentDao().update(student);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<Student> queryStudent(Student studentId) {
        return TravelApplication.getDaoInstant().getStudentDao().queryBuilder().where(StudentDao.Properties.Name.eq(studentId.getName())).list();
    }
}
