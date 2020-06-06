package Chap8_Method.item55;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemberRepository {

    private final Map<Long, Member> memberList = new HashMap<>();
    private Long id = 0L;

    public Member save(Member member) {
        id++;
        member.setId(id);
        memberList.put(id, member);
        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<>(memberList.values());
    }

    public Optional<Member> findByName(String name) {
        return memberList.values().stream()
                .filter(member -> member.getName().equals(name))
                .findFirst();
    }
}
