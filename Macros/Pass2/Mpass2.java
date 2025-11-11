import java.util.*;
import java.io.*;

class MNT {
String name;
int index;

MNT(String s, int i) {
	name = s;
	index = i;
}
}
class ALA
{
	String formal;
	String actual;
	ALA(String f,String a){
		formal=f;
		actual=a;
	}
	
}

public class Mpass2 {
	static List<MNT> mnt;
	static List<String> mdt;
	static int mntc;
	static int mdtc;
	static int mdtp;
	static List<ALA> ala;
	static Scanner sc;
	static PrintWriter op;
	public static void main(String args[]) throws Exception {
		op=new PrintWriter(new FileOutputStream("pass2_op.txt"));
		Mpass2 p2=new Mpass2();
		p2.initializeTables();
		System.out.println("ALA:");
		p2.showAla(1);
		System.out.println("\nMNT:");
		p2.showMnt();
		System.out.println("\nMDT:");
		p2.showMdt();
		System.out.println("\n===== PASS 2 =====\n");
		p2.pass2();
	}
	void pass2() throws Exception {
		File f=new File("op.txt");
		sc=new Scanner(f);
		
		while(sc.hasNextLine())
		{ int flag=0;
			String line=sc.nextLine();
			
			for(MNT l : mnt){
				if(line.contains(l.name))
				{   //macro call found process macro call
					
					mdtp=l.index;
					System.out.println(line);
					process_call(mdtp,line); //call expansion
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				op.println(line);
				op.flush();
			}
				
		}
	}
	void process_call(int mdtp,String s) throws Exception
	{   
		String mname[]=s.split(" ");
		String actual_args[]=mname[1].split(",");
		String mdt_words[]=mdt.get(mdtp).split(" "); //read line from MDT and split
		String args[]=mdt_words[1].split(",");
	
		for(int i=0;i<args.length;i++)
		{   
			for(int j=0;j<ala.size();j++) {
				ALA l=ala.get(j);
			   if(l.formal.equals(args[i]))
			   {   
				   //formal argument found, so set actual one
				   ala.set(j,new ALA(l.formal,actual_args[i]));
			   }
			}
		}
		//Show ALA After setting Actual arguments
		System.out.println("ALA After setting Actual arguments");
		showAla(2);
		mdtp++;
		String final1="";
		while(!mdt.get(mdtp).equals("MEND"))
		{
			String op_line=mdt.get(mdtp);
			mdtp++;
			if(op_line.contains("#"))
			{ int ind=op_line.indexOf("#");
			  final1=op_line.substring(0,ind);
			  ind=Integer.parseInt(op_line.substring(ind+1,op_line.length()));			  
			  ALA l=ala.get(ind);
			  final1=final1+l.actual;
			}
			else
				final1=op_line;
		   op.println(final1);
		   op.flush();
		}
	}
	void showAla(int pass) throws Exception {
		int i=0;
		for(ALA l : ala) {
			System.out.println(i+" "+l.formal+" "+l.actual);
			i++;
		}
	}

	void showMnt() throws Exception {
		int i=0;
		for(MNT l : mnt) {
			System.out.println(i+" "+l.name+" "+l.index);
			i++;
		
		}
	}

	void showMdt() throws Exception {
		int i=0;
		for(String l : mdt) {
			System.out.println(i+" "+l);
			i++;
			
		}
	}

	void initializeTables() throws Exception{
		mnt = new LinkedList<MNT>();
		mdt = new ArrayList<String>();
		ala = new LinkedList<ALA>();
		
		String mname=new String();
		//Load MNT
		String s=new String();
		File f=new File("MNT.txt");
		Scanner input = new Scanner(f);
		while(input.hasNextLine()) {
			s=input.nextLine();
			String words[]=s.split(" ");
			mnt.add(new MNT(words[0],Integer.parseInt(words[1])));		
		}
		//load MDT
	     f=new File("MDT.txt");
		 input = new Scanner(f);
		while(input.hasNextLine()) {
			s=input.nextLine();
			mdt.add(s);
		}
		//Load ALA pass1
		f=new File("ala.txt");
		 input = new Scanner(f);
		while(input.hasNextLine()) {
			s=input.nextLine();
			String words[]=s.split(" ");
			for(int i=0;i<words.length;i++)
			  ala.add(new ALA(words[i],"-"));
		}
		
	}
}//end of class
