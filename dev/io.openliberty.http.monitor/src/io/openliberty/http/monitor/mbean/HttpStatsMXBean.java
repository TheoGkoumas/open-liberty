/*******************************************************************************
 * Copyright (c) 2024 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package io.openliberty.http.monitor.mbean;

/**
 *
 */
public interface HttpStatsMXBean {

	/*
	 * HTTP Attributes
	 */
	
	public String getRequestMethod();
	
	public int getResponseStatus();
	
	public String getHttpRoute();
	
	public String getScheme();
	
	public String getNetworkProtocolName();
	
	public String getNetworkProtocolVersion();
		
	public String getServerName();
	
	public int getServerPort();
	
	public String getErrorType();
	
	/*
	 * Metric values
	 */
	
	public long getCount();
	
	public double getDuration();
    
}
