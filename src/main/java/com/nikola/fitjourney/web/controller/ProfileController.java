package com.nikola.fitjourney.web.controller;

import com.nikola.fitjourney.model.User;
import com.nikola.fitjourney.repository.jpa.WorkoutRepository;
import com.nikola.fitjourney.service.WorkoutService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final WorkoutRepository workoutService;

    public ProfileController(WorkoutRepository workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public String getProfilePage(HttpServletRequest request, Model model)
    {
        if(request.getSession().getAttribute("user") == null)
        {
            return "redirect:/home";
        }
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("workouts",user.getWorkoutsDone());

        return "profile";

    }
}
