package robic.tomislav.algebrahotel.algebrahotel.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("home")
    @PreAuthorize("hasAnyRole('ANONYMOUS')")
    public String HomeScreen() {

        return "home/home-screen";
    }
}
