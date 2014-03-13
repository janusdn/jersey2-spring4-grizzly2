/* Copyright 2013-2014 (c) Sepior Aps, all rights reserved. */

package com.test;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.test.resources.TestResource;

/**
 * MyDemoApplication
 * @author Janus Dam Nielsen
 */
public class MyDemoApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public MyDemoApplication() {
		this.register(RequestContextFilter.class);
		this.register(TestResource.class);
		this.register(JacksonFeature.class);
		// Use this for registering a full set of resources.
		// this.packages("org.foo.rest;org.bar.rest");
	}
}
