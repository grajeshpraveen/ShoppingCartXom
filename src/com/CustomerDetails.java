package com;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class CustomerDetails {

	public String customerName;
	public List<Cart> cart;
	public String categoryId;

	public double totalPrice() {
		double totalPrice = 0;
		for (Cart s : cart) {
			totalPrice = totalPrice + (s.price * s.quantity);
		}
		return totalPrice;
	}

	public String findCategory() {
		String category = null;
		if (!categoryId.isEmpty()) {
			if (categoryId.startsWith("G")) {
				category = "GOLD";
			} else if (categoryId.startsWith("S")) {
				category = "SLIVER";
			} else if (categoryId.startsWith("P")) {
				category = "PLATINUM";
			} else {
				category = "NotAllowed";
			}
		}
		return category;

	}

	public String getPropValues() throws IOException {
		String result = "";
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(
					propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}

			Date time = new Date(System.currentTimeMillis());

			// get the property value and print it out
			String user = prop.getProperty("user");
			String company1 = prop.getProperty("company1");
			String company2 = prop.getProperty("company2");
			String company3 = prop.getProperty("company3");

			result = "Company List = " + company1 + ", " + company2 + ", "
					+ company3;
			System.out.println(result + "\nProgram Ran on " + time
					+ " by user=" + user);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}
