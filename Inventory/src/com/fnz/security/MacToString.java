package com.fnz.security;

public class MacToString 
{
	public String createStringFromMac() throws Exception
	{
		String mac = new MacAddress().getMacAddress();
		String fourBitString="";
		String tempstr="";
		System.out.println(mac);
		String tempString="";
		for(int i=0;i<mac.length();i++)
		{
			if(mac.charAt(i)!='-')
			{
				tempString = tempString + (int)mac.charAt(i);
				System.out.println(tempString);
				 tempstr=tempString;
			}
			else
			{
				if(tempString!=null)
				{
					tempString = tempString.substring(0, 4);
					fourBitString = fourBitString + tempString + "-";
				}
				tempString="";
			}
		}
		if(fourBitString !=null)
		{
			fourBitString = fourBitString.substring(0,fourBitString.length()-1)+"-"+tempstr;
		}
		System.out.println(fourBitString);
		return fourBitString;
	}
	
	
}