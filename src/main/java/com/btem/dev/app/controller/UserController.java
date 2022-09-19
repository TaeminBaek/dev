package com.btem.dev.app.controller;

import com.btem.dev.app.dto.UserInsertDto;
import com.btem.dev.app.dto.UserUpdateDto;
import com.btem.dev.common.model.JsonResponse;
import com.btem.dev.app.service.UserService;
import com.btem.dev.common.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 회원정보 조회?
    @GetMapping(value = "/{id}")
    public Map getUser(@PathVariable String id) throws Exception {
        return JsonResponse.asSuccess("responseData", userService.getUser(id));
    }

    // 회원가입
    @PostMapping
    public Map insertUser(@RequestBody @Valid UserInsertDto user, Errors errors, Model model) throws Exception {
        if(errors.hasErrors()) return JsonResponse.asFailure("requestData", user, "validResult", Util.getValidResult(errors));
        userService.insertUser(user);
        return JsonResponse.asSuccess("requestData", user);
    }

    // 회원정보 수정
    @PutMapping
    public Map updateUser(@RequestBody @Valid UserUpdateDto user, Errors errors, Model model) throws Exception {
        if(errors.hasErrors()) return JsonResponse.asFailure("requestData", user, "validResult", Util.getValidResult(errors));
        userService.updateUser(user);
        return JsonResponse.asSuccess("requestData", user);
    }

    // 회원 탈퇴?
    @DeleteMapping(value = "/{id}")
    public Map deleteUser(@PathVariable String id) throws Exception{
        userService.deleteUser(id);
        return JsonResponse.asSuccess("msg", "삭제 성공");
    }
}
