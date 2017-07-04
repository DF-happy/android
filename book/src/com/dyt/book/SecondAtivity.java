package com.dyt.book;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dyt.bean.Book;
import com.dyt.dao.MyDataBase;
import com.dyt.dao.MyOpenHelper;
import com.example.ibook.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 *�����༭�ʼ�
 *isSave()������������
 */
public class SecondAtivity extends Activity {

	EditText ed1,ed2;
	Button bt1;
	MyDataBase myDatabase;
	Book book;
	int ids;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		ed1=(EditText) findViewById(R.id.editText1);
		ed2=(EditText) findViewById(R.id.editText2);
		bt1=(Button) findViewById(R.id.button1);

		myDatabase=new MyDataBase(this);

		Intent intent=this.getIntent();
		ids=intent.getIntExtra("ids", 0);
		//Ĭ��Ϊ0����Ϊ0,��Ϊ�޸�����ʱ��ת������
		if(ids!=0){
			//�õ�Ҫ�޸ĵ�����
			book=myDatabase.getTiandCon(ids);
			ed1.setText(book.getTitle());
			ed2.setText(book.getContent());
		}		
		//���水ť�ĵ���¼�������isSave()������
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isSave();
			}
		});
	}

	//���淽��
	private void isSave(){
		SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy.MM.dd  HH:mm:ss");     
		Date   curDate   =   new   Date(System.currentTimeMillis());//��ȡ��ǰʱ��     
		String times   =   formatter.format(curDate);      
		String title=ed1.getText().toString();
		String content=ed2.getText().toString();

		//�޸ıʼ�
		if(ids!=0){
			book=new Book(title,ids, content, times);
			//�޸ķ���
			myDatabase.toUpdate(book);
			Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
			startActivity(intent);
			SecondAtivity.this.finish();
		}

		//�½��ʼ�
		else{
			book=new Book(title,content,times);
			myDatabase.toInsert(book);
			Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
			startActivity(intent);
			SecondAtivity.this.finish();
		}
	}

}
