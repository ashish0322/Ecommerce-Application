package com.ashish.org.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ashish.org.dao.OrdersDAO;
import com.ashish.org.pojo.Address;
import com.ashish.org.pojo.Customer;
import com.ashish.org.pojo.Order;
import com.ashish.org.pojo.OrderDetails;
import com.ashish.org.pojo.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
public class GenerateBillPdf {
	
	
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
 
	static String orderdDate;
	static double orderTotal;
	static String customerName;
	static String addressLine1;
	static String addressLine2;
	static String addressLine3;
	static String contact;
	
	
	static String productName;
	static double productPrice;
	static long productId;
	static String productSeller;
	
	static List<OrderDetails> odList;
	
	public static void receiveOrderId(long orderId){
		
	OrdersDAO  ordersDao = new OrdersDAO();
	Order o = ordersDao.getOrderDetails(orderId);
	orderdDate = o.getOrderDate();
	orderTotal=o.getOrderTotal();
	Customer c = o.getCustomer();
	System.out.println(c.getUserId()+"-"+c.getFirstName());
	customerName = (c.getFirstName()+""+c.getLastName());
	Address add = c.getAddress();
	System.out.println(add.getCity());
	addressLine1 = (add.getStreet()+"--"+add.getCity());
	addressLine2 = (add.getState());
	addressLine3 = (add.getCountry()+"---Pin:"+add.getPinCode());
	contact = (add.getContactNumber());
	
	odList = o.getOrderDetails();
	for(OrderDetails od : odList){
		productName = od.getProductName();
		productPrice = od.getUnitPrice();
		productId = od.getProductId();
		System.out.println(productName);
		
	}
	
	Product p = ordersDao.getProductDetails(productId);
	p.getCompany();
	p.getDescription();
		
	}
	
	
	public static Document createPDF(String file) {
 
		Document document = null;
 
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
 
			addMetaData(document);
 
			addTitlePage(document);
 
			createTable(document);
			
			addContentPage(document);
			
 
			document.close();
 
		} catch (FileNotFoundException e) {
 
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
 
	}
 
	private static void addMetaData(Document document) {
		document.addTitle("Generate PDF report");
		document.addSubject("Generate PDF report");
		document.addAuthor("Ashish Maheedhara");
		document.addCreator("Ashish Maheedhara");
	}
 
	private static void addTitlePage(Document document)
			throws DocumentException {
 
		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("ProKart ", TIME_ROMAN));
 
		creteEmptyLine(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Bill Generated on "
				+orderdDate, TIME_ROMAN_SMALL));
		document.add(preface);
 
	}
 
	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
 
	
	
	private static void createTable(Document document) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(3);
 
		PdfPCell c1 = new PdfPCell(new Phrase("Product Id"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
 
		c1 = new PdfPCell(new Phrase("Product Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
 
		c1 = new PdfPCell(new Phrase("Price"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);
 
		for (OrderDetails od : odList) {
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(String.valueOf(od.getProductId()));
			table.addCell(od.getProductName());
			table.addCell("$"+String.valueOf(od.getUnitPrice()));
			
		}
 
		document.add(table);
	}
 
	private static void addContentPage(Document document)
			throws DocumentException {
		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("\n\n\t Shipping & Billing Info : \n"+"\n ==============================================\n\n"
                + "Ordered Date:\t\t\t\t" +orderdDate+"\n Customer Name: \t\t\t\t "+customerName+
        "\n Street and City: \t\t\t\t "+addressLine1+ "\n State : \t\t\t\t"+addressLine2+
        "\n Country and Pincode :\t\t\t\t" +addressLine3+ 
        "\n Contact Details:\t\t\t\t"+contact+
                "\n\n\n Shipper:\t\t\t\t ProKartLogistics"+
        "\n Deleivery Time: \t\t\t\t: 3 days"+ "\n Payment:\t\t\t COD"+
           "\n Total\t\t\t\t:$"+orderTotal+     
        "\n\n\t\t\t\t\t\t\t Thank You for Shopping at ProKart \t\t"+"\n\n\t"));
		
		document.add(preface);
		
	}
}