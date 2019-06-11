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
	private static JTextField jtf = new JTextField("", 30);    // �����ı������, 30 ��
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
	    frm.add(btn);   // ����˰�ť��ѱ�����ɫ��ס������ͨ�����������
	    btn.addActionListener((ActionEvent e)->{
			clickUpload();
			addSQL(jtf6.getText(), update.getX(), update.getY(), update.getZ(), update.getA(), update.getB(), update.getC());
		});
	    //add show button
	    JButton b = new JButton("Show Table");	
	    b.setBounds(800, 850, 100, 40);
	    b.setLocation(800, 850);
	    
		b.setSize(100, 40);
	    frm.add(b);   // ����˰�ť��ѱ�����ɫ��ס������ͨ�����������
	    b.addActionListener((ActionEvent e)->{
			clickShow();
			
		});
	    //here is the labels;
	    Label param1 = new Label("�龫%");
	    param1.setBounds(20, 20, 100, 20);
	    Label param2 = new Label("����%");
	    param2.setBounds(20, 60, 100, 20);
	    Label param3 = new Label("��ѹ%");
	    param3.setBounds(20, 100, 100, 20);
	    Label param4 = new Label("�쾫%");
	    param4.setBounds(20, 140, 100, 20);
	    Label param5 = new Label("����%");
	    param5.setBounds(20, 180, 100, 20);
	    Label param6 = new Label("ú��");
	    param6.setBounds(20, 220, 100, 20);
	    Label param7 = new Label("����");
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
	    frm.setBounds(100, 100, 200, 200);  // ������������ͬ		
	    frm.setTitle("ȷ��");		
	    Container c = frm.getContentPane(); // frm�а���һ�����ݴ��� ��Ҫ��ȡ���ݴ��������ñ�����ɫ��ֱ������frm�ı�����ɫ�ᱻ���ݴ���ס		
	    c.setBackground(Color.WHITE);
	    frm.setLayout(null);                // ���������ΪnullĬ�ϣ���ť������������ݿ򣬵�ס������ɫ		
	    
	    Label param6 = new Label("�ϴ����");
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
	// JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/coal?serverTimezone=GMT";
 
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "root";
    static final String PASS = "328904";
    
	public void addSQL(String date, int �龫, int ����, int ��ѹ, int �쾫, int ����, String ú��)
	{
		Connection conn = null;
        Statement stmt = null;
        try{
            // ע�� JDBC ����
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement����...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM coal_details";
            ResultSet rs = stmt.executeQuery(sql);
        
            // չ����������ݿ�
            while(rs.next()){
                // ͨ���ֶμ���
                Date id  = rs.getDate(1);
                int name = rs.getInt("�龫");
                String type = rs.getString("ú��");
    
                // �������
                System.out.print("Date " + id);
                System.out.print(", �龫 " + name);
                System.out.print(", ú��: " + type);
                System.out.print("\n");
            }
            
              
            PreparedStatement psql;
            //ResultSet res;
            //Ԥ����������ݣ���������������--������
            psql = conn.prepareStatement("insert into coal_details (date,�龫,����,��ѹ,�쾫,����,ú��) "
                    + "values(?,?,?,?,?,?,?)");
            //���ò���1������idΪ3212������
            psql.setInt(2, �龫);      //���ò���2��name Ϊ����
            psql.setInt(3, ����);
             
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date myDate2 = dateFormat2.parse(date);
            psql.setDate(1,new java.sql.Date(myDate2.getTime()));
            psql.setInt(4, ��ѹ);
            psql.setInt(5, �쾫);
            psql.setInt(6, ����);
            psql.setString(7, ú��);
            psql.executeUpdate();           //ִ�и���
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
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
            // ע�� JDBC ����
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement����...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM coal_details";
            ResultSet rs = stmt.executeQuery(sql);
        
            // չ����������ݿ�
            size = 0;
            while(rs.next()){
                // ͨ���ֶμ���
                Date id  = rs.getDate(1);
                int param1 = rs.getInt("�龫");
                int param2 = rs.getInt("����");
                int param3 = rs.getInt("��ѹ");
                int param4 = rs.getInt("�쾫");
                int param5 = rs.getInt("����");
                String type = rs.getString("ú��");
                DataPack temp = new DataPack(param1, param2, param3, param4, param5, type);
                datas[size] = temp;
                dates[size] = id;
                size++;
            }
            
              
            
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Finish reading!");
	}

}
