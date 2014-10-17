package com.ajtibben.learnandroid;
import java.io.*;

public class MT940Parser
{
	private Reader reader;
	private char lookahead;
	private boolean atEnd;
	private MT940Handler handler;

	public MT940Parser(Reader reader, MT940Handler handler) throws IOException
	{
		this.reader = reader;
		this.handler = handler;
		atEnd = false;
		shift();
	}

	private void shift() throws IOException
	{
		int next = reader.read();
		if (next == -1)
			atEnd = true;
		else
			lookahead = (char) next;
	}
	
	public void parseAll() throws IOException {
		while(! atEnd) {
			String line = readLine();
			handler.unknown(line);
		}
	}
	
	String readLine() throws IOException {
		StringBuilder b = new StringBuilder();
		while( ! match('\n') ) {
			b.append(lookahead);
			shift();
		}
		
		return b.toString();
	}

	private boolean match(char p0) throws IOException
	{
		if (atEnd) return false;
		if (lookahead == p0) {
			shift();
			return true;
		}
		return false;
	}
	
}
