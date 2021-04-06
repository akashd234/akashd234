import java.util.LinkedList;

public class ProducerConsumer2{
	public static void main(String[] args){
		PC pc=new PC();//PC clas object
		Thread t1=new Thread();//Thread class object for produce
		
		public void run(){
			try { 
					pc.produce();//call the method produce
				} 
				catch (InterruptedException e) { 
					e.printStackTrace(); 
				}
		}
		
		Thread t2=new Thread();//Thread class object for consume
		@Override
		public void run(){
			
				try { 
					pc.consume();//call the method consume
				} 
				catch (InterruptedException e) { 
					e.printStackTrace(); 
				}
		}
		
		t1.start();
		t2.start();
		//t1 must finishes before t2
		t1.join();
		t2.join();
	}
	public class PC{
		LinkedList<Integer> list=new LinkedList<>();
		int capacity=4;//fix the capacity as 4
		
		public void produce(){
			int value=0;
			while(true){
				synchronized(this){
					while(list.size()==capacity){
						wait();//wait for the consumer to consume
					}
					System.out.print("Produced : " + value);
					list.add(value++);
					notify();//notify that consumer can consume
					Thread.sleep(1000);
				}
			}
		}
		public void consume(){
			while(true){
				synchronized(this){
					while(list.size()==0){
						wait();//wait for the producer to produce
					}
					int val=list.removeFirst();
					System.out.print("consumed : " + val);
					notify();//notify that producer can produce
					Thread.sleep(1000);
				}
			}
		}
	}
}