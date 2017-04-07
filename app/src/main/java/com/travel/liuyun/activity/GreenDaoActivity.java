package com.travel.liuyun.activity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.bean.Student;
import com.travel.liuyun.database.MyDataBaseHelper;
import com.travel.liuyun.greendao.GreenDaoManager;
import com.travel.liuyun.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.travel.liuyun.greendao.GreenDaoManager;

/**
 * Created by liuguizhou on 2017/3/17.
 */

public class GreenDaoActivity extends BaseActivity {
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.update)
    Button update;
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<Student> studentList;
    private StudentAdapter studentAdapter;
    private MyDataBaseHelper dbHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_greendao;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        dbHelper = new MyDataBaseHelper(this, "liuguizhou", null, 1);
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        studentAdapter = new StudentAdapter();
        recyclerView.setAdapter(studentAdapter);
    }

    @OnClick({R.id.add, R.id.delete, R.id.update, R.id.query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
//                addStudent();
                insertStu();
                break;
            case R.id.delete:
                deleteStudent(100);
                break;
            case R.id.update:
//                Student student = new Student();
//                student.setName("刘贵州");
//                student.setAge(26);
//                student.setTelephone("15256298063");
//                student.setFamilyAddress("安徽省阜阳市礼泉县刘庄村");
//                student.setSex("nv");
//                updateStudent(student);
                updateStu();
                break;
            case R.id.query:
//                Student stu = new Student();
//                stu.setName("刘贵州");
//                studentList = queryStudent(stu);
                studentList = queryStu();
                if (studentList != null && studentList.size() > 0) {
                    studentAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void insertStu() {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("正在写入数据....");
        dialog.show();
        new Thread() {
            @Override
            public void run() {
                ContentValues values = new ContentValues();
                for (int i = 0; i < 30000; i++) {
                    values.put("name", "liuguizhou" + i);
                    values.put("sex", "nan");
                    values.put("age", 25 + i);
                    values.put("telephone", "15256898989");
                    values.put("familyAddress", "南京市");
                    db.insert("stu_table", null, values);
                }
                db.close();
                dialog.dismiss();
            }
        }.start();


    }

    private List<Student> queryStu() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String sql = "select * from stu_table order by age desc";//查询表中所有，并按照age降序排列
        String sql = "select * from stu_table limit 9,5";//查询5条数据，起始位置为9（但不包含）
////        String sql = "select * from stu_table where age > 27 and age < 30 order by age desc";//查询age在大于27而小于30之间的数据，降序排列
        Cursor cursor = db.rawQuery(sql, null);

//        //参数1：表名
//        //参数2：要想显示的列
//        //参数3：where子句
//        //参数4：where子句对应的条件值
//        //参数5：分组方式
//        //参数6：having条件
//        //参数7：排序方式
//        Cursor cursor = db.query("stu_table", new String[]{"_id", "name", "age", "sex"}, "_id=?", new String[]{"1"}, null, null, null);

        List<Student> studentList = new ArrayList<Student>();
        if (cursor != null && cursor.getCount() > 0) {
            Student student = null;
            while (cursor.moveToNext()) {
                student = new Student();
                student.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                student.setName(cursor.getString(cursor.getColumnIndex("name")));
                student.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                student.setAge(cursor.getInt(cursor.getColumnIndex("age")));
//                student.setTelephone(cursor.getString(cursor.getColumnIndex("telephone")));
//                student.setFamilyAddress(cursor.getString(cursor.getColumnIndex("familyAddress")));
                studentList.add(student);
            }
            cursor.close();
            db.close();
            return studentList;
        }
        return null;
    }

    private void updateStu() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "update stu_table set name='lgj' where _id=10  ";//修改id为10的那条数据的name值
        db.execSQL(sql);
    }

    private void addStudent() {
        Student student;
        for (int i = 0; i < 100; i++) {
            student = new Student();
            student.setName("刘贵州");
            student.setAge(24);
            student.setFamilyAddress("安徽省阜阳市礼泉县刘庄村");
            student.setSex("男");
            GreenDaoManager.insertStudent(student);
        }

    }

    private void deleteStudent(int id) {
        GreenDaoManager.deleteStudent(id);
    }

    private void updateStudent(Student student) {
        GreenDaoManager.updateStudent(student);
    }

    private List<Student> queryStudent(Student student) {
        return GreenDaoManager.queryStudent(student);
    }


    public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(GreenDaoActivity.this).inflate(R.layout.student_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Student student = studentList.get(position);
            holder.name.setText(student.getName());
            holder.age.setText(String.valueOf(student.getAge()));
            holder.sex.setText(student.getSex());
            holder.telephone.setText(student.getTelephone());
            holder.address.setText(student.getFamilyAddress());
        }

        @Override
        public int getItemCount() {
            return studentList == null ? 0 : studentList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.name)
            TextView name;
            @BindView(R.id.sex)
            TextView sex;
            @BindView(R.id.age)
            TextView age;
            @BindView(R.id.telephone)
            TextView telephone;
            @BindView(R.id.address)
            TextView address;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }

    }

}
