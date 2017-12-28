package cn.et.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.DeptMapper;
import cn.et.food.dao.EmpMapper;
import cn.et.food.dao.StudentMapper;
import cn.et.food.entity.Dept;
import cn.et.food.entity.DeptExample;
import cn.et.food.entity.Emp;
import cn.et.food.entity.EmpExample;
import cn.et.food.entity.Student;
import cn.et.food.entity.StudentExample;
import cn.et.food.entity.TreeNode;
import cn.et.food.service.DeptService;
import cn.et.food.utils.PageTools;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired 
	DeptMapper sm;
	
	@Autowired
	EmpMapper em;
	public List<TreeNode> queryTreeNode(Integer pid) {
		DeptExample se=new DeptExample();
		se.createCriteria().andPidEqualTo(pid);
		List<Dept> dept=sm.selectByExample(se);
		List<TreeNode> deptList=new ArrayList<TreeNode>();
		for(Dept d:dept){
			TreeNode tn=new TreeNode();
			tn.setId(d.getId());
			tn.setText(d.getDname());
			//�жϵ�ǰ�ڵ����Ƿ񻹴����ӽڵ�
			if(queryTreeNode(d.getId()).size()==0){
				tn.setState("open");
			}
			deptList.add(tn);
		}
		return deptList;
	}
	
	public List<Emp> queryEmp(Integer id){  //��ѯ��һ�����ŵ�����Ա��
		EmpExample ee = new EmpExample();
		if(id!=null)
			ee.createCriteria().andDeptidEqualTo(id);
		return em.selectByExample(ee);
	}
	
	public void saveEmp(Emp emp){//Ա������
		em.insertSelective(emp);
	}
	public void deleteEmp(String id){  //ɾ��Ա��
		String array[] = id.split(",");
		for (int i = 0; i < array.length; i++) {
			em.deleteByPrimaryKey(Integer.parseInt(array[i]));
		}
	}
	
	public void updateEmp(Emp emp){   //�޸�Ա��
		em.updateByPrimaryKey(emp);
	}
	
//	@Autowired
//	public List<Emp> queryLike(String ename,Integer id){
//		if(ename==null){
//			ename="";
//		}
//		EmpExample ee = new EmpExample();
//		ee.createCriteria().andEnameLike("%"+ename+"%");
//		List<Emp> empList=em.selectByExample(ee);
//		
//		return empList;
//	}
}
