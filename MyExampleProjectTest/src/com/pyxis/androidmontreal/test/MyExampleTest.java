package com.pyxis.androidmontreal.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.pyxis.androidmontreal.MyExample;

public class MyExampleTest extends ActivityInstrumentationTestCase2<MyExample>{

	public MyExampleTest() {
		super("com.pyxis.androidmontreal", MyExample.class);
	}
	
	public void testTitle() throws Exception {
		TextView hello = (TextView)getActivity().findViewById(com.pyxis.androidmontreal.R.id.hello);
		assertEquals("Hello World, MeExample!", hello.getText());
	}
	
}
