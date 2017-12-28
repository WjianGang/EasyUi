package cn.et.food.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.food.entity.Emp;
import cn.et.food.entity.Result;
import cn.et.food.entity.TreeNode;
import cn.et.food.service.DeptService;

@Controller
public class DeptController {
	@Autowired
	DeptService service;


	@ResponseBody
	@RequestMapping("/queryDept")
	public List<TreeNode> queryTreeNode(Integer id){
		if(id==null){
			id=0;
		}
		return service.queryTreeNode(id);
	}
	
//	@ResponseBody
//	@RequestMapping("/queryLike")
//	public List<Emp> queryAll(String ename,Integer id){
//		if(id==null){
//			id=0;
//		}
//		return service.queryLike(ename, id);
//	}
	
	@ResponseBody
	@RequestMapping("/queryEmp")
	public List<Emp> queryEmp(Integer id){
		return service.queryEmp(id);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public Result saveEmp(Emp emp){
		Result re = new Result();
		re.setCode(1);
		try {
			service.saveEmp(emp);
		} catch (Exception e) {
			re.setCode(0);
			re.setMessage("Ôö¼ÓÊ§°Ü");
		}
		return re;
	}
	
	@ResponseBody
	@RequestMapping(value="/emp/{sid}",method=RequestMethod.DELETE)
	public Result deleteSutdent(@PathVariable String sid){
		Result re = new Result();
		re.setCode(1);
		try {
			service.deleteEmp(sid);
		} catch (Exception e) {
			re.setCode(0);
			re.setMessage("É¾³ýÊ§°Ü");
		}
		return re;
	}
	
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.PUT)
	public Result updateSutdent(@PathVariable Integer id,Emp emp){
		emp.setId(id);
		Result re = new Result();
		re.setCode(1);
		try {
			service.updateEmp(emp);
		} catch (Exception e) {
			re.setCode(0);
			re.setMessage("ÐÞ¸ÄÊ§°Ü");
		}
		return re;
		
	}
}
