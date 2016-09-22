package net.mizucoffee.wiimote4j_example;

import java.awt.MouseInfo;
import java.awt.Point;

import com.mizucoffee.wiimote4j.device.DeviceConnector;
import com.mizucoffee.wiimote4j.device.Wiimote;

import purejavahidapi.DeviceRemovalListener;
import purejavahidapi.HidDevice;
import purejavahidapi.InputReportListener;

public class Main {
	
	public static Wiimote wiimote;

	public static void main(String[] args) {
		DeviceConnector wiimoteDC = new DeviceConnector();
		wiimoteDC.connect(0, DeviceConnector.WIIMOTE);

		wiimote = new Wiimote(wiimoteDC.getDevInfo());
		wiimote.setInputReportListener(new InputReportListener() {
			@Override
			public void onInputReport(HidDevice source, byte Id, byte[] b, int len) {
				for (int i = 0; i < len; i++)
					System.out.print(Integer.toBinaryString(b[i]) + " ");
				System.out.println();
			}
		});
		wiimote.setDeviceRemovalListener(new DeviceRemovalListener() {
			@Override
			public void onDeviceRemoval(HidDevice arg0) {
				System.out.println("[INFO] Disconnected");
			}
		});
		wiimote.vibrate(250);
		wiimote.requestStatus();
	}

}
