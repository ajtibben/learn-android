package com.ajtibben.learnandroid;

public class MT940Builder implements MT940Handler
{
	StringBuilder builder = new StringBuilder();

	@Override
	public void unknown(String line)
	{
		builder.append("Unknown: ");
		builder.append(line);
		builder.append('\n');
	}

	
}
