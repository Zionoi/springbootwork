package com.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.domain.Diary;
import com.study.springboot.service.EventService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/events")
public class DiaryController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Diary> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/add")
    public Diary addEvent(@RequestBody Diary event) {
        if (event.getMember() == null || event.getMember().getUserid() == null) {
            throw new IllegalArgumentException("Member information is missing or incomplete.");
        }
        return eventService.createEvent(event);
    }

    @GetMapping("/getDiary/{dNum}")
    public Diary getDiary(@PathVariable Long dNum) {
        System.out.println("dNum : " + dNum);
        return eventService.getDiaryBydNum(dNum);
    }


    @DeleteMapping("/delete/{dNum}")
    public void deleteEvent(@PathVariable Long dNum) {
        eventService.deleteEvent(dNum);
    }
}
