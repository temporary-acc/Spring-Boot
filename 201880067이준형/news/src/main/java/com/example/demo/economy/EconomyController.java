package com.example.demo.economy;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.user.WebUser;
import com.example.demo.user.WebUserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/economy")
@Controller
@RequiredArgsConstructor
public class EconomyController {
    //Clieent -> Controller ->  Service -> Repository -> DB

    private final EconomyService eService;
    @RequestMapping("/list")
    public String economy(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Economy> paging = this.eService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "economy";
    }

    //기본키 가져와서 맵핑시켜서 화면에 쫙 뿌려줌
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, EconomyForm economyForm) {
        Economy e = this.eService.getEconomyById(id);
        model.addAttribute("economy", e);
        return "economy_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(EconomyForm economyForm) {
        //기사 등록
        return "economy_form";
    }

    private final WebUserService webUserService;

    //post전송
    @PreAuthorize("isAuthenticated()")//로그인된 사용자만
    @PostMapping("/create")
    //principal=> 인증된 사용자 정보 가져오기 위해 파라미터로 전달받음
    public String create(@Valid EconomyForm economyForm, BindingResult bindingResult, Principal principal
    ) {
        //BindingResult검증오류
        if (bindingResult.hasErrors()) {
            return "economy_form";
        }
        //작성자 이름과 작성 제목,내용, 가져와서 저장
        WebUser user = webUserService.getUser(principal.getName());
        this.eService.create(economyForm.getSubject(), economyForm.getContent(), user);
        //작성했으면 목록으로
        return "redirect:/economy/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}") //수정/id(키)
    public String economyModify(EconomyForm economyForm, @PathVariable("id") Integer id, Principal principal) {
        Economy e = this.eService.getEconomyById(id);

        if (!principal.getName().equals(e.getAuthor().getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한 없음");
        }
        economyForm.setSubject(e.getSubject());
        economyForm.setContent(e.getContent());
        return "economy_modify";
    }

    //수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String economyModify(@Valid EconomyForm economyForm, BindingResult bindingResult, Principal principal,
                                @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "economy_modify";
        }
        Economy e = this.eService.getEconomyById(id);
        if (!principal.getName().equals(e.getAuthor().getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한 없음");
        }
        this.eService.modify(e, economyForm.getSubject(), economyForm.getContent());
        return "redirect:/economy/detail/" + id;
    }


    //	 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String economyDelete(Principal principal, @PathVariable("id") Integer id) {
        Economy e = this.eService.getEconomyById(id);
        if (!e.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.eService.delete(e);
        return "redirect:/";
    }
}
