package ch.zli.m223.crm.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.zli.m223.crm.model.AppUser;
import ch.zli.m223.crm.service.UserService;

@Controller
@RequestMapping("/web/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("")
	public String getAllUsers(Model model) {
		List<AppUser> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "userList";
	}
	
	@GetMapping("/{id}")
	public String getOneUserById(@PathVariable("id") long id, Model model) {
		AppUser user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user";
	}
	
	@GetMapping("/{id}/delete")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		userService.deleteById(id);
		return getAllUsers(model);
	}
	
	@GetMapping("/adduserform")
	public String addAnUserForm() {
		return "addUserForm";
	}
	
	@PostMapping("/adduser")
	public String addAnUser(Model model, @RequestParam String email, @RequestParam String password) {
		userService.addUser(email, password);
		return getAllUsers(model);
	}
}
