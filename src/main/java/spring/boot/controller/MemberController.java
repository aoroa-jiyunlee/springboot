package spring.boot.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.boot.dto.CreateMemberDto;
import spring.boot.dto.MemberDto;
import spring.boot.dto.UpdateMemberDto;
import spring.boot.model.Address;
import spring.boot.model.Member;
import spring.boot.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/member")
    public CreateMemberResponse createMember(@RequestBody @Valid CreateMemberDto request) {
        Member member = new Member();
        member.setName(request.getName());
        member.setAddress(new Address(request.getCity(), request.getStreet(), request.getZipcode()));

        Long memberId = memberService.join(member);
        return new CreateMemberResponse(memberId);
    }

    @PutMapping("/api/v1/member/{id}")
    public UpdateMemberResponse updateMember(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberDto request) {
        memberService.update(id, request.getName());
        Member oneMember = memberService.findOneMember(id);
        return new UpdateMemberResponse(oneMember.getId(), oneMember.getName());
    }

    @GetMapping("/api/v1/member")
    public FindMemberResponse findMembers() {
        List<Member> members = memberService.findMembers();
        List<MemberDto> memberDtos = members.stream()
                .map(m -> new MemberDto(
                        m.getId(), m.getName(), m.getAddress(), m.getOrders()
                )).collect(Collectors.toList());

        return new FindMemberResponse(memberDtos);
    }

    @GetMapping("/api/v1/member/{id}")
    public GetMemberResponse findOneMember(@PathVariable("id") Long id) {
        Member m = memberService.findOneMember(id);
        MemberDto memberDto = new MemberDto(m.getId(), m.getName(), m.getAddress(), m.getOrders());
        return new GetMemberResponse(memberDto);
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class FindMemberResponse {
        private List<MemberDto> members;
    }

    @Data
    @AllArgsConstructor
    static class GetMemberResponse {
        private MemberDto member;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
