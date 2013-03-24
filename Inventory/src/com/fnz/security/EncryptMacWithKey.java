package com.fnz.security;

import java.io.*;
import java.security.*;
public class EncryptMacWithKey

{

	public void MakeKeyFile()

	{
				try			
				{ 
					MacToString mts=new MacToString();
					String macfourbit=mts.createStringFromMac();
					
					//Checking the Key with Mac Address
					String macString="";
					String splt[]=macfourbit.split("-");
					
					for(int i=0;i<splt.length;i++)
					{
								String x=splt[i];
								int y=Integer.parseInt(x);
								int z=y/100;
								int z2=y%100;
								char c=(char)z;
								char c1=(char)z2;
								String s=Character.toString(c)+Character.toString(c1);
								macString=macString+s+"-";
					}
					macString = macString.substring(0,macString.length()-1);
					//System.out.println(macString);
					String mac=new MacAddress().getMacAddress();
					System.out.println("Mac Address is:"+mac);
				
					if (macString.equals(mac)) 
					{
						//System.out.println("Key Matched....Now writing to the file");
						MessageDigest md =MessageDigest.getInstance("SHA"); 
						
						byte[] array = macfourbit.getBytes();
						
						md.update(array);
						
						FileOutputStream fos =new FileOutputStream("key.txt");
						
						ObjectOutputStream oos =new ObjectOutputStream(fos);
						
						oos.writeObject(macfourbit);
						
						oos.writeObject(md.digest ());
					}
					else
					{
						System.out.println("Key is not Matching");
					}
				}
				
				catch(Exception e1)
				
				{ System.out.println(""+e1);}
				
				}

}