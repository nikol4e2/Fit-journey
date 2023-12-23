package com.nikola.fitjourney.web.controller;

import com.nikola.fitjourney.model.*;
import com.nikola.fitjourney.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


        User user=(User) request.getSession().getAttribute("user");


        Workout workout=this.workoutService.save(name,user);
        user.getWorkoutsDone().add(workout);

        authService.save(user);



        return "redirect:/workout/"+workout.getWorkoutId()+"/add-exercise";
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
            DoneExercise doneExercise=new DoneExercise(exercise);
            doneExerciseService.save(doneExercise);
            workoutService.addDoneExercise(workoutId,doneExercise);

        }
        return "redirect:/workout/"+workoutId+"/add-exercise";

    }








    @PostMapping(path = "/add-set")
    public String addSet(@RequestParam Long workoutId,@RequestParam Long doneExerciseId,@RequestParam int reps,@RequestParam double weight)
    {
        if(this.doneExerciseService.findById(doneExerciseId).isPresent())
        {
            DoneExercise doneExercise=this.doneExerciseService.findById(doneExerciseId).get();
            doneExercise.getSets().add(new ExerciseSet(reps,weight));
            this.doneExerciseService.update(doneExercise);

        }
        return "redirect:/workout/"+workoutId+"/add-exercise";
    }

    @PostMapping(path = "/workout/complete-workout")
    public String completeWorkout(@RequestParam Long workoutId,HttpServletRequest request,Model model)
    {
        if(this.workoutService.findById(workoutId).isPresent()) {
            model.addAttribute("totalVolume",workoutService.calculateTotalVolume(workoutId)); //Might need to delete this code
            request.getSession().setAttribute("totalVolume",workoutService.calculateTotalVolume(workoutId));
            request.getSession().setAttribute("workout",workoutService.findById(workoutId).get());
        }
        return "redirect:/workout/"+workoutId+"/finalize";
    }

    @GetMapping(path = "/workout/{id}/finalize")
    public String finalizeWorkout(@PathVariable Long id,HttpServletRequest request,Model model)
    {
        model.addAttribute("workout",(Workout)request.getSession().getAttribute("workout"));
        model.addAttribute("totalVolume",request.getSession().getAttribute("totalVolume"));
        return "finalizeWorkout";
    }

    @PostMapping(path = "/workout/finalize")
    public String addComment(@RequestParam Long workoutId,@RequestParam String comment)
    {
        if(this.workoutService.findById(workoutId).isPresent()) {
            Workout workout = workoutService.findById(workoutId).get();
            workout.setFeeling(comment);
            workoutService.update(workout);

        }
        return "redirect:/profile";
    }


    @PostMapping(path = "/workout/delete/{id}")
    public String deleteWorkout(@PathVariable Long id,HttpServletRequest request)
    {
        if(this.workoutService.findById(id).isPresent()) {
            User user=(User) request.getSession().getAttribute("user");
            user.getWorkoutsDone().removeIf(r->r.getWorkoutId().equals(id));
            this.workoutService.deleteById(id);

            this.authService.save(user);

        }
        return "redirect:/profile";
    }

}
