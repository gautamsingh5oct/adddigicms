package net.mycomp.test;

import java.sql.Timestamp;

import org.joda.time.Period;

public class Main {

	public static void main(String arg[]){
		Timestamp ts=new Timestamp(System.currentTimeMillis()+10000000);
	   	 
		Timestamp ts2=new Timestamp(System.currentTimeMillis());
   	 
		System.out.println((ts2.getTime()-ts.getTime())>0);
				
			
	}
}
