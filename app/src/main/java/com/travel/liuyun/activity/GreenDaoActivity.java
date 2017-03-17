package com.travel.liuyun.activity;

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
import com.travel.liuyun.greendao.GreenDaoManager;

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_greendao;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        studentAdapter = new StudentAdapter();
        recyclerView.setAdapter(studentAdapter);
    }

    @OnClick({R.id.add, R.id.delete, R.id.update, R.id.query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                addStudent();
                break;
            case R.id.delete:
                deleteStudent(100);
                break;
            case R.id.update:
                Student student = new Student();
                student.setName("刘贵州");
                student.setAge(26);
                student.setTelephone("15256298063");
                student.setFamilyAddress("安徽省阜阳市礼泉县刘庄村");
                student.setSex("nv");
                updateStudent(student);
                break;
            case R.id.query:
                Student stu = new Student();
                stu.setName("刘贵州");
                studentList = queryStudent(stu);
                if (studentList != null && studentList.size() > 0) {
                    studentAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void addStudent() {
        Student student = null;
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
            View view = LayoutInflater.from(GreenDaoActivity.this).inflate(R.layout.student_list_item, null);
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
