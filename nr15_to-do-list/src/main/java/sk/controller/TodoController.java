package sk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sk.model.Todo;
import sk.repository.TodoRepository;

@Controller
public class TodoController {
    
    @Autowired
    private TodoRepository todoRepository;

    //def.: return index page and add to model:
    //"todo" = new Todo entity (only to use in html form);
    //"todos" = list of all entities
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todo", new Todo());
        model.addAttribute("todos", todoRepository.findAll());
        return "index";
    }

    //def.: if no errors, save "todo" from HTML form is the db
    //@ModelAttribute("todo") is *optional*; it *bonds* "todo" data from HTML form with "todo" object
    //"Todo todo" does NOT use "todo" object created in GET, but uses *new* object
    //"BindingResult" extends "Errors"
    @PostMapping("/add")
    public String addTodo(@ModelAttribute("todo") @Valid Todo todo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("todos", todoRepository.findAll());
            return "index";
        }
        else {
            todoRepository.save(todo);
            return "redirect:/";
        }
    }

    //def.: finds todo by id, sets as "complete", saves back in the db
    //@Pathvariable marks path variable, e.g. "/complete/42"
    //".orElseThrow()" throws exception when no results
    //TODO: how to handle exception here?
    @PostMapping("/complete/{id}")
    public String completeTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);
        return "redirect:/";
    }

    //def.: deletes todo in the db by id
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }
}
