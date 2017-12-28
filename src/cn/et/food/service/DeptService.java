package cn.et.food.service;

import java.util.List;

import cn.et.food.entity.Emp;
import cn.et.food.entity.TreeNode;

public interface DeptService {
	public abstract List<TreeNode> queryTreeNode(Integer pid);
	public List<Emp> queryEmp(Integer id);
	public void saveEmp(Emp emp);
	public void deleteEmp(String id);
	public void updateEmp(Emp emp);
	//public List<Emp> queryLike(String ename,Integer id);
}