package directory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private static final String DEFAULT_NAME_VALUE = "Guest";

    @GetMapping({"/","/main"})
    public String main(/*HttpServletRequest request,*/ Model model){
//        HttpSession session = request.getSession(false);
//        if (Objects.nonNull(session)) {
//            model.addAttribute("name", session.getAttribute("name"));
//        } else {
//            model.addAttribute("name",DEFAULT_NAME_VALUE);
//        }
        model.addAttribute("name", DEFAULT_NAME_VALUE);
        return "main.html";
    }
}
