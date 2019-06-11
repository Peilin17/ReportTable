package table;

import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class Table extends JFrame  
{  
  
    public Table(int len, DataPack[] temp, Date[] dates)  
    {  
        intiComponent(len,  temp, dates);  
    }  
  
    /** 
     * 初始化窗体组件 
     */  
    private void intiComponent(int len, DataPack[] temp, Date[] dates)  
    {  
        /* 
         * 设置JTable的列名 
         */  
        String[] columnNames =  
        { "日期", "介精", "介泥", "加压", "混精", "介中", "煤种" };  
  
        
        Object[][] obj = new Object[len+1][7];  
        for (int i = 0; i < len; i++)  
        {  
            for (int j = 0; j < 7; j++)  
            {  
                switch (j)  
                {  
                case 0:  
                    obj[i][j] = dates[i];  
                    break;  
                case 1:  
                    obj[i][j] = temp[i].getX();  
                    break;  
                case 2:  
                    obj[i][j] = temp[i].getY();  
                    break;  
                case 3:  
                    obj[i][j] = temp[i].getZ();  
                    break;  
                case 4:  
                    obj[i][j] = temp[i].getA();  
                    break;  
                case 5:  
                    obj[i][j] = temp[i].getB();  
                case 6:  
                    obj[i][j] = temp[i].getC();  
                    break;  
                }  
            }  
        }  
        obj[len][0] = "平均"; 
        int total1 = 0;
        for (int i = 0; i < len; i++)
        {
        	total1 += temp[i].getX();
        }
        obj[len][1] = total1/len; 
        int total2 = 0;
        for (int i = 0; i < len; i++)
        {
        	total2 += temp[i].getY();
        }
        obj[len][2] = total2/len; 
        int total3 = 0;
        for (int i = 0; i < len; i++)
        {
        	total3 += temp[i].getZ();
        }
        obj[len][3] = total3/len; 
        int total4 = 0;
        for (int i = 0; i < len; i++)
        {
        	total4 += temp[i].getA();
        }
        obj[len][4] = total4/len;
        int total5 = 0;
        for (int i = 0; i < len; i++)
        {
        	total5 += temp[i].getB();
        }
        obj[len][5] = total5/len;
        obj[len][6] = "平均";
        /* 
         * JTable的其中一种构造方法 
         */  
        JTable table = new JTable(obj, columnNames);  
        /* 
         * 设置JTable的列默认的宽度和高度 
         */  
        TableColumn column = null;  
        int colunms = table.getColumnCount();  
        for(int i = 0; i < colunms; i++)  
        {  
            column = table.getColumnModel().getColumn(i);  
            /*将每一列的默认宽度设置为100*/  
            column.setPreferredWidth(100);  
        }  
        /* 
         * 设置JTable自动调整列表的状态，此处设置为关闭 
         */  
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
          
        /*用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看*/  
        JScrollPane scroll = new JScrollPane(table);  
        scroll.setSize(300, 200);  
          
          
        add(scroll);  
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.pack();  
    }  
  
    
}  
