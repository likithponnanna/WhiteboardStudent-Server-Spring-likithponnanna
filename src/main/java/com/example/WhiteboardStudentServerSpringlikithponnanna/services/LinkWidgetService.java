package com.example.WhiteboardStudentServerSpringlikithponnanna.services;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.*;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.LinkWidgetRepository;
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
public class LinkWidgetService {

    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    LinkWidgetRepository linkWidgetRepository;

    @GetMapping("/api/topic/{tid}/link/widget")
    public List<Widget> findAllLinkWidgets(@PathVariable("tid") Long id, HttpSession session) {

        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            List<Widget> finalList = new ArrayList<>();
            List<Widget> widgetList = topicRepository.findById(id).get().getWidgets();
            for (Widget w : widgetList) {
                if (w.getType().equals("LINK")) {
                    finalList.add(w);
                }

            }
            return finalList;
        }

        return new ArrayList <>(Arrays.asList(new Widget()));
    }

    @GetMapping("/api/link/widget/{wid}")
    public Widget findLinkWidgetById(@PathVariable("wid") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            return widgetRepository.findById(id).get();
        }
        return new Widget();
    }


    @PostMapping("/api/topic/{tid}/link/widget")
    public List<Widget> createLinkWidget(@PathVariable("tid") Long id,
                                         @RequestBody LinkWidget linkWidget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            linkWidget.setId(new Date().getTime());
            Topic dbTopic = topicRepository.findById(id).get();

            linkWidget.setTopic(dbTopic);
            widgetRepository.save(linkWidget);

            return dbTopic.getWidgets();
        }


        return new ArrayList<>(Arrays.asList(new Widget()));

    }


    @DeleteMapping("/api/link/widget/{wid}")
    public List<Widget> deleteLinkWidget(@PathVariable("wid") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            Widget dbWidget = widgetRepository.findById(id).get();

            widgetRepository.deleteById(id);
            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();
        }
        return new ArrayList <>(Arrays.asList(new Widget()));

    }

    @PutMapping("/api/link/widget/{wid}")
    public List<Widget> updateLinkWidget(@PathVariable("wid") Long id, @RequestBody LinkWidget linkWidget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");

        if (loggedUser != null) {
            Widget dbWidget = widgetRepository.findById(id).get();
            linkWidget.setTopic(dbWidget.getTopic());
            widgetRepository.deleteById(dbWidget.getId());
            widgetRepository.save(linkWidget);

            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();


        }
        return new ArrayList <>(Arrays.asList(new Widget()));


    }
}

