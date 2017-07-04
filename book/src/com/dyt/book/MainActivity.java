package com.dyt.book;

import java.util.ArrayList;

import com.dyt.bean.Book;
import com.dyt.dao.MyAdapter;
import com.dyt.dao.MyDataBase;
import com.example.ibook.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

/*
 * �������Ҫ�����������¼����ֱ�Ϊ
 * 1��ListView�ĳ�������¼�������AlertDialog���ж��Ƿ�ɾ�����ݡ�
 * 2��ListView�ĵ���¼�����ת���ڶ������棬�����޸�����
 * 3���½���ǩ��ť�ĵ���¼�����ת���ڶ����棬�����½���ǩ
 * 4��menu����˳��¼��������˳�����
 * 5��menu����½��¼��������½���ǩ
 */
public class MainActivity extends Activity {

	Button bt;
	ListView lv;
	LayoutInflater inflater;
	ArrayList<Book> array;
	MyDataBase mdb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv=(ListView) findViewById(R.id.listView1);
		bt=(Button) findViewById(R.id.button1);
		inflater=getLayoutInflater();

		mdb=new MyDataBase(this);
		//�õ��ʼ�����
		array=mdb.getArray();
		
		MyAdapter adapter=new MyAdapter(inflater,array);
		//��ʾ����
		lv.setAdapter(adapter);
		/*
		 * ���listView�����item,���뵽�ڶ���ҳ�棬�����޸ıʼ�
		 */
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub				
				Intent intent=new Intent(getApplicationContext(),SecondAtivity.class);
				intent.putExtra("ids",array.get(position).getIds() );
				startActivity(intent);
				MainActivity.this.finish();
			}
		});
		
		/*
		 * �����ж��Ƿ�ɾ������
		 */
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(MainActivity.this)
				.setTitle("��ȷ��Ҫɾ����")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mdb.toDelete(array.get(position).getIds());
						array=mdb.getArray();
						MyAdapter adapter=new MyAdapter(inflater,array);
						lv.setAdapter(adapter);
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				})

				.create().show();
				return true;
			}
		});
		/*
		 * ��Ӱ�ť����¼��������½��ʼ�
		 */
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),SecondAtivity.class);
				startActivity(intent);
				MainActivity.this.finish();
			}
		});					
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.item1:
			Intent intent=new Intent(getApplicationContext(),SecondAtivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.item2:
			this.finish();
			break;
		default:
			break;
		}
		return true;

	}


}
