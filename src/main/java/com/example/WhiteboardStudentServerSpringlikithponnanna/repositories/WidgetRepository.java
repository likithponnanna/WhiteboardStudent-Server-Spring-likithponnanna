package com.example.WhiteboardStudentServerSpringlikithponnanna.repositories;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.Widget;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WidgetRepository extends CrudRepository<Widget, Long> {
    @Modifying
    @Query(value = "DELETE FROM Widget widget WHERE widget.topic.id=:tid")
            public void deleteWidgetsByTopic_IdIsIn(@Param("tid") Long tid);

}
