package com.ourfood.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ourfood.util.VerifyCodeUtils;

@Controller
public class VcodeAction {
	
	@RequestMapping("vcode.do")
	protected void vcode(Model model, HttpServletResponse response) throws IOException
			{
		/**
		* 页面中图片的设置
		* <img src="vc.servlet" onclick='src="vc.servlet?"+new Date()'>
		* Servlet动态获取验证码
		* */
		// 定义图片大小
		int w = 200, h = 80;
		// 随机生成验证码
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		System.out.println(verifyCode);
		/**
		 * 将验证码添加到会话中，注意：在会话中保存的验证码的名称 vscode
		 */
		model.addAttribute("vscode",verifyCode);
		// 图形写给浏览器  
		response.setContentType("image/jpeg");
		// 发头控制浏览器不要缓存  
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 输出图片
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);

	}

	
}
