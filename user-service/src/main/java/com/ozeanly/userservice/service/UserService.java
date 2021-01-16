package com.ozeanly.userservice.service;

import com.ozeanly.userservice.VO.Department;
import com.ozeanly.userservice.VO.ResponseTemplateVO;
import com.ozeanly.userservice.entity.User;
import com.ozeanly.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("UserService:saveUser");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("UserService:getUserWithDepartment");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        log.info("departmentId={}", user.getDepartmentId());
        Department department =
                        restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                                        Department.class);

        log.info("department={}", department);
        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }
}
