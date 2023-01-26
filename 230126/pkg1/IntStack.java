package pkg1;

public class IntStack {
	private int storage[];
	private int index;
	public IntStack(int size) {
		storage = new int[size];
		index=0;
	}
	public void push(int x) throws StackFullException{
		try {
			storage[index++]=x;
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new StackFullException("Stack Full");
			//エラーはthrowで返す
		}
	}
	public int pop() throws StackEmptyException{
		try {
			return storage[--index];
		}catch(ArrayIndexOutOfBoundsException e){
			throw new StackEmptyException("Stack Empty");
		}
	}
	public boolean empty() {
		return (index==0);
	}
	public int[] getStorage() {
		return storage;
	}
	public int getIndex() {
		return index;
	}
	public void setStorage(int[] storage) {
		this.storage = storage;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
