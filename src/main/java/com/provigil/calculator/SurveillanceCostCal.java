package com.provigil.calculator;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.provigil.calculator.constants.SurveillanceCostCalConstants;
import com.provigil.calculator.pojo.Result;
import com.provigil.calculator.pojo.Subscriptions;

/**
 * Hello world!
 * 
 */
public class SurveillanceCostCal {

	public static void main(String[] args) {
		int cost = 0;
		try {
		File xmlFile = new File("C:\\Users\\Joshi\\Desktop\\Honey\\Pro Vigil Inc Company\\surveillance-cost-cal\\surveillance-cost-cal\\src\\main\\resources\\sample01.xml");
		FileOutputStream output = new FileOutputStream(new File("C:\\Users\\Joshi\\Desktop\\Honey\\Pro Vigil Inc Company\\surveillance-cost-cal\\surveillance-cost-cal\\src\\main\\resources\\result.xml"));
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Subscriptions obj = new Subscriptions();
		Result result = new Result();
		XMLEncoder xmlEnc = new XMLEncoder(output);

			dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(xmlFile);
			NodeList node = document.getElementsByTagName("subscription");
			System.out.println("Information of the subscription and length " + node.getLength());
			for (int i = 0; i < node.getLength(); i++) {
				Node firstNode = node.item(i);
				if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) firstNode;

					obj.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
					obj.setArea(Integer.parseInt(element.getElementsByTagName("area").item(0).getTextContent()));
					obj.setPlan(element.getElementsByTagName("plan").item(0).getTextContent());
					obj.setLocation(element.getElementsByTagName("location").item(0).getTextContent());
					System.out.println("Id : " + obj.getId() + " Area : " + obj.getArea() + " Plan : "+ obj.getPlan() + " Location : " + obj.getLocation());
					cost = surveillanceCostCalculation(obj);
					result.setId(obj.getId());
					result.setCost(cost);	
					xmlEnc.writeObject(result);
					System.out.println("Result set :" + "Id : " + result.getId() + " Cost : " + result.getCost());
					System.out.println("Total cost is :" + surveillanceCostCalculation(obj));
				}
			}
			xmlEnc.close();
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Please check the file location" + e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

		static int surveillanceCostCalculation(Subscriptions sub) {
			double area = 0.0;
			
			try {
			if(sub.getPlan().equalsIgnoreCase(SurveillanceCostCalConstants.MONTHLY_PLAN)) {
				if(sub.getLocation().equalsIgnoreCase(SurveillanceCostCalConstants.INDOOR_LOCATION)) {
					if(sub.getArea() <= 2500) 
						area = sub.getArea() * 2;
					else if(sub.getArea() >= 2501 && sub.getArea() <= 5000)
						area = (SurveillanceCostCalConstants.VALUE * 2) + ((sub.getArea() - SurveillanceCostCalConstants.VALUE) * 1.5);
					else if(sub.getArea() >= 5001 && sub.getArea() <= 50000)
						area = (SurveillanceCostCalConstants.VALUE * 2) + (SurveillanceCostCalConstants.VALUE * 1.5) + ((sub.getArea() - (SurveillanceCostCalConstants.VALUE * 2)) * 1);
					else
						area = (SurveillanceCostCalConstants.VALUE * 2) + (SurveillanceCostCalConstants.VALUE * 1.5) + (SurveillanceCostCalConstants.VALUE * 1) + ((sub.getArea() - (SurveillanceCostCalConstants.VALUE * 3)) * 0.8);
				} else if(sub.getLocation().equalsIgnoreCase(SurveillanceCostCalConstants.OUTDOOR_LOCATION)) {
					if(sub.getArea() <= 2500) 
						area = sub.getArea() * 2.5;
					else if(sub.getArea() >= 2501 && sub.getArea() <= 5000)
						area = (SurveillanceCostCalConstants.VALUE * 2.5) + ((sub.getArea() - SurveillanceCostCalConstants.VALUE) * 1.5);
					else if(sub.getArea() >= 5001 && sub.getArea() <= 50000)
						area = (SurveillanceCostCalConstants.VALUE * 2.5) + (SurveillanceCostCalConstants.VALUE * 1.5) + ((sub.getArea() - (SurveillanceCostCalConstants.VALUE * 2)) * 1.2);
					else
						area = (SurveillanceCostCalConstants.VALUE * 2.5) + (SurveillanceCostCalConstants.VALUE * 1.5) + (SurveillanceCostCalConstants.VALUE * 1.2) + ((sub.getArea() - (SurveillanceCostCalConstants.VALUE * 3)) * 0.8);
				} else
					System.out.println("Area should be either Indoor or Outdoor");
			} else if(sub.getPlan().equalsIgnoreCase(SurveillanceCostCalConstants.YEARLY_PLAN)) {
				if(sub.getLocation().equalsIgnoreCase(SurveillanceCostCalConstants.INDOOR_LOCATION)) {
					if(sub.getArea() <= 2500) 
						area = sub.getArea() * 1.5;
					else if(sub.getArea() >= 2501 && sub.getArea() <= 5000)
						area = (SurveillanceCostCalConstants.VALUE * 1.5) + ((sub.getArea() - 2500) * 1);
					else if(sub.getArea() >= 5001 && sub.getArea() <= 50000)
						area = (SurveillanceCostCalConstants.VALUE * 1.5) + (SurveillanceCostCalConstants.VALUE * 1) + ((sub.getArea() - (SurveillanceCostCalConstants.VALUE * 2)) * 0.6);
					else
						area = (SurveillanceCostCalConstants.VALUE * 2) + (SurveillanceCostCalConstants.VALUE * 1) + (SurveillanceCostCalConstants.VALUE * 0.6) + ((sub.getArea() - (SurveillanceCostCalConstants.VALUE * 2)) * 0.5);
				} else if(sub.getLocation().equalsIgnoreCase(SurveillanceCostCalConstants.OUTDOOR_LOCATION)) {
					if(sub.getArea() <= 2500) 
						area = sub.getArea() * 2;
					else if(sub.getArea() >= 2501 && sub.getArea() <= 5000)
						area = (SurveillanceCostCalConstants.VALUE * 2) + ((sub.getArea() - 2500) * 1);
					else if(sub.getArea() >= 5001 && sub.getArea() <= 50000)
						area = (SurveillanceCostCalConstants.VALUE * 2) + (SurveillanceCostCalConstants.VALUE * 1) + ((sub.getArea() - (SurveillanceCostCalConstants.VALUE * 2)) * 0.8);
					else
						area = (SurveillanceCostCalConstants.VALUE * 2) + (SurveillanceCostCalConstants.VALUE * 1) + (SurveillanceCostCalConstants.VALUE * 0.8) + ((sub.getArea() - (SurveillanceCostCalConstants.VALUE * 3)) * 0.5);
				}
			} else {
				System.out.println("Plan should be either MONTHLY or YEARLY");
			}	
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return (int) area;
		}

	}
