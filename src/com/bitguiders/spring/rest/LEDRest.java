package com.bitguiders.spring.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
@RequestMapping("/led")
public class LEDRest {
	private static GpioPinDigitalOutput pin;
	
	@RequestMapping(method=RequestMethod.GET , produces=MediaType.TEXT_PLAIN_VALUE )
	public String get(){
		return "Turn light on: /on";
	}
	
	
	@RequestMapping(value="/on",method=RequestMethod.GET , produces=MediaType.TEXT_PLAIN_VALUE )
	public String light(){
		if(pin==null) {
			GpioController gpio = GpioFactory.getInstance();
			GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,"myLed",PinState.LOW);
		}
		pin.toggle();
		return "on";
	}

}
