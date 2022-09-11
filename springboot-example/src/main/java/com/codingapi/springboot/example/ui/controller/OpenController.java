package com.codingapi.springboot.example.ui.controller;

import com.codingapi.springboot.example.application.executor.DemoExecutor;
import com.codingapi.springboot.example.infrastructure.entity.DemoEntity;
import com.codingapi.springboot.example.infrastructure.jap.repository.DemoEntityRepository;
import com.codingapi.springboot.framework.dto.request.PageRequest;
import com.codingapi.springboot.framework.dto.response.MultiResponse;
import com.codingapi.springboot.framework.dto.response.Response;
import com.codingapi.springboot.generator.dao.IdGeneratorDao;
import com.codingapi.springboot.generator.domain.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/open/demo")
@AllArgsConstructor
public class OpenController {

    private final DemoExecutor executor;

    private final DemoEntityRepository demoEntityRepository;

    private final IdGeneratorDao idGeneratorDao;

    @GetMapping("/save")
    public Response save(@RequestParam("name") String name) {
        executor.create(name);
        return Response.buildSuccess();
    }

    @GetMapping("/findAll")
    public MultiResponse<DemoEntity> findAll(PageRequest pageRequest) {
        return MultiResponse.of(demoEntityRepository.findAll(pageRequest));
    }



    @GetMapping("/test-list")
    public List<IdGenerator> test1() throws SQLException{
        return idGeneratorDao.findAll();
    }

    @GetMapping("/test-save")
    public Response test2() throws SQLException {
        IdGenerator generator = new IdGenerator();
        generator.setKey("xxxx");
        generator.setId(1L);
        generator.setUpdateTime(new Date());
        idGeneratorDao.save(generator);
        return Response.buildSuccess();
    }

}
