package com.example.WhiteboardStudentServerSpringlikithponnanna.services;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.ListWidget;
import com.example.WhiteboardStudentServerSpringlikithponnanna.model.Topic;
import com.example.WhiteboardStudentServerSpringlikithponnanna.model.User;
import com.example.WhiteboardStudentServerSpringlikithponnanna.model.Widget;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.ListWidgetRepository;
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
public class ListWidgetService {

    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    ListWidgetRepository listWidgetRepository;

    @GetMapping("/api/topic/{tid}/list/widget")
    public List<Widget> findAllListWidgets(@PathVariable("tid") Long id, HttpSession session) {

        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            List<Widget> finalList = new ArrayList<>();
            List<Widget> widgetList = topicRepository.findById(id).get().getWidgets();
            for (Widget w : widgetList) {
                if (w.getType().equals("LIST")) {
                    finalList.add(w);
                }

            }
            return finalList;
        }

        return new ArrayList <>(Arrays.asList(new Widget()));
    }

    @GetMapping("/api/list/widget/{wid}")
    public Widget findListWidgetById(@PathVariable("wid") Long id, HttpSession session) {

        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            return widgetRepository.findById(id).get();
        }
        return new Widget();
    }


    @PostMapping("/api/topic/{tid}/list/widget")
    public List<Widget> createListWidget(@PathVariable("tid") Long id,
                                     @RequestBody ListWidget listWidget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            listWidget.setId(new Date().getTime());
            Topic dbTopic = topicRepository.findById(id).get();

            listWidget.setTopic(dbTopic);
            widgetRepository.save(listWidget);

            return dbTopic.getWidgets();
        }


        return new ArrayList <>(Arrays.asList(new Widget()));

    }


    @DeleteMapping("/api/list/widget/{wid}")
    public List<Widget> deleteListWidget(@PathVariable("wid") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            Widget dbWidget = widgetRepository.findById(id).get();

            widgetRepository.deleteById(id);
            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();
        }
        return new ArrayList <>(Arrays.asList(new Widget()));

    }

    @PutMapping("/api/list/widget/{wid}")
    public List<Widget> updateListWidget(@PathVariable("wid") Long id, @RequestBody ListWidget listWidget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");

        if (loggedUser != null) {
            Widget dbWidget = widgetRepository.findById(id).get();
            listWidget.setTopic(dbWidget.getTopic());
            widgetRepository.deleteById(dbWidget.getId());
            widgetRepository.save(listWidget);

            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();


        }
        return new ArrayList <>(Arrays.asList(new Widget()));


    }
}
