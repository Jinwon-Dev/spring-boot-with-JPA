package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) { // @Valid -> 유효성 검증 어노테이션을 이용해 검증해준다.
        // MemberForm을 이용해서 필요한 형식에 맞게 데이터를 가공해서 보내자!

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/"; // 첫 번째 페이지로 넘어감
    }

    @GetMapping("/members")
    public String list(Model model) {
        /*
            여기서도 엔티티를 직접 사용하는 것은 좋지 않다. 엔티티는 비즈니스 로직만 갖고 있고, 화면 종속적 기능은 갖고 있지 않는게 좋다.
            유효성 점검 같은 경우도, 엔티티에서는 다른 유효성 점검이 필요할 수도 있고, 여러 차이가 의외로 많이 난다.
            API를 만들 때는 꼭 Form 객체나 DTO를 사용하고, 절대 엔티티를 외부로 반환하지 말자!
         */
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
