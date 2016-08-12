package com.raremile.training.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raremile.training.exceptions.NonFatalException;
import com.raremile.training.service.MyPOJO;
import com.raremile.training.utils.CamelCaseFormatter;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		MyPOJO myPOJO = new MyPOJO("library");

		try {
			myPOJO.createPOJO();
		} catch (NonFatalException e) {
			e.printStackTrace();
		}

	}

}
