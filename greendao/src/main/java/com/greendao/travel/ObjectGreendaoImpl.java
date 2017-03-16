package com.greendao.travel;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


public class ObjectGreendaoImpl {

    public static void main(String[]args){
        //两个参数分别代表：数据库版本号与自动生成代码的包路径。
        Schema schema = new Schema(1,"com.lgz.bean");
//        schema.setDefaultJavaPackageDao("com.lyx.dao");
        addBean(schema);
        try {
            /**
             * 最后我们将使用DAOGenerator类的generateAll()方法自动生成代码，
             * 此处你需要根据自己的情况更改输出目录（既之前创建的java-gen)。
             * 其实，输出目录的路径可以在build.gradle中设置。请看上面的截图
             */
            new DaoGenerator().generateAll(schema,"E:/AndroidStudio/Program/Travel/app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static  void addBean(Schema schema){
        Entity entity = schema.addEntity("Student");//一个实体（类）就关联到数据库中的一张表，表名为Student
        //以下配置为数据库表中的字段
        entity.addIdProperty();
        entity.addStringProperty("name");
        entity.addStringProperty("sex");
        entity.addStringProperty("address");
        entity.addIntProperty("age");
    }
}
