package com.example.WhiteboardStudentServerSpringlikithponnanna.services;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.*;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

@RestController
@Transactional
@CrossOrigin(origins = "*", allowCredentials= "true", allowedHeaders ="*")
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    TopicRepository topicRepository;


    @Autowired
    ListWidgetRepository listWidgetRepository;
    @Autowired
    LinkWidgetRepository linkWidgetRepository;
    @Autowired
    ParagraphWidgetRepository paragraphWidgetRepository;
    @Autowired
    HeadingWidgetRepository headingWidgetRepository;
    @Autowired
    ImageWidgetRepository imageWidgetRepository;




    @GetMapping("/api/topic/{tid}/widget")
    public List<Widget> findAllWidgets(@PathVariable("tid") Long id, HttpSession session) {
       User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            Topic dbTopic = topicRepository.findById(id).get();
            List<Widget> tempPreOrder = dbTopic.getWidgets();

            if(tempPreOrder.size()>0){
                if(tempPreOrder.get(0).getOrderId()!=null){
                    Collections.sort(tempPreOrder, Comparator.comparingLong(Widget::getOrderId));
                }
            }



            return tempPreOrder;
        }
        return new ArrayList <>(Collections.singletonList(new Widget())) ;
    }

    @GetMapping("/api/widget/{wid}")
    public Widget findWidgetById(@PathVariable("wid") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            return widgetRepository.findById(id).get();
        }
        return new Widget();
    }


    @PostMapping("/api/topic/{tid}/widget")
    public List<Widget> createWidget(@PathVariable("tid") Long id,
                                   @RequestBody String widget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {


            Gson gson = new Gson();

            Widget widgetFromJson = gson.fromJson(widget, Widget.class);
            Topic dbTopic = topicRepository.findById(id).get();

            switch (widgetFromJson.getType()){
                case "HEADING":
                    HeadingWidget headingWidget = gson.fromJson(widget, HeadingWidget.class);
                    widgetRepository.save(headingWidget);
                    break;

                case "PARAGRAPH":
                    ParagraphWidget paragraphWidget = gson.fromJson(widget, ParagraphWidget.class);
                    paragraphWidget.setId(new Date().getTime());

                    paragraphWidget.setTopic(dbTopic);
                    widgetRepository.save(paragraphWidget);
                    break;

                case "IMAGE":
                    ImageWidget imageWidget = gson.fromJson(widget, ImageWidget.class);
                    imageWidget.setId(new Date().getTime());
                    imageWidget.setTopic(dbTopic);
                    widgetRepository.save(imageWidget);
                    break;

                case "LINK":
                    LinkWidget linkWidget = gson.fromJson(widget, LinkWidget.class);
                    linkWidget.setId(new Date().getTime());
                    linkWidget.setTopic(dbTopic);
                    widgetRepository.save(linkWidget);
                    break;

                case "LIST":
                    ListWidget listWidget = gson.fromJson(widget, ListWidget.class);
                    listWidget.setId(new Date().getTime());
                    listWidget.setTopic(dbTopic);
                    widgetRepository.save(listWidget);
                    break;

            }

            Topic dbTopicFin = topicRepository.findById(id).get();
            return dbTopicFin.getWidgets();


        }


        return new ArrayList <>(Arrays.asList(new Widget()));

    }


    @DeleteMapping("/api/widget/{wid}")
    public List<Widget> deleteWidget(@PathVariable("wid") Long id, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
            Widget dbWidget =  widgetRepository.findById(id).get();

            widgetRepository.deleteById(id);
            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();
        }

        return new ArrayList <>(Collections.singletonList(new Widget())) ;

    }


    @DeleteMapping("/api/topic/{tid}/widget/")
    public List<Widget> deleteWidgetsForTopic(@PathVariable("tid") Long id, HttpSession session){
        User loggedUser = (User) session.getAttribute("currentUser");
        if(loggedUser!=null) {
         widgetRepository.deleteWidgetsByTopic_IdIsIn(id);
        }

        return topicRepository.findById(id).get().getWidgets();

    }



    @PutMapping("/api/widget/{wid}")
    public List<Widget> updateWidget(@PathVariable("wid") Long id, @RequestBody String widget, HttpSession session) {
        User loggedUser = (User) session.getAttribute("currentUser");

        if (loggedUser != null) {

            Gson gson = new Gson();

            Widget widgetFromJson = gson.fromJson(widget, Widget.class);

            Widget dbWidget = widgetRepository.findById(id).get();

            widgetRepository.deleteById(id);


            switch (widgetFromJson.getType()){
                case "HEADING":
                    HeadingWidget headingWidget = gson.fromJson(widget, HeadingWidget.class);
                    headingWidget.setTopic(dbWidget.getTopic());
                    headingWidget.setId(id);
                    widgetRepository.save(headingWidget);
                    break;

                case "PARAGRAPH":
                    ParagraphWidget paragraphWidget = gson.fromJson(widget, ParagraphWidget.class);
                    paragraphWidget.setTopic(dbWidget.getTopic());
                    paragraphWidget.setId(id);
                    widgetRepository.save(paragraphWidget);
                    break;

                case "IMAGE":
                    ImageWidget imageWidget = gson.fromJson(widget, ImageWidget.class);
                    imageWidget.setTopic(dbWidget.getTopic());
                    imageWidget.setId(id);
                    widgetRepository.save(imageWidget);
                    break;

                case "LINK":
                    LinkWidget linkWidget = gson.fromJson(widget, LinkWidget.class);
                    linkWidget.setTopic(dbWidget.getTopic());
                    linkWidget.setId(id);
                    widgetRepository.save(linkWidget);
                    break;

                case "LIST":
                    ListWidget listWidget = gson.fromJson(widget, ListWidget.class);
                    listWidget.setTopic(dbWidget.getTopic());
                    listWidget.setId(id);
                    widgetRepository.save(listWidget);
                    break;
            }


            Topic dbTopic = topicRepository.findById(dbWidget.getTopic().getId()).get();
            return dbTopic.getWidgets();


        }
        return new ArrayList <>(Arrays.asList(new Widget()));
    }



}
