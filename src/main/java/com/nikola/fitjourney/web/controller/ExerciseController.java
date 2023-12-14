package com.nikola.fitjourney.web.controller;

import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.enumerations.MuscleType;
import com.nikola.fitjourney.repository.jpa.ExerciseRepository;
import com.nikola.fitjourney.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExerciseController {

    private final ExerciseService exerciseService;


    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping(path = "/add-exercise-DB")
    public String addExerciseToDB(Model model)
    {
        model.addAttribute("muscleTypes",MuscleType.values());


        return "addExerciseForm";
    }

    @PostMapping(path = "/add-exercise-DB")
    public String addExercise(@RequestParam String name, @RequestParam MuscleType muscleType)
    {

        Exercise exercise=this.exerciseService.save(name,muscleType);

        return "redirect:/add-exercise-DB";
    }
}
