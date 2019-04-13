package directory.controllers;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService repository;

    public UserController(UserService repository) {
        this.repository = repository;
    }

    @GetMapping("/login")
    public String loginPage(){

        return "login.html";
    }

    @GetMapping("/admin")
    public RedirectView admin() {
        return new RedirectView("/show");
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add.html";
    }

    @PostMapping("/add")
    public RedirectView addingUser(@ModelAttribute User user) {
        repository.save(user);
        return new RedirectView("/show", true);
    }

    @GetMapping("/show")
    public String showUsers(Model model) {
        List<User> users = repository.loadAll();
        model.addAttribute("users", users);
        return "show.html";
    }
}
