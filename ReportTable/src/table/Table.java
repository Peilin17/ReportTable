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
     * ��ʼ��������� 
     */  
    private void intiComponent(int len, DataPack[] temp, Date[] dates)  
    {  
        /* 
         * ����JTable������ 
         */  
        String[] columnNames =  
        { "����", "�龫", "����", "��ѹ", "�쾫", "����", "ú��" };  
  
        
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
        obj[len][0] = "ƽ��"; 
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
        obj[len][6] = "ƽ��";
        /* 
         * JTable������һ�ֹ��췽�� 
         */  
        JTable table = new JTable(obj, columnNames);  
        /* 
         * ����JTable����Ĭ�ϵĿ�Ⱥ͸߶� 
         */  
        TableColumn column = null;  
        int colunms = table.getColumnCount();  
        for(int i = 0; i < colunms; i++)  
        {  
            column = table.getColumnModel().getColumn(i);  
            /*��ÿһ�е�Ĭ�Ͽ������Ϊ100*/  
            column.setPreferredWidth(100);  
        }  
        /* 
         * ����JTable�Զ������б��״̬���˴�����Ϊ�ر� 
         */  
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
          
        /*��JScrollPaneװ��JTable������������Χ���оͿ���ͨ�����������鿴*/  
        JScrollPane scroll = new JScrollPane(table);  
        scroll.setSize(300, 200);  
          
          
        add(scroll);  
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.pack();  
    }  
  
    
}  
