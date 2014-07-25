package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.nio.channels.SelectableChannel;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 1
@Aspect
public class ServerSocketChannelAudit extends AbstractChannelsAudit
{
	@Before("call(java.nio.channels.SocketChannel java.nio.channels.ServerSocketChannel.accept())")
	public void accept(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			SelectableChannel asc = (SelectableChannel) thisJoinPoint.getTarget();
			if (asc.isBlocking())
				latency(HIGH, thisJoinPoint);
		}
	}
}