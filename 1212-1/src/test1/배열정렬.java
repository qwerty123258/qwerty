package test1;

public class 배열정렬 {
	public static void main(String[] args) {
		//저장된 데이터의 정렬순서 변경하기.
			        int save=0;
			        int num=0;
			        int i;
			        int ar[] = {3,45,6,1,2};
			        int ar2[] = {3,45,6,1,2};
			        
		        for (i = 0; i < ar.length; i++) { //오름차순 정렬
         			for(int j=i+1; j<ar.length; j++) {
          				if(ar[i]>ar[j]) { 
         					save=ar[j]; 
         					ar[j]=ar[i]; 
          					ar[i]=save; 
            					
           				}
           						
   			        	
           				}
			        }
			        for (i = 0; i < ar2.length; i++) {  //내림 차순 정렬
            			for(int j=i+1; j<ar2.length; j++) {
            				if(ar2[i]<ar2[j]) { 
            					save=ar2[j]; 
            					ar2[j]=ar2[i]; 
            					ar2[i]=save; 
            					
            				}
            						
    			        	
            				}
			        }
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

			      
			
