package com.putaolab.dex;

import android.content.Context;
import android.widget.Toast;

public class Iclass implements Iinterface {

	public Context context;
	public Iclass(Context context){
		this.context = context;
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return "hello,i am from the method of getData()...";
	}

}
