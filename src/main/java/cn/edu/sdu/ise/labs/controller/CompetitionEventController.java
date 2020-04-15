package cn.edu.sdu.ise.labs.controller;

import cn.edu.sdu.ise.labs.dto.CompetitionEventListDTO;
import cn.edu.sdu.ise.labs.dto.ModifyCompetitionEventDTO;
import cn.edu.sdu.ise.labs.dto.NewCompetitionEventDTO;
import cn.edu.sdu.ise.labs.model.ResultContext;
import cn.edu.sdu.ise.labs.service.CompetitionEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ author:周健平
 * @ date:2020/3/29 16:21
 * @ modifiedBy:
 */
@RestController
@RequestMapping("competitionEvent")
public class CompetitionEventController {

    @Autowired
    private CompetitionEventService competitionEventService;

    @GetMapping("getDetails")
    public ResultContext getDetails( @RequestBody String eventCode ) {
        return ResultContext.returnSuccess( competitionEventService.getDetails( eventCode ) );
    }

    @PostMapping("listByPage")
    public ResultContext listByPage( @RequestBody CompetitionEventListDTO competitionEventListDTO ) {
        return ResultContext.returnSuccess( competitionEventService.listByPage( competitionEventListDTO ) );
    }

    @PostMapping("addCompetitionEvent")
    public ResultContext addCompetitionEvent( @RequestBody NewCompetitionEventDTO newCompetitionEventDTO ) {
        return ResultContext.returnSuccess( competitionEventService.addCompetitionEvent( newCompetitionEventDTO ) );
    }

    @PostMapping("updateCompetitionEvent")
    public ResultContext updateCompetitionEvent( @RequestBody ModifyCompetitionEventDTO modifyCompetitionEventDTO ) {
        return ResultContext.returnSuccess( competitionEventService.updateCompetitionEvent( modifyCompetitionEventDTO ) );
    }

    @PostMapping("deleteCompetitionEvent")
    public ResultContext deleteCompetitionEvent( @RequestBody List<String> competitionEventCodeList ) {
        return ResultContext.returnSuccess( competitionEventService.deleteCompetitionEvent( competitionEventCodeList ) );
    }

    @PostMapping("listByName")
    public ResultContext listByName( @RequestBody String competitionEventName ) {
        return ResultContext.returnSuccess( competitionEventService.listByName( competitionEventName ) );
    }
}
