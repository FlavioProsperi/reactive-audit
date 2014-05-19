package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 06/05/14.
 */
public class SequenceInputStreamTest extends ByteArrayInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		push();
		InputStream in1=super.newInputStream();
		InputStream in2=super.newInputStream();
		pop();
		return new SequenceInputStream(in1,in2);
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends SequenceInputStream
		{
			Derived() throws IOException
			{
				super(new ByteArrayInputStream(new byte[10]),new ByteArrayInputStream(new byte[10]));
			}
		};
		new Derived();
	}
}