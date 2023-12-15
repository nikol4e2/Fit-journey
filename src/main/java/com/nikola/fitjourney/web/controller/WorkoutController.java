package com.nikola.fitjourney.web.controller;

import com.nikola.fitjourney.model.*;
import com.nikola.fitjourney.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller

public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final DoneExerciseService doneExerciseService;
    private final SetService setService;
    private final AuthService authService;




    public WorkoutController(AuthService authService,
            WorkoutService workoutService, ExerciseService exerciseService, DoneExerciseService doneExerciseService, SetService setService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.doneExerciseService = doneExerciseService;
        this.setService = setService;
        this.authService=authService;
    }

    @GetMapping(path = "/add-workout")
    public String getWorkoutPage(HttpServletRequest request,Model model)
    {
        User user=(User) request.getSession().getAttribute("user");
        model.addAttribute("workouts",user.getWorkoutsDone());
        return "addWorkout";
    }

    @PostMapping(path = "/add-workout")
    public String addWorkout(HttpServletRequest request,@RequestParam String name)
    {
        Workout workout=this.workoutService.save(name);
        User user=(User) request.getSession().getAttribute("user");
        user.getWorkoutsDone().add(new Workout(name, LocalDate.now()));
        authService.save(user);



        return "redirect:/workout/"+workout.getId()+"/add-exercise";
    }

    @GetMapping(path = "/workout/{id}/add-exercise")
    public String getAddExercisePage(HttpServletRequest request, Model model, @PathVariable Long id)
    {
        model.addAttribute("exercises",exerciseService.findAll());
        if(this.workoutService.findById(id).isPresent()) {
            Workout workout = workoutService.findById(id).get();
            model.addAttribute("workout",workout);

            model.addAttribute("doneExercises",workout.getExercises());
            return "addExerciseToWorkout";

        }
        return "redirect:/home";
    }

    @PostMapping(path = "/workout/add-exercise")
    public String addDoneExercise(@RequestParam Long exerciseId,@RequestParam Long workoutId)
    {
        if(this.workoutService.findById(workoutId).isPresent()) {
            Workout workout = workoutService.findById(workoutId).get();
            Exercise exercise=exerciseService.findById(exerciseId).get();
            workoutService.addDoneExercise(workoutId,new DoneExercise(exercise));

        }
        return "redirect:/workout/"+workoutId+"/add-exercise";

    }




    @PostMapping(path = "/add-existing-workout")
    public String trackWorkout(HttpServletRequest request,Model model,@RequestParam long workoutId)
    {
        Workout oldWorkout = this.workoutService.findById(workoutId).get();
        Workout newWorkout=this.workoutService.save(oldWorkout.getName());
        newWorkout.getExercises().addAll(oldWorkout.getExercises());
        model.addAttribute("workout",newWorkout);
        model.addAttribute("doneExercises",newWorkout.getExercises());
        model.addAttribute("allExercises",exerciseService.findAll());
        return "redirect:/workout/"+newWorkout.getId()+"/add-exercise";
    }
}
