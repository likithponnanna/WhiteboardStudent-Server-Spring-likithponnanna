package com.example.WhiteboardStudentServerSpringlikithponnanna.services;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.*;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.ImageWidgetRepository;
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
public class ImageWidgetService {
    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    ImageWidgetRepository imageWidgetRepository;


    @GetMapping("/api/topic/{tid}/image/widget")
    public List<Widget> findAllImageWidgets(@PathVariable("tid") Long id, HttpSession session) {

        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            List<Widget> finalList = new ArrayList<>();
            List<Widget> widgetList = topicRepository.findById(id).get().getWidgets();
            for (Widget w : widgetList) {
                if (w.getType().equals("IMAGE")) {
                    finalList.add(w);
                }

            }
            return finalList;
        }

        return new ArrayList <>(Arrays.asList(new Widget()));
    }

    @GetMapping("/api/image/widget/{wid}")
    public Widget findImageWidgetById(@PathVariable("wid") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            return widgetRepository.findById(id).get();
        }
        return new Widget();
    }


    @PostMapping("/api/topic/{tid}/image/widget")
    public List<Widget> createImageWidget(@PathVariable("tid") Long id,
                                         @RequestBody ImageWidget imageWidget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            imageWidget.setId(new Date().getTime());
            Topic dbTopic = topicRepository.findById(id).get();

            imageWidget.setTopic(dbTopic);
            widgetRepository.save(imageWidget);

            return dbTopic.getWidgets();
        }


        return new ArrayList <>(Arrays.asList(new Widget()));

    }


    @DeleteMapping("/api/image/widget/{wid}")
    public List<Widget> deleteImageWidget(@PathVariable("wid") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            Widget dbWidget = widgetRepository.findById(id).get();

            widgetRepository.deleteById(id);
            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();
        }
        return new ArrayList <>(Arrays.asList(new Widget()));

    }

    @PutMapping("/api/image/widget/{wid}")
    public List<Widget> updateImageWidget(@PathVariable("wid") Long id, @RequestBody ImageWidget imageWidget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");

        if (loggedUser != null) {
            Widget dbWidget = widgetRepository.findById(id).get();
            imageWidget.setTopic(dbWidget.getTopic());
            widgetRepository.deleteById(dbWidget.getId());
            widgetRepository.save(imageWidget);

            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();


        }
        return new ArrayList <>(Arrays.asList(new Widget()));
    }
}
