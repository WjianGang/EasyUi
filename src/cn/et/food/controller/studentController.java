package cn.et.food.controller;



import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.et.food.entity.Result;
import cn.et.food.entity.Student;
import cn.et.food.service.StudentService;
import cn.et.food.utils.PageTools;

@Controller
public class studentController {
	@Autowired
	StudentService service;
	@ResponseBody
	@RequestMapping("/queryStudent")
	//@RequestParam(required=false)加上这个可以解决500这个错误，这个代表不需要家?sname=xxx也可以运行
	public PageTools queryStudent( String sname,Integer page,Integer rows){
		return service.queryStudent(sname,page,rows);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/student/{sid}",method=RequestMethod.DELETE)
	public Result deleteSutdent(@PathVariable String sid,Integer page,Integer rows){
		Result re = new Result();
		re.setCode(1);
		try {
			service.deleteStudent(sid);
		} catch (Exception e) {
			re.setCode(0);
			re.setMessage("删除失败");
		}
		return re;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/student/{sid}",method=RequestMethod.PUT)
	public Result updateSutdent(@PathVariable Integer sid,Student stu,Integer page,Integer rows){
		stu.setSid(sid);
		Result re = new Result();
		re.setCode(1);
		try {
			service.updateStudent(stu);
		} catch (Exception e) {
			re.setCode(0);
			re.setMessage("修改失败");
		}
		return re;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/student",method=RequestMethod.POST)
	public Result saveSutdent(Student student,MultipartFile myImage){
		Result re = new Result();
		re.setCode(1);
		try {
			String fileName=myImage.getOriginalFilename();
			File destFile=new File("E:\\myImage\\"+fileName);
			myImage.transferTo(destFile);
			//service.saveStudent(student);
		} catch (Exception e) {
			re.setCode(0);
			re.setMessage("增加失败");
		}
		return re;
		
	}
}
