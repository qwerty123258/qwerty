package test1;

import java.util.ArrayList;
import java.util.List;

public class Classinlist {

	public static void main(String[] args) {
		List<Navermember> navermember = new ArrayList<Navermember>();
		Navermember member = new Navermember("id","비밀번호","이름","생년","성별","이메일","연락처");
		Navermember member1 = new Navermember("id1","비밀번호1","이름1","생년1","성별1","이메일1","연락처1");
		Navermember member2 = new Navermember("id2","비밀번호2","이름2","생년2","성별2","이메일2","연락처2");
		navermember.add(member);
		navermember.add(member1);
		navermember.add(member2);
		System.out.println(navermember.get(0).getId());
		System.out.println(navermember.get(1).getId());
		System.out.println(navermember.get(2).getId());

	}

}
