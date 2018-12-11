package com.lewei.production.print;

import com.lewei.production.util.CodeAndPrint;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import java.awt.*;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;

public class WarehouseStcoPrint implements Printable {

	/**序号 */
	private  String code="14843";
	/**编号 */
	private  String stco="1WAB00";
	/**说明 */
	private  String dsca="CRB02";

	/** 左边距 */
	private double paddingLeft = 0;

	/** 上边距 */
	private double paddingTop = 0;

	/** 打印机名称 */
	private String printerName = "Send To OneNote 2016";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStco() {
		return stco;
	}

	public void setStco(String stco) {
		this.stco = stco;
	}

	public String getDsca() {
		return dsca;
	}

	public void setDsca(String dsca) {
		this.dsca = dsca;
	}

	public double getPaddingLeft() {
		return paddingLeft;
	}


	public void setPaddingLeft(double paddingLeft) {
		this.paddingLeft = paddingLeft;
	}


	public double getPaddingTop() {
		return paddingTop;
	}


	public void setPaddingTop(double paddingTop) {
		this.paddingTop = paddingTop;
	}


	public String getPrinterName() {
		return printerName;
	}


	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	/**
	 * 设置边距。
	 *
	 * @param paddingLeft
	 * @param paddingTop
	 */
	public void setPadding(double paddingLeft, double paddingTop) {
		this.paddingLeft = paddingLeft;
		this.paddingTop = paddingTop;
	}

	public WarehouseStcoPrint(String printerName) {
		super();
		this.printerName = printerName;
	}

	public WarehouseStcoPrint() {
		super();
	}

	/**
	 * @param gra
	 * 指明打印的图形环境
	 * @param pf
	 * 指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×
	 *            842点）
	 * @param pageIndex
	 * 指明页号
	 **/

	@Override
	public int print(Graphics gra, PageFormat pf, int pageIndex)
			throws PrinterException {
		// TODO Auto-generated method stub
		Component c = null;
		// 转换成Graphics2D
		Graphics2D g2 = (Graphics2D) gra;
		// 设置打印颜色为黑色
		g2.setColor(Color.black);
//				 打印起点坐标
		double x = pf.getImageableX()+50;
		double y = pf.getImageableY();

		switch (pageIndex) {
			case 0:
				Font font = new Font("微软雅黑", Font.BOLD, 12);
				g2.setFont(font);
				g2.drawString("工作中心标签", (float) (x + 40),
						(float) (y + 25));
				font = new Font("微软雅黑", Font.BOLD, 8);
					g2.setFont(font);
//					g2.drawString("工作中心", (float) (x + 60),
//						(float) (y + 35));
//					g2.drawString("编号："+stco, (float) (x+ 60),
//							(float) (y+45));
					g2.drawString("说明："+dsca, (float) (x+ 5),
						(float) (y+80));
//				g2.drawString("标签编号："+ code, (float) (x+ 60),
//						(float) (y+65));
				   CodeAndPrint codeAndPrint = new CodeAndPrint();
				String filename = "C:\\toolsZ\\production\\codeZ\\11.jpg";
				   codeAndPrint.printDimensionalCode(stco,filename);
					Image Img = null;
					try {
						Img = ImageIO.read(new File(filename));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g2.drawImage(Img,(int)x,(int)y+30,140,40,c);


				return PAGE_EXISTS;
			default:
				return NO_SUCH_PAGE;
		}
	}

	public static void main(String[] args) {

		// 通俗理解就是书、文档
		Book book = new Book();
		// 设置成竖打
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);
		// 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
		Paper p = new Paper();
		p.setSize(350, 210);// 纸张大小
		// A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
		p.setImageableArea(0, 0, 350, 210);
		pf.setPaper(p);
		// 把 PageFormat 和 Printable 添加到书中，组成一个页面
		book.append(new WarehouseStcoPrint(), pf);

		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();

		HashAttributeSet hs = new HashAttributeSet();

		//String printerName = "HP LaserJet Professional P1106";
		String printerName = "Send To OneNote 2016";
		hs.add(new PrinterName(printerName, null));

		PrintService[] pss = PrintServiceLookup.lookupPrintServices(null, hs);

		try {
			job.setPrintService(pss[0]);
		} catch (PrinterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 设置打印类
		job.setPageable(book);

		try {
			// 可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
			// boolean a=job.printDialog();
			// if(a)
			// {
			job.print();
			// }
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}

	public void printcode() {

		// 通俗理解就是书、文档
		Book book = new Book();

		PageFormat pageFormat = new PageFormat();
		// 打印方向
		pageFormat.setOrientation(PageFormat.PORTRAIT);
		// 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
		Paper paper = new Paper();
		paper.setSize(350, 220);// 纸张
			// A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
			paper.setImageableArea(0, 0,  350, 220);


		pageFormat.setPaper(paper);
		// 把 PageFormat 和 Printable 添加到书中，组成一个页面
		book.append(this, pageFormat);

		// 获取打印服务对象
		PrinterJob printJob = PrinterJob.getPrinterJob();

		HashAttributeSet hashAttributeSet = new HashAttributeSet();

		hashAttributeSet.add(new PrinterName(printerName, null));

		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, hashAttributeSet);

		try {
			printJob.setPrintService(printServices[0]);
		} catch (PrinterException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}

		// 设置打印类
		printJob.setPageable(book);
		try {
			// 可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
			// boolean a=job.printDialog();
			// if(a)
			// {
			printJob.print();
			// }
		} catch (PrinterException e) {
			e.printStackTrace();
		}

	}

}
