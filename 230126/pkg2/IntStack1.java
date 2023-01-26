package pkg2;
import pkg1.IntStack;

public class IntStack1 extends IntStack {
	public IntStack1(int size) {
		super(size);
	}
	public int total() {
		int i;
		int sum=0;
		int num[]=getStorage();
		for(i=0;i<getIndex();i++) {
			sum+=num[i];
		}
		return sum;
	}

}
