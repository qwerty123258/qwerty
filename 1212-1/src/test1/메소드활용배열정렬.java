package test1;

import java.util.Arrays;
import java.util.Collections;

public class 메소드활용배열정렬 {

	public static void main(String[] args) {
        int ar[] = {3,45,6,1,2};
        Integer ar2[] = {3,45,6,1,2};
        Arrays.parallelSort(ar);
        Arrays.sort(ar); // 이렇게도 가능.  
        Arrays.sort(ar2,Collections.reverseOrder()); //내림차순 사용시
        
        System.out.println("-----------오름차순--------");
        System.out.println(ar[0]);
        System.out.println(ar[1]);
        System.out.println(ar[2]);
        System.out.println(ar[3]);
        System.out.println(ar[4]);
        System.out.println("-----------오름차순--------");
        System.out.println("-----------내림차순--------");
        System.out.println(ar2[0]);
        System.out.println(ar2[1]);
        System.out.println(ar2[2]);
        System.out.println(ar2[3]);
        System.out.println(ar2[4]);
        System.out.println("-----------내림차순--------");
	}
	

}
