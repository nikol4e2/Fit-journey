package com.nikola.fitjourney.web.controller;

import com.nikola.fitjourney.model.DoneExercise;
import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.User;
import com.nikola.fitjourney.model.Workout;
import com.nikola.fitjourney.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExistWorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final DoneExerciseService doneExerciseService;
    private final SetService setService;
    private final AuthService authService;




    public ExistWorkoutController(AuthService authService,
                                  WorkoutService workoutService, ExerciseService exerciseService, DoneExerciseService doneExerciseService, SetService setService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.doneExerciseService = doneExerciseService;
        this.setService = setService;
        this.authService=authService;
    }

    @GetMapping(path = "/existingworkout/{id}/add-exercise")
    public String trackWorkout(HttpServletRequest request, @PathVariable Long id, Model model)
    {
        model.addAttribute("exercises",exerciseService.findAll());
        if(this.workoutService.findById(id).isPresent()) {
            Workout workout = workoutService.findById(id).get();
            model.addAttribute("isPreviousWorkout",true);
            model.addAttribute("workout",workout);
            model.addAttribute("previoslyDoneExercises",request.getSession().getAttribute("previoslyDoneExercises"));
            model.addAttribute("doneExercises",workout.getExercises());
            return "addExerciseToExsitingWorkout";
        }
        return "redirect:/home";
    }



    @PostMapping(path = "/add-existing-workout")
    public String trackWorkout(HttpServletRequest request, Model model, @RequestParam long workoutId)
    {
        User user=(User)request.getSession().getAttribute("user");
        Workout oldWorkout = this.workoutService.findById(workoutId).get();
        Workout newWorkout=this.workoutService.save(oldWorkout.getName(),user);
        List<DoneExercise> newDoneExercises =new ArrayList<>();
        for(int i=0;i<oldWorkout.getExercises().size();i++)
        {
          newDoneExercises.add(new DoneExercise(oldWorkout.getExercises().get(i).getExercise()));
        }
        newWorkout.getExercises().addAll(newDoneExercises);
        request.getSession().setAttribute("previoslyDoneExercises",oldWorkout.getExercises());
        return "redirect:/existingworkout/"+newWorkout.getWorkoutId()+"/add-exercise";
    }

    @PostMapping(path = "/existingworkout/add-exercise")
    public String addDoneExercise(@RequestParam Long exerciseId,@RequestParam Long workoutId) {
        if (this.workoutService.findById(workoutId).isPresent()) {
            Workout workout = workoutService.findById(workoutId).get();
            Exercise exercise = exerciseService.findById(exerciseId).get();
            DoneExercise doneExercise = new DoneExercise(exercise);
            doneExerciseService.save(doneExercise);
            workoutService.addDoneExercise(workoutId, doneExercise);

        }
        return "redirect:/existingworkout/" + workoutId + "/add-exercise";
    }

}
