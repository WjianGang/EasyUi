package cn.et.food.service;

import cn.et.food.entity.Student;
import cn.et.food.utils.PageTools;

public interface StudentService {
	public PageTools queryStudent(String sname,Integer page,Integer rows);
	public void deleteStudent(String sid);
	public void updateStudent(Student stu);
	public void saveStudent(Student student);
}