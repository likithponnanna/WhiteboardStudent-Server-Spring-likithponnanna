package com.example.WhiteboardStudentServerSpringlikithponnanna.services;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.*;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.ParagraphWidgetRepository;
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
public class ParagraphWidgetService {
    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    ParagraphWidgetRepository paragraphWidgetRepository;


    @GetMapping("/api/topic/{tid}/paragraph/widget")
    public List<Widget> findAllParagraphWidgets(@PathVariable("tid") Long id, HttpSession session) {

        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            List<Widget> finalList = new ArrayList<>();
            List<Widget> widgetList = topicRepository.findById(id).get().getWidgets();
            for (Widget w : widgetList) {
                if (w.getType().equals("PARAGRAPH")) {
                    finalList.add(w);
                }

            }
            return finalList;
        }

        return new ArrayList <>(Arrays.asList(new Widget()));
    }

    @GetMapping("/api/paragraph/widget/{wid}")
    public Widget findParagraphWidgetById(@PathVariable("wid") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            return widgetRepository.findById(id).get();
        }
        return new Widget();
    }


    @PostMapping("/api/topic/{tid}/paragraph/widget")
    public List<Widget> createParagraphWidget(@PathVariable("tid") Long id,
                                         @RequestBody ParagraphWidget paragraphWidget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            paragraphWidget.setId(new Date().getTime());
            Topic dbTopic = topicRepository.findById(id).get();

            paragraphWidget.setTopic(dbTopic);
            widgetRepository.save(paragraphWidget);

            return dbTopic.getWidgets();
        }


        return new ArrayList <>(Arrays.asList(new Widget()));

    }


    @DeleteMapping("/api/paragraph/widget/{wid}")
    public List<Widget> deleteParagraphWidget(@PathVariable("wid") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            Widget dbWidget = widgetRepository.findById(id).get();

            widgetRepository.deleteById(id);
            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();
        }
        return new ArrayList <>(Arrays.asList(new Widget()));


    }

    @PutMapping("/api/paragraph/widget/{wid}")
    public List<Widget> updateParagraphWidget(@PathVariable("wid") Long id, @RequestBody ParagraphWidget paragraphWidget,
                                         HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");

        if (loggedUser != null) {
            Widget dbWidget = widgetRepository.findById(id).get();
            paragraphWidget.setTopic(dbWidget.getTopic());
            widgetRepository.deleteById(dbWidget.getId());
            widgetRepository.save(paragraphWidget);

            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();


        }
        return new ArrayList <>(Arrays.asList(new Widget()));


    }
}
