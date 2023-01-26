package pkg2;
import pkg1.IntStack;
import pkg1.StackFullException;


public class StackTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		IntStack1 s1=new IntStack1(1);
		IntStack s=new IntStack(1);
		System.out.println(s1.getStorage().length);
		System.out.println(s.getStorage().length);
		//s1.push(1);
		
		try {
			s1.push(1);
		}catch(StackFullException e) {
			
		}
	}
}
