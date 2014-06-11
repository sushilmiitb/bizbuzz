/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bizbuzz.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.atmosphere.cpr.AtmosphereResource;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.bizbuzz.utils.AtmosphereUtils;
import com.bizbuzz.dto.Message;
import com.bizbuzz.dto.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Main controller.
 *
 * @author Gunnar Hillert
 * @since  1.0
 *
 */
@Controller
public class ChatController {

	private static final Logger logger = LoggerFactory
			.getLogger(ChatController.class);

	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "test";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/websockets", method = RequestMethod.POST)
	@ResponseBody
	public void post(final AtmosphereResource event, @RequestBody String msg)
			throws JsonGenerationException, JsonMappingException, IOException {

		logger.info("Received message to broadcast: {}", msg);
		event.getBroadcaster().getAtmosphereResources().size();
		event.getBroadcaster().broadcast(msg);
	}

	/**
	 * Responsible for suspending the {@link HttpServletResponse} and executing
	 * a broadcasts periodically.
	 *
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@RequestMapping(value = "/websockets", method = RequestMethod.GET)
	@ResponseBody
	public void websockets(final AtmosphereResource event)
			throws JsonGenerationException, JsonMappingException, IOException {

		AtmosphereUtils.suspend(event);

		//final Broadcaster bc = event.getBroadcaster();

		//final int numberOfClients = bc.getAtmosphereResources().size();

//		String statusMessage = "A new Client has connected on "
//				+ new Date().toString() + " (Total: " + numberOfClients + ")";

	//	logger.info(statusMessage);

		//bc.broadcast(objectMapper.writeValueAsString(new StatusMessage(
		//		statusMessage)));

	}

}
