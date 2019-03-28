package com.example.WhiteboardStudentServerSpringlikithponnanna.services;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.*;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.HeadingWidgetRepository;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.TopicRepository;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials= "true", allowedHeaders ="*")
public class HeadingWidgetService {
    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    HeadingWidgetRepository headingWidgetRepository;

    @GetMapping("/api/topic/{tid}/heading/widget")
    public List<Widget> findAllHeadingWidgets(@PathVariable("tid") Long id, HttpSession session) {


        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            List<Widget> finalList = new ArrayList<>();
            List<Widget> widgetList = topicRepository.findById(id).get().getWidgets();
            for (Widget w : widgetList) {
                if (w.getType().equals("HEADING")) {
                    finalList.add(w);
                }

            }
            return finalList;
        }

        return new ArrayList <>(Arrays.asList(new Widget()));
    }

    @GetMapping("/api/heading/widget/{wid}")
    public Widget findHeadingWidgetById(@PathVariable("wid") Long id, HttpSession session) {

        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            return widgetRepository.findById(id).get();
        }
        return new Widget();
    }



    @PostMapping("/api/topic/{tid}/heading/widget")
    public List<Widget> createHeadingWidget(@PathVariable("tid") Long id,
                                         @RequestBody HeadingWidget headingWidget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if (loggedUser != null) {
            headingWidget.setId(new Date().getTime());
            Topic dbTopic = topicRepository.findById(id).get();

            headingWidget.setTopic(dbTopic);
            widgetRepository.save(headingWidget);

            return dbTopic.getWidgets();
        }


        return new ArrayList<>(Arrays.asList(new Widget()));

    }


    @DeleteMapping("/api/heading/widget/{wid}")
    public List<Widget> deleteHeadingWidget(@PathVariable("wid") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            Widget dbWidget = widgetRepository.findById(id).get();

            widgetRepository.deleteById(id);
            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();
        }
        return new ArrayList <>(Arrays.asList(new Widget()));

    }

    @PutMapping("/api/heading/widget/{wid}")
    public List<Widget> updateHeadingWidget(@PathVariable("wid") Long id, @RequestBody HeadingWidget headingWidget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");

        if (loggedUser != null) {
            Widget dbWidget = widgetRepository.findById(id).get();
            headingWidget.setTopic(dbWidget.getTopic());
            widgetRepository.deleteById(dbWidget.getId());
            widgetRepository.save(headingWidget);

            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();


        }
        return new ArrayList<>(Arrays.asList(new Widget()));


    }
}
