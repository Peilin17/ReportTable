/**
 * 
 */
package table;

import java.awt.Color;
import java.awt.Container;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author Peilin
 *
 */
public class GUI {
	private static JTextField jtf = new JTextField("", 30);    // 创建文本行组件, 30 列
	private static JTextField jtf1 = new JTextField("", 30);
	private static JTextField jtf2 = new JTextField("", 30);
	private static JTextField jtf3 = new JTextField("", 30);
	private static JTextField jtf4 = new JTextField("", 30);
	private static JTextField jtf5 = new JTextField("", 30);
	private static JTextField jtf6 = new JTextField("", 30);
	private DataPack update;
	private DataPack[] datas = new DataPack[40];
	private Date[] dates = new Date[40];
	private int size;
	/**
	 * the main window with buttons
	 */
	public GUI()
	{
		JFrame frm = new JFrame("Input Data");
		frm.setSize(1530, 960);	
		//the update Button
	    JButton btn = new JButton("upload");	
	    btn.setBounds(700, 850, 100, 40);
	    btn.setLocation(700, 850);
	    frm.setLayout(null);     
		btn.setSize(100, 40);
	    frm.add(btn);   // 添加了按钮会把背景颜色挡住，可以通过面板来调节
	    btn.addActionListener((ActionEvent e)->{
			clickUpload();
			addSQL(jtf6.getText(), update.getX(), update.getY(), update.getZ(), update.getA(), update.getB(), update.getC());
		});
	    //add show button
	    JButton b = new JButton("Show Table");	
	    b.setBounds(800, 850, 100, 40);
	    b.setLocation(800, 850);
	    
		b.setSize(100, 40);
	    frm.add(b);   // 添加了按钮会把背景颜色挡住，可以通过面板来调节
	    b.addActionListener((ActionEvent e)->{
			clickShow();
			
		});
	    //here is the labels;
	    Label param1 = new Label("介精%");
	    param1.setBounds(20, 20, 100, 20);
	    Label param2 = new Label("介泥%");
	    param2.setBounds(20, 60, 100, 20);
	    Label param3 = new Label("加压%");
	    param3.setBounds(20, 100, 100, 20);
	    Label param4 = new Label("混精%");
	    param4.setBounds(20, 140, 100, 20);
	    Label param5 = new Label("介中%");
	    param5.setBounds(20, 180, 100, 20);
	    Label param6 = new Label("煤种");
	    param6.setBounds(20, 220, 100, 20);
	    Label param7 = new Label("日期");
	    param7.setBounds(20, 260, 100, 20);
        jtf.setBounds(20, 40, 140, 20);
        jtf1.setBounds(20, 80, 140, 20);
        jtf2.setBounds(20, 120, 140, 20);
        jtf3.setBounds(20, 160, 140, 20);
        jtf4.setBounds(20, 200, 140, 20);
        jtf5.setBounds(20, 240, 140, 20);
        jtf6.setBounds(20, 280, 140, 20);
        
        //add into
        frm.add(jtf);
        frm.add(jtf1);
        frm.add(jtf2);
        frm.add(jtf3);
        frm.add(jtf4);
        frm.add(jtf5);
        frm.add(jtf6);
        frm.add(param1);
        frm.add(param2);
        frm.add(param3);
        frm.add(param4);
        frm.add(param5);
        frm.add(param6);
        frm.add(param7);
        //frm.add(btn);
        //frm.add(jsp);

	    frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frm.setVisible(true);
	}
	public static void displayConfirm()
	{
		JFrame frm = new JFrame();
		frm.setLocation(100, 100);
	    frm.setSize(200, 200);		
	    frm.setBounds(100, 100, 200, 200);  // 功能与上面相同		
	    frm.setTitle("确认");		
	    Container c = frm.getContentPane(); // frm中包含一个内容窗格， 需要获取内容窗格，再设置背景颜色，直接设置frm的背景颜色会被内容窗格挡住		
	    c.setBackground(Color.WHITE);
	    frm.setLayout(null);                // 如过不设置为null默认，按钮会充满整个内容框，挡住背景颜色		
	    
	    Label param6 = new Label("上传完成");
	    param6.setBounds(20, 100, 100, 20);
	    frm.add(param6);
	    //frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frm.setVisible(true);
	}
	/**
	 * click method
	 */
	public void clickUpload()
	{
		update = new DataPack(Integer.parseInt(jtf.getText()),
		Integer.parseInt(jtf1.getText()),
		Integer.parseInt(jtf2.getText()),
		Integer.parseInt(jtf3.getText()),
		Integer.parseInt(jtf4.getText()),
		jtf5.getText());
		displayConfirm();
		
	}
	/**
	 * click ShowTable method
	 */
	public void clickShow()
	{
		readSQL();
		//System.out.println(datas[0].getX());
		Table temp = new Table(size, datas, dates);
	}
	
	/**
	 * simple get method
	 * @return
	 */
	public DataPack getData()
	{
		return update;
	}
	/**
	 * here is the function of SQL
	 * use local severs this time.
	 */
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/coal?serverTimezone=GMT";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "328904";
    
	public void addSQL(String date, int 介精, int 介泥, int 加压, int 混精, int 介中, String 煤种)
	{
		Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM coal_details";
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                Date id  = rs.getDate(1);
                int name = rs.getInt("介精");
                String type = rs.getString("煤种");
    
                // 输出数据
                System.out.print("Date " + id);
                System.out.print(", 介精 " + name);
                System.out.print(", 煤种: " + type);
                System.out.print("\n");
            }
            
              
            PreparedStatement psql;
            //ResultSet res;
            //预处理添加数据，其中有两个参数--“？”
            psql = conn.prepareStatement("insert into coal_details (date,介精,介泥,加压,混精,介中,煤种) "
                    + "values(?,?,?,?,?,?,?)");
            //设置参数1，创建id为3212的数据
            psql.setInt(2, 介精);      //设置参数2，name 为王刚
            psql.setInt(3, 介泥);
             
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date myDate2 = dateFormat2.parse(date);
            psql.setDate(1,new java.sql.Date(myDate2.getTime()));
            psql.setInt(4, 加压);
            psql.setInt(5, 混精);
            psql.setInt(6, 介中);
            psql.setString(7, 煤种);
            psql.executeUpdate();           //执行更新
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
	}
	/**
	 * read all data in SQL and store in array.
	 */
	public void readSQL()
	{
		Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM coal_details";
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            size = 0;
            while(rs.next()){
                // 通过字段检索
                Date id  = rs.getDate(1);
                int param1 = rs.getInt("介精");
                int param2 = rs.getInt("介泥");
                int param3 = rs.getInt("加压");
                int param4 = rs.getInt("混精");
                int param5 = rs.getInt("介中");
                String type = rs.getString("煤种");
                DataPack temp = new DataPack(param1, param2, param3, param4, param5, type);
                datas[size] = temp;
                dates[size] = id;
                size++;
            }
            
              
            
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Finish reading!");
	}

}
