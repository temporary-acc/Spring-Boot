package com.example.demo.economyComment;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.economy.Economy;
import com.example.demo.economy.EconomyService;
import com.example.demo.user.WebUser;
import com.example.demo.user.WebUserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/ecomment")
@RequiredArgsConstructor
@Controller
public class EcommentController {
	private final EconomyService eService;
	private final EcommentService eCommentService;
	private final WebUserService webUserService;

	//해당 기사의 id값 가져옴 U
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/create/{id}")
	public String create(Model model, @PathVariable("id") Integer id, @Valid EcommentForm economyForm,
			BindingResult bindingResult, Principal principal // 사용자 이름을 얻을수 있다
	) {
		Economy e = eService.getEconomyById(id);
		if (bindingResult.hasErrors()) {
			model.addAttribute("economy", e);
			return "economy_detail";
		}
		WebUser user = webUserService.getUser(principal.getName());
		eCommentService.create(e, economyForm.getContent(), user);
		return "redirect:/economy/detail/" + id;
	}

	//수정
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String economyModify(EcommentForm ecommentForm, @PathVariable("id") Integer id, Principal principal) {
		//기사를 작성한 작성자의 id, 이름과 비교해서 아닌경우 처리 principal클래스는 현재 사용자 정보가 들어있음
		Ecomment comment = this.eCommentService.getEcomment(id);
		if (!comment.getAuthor().getName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		//만약 일치한다면 내용 수정 form으로 이동
		ecommentForm.setContent(comment.getContent());
		return "ecomment_form";
	}


	//수정 처리
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String economyModify(@Valid EcommentForm ecommentForm, BindingResult bindingResult,
			@PathVariable("id") Integer id, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "ecomment_form";
		}
		Ecomment comment = this.eCommentService.getEcomment(id);
		if (!comment.getAuthor().getName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.eCommentService.modify(comment, ecommentForm.getContent());
		return String.format("redirect:/economy/detail/%s#ecomment_%s", comment.getEconomy().getId(), comment.getId());
	}

	//삭제
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String economyDelete(Principal principal, @PathVariable("id") Integer id) {
		Ecomment comment = this.eCommentService.getEcomment(id);
		if (!comment.getAuthor().getName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.eCommentService.delete(comment);
		return String.format("redirect:/economy/detail/%s#ecomment_%s", comment.getEconomy().getId(), comment.getId());
	}

}